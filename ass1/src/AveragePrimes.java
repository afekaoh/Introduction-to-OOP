//ID 316044809t


/**
 * The class AveragePrimes.
 * Assigment Ass1.
 *
 * @author Adam Shay Shapira.
 * @author adam.shspira@live.biu.ac.il
 */
public class AveragePrimes {

    /**
     * Main method of the AveragePrimes Class.
     * gets a number as an argument and prints
     * the average of all the primes less then or equal to him
     *
     * @param args the input arguments should contain a single natural number
     */
    public static void main(String[] args) {
        final int num = Integer.parseInt(args[0]);

        // validity check
        if (num <= 1) {
            System.out.println("Invalid value");
            return;
        }

        int countPrimes = 0;
        int sumOfPrimes = 0;
        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                countPrimes++;
                sumOfPrimes += i;
            }
        }
        final double averagePrime = (double) sumOfPrimes / countPrimes;
        System.out.println(averagePrime);
    }

    /**
     * Is prime boolean.
     *
     * @param num the number we check if it's a prime number
     * @return if the number is a prime
     */
    public static boolean isPrime(final int num) {
        double primeCheck;
        for (double i = 2; i <= Math.sqrt(num); i++) {
            primeCheck = num / i;
            if (primeCheck % 1 == 0) {
                return false;
            }
        }
        return true;
    }
}
