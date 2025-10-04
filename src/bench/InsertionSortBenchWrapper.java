package bench;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Arrays;

public class InsertionSortBenchWrapper {

    public static int[] generate(int n, String distribution, double nearlyRatio) {
        Random rnd = new Random(123456);
        int[] a = new int[n];
        switch (distribution.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly":
            case "nearly-sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                // swap some small percentage
                int swaps = Math.max(1, (int)(n * nearlyRatio));
                for (int i = 0; i < swaps; i++) {
                    int x = rnd.nextInt(n);
                    int y = rnd.nextInt(n);
                    int t = a[x]; a[x] = a[y]; a[y] = t;
                }
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
                break;
        }
        return a;
    }

    public static void runOnceAndWriteCsv(String algorithm, int n, String distribution, int run,
                                          boolean useBinary, PrintWriter csvWriter) {
        int[] arr = generate(n, distribution, 0.02);
        PerformanceTracker tracker = new PerformanceTracker();
        if ("binary".equalsIgnoreCase(algorithm) || useBinary) {
            InsertionSort.sort(arr, tracker);
        } else {
            InsertionSort.sort(arr, tracker);
        }
        csvWriter.println(tracker.toString());
        csvWriter.flush();
    }
}
