import java.util.Random;
/**
 * Code Author: Ariel Myers
 * Purpose: A binary operator for Addition
 */
 public class Plus extends Binop {
    public double eval(double left, double right) { return left + right; }
    public String toString() { return "+"; }
}
