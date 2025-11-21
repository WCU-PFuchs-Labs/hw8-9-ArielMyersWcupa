/*
* Code Author: Ariel Myers
*/

import java.util.*;
import tabular.DataSet;
import tabular.DataRow;

public class GPTree implements Comparable<GPTree>, Cloneable, Collector {

    private Node root;
    private ArrayList<Node> crossNodes;
    private boolean traversed;


    private double fitness;

    GPTree() {
        this.root = null;
        this.crossNodes = new ArrayList<>();
        this.traversed = false;
        this.fitness = Double.POSITIVE_INFINITY;
    }

  
    public GPTree(NodeFactory nf, int maxDepth, Random rand) {
        this.root = nf.getOperator(rand);
        this.root.addRandomKids(nf, maxDepth, rand);
        this.crossNodes = new ArrayList<>();
        this.traversed = false;
        this.fitness = Double.POSITIVE_INFINITY;
    }


    @Override
    public void collect(Node node) {
        if (node != null && node.getOp() instanceof Binop) {
            crossNodes.add(node);
        }
    }

    public void traverse() {
        if (root == null) {
            crossNodes.clear();
            traversed = true;
            return;
        }
        crossNodes.clear();
        root.traverse(this);  
        traversed = true;
    }

    public String getCrossNodes() {
        if (!traversed) return "";
        StringJoiner sj = new StringJoiner(";");
        for (Node n : crossNodes) sj.add(n.toString());
        return sj.toString();
    }

    public void crossover(GPTree other, Random rand) {
        this.traverse();
        other.traverse();

        if (this.crossNodes.isEmpty() || other.crossNodes.isEmpty()) return;

        Node n1 = this.crossNodes.get(rand.nextInt(this.crossNodes.size()));
        Node n2 = other.crossNodes.get(rand.nextInt(other.crossNodes.size()));

        if (rand.nextBoolean()) {
            n1.swapLeft(n2);
        } else {
            n1.swapRight(n2);
        }
    }

    public String toString() {
        return (root == null) ? "" : root.toString();
    }

    public double eval(double[] data) {
        return root.eval(data);
    }
 
    public void evalFitness(DataSet dataSet) {
        double sum = 0.0;

        for (int i = 0; i < dataSet.getNumRows(); i++) {
            DataRow row = dataSet.getRow(i);
            
            double[] x = row.getIndependentVariables();
            double y = row.getDependentVariable();

            double predicted = eval(x);
            double diff = predicted - y;
            sum += diff * diff;
        }

        this.fitness = sum;
    }

    public double getFitness() {
        return fitness;
    }

    public int compareTo(GPTree other) {
        return Double.compare(this.fitness, other.fitness);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof GPTree)) return false;
        GPTree other = (GPTree) o;
        return this.compareTo(other) == 0;
    }

    @Override
    public Object clone() {
        try {
            GPTree copy = (GPTree) super.clone();

            if (this.root != null) {
                copy.root = (Node) this.root.clone(); 
            }

            copy.crossNodes = new ArrayList<>();
            copy.traversed = false;
            copy.fitness = this.fitness;

            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
