package algorithms;

import metrics.PerformanceTracker;

import java.util.Objects;

public class InsertionSort {

    public static void sort(int[] a, PerformanceTracker t) {
        Objects.requireNonNull(a, "Array must not be null");
        if (t == null) t = new PerformanceTracker();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            t.addArrayAccesses(1);
            int insertPos = binarySearchInsertPos(a, key, 0, i - 1, t);
            int numToMove = i - insertPos;
            if (numToMove > 0) {
                System.arraycopy(a, insertPos, a, insertPos + 1, numToMove);
                t.addArrayAccesses(2L * numToMove);
                t.addShifts(numToMove);
                a[insertPos] = key;
                t.addArrayAccesses(1);
            }
        }
    }

    private static int binarySearchInsertPos(int[] a, int key, int lo, int hi, PerformanceTracker t) {
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            t.addArrayAccesses(1);
            t.incComparisons();
            if (a[mid] <= key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}
