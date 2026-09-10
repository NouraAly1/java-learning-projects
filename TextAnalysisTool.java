import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Step 1: Get the text from the user
        // nextLine() reads a whole line, not just one word, since we need a full paragraph
        System.out.println("Enter your text:");
        String text = input.nextLine();

        // Input validation: make sure the user actually typed something
        // trim() removes spaces so a text that's just blank spaces doesn't count as valid
        while (text.trim().isEmpty()) {
            System.out.println("You can't leave this empty. Please enter some text:");
            text = input.nextLine();
        }

        // Step 2: Character count
        // length() just counts every character in the string, including spaces
        int charCount = text.length();
        System.out.println("Total characters: " + charCount);

        // Step 3: Word count
        // trim() first so leading/trailing spaces don't create extra empty "words"
        // split(" ") breaks the string apart wherever there's a space
        String[] words = text.trim().split(" ");
        int wordCount = words.length;
        System.out.println("Total words: " + wordCount);

        // Step 4: Most common character
        // Using a HashMap to keep track of how many times each character shows up
        // Key = the character, Value = how many times we've seen it so far
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Skipping spaces because "the most common character" being a space
            // isn't really useful information for the user
            if (c == ' ') {
                continue;
            }

            // toLowerCase makes sure 'A' and 'a' are counted as the same character
            c = Character.toLowerCase(c);

            // getOrDefault: if we've seen this character before, grab its current count
            // if not, start counting from 0
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
        }

        char mostCommonChar = ' ';
        int highestCharCount = 0;

        // Go through everything we counted and keep the character with the biggest count
        for (Map.Entry<Character, Integer> entry : charFrequencyMap.entrySet()) {
            if (entry.getValue() > highestCharCount) {
                highestCharCount = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }

        System.out.println("Most common character: '" + mostCommonChar + "' (appears " + highestCharCount + " times)");

        // Step 5: Character frequency (user picks a character to search for)
        System.out.println("Enter a character to check its frequency:");
        String charInput = input.nextLine();

        // Input validation: the user needs to type exactly one character, not a whole word
        while (charInput.length() != 1) {
            System.out.println("Please enter exactly one character:");
            charInput = input.nextLine();
        }

        // Same lowercase trick so the search is case-insensitive, like the task asks
        char targetChar = Character.toLowerCase(charInput.charAt(0));

        int targetCharCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.toLowerCase(text.charAt(i)) == targetChar) {
                targetCharCount++;
            }
        }

        System.out.println("'" + targetChar + "' appears " + targetCharCount + " times in the text.");

        // Step 6: Word frequency (user picks a word to search for)
        System.out.println("Enter a word to check its frequency:");
        String wordInput = input.nextLine().trim();

        // Input validation: don't accept an empty word
        while (wordInput.isEmpty()) {
            System.out.println("Please enter a word (not empty):");
            wordInput = input.nextLine().trim();
        }

        // Lowercase again so "Java" and "java" are treated as the same word
        String targetWord = wordInput.toLowerCase();

        int targetWordCount = 0;
        for (String w : words) {
            if (w.toLowerCase().equals(targetWord)) {
                targetWordCount++;
            }
        }

        System.out.println("\"" + targetWord + "\" appears " + targetWordCount + " times in the text.");

        // Step 7: Unique word count
        // Reusing the same HashMap idea, but this time we only care about how many
        // different keys exist, not the actual counts
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String w : words) {
            String lowerWord = w.toLowerCase();
            wordFrequencyMap.put(lowerWord, wordFrequencyMap.getOrDefault(lowerWord, 0) + 1);
        }

        // The number of keys in the map = the number of different (unique) words
        int uniqueWordCount = wordFrequencyMap.size();
        System.out.println("Unique words: " + uniqueWordCount);

        input.close();
    }
}