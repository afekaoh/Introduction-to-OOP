/**
 * The class Average primes.
 *
 * @author Adam Shay Shapira adam.shapira@live.biu.ac.il
 * Id 316044809
 * Assigmnet Ass1
 */
public class AveragePrimes {

    /**
     * Main method of the CharCount Class.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        int num = Integer.parseInt(args[0]);
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
        double averagePrimes = (double) sumOfPrimes / countPrimes;
        System.out.println(averagePrimes);
    }

    /**
     * Is prime boolean.
     *
     * @param num the number we check if it's a prime number
     * @return if the number is a prime
     */
    public static boolean isPrime(final int num) {
        for (double i = 2; i <= Math.sqrt(num); i++) {
            double primeCheck = num / i;
            if (primeCheck % 1 == 0) {
                return false;
            }
        }
        return true;
    }
}
