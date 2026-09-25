import java.io.IOException;

/**
 * Starts the clock with two threads that have different priorities,
 * and stops it when the user presses Enter.
 */
public class ClockApp {

    public static void main(String[] args) {
        // Both threads use the same Clock object
        Clock clock = new Clock();

        // Method references mean I don't need two extra Runnable classes
        Thread updaterThread = new Thread(clock::keepTimeUpdated, "Updater-Thread");
        Thread displayThread = new Thread(clock::keepDisplayingTime, "Display-Thread");

        try {
            // Display gets top priority because showing the time on time matters most
            displayThread.setPriority(Thread.MAX_PRIORITY);
            // Updater gets the lowest because a small delay here doesn't hurt
            updaterThread.setPriority(Thread.MIN_PRIORITY);
        } catch (SecurityException | IllegalArgumentException e) {
            // The clock still works without priorities, so just warn
            System.out.println("Warning: could not set thread priorities: "
                    + e.getMessage());
        }

        // Print the header before starting the threads so it doesn't get mixed in
        System.out.println("=== Java Thread Clock ===");
        System.out.println(displayThread.getName() + " priority: "
                + displayThread.getPriority() + " (clock display thread)");
        System.out.println(updaterThread.getName() + " priority: "
                + updaterThread.getPriority() + " (background updating thread)");
        System.out.println("Press Enter to stop the clock.");
        System.out.println();

        // Start the updater first so the time is fresh when the display starts
        updaterThread.start();
        displayThread.start();

        waitForEnterKey();

        // Stop the loops, wake up any sleeping thread, then wait for both to finish
        clock.stop();
        updaterThread.interrupt();
        displayThread.interrupt();
        try {
            updaterThread.join();
            displayThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Clock stopped.");
    }

    // Waits until the user presses Enter
    private static void waitForEnterKey() {
        try {
            System.in.read();
        } catch (IOException e) {
            // If input can't be read, the clock just stops
            System.out.println("Could not read keyboard input: " + e.getMessage());
        }
    }
}