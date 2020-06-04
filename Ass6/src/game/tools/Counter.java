// ID 316044809
package game.tools;


/**
 * The class Counter.
 */
public class Counter {
    /**
     * The Counter.
     */
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this(0);
    }

    /**
     * Instantiates a new Counter.
     *
     * @param startValue the start value
     */
    public Counter(final int startValue) {
        this.counter = startValue;
    }

    /**
     * Increase.
     * <p>
     * add 1 to current count.
     */
    public void increase() {
        increase(1);
    }

    /**
     * Increase.
     * <p>
     * add number to current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Decrease.
     * <p>
     * subtract 1 from current count.
     */
    public void decrease() {
        decrease(1);
    }

    /**
     * Decrease.
     * <p>
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Get value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return counter;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}

