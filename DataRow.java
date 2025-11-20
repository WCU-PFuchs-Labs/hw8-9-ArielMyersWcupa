package tabular;

/**
 * Author: Ariel Myers
 * Purpose: Tabular data and Linear Regression:
 * To read and store a data file
 */


public class DataRow {

    private double y;
    private double[] x; // add fields here

    /**
     * @param y the dependent variable
     * @param x the array of independent variables
     */
    public DataRow(double y, double[] x)
    {
        this.y = y;
        this.x = x; // initialize fields here
    }

    /**
     * @return the dependent variable
     */
    public double getDependentVariable() {
        // FIXME: return the right thing here
        return y;
    }

    /**
     * @return the array of independent variables
     */
    public double[] getIndependentVariables() {
        // FIXME: return the right thing here
        return x;
    }
}
