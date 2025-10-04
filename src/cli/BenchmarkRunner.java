package cli;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 1000, 10000, 100000};
        int warmups = 3;
        int trials = 5;

        String outFile = "results.csv";
        try (FileWriter fw = new FileWriter(outFile)) {
            fw.write("algorithm,distribution,n,trial,timeNanos,comparisons,shifts,arrayAccesses\n");

            for (int n : sizes) {
                for (String dist : new String[]{"random", "sorted", "reverse", "nearly-sorted"}) {
                    for (int trial = 0; trial < trials + warmups; trial++) {
                        int[] arr = generateArray(n, dist);
                        PerformanceTracker t = new PerformanceTracker();

                        if (trial < warmups) {
                            InsertionSort.sort(arr.clone(), t);
                            continue;
                        }

                        t.startTimer();
                        InsertionSort.sort(arr, t);
                        t.stopTimer();

                        fw.write(String.format("InsertionSort,%s,%d,%d,%d,%d,%d,%d\n",
                                dist, n, trial - warmups,
                                t.elapsedNanos(), t.getComparisons(),
                                t.getShifts(), t.getArrayAccesses()));
                    }
                }
            }
        }
        System.out.println("✅ Benchmarks done. Results saved to " + outFile);
    }

    private static int[] generateArray(int n, String type) {
        Random rand = new Random();
        int[] arr = new int[n];
        switch (type) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly-sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                for (int k = 0; k < n / 100; k++) {
                    int i = rand.nextInt(n);
                    int j = rand.nextInt(n);
                    int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                }
                break;
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n);
        }
        return arr;
    }
}
