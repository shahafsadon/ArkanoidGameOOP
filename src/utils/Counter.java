package utils;

/**
 * A simple utility class to keep track of a count (e.g., remaining blocks).
 */
public class Counter {
    private int count;

    /**
     * Constructs a new Counter starting at 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the current count by a given number.
     *
     * @param number the number to add.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the current count by a given number.
     *
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the count.
     */
    public int getValue() {
        return this.count;
    }
}
