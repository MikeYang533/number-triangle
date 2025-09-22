import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
     * <p>
     * Hint: think recursively and use the idea of partial tracing from first year :)
     * <p>
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
     * <p>
     * You can decide if you want to use a recursive or an iterative approach in your solution.
     * <p>
     * You can assume that:
     * the length of path is less than the height of this NumberTriangle structure.
     * each character in the string is either 'l' or 'r'
     *
     * @param path the path to follow through this NumberTriangle
     * @return the root value at the location indicated by path
     *
     */
    public int retrieve(String path) {
    //TODO//


    /**
     * Read in the NumberTriangle structure from a file.
     * <p>
     * You may assume that it is a valid format with a height of at least 1,
     * so there is at least one line with a number on it to start the file.
     * <p>
     * See resources/input_tree.txt for an example NumberTriangle format.
     *
     * @param fname the file to load the NumberTriangle structure from
     * @return the topmost NumberTriangle object in the NumberTriangle structure read from the specified file
     * @throws IOException may naturally occur if an issue reading the file occurs
     */
    public static NumberTriangle loadTriangle(String fname) throws IOException {
        // open the file and get a BufferedReader object whose methods
        // are more convenient to work with when reading the file contents.
        InputStream inputStream = NumberTriangle.class.getClassLoader().getResourceAsStream(fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        // Initialize the top node
        NumberTriangle top = new NumberTriangle(Integer.parseInt(line));
        List<NumberTriangle> previous = new ArrayList<>();
        previous.add(top);

        // Process remaining lines
        while ((line = br.readLine()) != null) {
            String[] values = line.trim().split(" ");
            List<NumberTriangle> row = new ArrayList<>();

            // Create nodes for current row
            for (String value : values) {
                row.add(new NumberTriangle(Integer.parseInt(value)));
            }
            for (int j = 0; j < previous.size(); j++) {
                previous.get(j).setLeft(row.get(j));
                previous.get(j).setRight(row.get(j + 1));
            }
            previous = row;
        }
        br.close();
        return top;
    }

    public static void main(String[] args) throws IOException {

        NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");
        System.out.println(mt.getRoot());
    }
}
