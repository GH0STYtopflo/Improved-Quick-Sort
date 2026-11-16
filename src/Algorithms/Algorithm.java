package Algorithms;

public abstract class Algorithm {
    protected final int[] arr;
    protected static String NAME;

    protected Algorithm(int[] arr) {
        this.arr = arr;
    }

    public abstract void sort();
}
