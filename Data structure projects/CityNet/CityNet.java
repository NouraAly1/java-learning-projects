import java.util.*;

public class CityNet {

    // ===== Code 1.1: Alerts by severity =====
    // A record is a small, simple class that just holds data.
    // Each alert has a type (what happened) and a severity (how serious it is, 1 to 10).
    record Alert(String type, int severity) {}

    // ===== Code 1.3: Junctions (record and green light) =====
    // Each junction has a name and the number of cars waiting there right now.
    record Junction(String name, int carsWaiting) {}

    // Placeholder method. In a real system this would talk to the signal controller.
    static void extendGreenLight(String junctionName) {
        System.out.println("Extending green light at " + junctionName);
    }

    // ===== Code 2.2: Shortest route =====
    // A special number that means "no route found".
    // We use the biggest int so any real travel time is smaller than it.
    static final int NONE = Integer.MAX_VALUE;

    // time[a][b] = minutes from a to b (0 = no road), blocked[a][b] = road closed
    // seen[] remembers which junctions are on our current path, so we never go in circles.
    static int shortest(int from, int to, int[][] time, boolean[][] blocked, boolean[] seen) {
        // Base case: we are already at the destination, so it takes 0 more minutes.
        // Why: every recursive function needs a place to stop, or it runs forever.
        if (from == to) return 0;

        // Mark this junction as "in use" on the current path.
        seen[from] = true;

        // Start with "no route yet". We will replace it when we find a better one.
        int best = NONE;

        // Try every junction as the next step.
        for (int next = 0; next < time.length; next++) {
            // Skip it if: there is no road, the road is closed, or we already visited it.
            // Why: these steps would be impossible or would loop back.
            if (time[from][next] == 0 || blocked[from][next] || seen[next]) continue;

            // Recursive call: the same problem, but smaller. "How long from next to the destination?"
            int rest = shortest(next, to, time, blocked, seen);

            // Only use the answer if a route really exists.
            // Why: adding to NONE (the biggest int) would overflow and give a wrong number.
            // Keep the smaller total: the time for this road plus the time for the rest.
            if (rest != NONE) best = Math.min(best, time[from][next] + rest);
        }

        // Backtrack: free this junction so other paths can use it.
        // Why: a junction blocked on one path may be part of a better path.
        seen[from] = false;

        // Return the best time found, or NONE if every path was dead.
        return best;
    }

    // ===== Code 2.3: Signal plan simulation =====
    // Tries every signal plan, minute by minute, and finds the lowest number of waiting cars at the end.
    // minute = the current minute, horizon = when to stop, carsWaiting = the queue right now,
    // plans = the signal plans we can choose from.
    static int simulate(int minute, int horizon, int carsWaiting, int[] plans) {
        // Base case: time is up, so report how many cars are still waiting.
        // Why: this stops the recursion and gives each path its final score.
        if (minute == horizon) return carsWaiting;

        // Start with the biggest possible number so any real result is better.
        int best = Integer.MAX_VALUE;

        // Try each plan for this minute.
        for (int plan : plans) {
            // New queue = cars already waiting + cars that arrive - cars the plan lets through.
            // arrivals() and released() are assumed helper methods.
            int next = carsWaiting + arrivals(minute) - released(plan);

            // Move to the next minute and see how this choice turns out.
            // Math.max(next, 0) stops the queue from going below zero, because you can't have -3 cars.
            best = Math.min(best, simulate(minute + 1, horizon, Math.max(next, 0), plans));
        }

        // Return the best result among all plans: the fewest cars left waiting.
        return best;
    }

    // Placeholder helpers so the code compiles.
    static int arrivals(int minute) { return 10; }    // cars arriving each minute
    static int released(int plan)   { return plan; }  // cars the plan lets through

    public static void main(String[] args) {

        // ===== Code 1.1: Alerts by severity (main) =====
        // A PriorityQueue is a heap inside. It always keeps the "top" item ready to take out fast.
        // By default Java puts the SMALLEST value on top. We want the MOST serious alert first,
        // so we give it our own rule (a comparator) that flips the order.
        PriorityQueue<Alert> queue = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.severity(), a.severity())); // b before a = biggest severity first

        // offer() adds an alert. The heap fixes itself (ReHeap-Up) so the most serious one rises to the top.
        // This takes O(log n) time, so it stays fast even with thousands of alerts.
        queue.offer(new Alert("Routine traffic count", 1));
        queue.offer(new Alert("Ambulance route blocked", 10));
        queue.offer(new Alert("Accident on Main St", 9));

        // poll() removes and returns the top alert. The heap fixes itself again (ReHeap-Down).
        // Why: operators always handle the most urgent problem first, not the one that came first.
        Alert next = queue.poll(); // "Ambulance route blocked"

        // Print the result so you can see it work.
        System.out.println(next.type());

        // ===== Code 1.2: Min-heap and max-heap =====
        // Default order: smallest number is on top. This is a min-heap.
        // Useful when "smaller is more urgent", like the shortest travel time.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Collections.reverseOrder() flips the rule, so the biggest number is on top. This is a max-heap.
        // Useful when "bigger is more urgent", like the most cars waiting.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add the same numbers to both heaps so you can see the difference.
        for (int n : new int[]{5, 1, 9, 3}) {
            minHeap.offer(n);
            maxHeap.offer(n);
        }

        // peek() shows the top item without removing it.
        System.out.println("Min-heap top: " + minHeap.peek()); // 1
        System.out.println("Max-heap top: " + maxHeap.peek()); // 9

        // ===== Code 1.3: Junctions (main) =====
        // Max-heap by carsWaiting: the junction with the longest queue stays on top.
        // Why: the worst jam should get help first.
        PriorityQueue<Junction> jams = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.carsWaiting(), a.carsWaiting()));

        // Insertion: add junctions as the sensors report them.
        // Each add costs O(log n) because the heap only moves the new item up a few levels.
        jams.offer(new Junction("5th and Main", 42));   // insertion
        jams.offer(new Junction("Elm and 2nd", 15));

        // Deletion: take out the junction with the most cars waiting.
        // We remove it so the next-worst junction becomes the new top.
        Junction worst = jams.poll();                    // deletion: 5th and Main

        // Act on the result: a longer green light clears the biggest queue.
        extendGreenLight(worst.name());

        // ===== Tests for Code 2.2 and 2.3 =====
        // Test map with 3 junctions: 0 -- 5 min -- 1 -- 3 min -- 2
        int[][] time = { {0, 5, 0}, {5, 0, 3}, {0, 3, 0} };
        boolean[][] blocked = new boolean[3][3];
        System.out.println("Shortest time 0 to 2: " + shortest(0, 2, time, blocked, new boolean[3])); // 8

        // Test: 3 minutes, start with 20 cars, plans that release 8, 12 or 15 cars per minute
        System.out.println("Fewest cars left: " + simulate(0, 3, 20, new int[]{8, 12, 15})); // 5
    }
}