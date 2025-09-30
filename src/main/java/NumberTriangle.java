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
        // TODO implement this method
        return 0;
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


        // TODO define any variables that you want to use to store things
        // will need to return the top of the NumberTriangle,
        // so might want a variable for that.
        NumberTriangle top = null;

        String line = br.readLine();

        while (line != null) {

            // remove when done; this line is included so running starter code prints the contents of the file
            System.out.println(line);
            String[] lst = line.split(" ");

            // TODO process the line
            if(lst.length == 1) {
                top = new NumberTriangle((Integer.parseInt(lst[0])));
                top.setLeft(null);
                top.setRight(null);
            } else if(lst.length == 2) {
                top.left = new NumberTriangle(Integer.parseInt(lst[0]));
                top.right = new NumberTriangle(Integer.parseInt(lst[1]));
                top.left.setLeft(null);
                top.left.setRight(null);
                top.right.setLeft(null);
                top.right.setRight(null);
            } else {
                for(int i = 0; i < lst.length; i++) {
                    if(i == 0){
                        findparent(lcreator(i, lst.length - 1), top).left = new NumberTriangle(Integer.parseInt(lst[i]));
                    } else if (i == lst.length - 1) {
                        findparent(rcreator(i, lst.length - 1), top).right = new NumberTriangle(Integer.parseInt(lst[i]));
                    } else{
                        findparent(lcreator(i, lst.length - 1), top).right = new NumberTriangle(Integer.parseInt(lst[i]));
                        findparent(rcreator(i, lst.length - 1), top).left = findparent(lcreator(i, lst.length - 1), top).right;
                    }

                }
            }

            //read the next line
            line = br.readLine();
        }
        br.close();
        return top;
    }

    private static String lcreator(int num, int len){
        StringBuilder s =  new StringBuilder();
        if(num == 0){
            for(int i = 1; i < num + 1; i++){
                s.append('r');
            }
            for(int j = 1; j < len - num - 1; j++){
                s.append('l');
            }
            s.append('l');
            return s.toString();
        }
        for(int i = 1; i < num; i++){
            s.append('r');
        }
        for(int j = 1; j < len - num; j++){
            s.append('l');
        }
        s.append('l');
        return s.toString();
    }

    private static String rcreator(int num, int len){
        StringBuilder s =  new StringBuilder();
        if (num == len){
            for(int i = 1; i < num - 1; i++){
                s.append('r');
            }
            for(int j = 1; j < len - num + 1; j++){
                s.append('l');
            }
            s.append('r');
            return s.toString();
        }
        for(int i = 1; i < num; i++){
            s.append('r');
        }
        for(int j = 1; j < len - num; j++){
            s.append('l');
        }
        s.append('r');
        return s.toString();
    }

    private static NumberTriangle findparent(String path, NumberTriangle top) {
        NumberTriangle n1 = top;
        for(int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'l') {
                n1 = n1.left;
            }  else {
                n1 = n1.right;
            }
        }
        return n1;
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
