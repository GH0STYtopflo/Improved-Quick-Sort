package Algorithms;

public class InsertionSort extends Algorithm {
    public static final String NAME = "Insertion Sort";

    public InsertionSort(int[] arr) {
        super(arr);
    }


    public void sort() {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];

            int j = i - 1;
            while (j > -1 && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }

            arr[j + 1] = key;
        }
    }
}
