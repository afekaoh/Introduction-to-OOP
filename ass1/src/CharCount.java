//ID 316044809

/**
 * The class CharCount.
 * Assigment Ass1.
 *
 * @author Adam Shay Shapira.
 * @author adam.shspira@live.biu.ac.il
 */
public class CharCount {


    /**
     * Main method of the CharCount Class.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // validity check
        final int lastArgsIndex = args.length - 1;
        if (args.length < 2 || args[lastArgsIndex].length() != 1) {
            System.out.println("Invalid input");
            return;
        }

        // sorting the words base on the char
        String[] toPrint = new String[lastArgsIndex];
        int printFirstIndex = 0;
        int printLastIndex = lastArgsIndex - 1;
        final String checkChar = args[lastArgsIndex];
        for (int i = 0; i < lastArgsIndex; i++) {
            if (args[i].contains(checkChar)) {
                // all of the words that contain the char
                toPrint[printFirstIndex++] = args[i];
            } else {
                // all the words that dont
                toPrint[printLastIndex--] = args[i];
            }
        }

        // printing the words that containing the chars
        for (int i = 0; i < printFirstIndex; i++) {
            System.out.println(toPrint[i]);
        }

        // printing the rest of the words
        for (int i = lastArgsIndex - 1; i > printLastIndex; i--) {
            System.out.println(toPrint[i]);
        }
    }
}