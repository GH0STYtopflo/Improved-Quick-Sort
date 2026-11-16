package Algorithms;

public class ImprovedQuickSort extends Algorithm{
    public static final String NAME = "Improved Quick Sort";
    private static final int K = 2;

    private int start;
    private int finish;

    public ImprovedQuickSort(int[] arr, int start, int finish) {
        super(arr);

        this.start = start;
        this.finish = finish;
    }

    @Override
    public void sort() {
        quickSort();
        InsertionSort insertionSort = new InsertionSort(arr);

        insertionSort.sort();
    }

    private void quickSort() {
        if (finish - start < K || start < 0 || finish >= arr.length) return; // base case

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
        new NormalQuickSort(arr, start, j).sort();
        new NormalQuickSort(arr, j + 2, finish).sort();
    }
}
