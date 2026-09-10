
import java.util.ArrayList;

public class StockAnalyzer {

    // PART 1: Average stock price

    public static double calculateAveragePrice(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total = total + prices[i];   // int is fine here, we only convert at the very end
        }
        return (double) total / prices.length;   // cast BEFORE dividing, or Java rounds down and drops the decimal
    }

    public static double calculateAveragePrice(ArrayList<Integer> prices) {
        int total = 0;
        for (int price : prices) {       // for-each works fine since we're just reading, not modifying the list
            total = total + price;
        }
        return (double) total / prices.size();
    }


    // PART 2: Maximum stock price

    public static int findMaximumPrice(int[] prices) {
        int max = prices[0];             // start with the first price as our best guess
        for (int i = 1; i < prices.length; i++) {   // start at 1, no point comparing prices[0] to itself
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        return max;
    }

    public static int findMaximumPrice(ArrayList<Integer> prices) {
        int max = prices.get(0);
        for (int price : prices) {       // for-each doesn't give us an index to skip, so we just loop through all of it
            if (price > max) {
                max = price;
            }
        }
        return max;
    }


    // PART 3: Occurrence count of a specific price

    public static int countOccurrences(int[] prices, int target) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == target) {   // == works fine, target and prices[i] are both primitive ints
                count++;
            }
        }
        return count;
    }


    // PART 4: Cumulative sum of stock prices

    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> prices) {
        ArrayList<Integer> cumulativeSums = new ArrayList<>();
        int runningTotal = 0;
        for (int price : prices) {
            runningTotal = runningTotal + price;   // keep adding on top of what we already had
            cumulativeSums.add(runningTotal);       // save the total exactly as it stood at this point
        }
        return cumulativeSums;
    }


    // Quick test to make sure everything actually works

    public static void main(String[] args) {
        int[] stockArray = {100, 105, 98, 102, 110, 98, 115};

        ArrayList<Integer> stockList = new ArrayList<>();
        stockList.add(100);
        stockList.add(105);
        stockList.add(98);
        stockList.add(102);
        stockList.add(110);
        stockList.add(98);
        stockList.add(115);

        System.out.println("Average (array): " + calculateAveragePrice(stockArray));
        System.out.println("Average (list): " + calculateAveragePrice(stockList));
        System.out.println("Max (array): " + findMaximumPrice(stockArray));
        System.out.println("Max (list): " + findMaximumPrice(stockList));
        System.out.println("Occurrences of 98: " + countOccurrences(stockArray, 98));
        System.out.println("Cumulative sum: " + computeCumulativeSum(stockList));
    }
}