/**
 * The class Char count.
 *
 * @author Adam Shay Shapira
 * Id 316044809
 * Assigmnet Ass1
 */
public class CharCount {


    /**
     * Main method of the CharCount Class.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // validity check
        if (args.length < 2 || args[args.length - 1].length() != 1) {
            System.out.println("Invalid input");
        }

        char toSearch = args[args.length - 1].charAt(0);
        boolean[] toPrintFirst = new boolean[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) {
            toPrintFirst[i] = isCharIn(args[i], toSearch);
            if (toPrintFirst[i]) {
                System.out.println(args[i]);
            }
        }

        for (int i = 0; i < args.length - 1; i++) {
            if (!toPrintFirst[i]) {
                System.out.println(args[i]);
            }
        }
    }

    /**
     * Is char in.
     *
     * @param word a string to check if it contains the char.
     * @param toSearch the char we check if it contained in the string.
     * @return true if the char is in the string and false if not.
     */
    public static boolean isCharIn(final String word, final char toSearch) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == toSearch) {
                return true;
            }
        }
        return false;
    }
}
