package ru.nsu.kataeva;

import java.util.Scanner;

/**
 * Does player and dealer decisions during the game.
 */
public class Decision {
    /**
     * The player's turn to take or pass.
     *
     * @param playerHand player's hand.
     * @param gameDeck   the main deck.
     */
    public void playerDecision(Deck playerHand, Deck gameDeck) {
        Scanner scanner = new Scanner(System.in);
        int decision = 1;
        while (decision == 1) {
            System.out.println("\nWant to take a card? 1-Take, 0-Pass");

            try {
                decision = scanner.nextInt();

                if (decision == 1) {
                    playerHand.takeForRound(gameDeck);
                    System.out.println("Your cards:");
                    System.out.println(playerHand);

                    int currentSum = playerHand.cardsInHand(playerHand);
                    System.out.println("Your score: " + currentSum);

                    if (currentSum >= 21) {
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

    public void less17Decision(Deck deckForPlayer,
                               Deck deckForDealer, int winDealer, int winPlayer) {
        int dealerSum = deckForDealer.cardsInHand(deckForDealer);
        int playerSum = deckForPlayer.cardsInHand(deckForPlayer);

        if (dealerSum > playerSum) {
            System.out.println("You lost!");
            winDealer++;
            System.out.println("Score: " + winPlayer + ":" + winDealer);

        } else if (dealerSum < playerSum) {
            System.out.println("You won!");
            winPlayer++;
            System.out.println("Score: " + winPlayer + ":" + winDealer);
        } else {
            System.out.println("It's a draw!");
            winDealer++;
            winPlayer++;
            System.out.println("Score: " + winPlayer + ":" + winDealer);
        }
    }
}