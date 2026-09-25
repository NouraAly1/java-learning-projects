import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Keeps one shared copy of the current date and time.
 * One thread updates it and the other thread prints it.
 */
public class Clock {

    // Only one formatter is needed since the pattern never changes
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    // Update often enough that the display is never a whole second behind
    private static final long UPDATE_INTERVAL_MS = 100;

    // Check for a new second often so each one gets printed on time
    private static final long DISPLAY_CHECK_INTERVAL_MS = 100;

    // Private so the threads can only use it through the synchronized methods
    private LocalDateTime currentDateTime;

    // volatile so both threads see right away when stop() is called
    private volatile boolean running = true;

    public Clock() {
        // Set a starting time so the display never prints null
        currentDateTime = LocalDateTime.now();
    }

    // synchronized because one thread writes the time while the other reads it
    public synchronized void updateTime() {
        currentDateTime = LocalDateTime.now();
    }

    // Same lock as updateTime(), so we never read in the middle of an update
    public synchronized String getFormattedTime() {
        return currentDateTime.format(DISPLAY_FORMAT);
    }

    /**
     * Run by the background thread. Keeps updating the time until the app stops.
     */
    public void keepTimeUpdated() {
        while (running) {
            updateTime();
            try {
                // Sleep so this thread doesn't take all the CPU time
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                // Interrupted means the app is shutting down, so exit the loop
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Run by the display thread. Prints the time each time a new second starts.
     */
    public void keepDisplayingTime() {
        // Used to show the thread's name and priority in the output
        Thread displayThread = Thread.currentThread();

        // Remember the last time printed so each second only shows once
        String lastShownTime = "";

        while (running) {
            String timeNow = getFormattedTime();
            if (!timeNow.equals(lastShownTime)) {
                System.out.println("[" + displayThread.getName()
                        + " | priority " + displayThread.getPriority() + "]  "
                        + timeNow);
                lastShownTime = timeNow;
            }
            try {
                // Short sleep between checks so the updater thread also gets CPU time
                Thread.sleep(DISPLAY_CHECK_INTERVAL_MS);
            } catch (InterruptedException e) {
                // Same as above, stop when interrupted
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Lets both loops finish on their own instead of forcing the threads to stop
    public void stop() {
        running = false;
    }
}