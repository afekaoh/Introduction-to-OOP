import java.util.Arrays;

/**
 * The class Triangle check.
 *
 * @author Adam Shay Shapira
 * Id 316044809
 * Assigmnet Ass1
 */
public class TriangleCheck {

    /**
     * Main method of the CharCount Class.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int numOfTriangleEdges = 3;
        if (args.length != numOfTriangleEdges) {
            System.out.println("Invalid input");
            return;
        }

        // turning the args into a workable format
        double[] triangleArr = new double[args.length];
        for (int i = 0; i < triangleArr.length; i++) {
            triangleArr[i] = Double.parseDouble(args[i]);
            if (triangleArr[i] == 0) {
                System.out.println("Invalid input");
                return;
            }
        }

        // checking for triangle and right angle
        if (isTriangle(triangleArr)) {
            System.out.println("triangle!");
            // no need to check right angle if its not a triangle
            if (isRightAngle(triangleArr)) {
                System.out.println("right angled!");
            }
        }
    }

    /**
     * Is right angle.
     *
     * @param triangle an array representing 3 edges of a triangle
     * @return if the triangle contains right angle
     */
    public static boolean isRightAngle(final double[] triangle) {
        // checking for right angle with Pythagoras Theorem
        Arrays.sort(triangle);
        return (Math.pow(triangle[0], 2) + Math.pow(triangle[1], 2) == Math.pow(triangle[2], 2));
    }

    /**
     * is Triangle.
     *
     * @param triangle an array representing 3 edges
     * @return true if the the edges represents a triangle and false if not
     */
    public static boolean isTriangle(final double[] triangle) {
        // checking if the edges makes a triangle with the triangle inequality
        Arrays.sort(triangle);
        return triangle[0] + triangle[1] >= triangle[2];
    }
}
