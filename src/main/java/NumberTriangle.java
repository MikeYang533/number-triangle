import java.io.*;

/**
 * This is the provided NumberTriangle class to be used in this coding task.
 *
 * Note: This is like a tree, but some nodes in the structure have two parents.
 *
 * The structure is shown below. Observe that the parents of e are b and c, whereas
 * d and f each only have one parent. Each row is complete and will never be missing
 * a node. So each row has one more NumberTriangle object than the row above it.
 *
 *                  a
 *                b   c
 *              d   e   f
 *            h   i   j   k
 *
 * Also note that this data structure is minimally defined and is only intended to
 * be constructed using the loadTriangle method, which you will implement
 * in this file. We have not included any code to enforce the structure noted above,
 * and you don't have to write any either.
 *
 *
 * See NumberTriangleTest.java for a few basic test cases.
 *
 * Extra: If you decide to solve the Project Euler problems (see main),
 *        feel free to add extra methods to this class. Just make sure that your
 *        code still compiles and runs so that we can run the tests on your code.
 *
 */
public class NumberTriangle {

    private int root;

    private NumberTriangle left;
    private NumberTriangle right;

    public NumberTriangle(int root) {
        this.root = root;
    }

    public void setLeft(NumberTriangle left) {
        this.left = left;
    }


    public void setRight(NumberTriangle right) {
        this.right = right;
    }

    public int getRoot() {
        return root;
    }


    /**
     * [not for credit]
     * Set the root of this NumberTriangle to be the max path sum
     * of this NumberTriangle, as defined in Project Euler problem 18.
     * After this method is called, this NumberTriangle should be a leaf.
     *
     * Hint: think recursively and use the idea of partial tracing from first year :)
     *
     * Note: a NumberTriangle contains at least one value.
     */
    public void maxSumPath() {
        // for fun [not for credit]:
    }


    public boolean isLeaf() {
        return right == null && left == null;
    }


    /**
     * Follow path through this NumberTriangle structure ('l' = left; 'r' = right) and
     * return the root value at the end of the path. An empty string will return
     * the root of the NumberTriangle.
     *
     * You can decide if you want to use a recursive or an iterative approach in your solution.
     *
     * You can assume that:
     *      the length of path is less than the height of this NumberTriangle structure.
     *      each character in the string is either 'l' or 'r'
     *
     * @param path the path to follow through this NumberTriangle
     * @return the root value at the location indicated by path
     *
     */
    public int retrieve(String path) {
        NumberTriangle curr = this;
        for (int i = 0; i < path.length() && curr != null; i++) {
            char ch = path.charAt(i);
            if (ch == 'l')      curr = curr.left;
            else if (ch == 'r') curr = curr.right;
            else                ; // ignore anything else (no exceptions)
        }
        return (curr == null) ? -1 : curr.getRoot();
    }


    /** Read in the NumberTriangle structure from a file.
     *
     * You may assume that it is a valid format with a height of at least 1,
     * so there is at least one line with a number on it to start the file.
     *
     * See resources/input_tree.txt for an example NumberTriangle format.
     *
     * @param fname the file to load the NumberTriangle structure from
     * @return the topmost NumberTriangle object in the NumberTriangle structure read from the specified file
     * @throws IOException may naturally occur if an issue reading the file occurs
     */
    public static NumberTriangle loadTriangle(String fname) throws IOException {
        InputStream in = NumberTriangle.class.getClassLoader().getResourceAsStream(fname);
        if (in == null) throw new FileNotFoundException("Resource not found on classpath: " + fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        java.util.List<java.util.List<Integer>> rows = new java.util.ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] toks = line.split("\\s+");
            java.util.List<Integer> r = new java.util.ArrayList<>(toks.length);
            for (String t : toks) r.add(Integer.parseInt(t));
            rows.add(r);
        }
        br.close();

        int last = rows.size() - 1;
        java.util.List<NumberTriangle> below = new java.util.ArrayList<>(rows.get(last).size());
        for (int v : rows.get(last)) below.add(new NumberTriangle(v));

        for (int r = last - 1; r >= 0; r--) {
            java.util.List<Integer> vals = rows.get(r);
            java.util.List<NumberTriangle> curr = new java.util.ArrayList<>(vals.size());
            for (int c = 0; c < vals.size(); c++) {
                NumberTriangle node = new NumberTriangle(vals.get(c));
                node.setLeft(below.get(c));       // below-left
                node.setRight(below.get(c + 1));  // below-right
                curr.add(node);
            }
            below = curr;
        }
        return below.get(0);
    }


    public static void main(String[] args) throws IOException {

        NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");

        // [not for credit]
        // you can implement NumberTriangle's maxPathSum method if you want to try to solve
        // Problem 18 from project Euler [not for credit]
        mt.maxSumPath();
        System.out.println(mt.getRoot());
    }
}