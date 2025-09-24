import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the provided NumberTriangle class to be used in this coding task.
 * <p>
 * Note: This is like a tree, but some nodes in the structure have two parents.
 * <p>
 * The structure is shown below. Observe that the parents of e are b and c, whereas
 * d and f each only have one parent. Each row is complete and will never be missing
 * a node. So each row has one more NumberTriangle object than the row above it.
 * <p>
 * a
 * b   c
 * d   e   f
 * h   i   j   k
 * <p>
 * Also note that this data structure is minimally defined and is only intended to
 * be constructed using the loadTriangle method, which you will implement
 * in this file. We have not included any code to enforce the structure noted above,
 * and you don't have to write any either.
 * <p>
 * <p>
 * See NumberTriangleTest.java for a few basic test cases.
 * <p>
 * Extra: If you decide to solve the Project Euler problems (see main),
 * feel free to add extra methods to this class. Just make sure that your
 * code still compiles and runs so that we can run the tests on your code.
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
        // TODO implement this method

        if (this.isLeaf() | path.length() == 0) {
            return this.root;
        } else if (path.charAt(0) == 'l' && this.left != null) {

            return this.left.retrieve(path.substring(1));

        } else if (path.charAt(0) == 'r' && this.right != null) {
            return this.right.retrieve(path.substring(1));
        }
        return -1;
    }

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
        NumberTriangle top = null;
        List<NumberTriangle> pre_triangles = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            List<Integer> numbers = new ArrayList<>();
            List<NumberTriangle> cur_triangles = new ArrayList<>();
            for (String s : line.split("\\s+")) {
                numbers.add(Integer.parseInt(s));
            }
            if (top == null) {
                top = new NumberTriangle(numbers.get(0));
                pre_triangles.add(top);
            } else {
                for (int i = 0; i < numbers.size(); i++) {
                    cur_triangles.add(new NumberTriangle(numbers.get(i)));
                    if (i == 0) {
                        pre_triangles.get(0).setLeft(cur_triangles.get(0));
                    } else if (i == numbers.size() - 1) {
                        pre_triangles.get(i - 1).setRight(cur_triangles.get(i));
                    } else {
                        pre_triangles.get(i).setLeft(cur_triangles.get(i));
                        pre_triangles.get(i - 1).setRight(cur_triangles.get(i));
                    }
                }
                pre_triangles = cur_triangles;
            }
            System.out.println(numbers);
            line = br.readLine();
        }
        br.close();
        return top;
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
