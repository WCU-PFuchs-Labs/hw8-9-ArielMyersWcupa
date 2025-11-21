import java.util.*;
import tabular.DataSet;
/*
* Code Author: Ariel Myers
*/

public class Generation {
    private GPTree[] trees;
    private DataSet dataSet;
    private NodeFactory factory;
    private Random rand;
    private int size;
    private int maxDepth;

    public Generation(int size, int maxDepth, String fileName) {
        this.size = size;
        this.maxDepth = maxDepth;

        dataSet = new DataSet(fileName);
        rand = new Random();
        factory = new NodeFactory(dataSet.getNumIndependentVariables());

        //Creating the array of GPTrees//
        trees = new GPTree[size];

        // Fill with random trees//
        for (int i = 0; i < size; i++) {
            trees[i] = new GPTree(factory, maxDepth, rand);
        }
    }
    
  //Evaluating fitness for all trees and sort them by fitness (best = index 0).//

    public void evalAll() {
        for (int i = 0; i < trees.length; i++) {
            trees[i].evalFitness(dataSet);
        }
        Arrays.sort(trees); 
    }

    //An ArrayList of the top 10 GPTrees (lowest fitness).//
    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> top = new ArrayList<>();
        int limit = Math.min(10, trees.length);
        for (int i = 0; i < limit; i++) {
            top.add(trees[i]);
        }
        return top;
    }

     //To print the best fitness value (only valid after evalAll()).// 
    public void printBestFitness() {
        if (trees.length > 0) {
            System.out.println(trees[0].getFitness());
        }
    }

      //To Print the expression of the best tree//  
    public void printBestTree() {
        if (trees.length > 0) {
            System.out.println(trees[0].toString());
        }
    }

    public void evolve() {
        GPTree[] children = new GPTree[trees.length];

        int selectionPool = trees.length / 2;
        if (selectionPool == 0) {
            selectionPool = trees.length;
        }

        for (int i = 0; i < trees.length; i += 2) {
            //To choose two parents from [0, selectionPool)//
            GPTree parent1 = trees[rand.nextInt(selectionPool)];
            GPTree parent2 = trees[rand.nextInt(selectionPool)];

            //To clone parents//
            GPTree child1 = (GPTree) parent1.clone();
            GPTree child2 = (GPTree) parent2.clone();

            //crossover the children within place//
            child1.crossover(child2, rand);

            children[i] = child1;
            if (i + 1 < trees.length) {
                children[i + 1] = child2;
            }
        }

        //Replacing the current population with children//
        trees = children;
    }
}
