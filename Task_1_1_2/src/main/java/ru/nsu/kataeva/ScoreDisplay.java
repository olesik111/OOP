package ru.nsu.kataeva;

/**
 * Class for displaying game messages.
 */
public class ScoreDisplay {

    /**
     * Displays the current score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayScore(int winPlayer, int winDealer) {
        System.out.println("Score: " + winPlayer + ":" + winDealer);
    }

    /**
     * Displays "You won!" message with score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayYouWin(int winPlayer, int winDealer) {
        System.out.println("You won!");
        displayScore(winPlayer, winDealer);
    }

    /**
     * Displays "You lost!" message with score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayYouLose(int winPlayer, int winDealer) {
        System.out.println("You lost!");
        displayScore(winPlayer, winDealer);
    }

    /**
     * Displays "It's a draw!" message with score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayDraw(int winPlayer, int winDealer) {
        System.out.println("It's a draw!");
        displayScore(winPlayer, winDealer);
    }

    /**
     * Displays "You lost! Bust!" message with score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayBust(int winPlayer, int winDealer) {
        System.out.println("You lost! Bust!");
        displayScore(winPlayer, winDealer);
    }

    /**
     * Displays "You lost! Dealer has Blackjack!" message with score.
     *
     * @param winPlayer number of player wins
     * @param winDealer number of dealer wins
     */
    public static void displayDealerBlackjack(int winPlayer, int winDealer) {
        System.out.println("You lost! Dealer has Blackjack!");
        displayScore(winPlayer, winDealer);
    }
}