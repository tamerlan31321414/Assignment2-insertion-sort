package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long shifts = 0;
    private long arrayAccesses = 0;
    private long startTimeNanos = 0;
    private long endTimeNanos = 0;

    public void startTimer(){ startTimeNanos = System.nanoTime(); }
    public void stopTimer(){ endTimeNanos = System.nanoTime(); }
    public long elapsedNanos(){ return endTimeNanos - startTimeNanos; }

    public void incComparisons(){ comparisons++; }
    public void addComparisons(long v){ comparisons += v; }
    public void incShifts(){ shifts++; }
    public void addShifts(long v){ shifts += v; }
    public void addArrayAccesses(long v){ arrayAccesses += v; }

    public long getComparisons(){ return comparisons; }
    public long getShifts(){ return shifts; }
    public long getArrayAccesses(){ return arrayAccesses; }
}
