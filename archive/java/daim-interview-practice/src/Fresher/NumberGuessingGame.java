package Fresher;

import java.util.Random;
import java.util.Scanner;

/**
 * A number guessing game with multiple difficulty levels and game modes
 */
public class NumberGuessingGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    // Game statistics
    private static int gamesPlayed = 0;
    private static int gamesWon = 0;
    private static int totalGuesses = 0;
    private static int bestScore = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println("=== Number Guessing Game ===\n");
        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            playGame();

            System.out.print("\nWould you like to play again? (y/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("y") || response.equals("yes");
        }

        // Display final statistics
        displayStatistics();
        System.out.println("\nThank you for playing! Goodbye!");
        scanner.close();
    }

    /**
     * Main game logic
     */
    private static void playGame() {
        // Choose difficulty level
        DifficultyLevel difficulty = chooseDifficulty();

        // Choose game mode
        GameMode gameMode = chooseGameMode();

        System.out.println("\n" + gameMode.getDescription());

        // Initialize game parameters based on difficulty and mode
        int lowerBound = 1;
        int upperBound = difficulty.getRange();
        int maxAttempts = difficulty.getMaxAttempts();
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int attempts = 0;
        boolean hasWon = false;

        System.out.printf("I'm thinking of a number between %d and %d.\n", lowerBound, upperBound);
        System.out.printf("You have %d attempts to guess it.\n\n", maxAttempts);

        // Main game loop
        while (attempts < maxAttempts) {
            System.out.printf("Attempt %d/%d - Enter your guess: ", attempts + 1, maxAttempts);

            // Get and validate user's guess
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            // Check if guess is in valid range
            if (guess < lowerBound || guess > upperBound) {
                System.out.printf("Please enter a number between %d and %d.\n", lowerBound, upperBound);
                continue;
            }

            attempts++;

            // Process the guess based on game mode
            if (gameMode == GameMode.STANDARD) {
                if (guess == targetNumber) {
                    hasWon = true;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            } else if (gameMode == GameMode.HOT_COLD) {
                if (guess == targetNumber) {
                    hasWon = true;
                    break;
                } else {
                    int distance = Math.abs(guess - targetNumber);
                    String feedback = getHotColdFeedback(distance, upperBound);
                    System.out.println(feedback);
                }
            } else if (gameMode == GameMode.BINARY_SEARCH) {
                if (guess == targetNumber) {
                    hasWon = true;
                    break;
                } else if (guess < targetNumber) {
                    lowerBound = guess + 1;
                    System.out.printf("Too low! The number is between %d and %d.\n", lowerBound, upperBound);
                } else {
                    upperBound = guess - 1;
                    System.out.printf("Too high! The number is between %d and %d.\n", lowerBound, upperBound);
                }
            }

            // Show remaining attempts
            if (attempts < maxAttempts) {
                System.out.printf("You have %d attempts remaining.\n\n", maxAttempts - attempts);
            }
        }

        // Game outcome
        if (hasWon) {
            System.out.printf("\nCongratulations! You guessed the number %d correctly in %d attempts!\n", targetNumber, attempts);
            gamesWon++;
            totalGuesses += attempts;
            bestScore = Math.min(bestScore, attempts);
        } else {
            System.out.printf("\nGame over! You've run out of attempts. The number was %d.\n", targetNumber);
            totalGuesses += attempts;
        }

        gamesPlayed++;
    }

    /**
     * Choose the difficulty level for the game
     */
    private static DifficultyLevel chooseDifficulty() {
        System.out.println("\nSelect difficulty level:");
        for (DifficultyLevel level : DifficultyLevel.values()) {
            System.out.printf("%d. %s (1-%d, %d attempts)\n", 
                             level.ordinal() + 1, 
                             level.name(), 
                             level.getRange(), 
                             level.getMaxAttempts());
        }

        System.out.print("\nEnter your choice (1-" + DifficultyLevel.values().length + "): ");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Selecting EASY difficulty by default.");
            scanner.nextLine(); // Clear the invalid input
            return DifficultyLevel.EASY;
        }

        if (choice < 1 || choice > DifficultyLevel.values().length) {
            System.out.println("Invalid choice. Selecting EASY difficulty by default.");
            return DifficultyLevel.EASY;
        }

        return DifficultyLevel.values()[choice - 1];
    }

    /**
     * Choose the game mode
     */
    private static GameMode chooseGameMode() {
        System.out.println("\nSelect game mode:");
        for (GameMode mode : GameMode.values()) {
            System.out.printf("%d. %s - %s\n", 
                             mode.ordinal() + 1, 
                             mode.name(), 
                             mode.getDescription());
        }

        System.out.print("\nEnter your choice (1-" + GameMode.values().length + "): ");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Selecting STANDARD mode by default.");
            scanner.nextLine(); // Clear the invalid input
            return GameMode.STANDARD;
        }

        if (choice < 1 || choice > GameMode.values().length) {
            System.out.println("Invalid choice. Selecting STANDARD mode by default.");
            return GameMode.STANDARD;
        }

        return GameMode.values()[choice - 1];
    }

    /**
     * Generate feedback for the Hot/Cold game mode
     */
    private static String getHotColdFeedback(int distance, int range) {
        // Calculate percentage distance from target
        double percentage = (double) distance / range;

        if (percentage < 0.05) return "Boiling hot! You're extremely close!";
        if (percentage < 0.1) return "Very hot! You're getting very close!";
        if (percentage < 0.2) return "Hot! You're getting closer!";
        if (percentage < 0.3) return "Warm. You're in the vicinity.";
        if (percentage < 0.4) return "Cool. Not too far, not too close.";
        if (percentage < 0.6) return "Cold. You're quite a way off.";
        if (percentage < 0.8) return "Very cold. You're far away.";
        return "Freezing! You're nowhere near the target!";
    }

    /**
     * Display game statistics
     */
    private static void displayStatistics() {
        System.out.println("\n=== Game Statistics ===");
        System.out.println("Games Played: " + gamesPlayed);
        System.out.println("Games Won: " + gamesWon);
        System.out.println("Win Rate: " + (gamesPlayed > 0 ? (gamesWon * 100.0 / gamesPlayed) : 0) + "%");

        if (gamesWon > 0) {
            System.out.println("Average Guesses per Win: " + (totalGuesses * 1.0 / gamesWon));
            System.out.println("Best Score (fewest guesses): " + bestScore);
        }
    }

    /**
     * Enum for difficulty levels
     */
    enum DifficultyLevel {
        EASY(50, 10),
        MEDIUM(100, 7),
        HARD(200, 7),
        EXPERT(500, 9),
        INSANE(1000, 10);

        private final int range;
        private final int maxAttempts;

        DifficultyLevel(int range, int maxAttempts) {
            this.range = range;
            this.maxAttempts = maxAttempts;
        }

        public int getRange() {
            return range;
        }

        public int getMaxAttempts() {
            return maxAttempts;
        }
    }

    /**
     * Enum for game modes
     */
    enum GameMode {
        STANDARD("Standard higher/lower feedback"),
        HOT_COLD("Hot/Cold feedback based on how close you are"),
        BINARY_SEARCH("Narrows down the range with each guess");

        private final String description;

        GameMode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
