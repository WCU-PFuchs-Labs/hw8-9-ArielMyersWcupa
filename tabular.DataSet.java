package tabular;
/**
 * Author: Ariel Myers
 * Purpose: Tabular data and Linear Regression:
 * To read and store a data file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {

    private ArrayList<DataRow> data;
    private int numIndepVariables; // add fields here

    /**
     * @param filename the name of the file to read the data set from
     */
    public DataSet(String filename)
    {
      data = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(filename));

            if (sc.hasNextLine()) {
                String header = sc.nextLine();
                numIndepVariables = header.split(",").length - 1;
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                String[] parts = line.split(",");

                if (parts.length >= numIndepVariables + 1) {
                    double y = Double.parseDouble(parts[0].trim());
                    double[] x = new double[numIndepVariables];

                    for (int i = 0; i < numIndepVariables; i++) {
                        x[i] = Double.parseDouble(parts[i + 1].trim());
                    }

                    data.add(new DataRow(y, x));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
   // initialize fields here
    /**
     * @return the list of rows
     */
    public ArrayList<DataRow> getRows() {
        // FIXME: return the right thing here
        return data;
    }

    /**
     * @return the number of independent variables in each row of the data set
     */
    public int getNumIndependentVariables() {
        // FIXME: return the right thing here
        return numIndepVariables;
    }
}
