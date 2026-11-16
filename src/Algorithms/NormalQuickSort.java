package Algorithms;

import java.util.Arrays;

public class NormalQuickSort extends Algorithm {
    public static final String NAME = "Normal Quick Sort";

    private int start;
    private int finish;

    public NormalQuickSort(int[] arr, int start, int finish) {
        super(arr);

        this.start = start;
        this.finish = finish;
    }

    @Override
    public void sort() {
        if (!(start < finish) || start < 0 || finish >= arr.length) return; // base case

        int pivot = arr[finish];

        int i;
        int j = start - 1;
        int temp;

        for (i = start; i < finish; i++) {
            if (arr[i] < pivot) {
                j++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[finish] = arr[j + 1];
        arr[j + 1] = pivot;

        // recursion starts here
        new NormalQuickSort(arr, start, j).sort(); // sub array to the left
        new NormalQuickSort(arr, j + 2, finish).sort(); // sub array to the right

    }
}
