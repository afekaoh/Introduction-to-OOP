// ID 316044809
package game.tools;


public class Counter {
    private int counter;

    public Counter(final int startValue) {
        this.counter = startValue;
    }

    // add number to current count.
    public void increase(int number) {
        counter += number;
    }

    // subtract number from current count.
    public void decrease(int number) {
        counter -= number;
    }

    // get current count.
    public int getValue() {
        return counter;
    }
}

