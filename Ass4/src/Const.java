// ID 316044809

/**
 * The class Const.
 */
public class Const {
    public static final Var E = new Var("e");
    public static final Num ZERO = new Num(0);
    public static final Num ONE = new Num(1);
    public static final double EPSILON = 10e-12;

    /**
     * Double equals boolean.
     *
     * @param num1 the num 1
     * @param num2 the num 2
     * @return the boolean
     */
    public static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) <= EPSILON;
    }
}
