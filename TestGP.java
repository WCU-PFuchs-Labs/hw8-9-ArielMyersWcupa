import java.util.*;

public class TestGP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the data file name: ");
        String fileName = in.nextLine();

        int size = 500;
        int maxDepth = 3; 

        Generation gen = new Generation(size, maxDepth, fileName);

        //Run 50 generations//
        for (int g = 1; g <= 50; g++) {
            gen.evalAll();  //Compute the fitness and sort//

            System.out.println("Generation " + g + ":");
            gen.printBestTree();
            System.out.print("Best Fitness: ");
            gen.printBestFitness();

            //Prepare for the next generation//
            gen.evolve();
        }

        in.close();
    }
}
