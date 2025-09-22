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
        NumberTriangle current = this;
        for (char c : path.toCharArray()) {
            if (c == 'l') {
                current = current.left;
            } else if (c == 'r') {
                current = current.right;
            } else {
                throw new IllegalArgumentException("Invalid char: " + c);
            }
        }
        return current.root;//to the top
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
        // open the file and get a BufferedReader object whose methods
        // are more convenient to work with when reading the file contents.
        InputStream inputStream = NumberTriangle.class.getClassLoader().getResourceAsStream(fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        List<List<NumberTriangle>> levels = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] nums = line.trim().split("\\s+");
            List<NumberTriangle> currentLevel = new ArrayList<>();
            for (String num : nums) {
                currentLevel.add(new NumberTriangle(Integer.parseInt(num)));
            }
            levels.add(currentLevel);
        }
        br.close();

        // link children
        for (int i = 0; i < levels.size() - 1; i++) {
            List<NumberTriangle> upper = levels.get(i);
            List<NumberTriangle> lower = levels.get(i + 1);
            for (int j = 0; j < upper.size(); j++) {
                NumberTriangle node = upper.get(j);
                node.setLeft(lower.get(j));
                node.setRight(lower.get(j + 1));
            }
        }

        return levels.get(0).get(0);
    }

    public static void main(String[] args) throws IOException {

        NumberTriangle mt = NumberTriangle.loadTriangle("input_tree.txt");

        System.out.println(mt.retrieve(""));
        System.out.println(mt.retrieve("l"));
        System.out.println(mt.retrieve("r"));
        System.out.println(mt.retrieve("ll"));
        System.out.println(mt.retrieve("lr"));
        System.out.println(mt.retrieve("rl"));
        System.out.println(mt.retrieve("rr"));
    }

}
