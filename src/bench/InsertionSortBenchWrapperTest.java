package bench;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertionSortBenchWrapperTest {

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
                int swaps = Math.max(1, (int) (n * nearlyRatio));
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

    @Test
    public void testInsertionSortPerformance() {
        int n = 100;
        String distribution = "random";
        boolean useBinary = false;

        int[] arr = generate(n, distribution, 0.02);
        PerformanceTracker tracker = new PerformanceTracker();

        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        InsertionSort.sort(arr, tracker);

        assertTrue(Arrays.equals(arr, expected), "Array should be sorted correctly");

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        writer.println(tracker.toString());
        writer.flush();

        System.out.println("CSV Output: " + sw);
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Array Accesses: " + tracker.getArrayAccesses());
    }
}
