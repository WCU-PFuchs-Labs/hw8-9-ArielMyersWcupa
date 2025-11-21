import java.util.Random;
/**
 * Code Author: Ariel Myers
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node implements Cloneable {
    private Node left, right;
    private Op operation;
    protected int depth = 0;

    //Constructors//
    public Node(Binop op) {
        this.operation = op;
    }

    public Node(Unop op) {
        this.operation = op;
    }

    public Op getOp() {
        return operation;
    }
    //RandomKids algorithm//
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        if (operation instanceof Unop) {
            return; 
        }

        if (depth >= maxDepth) {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;

            right = nf.getTerminal(rand);
            right.depth = depth + 1;
            return;
        }

        int total = nf.getNumOps() + nf.getNumIndepVars();
        int r = rand.nextInt(total + 1);

        left = (r < nf.getNumOps()) ? nf.getOperator(rand) : nf.getTerminal(rand);
        left.depth = depth + 1;
        left.addRandomKids(nf, maxDepth, rand);

        r = rand.nextInt(total + 1);
        right = (r < nf.getNumOps()) ? nf.getOperator(rand) : nf.getTerminal(rand);
        right.depth = depth + 1;
        right.addRandomKids(nf, maxDepth, rand);
    }


    public double eval(double[] values) {
        if (operation instanceof Unop) {
            return ((Unop) operation).eval(values);
        }
        double leftVal = left.eval(values);
        double rightVal = right.eval(values);
        return ((Binop) operation).eval(leftVal, rightVal);
    }

        public String toString() {
            if (operation instanceof Unop) {
                return operation.toString();
            }

            String leftStr = left.toString();
            String rightStr = right.toString();
            return leftStr + " " + operation.toString() + " " + rightStr;
        }

    //Object clone//
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported in Node.");
        }
        Node b = (Node) o;

        if (left != null) {
            b.left = (Node) left.clone();
        }
        if (right != null) {
            b.right = (Node) right.clone();
        }
        if (operation != null) {
            b.operation = (Op) operation.clone();
        }
        return b;
    }
    
    public void traverse(Collector c) {
    c.collect(this);
    if (operation instanceof Unop) {
        if (left != null) {
            left.traverse(c);
        }
        return;
    }
    if (left != null) {
        left.traverse(c);
    }
    if (right != null) {
        right.traverse(c);
    }
}
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public void swapLeft(Node trunk) {
        if (trunk == null) return;
        Node temp = this.left;
        this.left = trunk.left;
        trunk.left = temp;
    }

    public void swapRight(Node trunk) {
        if (trunk == null) return;
        Node temp = this.right;
        this.right = trunk.right;
        trunk.right = temp;
    }
}
