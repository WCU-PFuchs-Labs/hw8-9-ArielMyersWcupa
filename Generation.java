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
