package ru.nsu.kataeva;

import java.util.Scanner;

/**
 * Does player and dealer decisions during the game.
 */
public class Decision {
    private static final int BLACKJACK = 21;

    /**
     * The player's turn to take or pass.
     *
     * @param playerHand player's hand.
     * @param gameDeck   the main deck.
     */
    public static void playerDecision(Hand playerHand, Deck gameDeck, Scanner scanner) {
        int decision = 1;
        while (decision == 1) {
            System.out.println("\nWant to take a card? 1-Take, 0-Pass");

            try {
                decision = scanner.nextInt();

                if (decision == 1) {
                    playerHand.takeForRound(gameDeck);
                    System.out.println("Your cards:");
                    System.out.println(playerHand);

                    int currentSum = playerHand.valueInHands();
                    System.out.println("Your score: " + currentSum);

                    if (currentSum >= BLACKJACK) {
                        return;
                    }
                }
                if (decision == 0) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Enter 1 or 0");
                scanner.next();
            }
        }
    }

    /**
     * Comparing values to decide who won without blackjack.
     *
     * @param deckForPlayer player's deck
     * @param deckForDealer dealer's deck
     * @param winDealer     number of dealer's wins
     * @param winPlayer     number of player's wins
     */
    public static void less17Decision(Hand deckForPlayer,
                               Hand deckForDealer, int winDealer, int winPlayer) {
        int dealerSum = deckForDealer.valueInHands();
        int playerSum = deckForPlayer.valueInHands();

        if (dealerSum > playerSum) {
            ScoreDisplay.displayYouLose(winPlayer, winDealer);
        } else if (dealerSum < playerSum) {
            ScoreDisplay.displayYouWin(winPlayer, winDealer);
        } else {
            ScoreDisplay.displayDraw(winPlayer, winDealer);
        }
    }
}