package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;
import metrics.PerformanceTracker;

public class InsertionSortTest {

    @Test
    void testEmptyArray() {
        int[] a = new int[0];
        InsertionSort.sort(a, null);
        assertArrayEquals(new int[0], a);
    }

    @Test
    void testSingleElement() {
        int[] a = new int[]{42};
        InsertionSort.sort(a, null);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void testAllEqual() {
        int[] a = new int[50];
        Arrays.fill(a, 7);
        InsertionSort.sort(a, null);
        for (int v : a) assertEquals(7, v);
    }

    @Test
    void testSortedInput() {
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) a[i] = i;
        InsertionSort.sort(a, null);
        for (int i = 0; i < a.length; i++) assertEquals(i, a[i]);
    }

    @Test
    void testRandomPropertyBased() {
        Random rnd = new Random(123);
        for (int t = 0; t < 50; t++) {
            int n = 200;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt(1000);
            int[] copy = Arrays.copyOf(a, a.length);
            InsertionSort.sort(a, null);
            Arrays.sort(copy);
            assertArrayEquals(copy, a);
        }
    }

    @Test
    void testPerformanceTrackerIntegration() {
        int[] a = {3,2,1,5,4};
        PerformanceTracker t = new PerformanceTracker();
        InsertionSort.sort(a, t);
        assertTrue(t.getComparisons() >= 0);
        assertTrue(t.getShifts() >= 0);
    }
}
