package app.bpmCounter;
import java.time.*;
import java.util.Arrays;

public class Counter {

    private int currentBPM;
    private long[] storedTimes;
    private int currentCount;
    private boolean hasLooped;

    public Counter(AppWindow currentWindow) {
        currentBPM = 0;
        storedTimes = new long[10];
        currentCount = 0;
        hasLooped = false;
    }

    public int getCurrentBPM() {
        return currentBPM;
    }

    public void buttonPress() {
        LocalTime pressTime = LocalTime.now();
        storedTimes[currentCount] = pressTime.toNanoOfDay();
        if(currentCount == 9) {currentCount = 0; hasLooped = true;} else {currentCount++;}
        if(hasLooped || currentCount > 1) {
            updateBPM();
        }
    }

    public void updateBPM() {
        int times = 0;
        if(hasLooped) {
            times = 9;
        } else {
            times = currentCount - 1 ;
        }

        int[] timeDifferences = new int[times];
        for(int i = times; i > 0; i--) {
            timeDifferences[i-1] = Math.toIntExact(storedTimes[i] - storedTimes[i-1]);
        }

        int sum = 0;
        for(int diff : timeDifferences) {
            sum += diff;
        }
        currentBPM = sum / currentCount;

    }
}
