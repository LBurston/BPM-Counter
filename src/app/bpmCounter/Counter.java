package app.bpmCounter;
import java.time.*;

public class Counter {

    private int currentBPM;
    private final Instant[] storedTimes = new Instant[10];
    private int currentCount;
    private boolean hasLooped;

    public Counter(AppWindow currentWindow) {
        currentBPM = 0;
        currentCount = 0;
        hasLooped = false;
    }

    public int getCurrentBPM() {
        return currentBPM;
    }

    public void buttonPress() {
        int checker;
        if(currentCount == 0) {
            checker = 10;
        } else { checker = currentCount;}

        if((hasLooped || currentCount > 0) && Instant.now().isAfter(storedTimes[checker-1].plusSeconds(2)) ) {
            reset();
        }

        storedTimes[currentCount] = Instant.now();

        if(currentCount == 9) {currentCount = 0; hasLooped = true;} else {currentCount++;}
        if(hasLooped || currentCount > 1) {updateBPM();}
    }

    public void reset() {
        for(int i = 0; i < 10; i++) {
            if(storedTimes[i] != null) {
                storedTimes[i] = null;
            }
        }
        currentBPM = 0;
        currentCount = 0;
        hasLooped = false;
    }

    public void updateBPM() {
        // Sets a counter for how many times have been stored so far
        int times;
        if(hasLooped) {
            times = 9;
        } else {
            times = currentCount - 1 ;
        }

        // Creates an array of time differences between each input
        Duration[] timeDifferences = new Duration[times];
        if(!hasLooped || currentCount == 0) { // If less than
            for (int i = 0; i < times; i++) {
                timeDifferences[i] = Duration.between(storedTimes[i], storedTimes[i+1]);
            }
        } else {
            for (int i = currentCount; i < times; i++) {
                timeDifferences[i] = Duration.between(storedTimes[i], storedTimes[i+1]);
            }
            for (int i = 0; i < currentCount; i++) {
                if(i == 0) {
                    timeDifferences[i] = Duration.between(storedTimes[9], storedTimes[i]);
                } else {
                    timeDifferences[i] = Duration.between(storedTimes[i-1], storedTimes[i]);
                }
            }
        }

        float sum = 0;
        for(Duration diff : timeDifferences) {
            sum += diff.toNanos();
        }
        sum = 60 / ((sum/times)/1000000000);
        currentBPM = Math.round(sum);
    }
}
