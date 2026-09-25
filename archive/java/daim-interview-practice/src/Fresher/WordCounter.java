package Fresher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A program to count words, characters, and lines in a text
 * along with finding the most frequent words.
 */
public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Word Counter ===\n");

        System.out.println("Select input method:");
        System.out.println("1. Enter text directly");
        System.out.println("2. Read from a file");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String text = "";
        if (choice == 1) {
            System.out.println("\nEnter your text (type 'END' on a new line to finish):");
            String line;
            StringBuilder sb = new StringBuilder();
            while (!(line = scanner.nextLine()).equals("END")) {
                sb.append(line).append("\n");
            }
            text = sb.toString();
        } else if (choice == 2) {
            System.out.print("\nEnter the file path: ");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }

        // Analyze the text
        TextAnalysis analysis = analyzeText(text);

        // Display results
        System.out.println("\n=== Text Analysis Results ===");
        System.out.println("Character count: " + analysis.getCharCount());
        System.out.println("Word count: " + analysis.getWordCount());
        System.out.println("Line count: " + analysis.getLineCount());
        System.out.println("Sentence count: " + analysis.getSentenceCount());
        System.out.println("Paragraph count: " + analysis.getParagraphCount());
        System.out.println("Unique word count: " + analysis.getUniqueWordCount());

        // Display word frequency
        System.out.println("\n=== Most Frequent Words ===");
        int topN = Math.min(10, analysis.getWordFrequency().size());
        List<Map.Entry<String, Integer>> sortedWords = analysis.getTopWords(topN);
        for (int i = 0; i < sortedWords.size(); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            System.out.printf("%d. %-15s : %d occurrences\n", i + 1, entry.getKey(), entry.getValue());
        }

        // Display readability statistics
        System.out.println("\n=== Readability Statistics ===");
        System.out.printf("Average words per sentence: %.2f\n", analysis.getAvgWordsPerSentence());
        System.out.printf("Average sentence length (characters): %.2f\n", analysis.getAvgSentenceLength());
        System.out.printf("Flesch Reading Ease score: %.2f\n", analysis.getFleschReadingEase());
        System.out.println("Interpretation: " + analysis.getReadabilityInterpretation());

        // Search for a specific word
        System.out.print("\nEnter a word to search for (or press Enter to skip): ");
        String searchWord = scanner.nextLine().trim().toLowerCase();
        if (!searchWord.isEmpty()) {
            int frequency = analysis.getWordFrequency().getOrDefault(searchWord, 0);
            System.out.println("\nThe word '" + searchWord + "' appears " + frequency + " times.");

            if (frequency > 0) {
                List<String> contexts = analysis.getWordContexts(searchWord);
                System.out.println("\nContext examples (up to 5):");
                int contextLimit = Math.min(5, contexts.size());
                for (int i = 0; i < contextLimit; i++) {
                    System.out.println((i + 1) + ". " + contexts.get(i));
                }
            }
        }

        scanner.close();
    }

    /**
     * Read text from a file
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * Analyze the given text and return a TextAnalysis object
     */
    private static TextAnalysis analyzeText(String text) {
        // Count characters (excluding spaces)
        int charCount = text.replaceAll("\\s", "").length();

        // Count words
        String[] words = text.split("\\s+");
        int wordCount = 0;
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                wordCount++;
            }
        }

        // Count lines
        String[] lines = text.split("\\r?\\n");
        int lineCount = lines.length;

        // Count sentences
        Pattern sentencePattern = Pattern.compile("[^.!?]+[.!?]+");
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        int sentenceCount = 0;
        List<String> sentences = new ArrayList<>();
        while (sentenceMatcher.find()) {
            sentences.add(sentenceMatcher.group().trim());
            sentenceCount++;
        }

        // Count paragraphs (non-empty lines separated by one or more empty lines)
        Pattern paragraphPattern = Pattern.compile("\\n\\s*\\n");
        String[] paragraphs = paragraphPattern.split(text);
        int paragraphCount = 0;
        for (String paragraph : paragraphs) {
            if (!paragraph.trim().isEmpty()) {
                paragraphCount++;
            }
        }
        if (paragraphCount == 0 && !text.trim().isEmpty()) {
            paragraphCount = 1; // If there's text but no paragraph breaks
        }

        // Calculate word frequency
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.trim().toLowerCase();
            // Remove punctuation
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Get contexts for words
        Map<String, List<String>> wordContexts = new HashMap<>();
        for (String word : wordFrequency.keySet()) {
            wordContexts.put(word, findContexts(text, word));
        }

        // Calculate readability statistics
        double avgWordsPerSentence = sentenceCount > 0 ? (double) wordCount / sentenceCount : 0;
        double avgSentenceLength = sentenceCount > 0 ? (double) charCount / sentenceCount : 0;

        // Calculate Flesch Reading Ease score
        // Formula: 206.835 - 1.015 * (words/sentences) - 84.6 * (syllables/words)
        double totalSyllables = estimateTotalSyllables(words);
        double syllablesPerWord = wordCount > 0 ? totalSyllables / wordCount : 0;
        double fleschReadingEase = 206.835 - (1.015 * avgWordsPerSentence) - (84.6 * syllablesPerWord);

        return new TextAnalysis(charCount, wordCount, lineCount, sentenceCount, 
                paragraphCount, wordFrequency, wordContexts, avgWordsPerSentence, 
                avgSentenceLength, fleschReadingEase);
    }

    /**
     * Find contexts where a word appears in the text
     */
    private static List<String> findContexts(String text, String word) {
        List<String> contexts = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            int start = Math.max(0, matcher.start() - 30);
            int end = Math.min(text.length(), matcher.end() + 30);
            String context = text.substring(start, end).replace("\n", " ").trim();
            context = context.replaceAll("(?i)\\b" + word + "\\b", "**" + word.toUpperCase() + "**");
            contexts.add("..." + context + "...");
        }

        return contexts;
    }

    /**
     * Estimate the total number of syllables in an array of words
     */
    private static double estimateTotalSyllables(String[] words) {
        int totalSyllables = 0;
        for (String word : words) {
            word = word.trim().toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty()) {
                totalSyllables += countSyllables(word);
            }
        }
        return totalSyllables;
    }

    /**
     * Count the number of syllables in a word
     */
    private static int countSyllables(String word) {
        // This is a simple approximation of syllable counting
        word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
        if (word.length() <= 3) return 1;

        // Remove silent e at the end
        word = word.replaceAll("e$", "");

        // Count vowel groups
        Matcher matcher = Pattern.compile("[aeiouy]+").matcher(word);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count > 0 ? count : 1; // Every word has at least one syllable
    }
}

/**
 * Class to hold text analysis results
 */
class TextAnalysis {
    private int charCount;
    private int wordCount;
    private int lineCount;
    private int sentenceCount;
    private int paragraphCount;
    private Map<String, Integer> wordFrequency;
    private Map<String, List<String>> wordContexts;
    private double avgWordsPerSentence;
    private double avgSentenceLength;
    private double fleschReadingEase;

    public TextAnalysis(int charCount, int wordCount, int lineCount, int sentenceCount,
                        int paragraphCount, Map<String, Integer> wordFrequency,
                        Map<String, List<String>> wordContexts, double avgWordsPerSentence,
                        double avgSentenceLength, double fleschReadingEase) {
        this.charCount = charCount;
        this.wordCount = wordCount;
        this.lineCount = lineCount;
        this.sentenceCount = sentenceCount;
        this.paragraphCount = paragraphCount;
        this.wordFrequency = wordFrequency;
        this.wordContexts = wordContexts;
        this.avgWordsPerSentence = avgWordsPerSentence;
        this.avgSentenceLength = avgSentenceLength;
        this.fleschReadingEase = fleschReadingEase;
    }

    public int getCharCount() {
        return charCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getParagraphCount() {
        return paragraphCount;
    }

    public int getUniqueWordCount() {
        return wordFrequency.size();
    }

    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    public double getAvgWordsPerSentence() {
        return avgWordsPerSentence;
    }

    public double getAvgSentenceLength() {
        return avgSentenceLength;
    }

    public double getFleschReadingEase() {
        return fleschReadingEase;
    }

    public List<String> getWordContexts(String word) {
        return wordContexts.getOrDefault(word.toLowerCase(), new ArrayList<>());
    }

    /**
     * Get the top N most frequent words
     */
    public List<Map.Entry<String, Integer>> getTopWords(int n) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordFrequency.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        return sortedEntries.subList(0, Math.min(n, sortedEntries.size()));
    }

    /**
     * Get interpretation of Flesch Reading Ease score
     */
    public String getReadabilityInterpretation() {
        if (fleschReadingEase >= 90) return "Very Easy to Read (5th Grade)";
        if (fleschReadingEase >= 80) return "Easy to Read (6th Grade)";
        if (fleschReadingEase >= 70) return "Fairly Easy to Read (7th Grade)";
        if (fleschReadingEase >= 60) return "Plain English (8th-9th Grade)";
        if (fleschReadingEase >= 50) return "Fairly Difficult (10th-12th Grade)";
        if (fleschReadingEase >= 30) return "Difficult (College Level)";
        return "Very Difficult (College Graduate Level)";
    }
}
