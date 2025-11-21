import java.util.Random;
/*
* Code Author: Ariel Myers
* Purpose: Turning the Arithmetic project from the last homework 
* into the Algebra project, by including a new class named Variable.
*/

public class Variable extends Unop {
    private int index;

    public Variable(int idx) { this.index = idx; }

    public double eval(double[] values) { return values[index]; }

    public String toString() { return "X" + index; }
}
