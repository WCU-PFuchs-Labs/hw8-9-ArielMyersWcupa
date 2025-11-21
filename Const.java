import java.util.Random;
/**
 * Code Author: Ariel Myers
 * Purpose: An operation class representing a constant number
 */
public class Const extends Unop {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public double eval(double[] values) {
        return value;
    }

    public String toString() { return String.format("%.2f", value); }
    
 }
