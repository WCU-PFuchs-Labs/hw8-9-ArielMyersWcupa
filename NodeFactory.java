import java.util.Random;
/*
* Code Author: Ariel Myers
*/
public class NodeFactory {
    private int numIndepVars;
    private Binop[] currentOps;
    
    public NodeFactory(Binop[] b, int numVars) {
        numIndepVars = numVars;
        currentOps = b;
    }

    public Node getOperator(Random rand) {
        Binop op = (Binop) currentOps[rand.nextInt(currentOps.length)].clone();
        return new Node(op);
    }

    public Node getTerminal(Random rand) {
        int r = rand.nextInt(numIndepVars + 1);
        if (r < numIndepVars) return new Node(new Variable(r));
        return new Node(new Const(rand.nextDouble()));
    }

    public int getNumOps() { return currentOps.length; }
    public int getNumIndepVars() { return numIndepVars; }
}
