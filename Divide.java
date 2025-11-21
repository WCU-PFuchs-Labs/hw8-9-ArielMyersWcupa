import java.util.Random;
/*
* Code Author: Ariel Myers
* Purpose: A binary operator for Division
*/
public class Divide extends Binop {
    
    public double eval(double a, double b) {
        if (Math.abs(b) < 0.0001) {
            return 1.0; 
        }
        return a / b;
    }
    public String toString() { 
        return "/";
    }
}
