import java.util.*;

public class TestGeneration {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the data file name: ");
        String fileName = in.nextLine();

        int size = 500;
        int maxDepth = 3; 

        Generation gen = new Generation(size, maxDepth, fileName);

        gen.evalAll();

        System.out.println("Best Tree:");
        gen.printBestTree();

        System.out.print("Best Fitness: ");
        gen.printBestFitness();

       
        ArrayList<GPTree> topTen = gen.getTopTen();

        System.out.print("Top Ten Fitness Values: ");
        for (int i = 0; i < topTen.size(); i++) {
            double fit = topTen.get(i).getFitness();
            System.out.print(String.format("%.2f", fit));
            if (i < topTen.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        in.close();
    }
}
