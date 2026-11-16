package Random;

import java.util.Random;

public class VarLengthRandomArray {
    public static int[] generate(int len, int[] limits) {
        int[] arr = new int[len];

        fill(arr, limits, new Random());

        return arr;
    }

    private static void fill(int[] arr, int[] limits, Random rand) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(limits[0], limits[1]);
        }
    }
}
