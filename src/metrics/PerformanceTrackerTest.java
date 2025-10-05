package metrics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTrackerTest {

    @Test
    public void testPerformanceTrackerCounting() {
        PerformanceTracker tracker = new PerformanceTracker();

        tracker.incComparisons();
        tracker.addComparisons(4);
        tracker.incShifts();
        tracker.addShifts(3);
        tracker.addArrayAccesses(5);

        assertEquals(5, tracker.getComparisons());
        assertEquals(4, tracker.getShifts());
        assertEquals(5, tracker.getArrayAccesses());
    }

    @Test
    public void testTimerAndElapsedTime() {
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();
        for (int i = 0; i < 1000000; i++);
        tracker.stopTimer();

        assertTrue(tracker.elapsedNanos() > 0);
    }

    @Test
    public void testToStringFormat() {
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.addComparisons(10);
        tracker.addShifts(5);
        tracker.addArrayAccesses(8);
        tracker.startTimer();
        tracker.stopTimer();

        String result = tracker.toString();
        assertTrue(result.contains(","));
    }
}
