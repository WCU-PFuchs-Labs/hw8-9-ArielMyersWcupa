import java.util.*;
/*
* Code Author: Ariel Myers
*/

public class GPTree implements Collector {
    private Node root;
    private ArrayList<Node> crossNodes;
    private boolean traversed; 

    
    GPTree() {
        this.root = null;
        this.crossNodes = new ArrayList<>();
        this.traversed = false;
    }

    
    public GPTree(NodeFactory nf, int maxDepth, Random rand) {
        this.root = nf.getOperator(rand);
        this.root.addRandomKids(nf, maxDepth, rand);
        this.crossNodes = new ArrayList<>();
        this.traversed = false;
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

        if (rand.nextBoolean()) n1.swapLeft(n2);
        else n1.swapRight(n2);
    }

    
    public String toString() {
        return (root == null) ? "" : root.toString();
    }

    public double eval(double[] data) {
        return root.eval(data);
    }
}
