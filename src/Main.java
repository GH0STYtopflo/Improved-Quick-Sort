
import Algorithms.Algorithm;
import Algorithms.ImprovedQuickSort;
import Algorithms.InsertionSort;
import Algorithms.NormalQuickSort;
import Random.VarLengthRandomArray;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // ANSI colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";

    static void main(String[] args) {

        int[] original = buildArray();

        // Array information
        printArrayProperties(original);

        System.out.println();
        System.out.println(CYAN + BOLD +
                "================ ALGORITHM BENCHMARK ================" +
                RESET);

        System.out.printf(
                CYAN + "%-24s | %15s | %-10s%n" + RESET,
                "ALGORITHM",
                "RUNTIME (ms)",
                "SORTED"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        // Each algorithm receives an independent copy.
        int[] insertionArray = original.clone();
        int[] quickSortArray = original.clone();
        int[] improvedQuickSortArray = original.clone();

        Algorithm[] algorithms = {
                new InsertionSort(insertionArray),

                new NormalQuickSort(
                        quickSortArray,
                        0,
                        quickSortArray.length - 1
                ),

                new ImprovedQuickSort(
                        improvedQuickSortArray,
                        0,
                        improvedQuickSortArray.length - 1
                        ),
        };

        String[] names = {
                InsertionSort.NAME,
                NormalQuickSort.NAME,
                ImprovedQuickSort.NAME
        };

        int[][] results = {
                insertionArray,
                quickSortArray,
                improvedQuickSortArray
        };

        for (int i = 0; i < algorithms.length; i++) {
            runTest(names[i], algorithms[i], results[i]);
        }

        System.out.println(
                CYAN + "============================================================" +
                        RESET
        );
    }

    private static int[] buildArray() {

        Scanner scn = new Scanner(System.in);

        System.out.print("Array size: ");
        int length = scn.nextInt();

        scn.nextLine(); // Consume leftover newline

        System.out.print("Element limits (origin,bound): ");

        String[] split = scn.nextLine().split(",");

        int[] limits = new int[2];

        for (int i = 0; i < 2; i++) {
            limits[i] = Integer.parseInt(split[i].trim());
        }

        return VarLengthRandomArray.generate(length, limits);
    }

    private static void printArrayProperties(int[] arr) {

        System.out.println();
        System.out.println(PURPLE + BOLD +
                "                  ARRAY PROPERTIES" + RESET);

        System.out.println(PURPLE +
                "------------------------------------------------------------" +
                RESET);

        System.out.printf("Length       : %s%d%s%n",
                YELLOW, arr.length, RESET);

        if (arr.length > 0) {
            int min = arr[0];
            int max = arr[0];

            for (int value : arr) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

            System.out.printf("Minimum      : %s%d%s%n",
                    GREEN, min, RESET);

            System.out.printf("Maximum      : %s%d%s%n",
                    GREEN, max, RESET);

            System.out.printf("Value range  : %s%d%s%n",
                    YELLOW, max - min, RESET);
        }

        System.out.print("Array        : ");

        // Avoid printing excessively large arrays.
        int previewLength = Math.min(arr.length, 20);

        System.out.println(
                Arrays.toString(Arrays.copyOf(arr, previewLength)) +
                        (arr.length > 20 ? " ..." : "")
        );

        System.out.println(PURPLE +
                "------------------------------------------------------------" +
                RESET);
    }

    private static void runTest(
            String name,
            Algorithm algorithm,
            int[] result
    ) {

        long start = System.nanoTime();

        algorithm.sort();

        long end = System.nanoTime();

        long elapsed = end - start;

        double milliseconds = elapsed / 1_000_000.0;

        boolean sorted = isSorted(result);

        System.out.printf(
                "%-24s | %15.6f | %s%n",
                name,
                milliseconds,
                sorted
                        ? GREEN + "YES" + RESET
                        : "\u001B[31mNO" + RESET
        );
    }

    private static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }

        return true;
    }
}
