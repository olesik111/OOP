package ru.nsu.kataeva;

import java.util.Scanner;

/**
 * Main Blackjack game class.
 */
public class Blackjack {
    private boolean playFlag;
    private int winPlayer = 0;
    private int winDealer = 0;
    private final Deck deckForGame;
    private Hand deckForPlayer;
    private Hand deckForDealer;

    /**
     * Main method to start the Blackjack game.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack!");
        Blackjack game = new Blackjack();
        game.game();
    }

    /**
     * Initializes a new Blackjack game.
     */
    public Blackjack() {
        this.deckForGame = new Deck();
        this.deckForPlayer = new Hand();
        this.deckForDealer = new Hand();
    }

    /**
     * Initializes a new Blackjack game with a specific deck for testing.
     *
     * @param deck the deck to use.
     */
    public Blackjack(Deck deck) {
        this.deckForGame = deck;
        this.deckForPlayer = new Hand();
        this.deckForDealer = new Hand();
    }

    /**
     * Game loop for one deck.
     */
    public void game() {
        playFlag = true;

        Scanner userInput = new Scanner(System.in);

        while (playFlag) {
            System.out.println();
            System.out.println("New round? y/n");

            String answer = userInput.next();

            if (answer.equals("y")) {
                roundPlay(userInput);
            } else if (answer.equals("n")) {
                System.out.println("Good game!");
                playFlag = false;
                break;
            } else {
                System.out.println("Please answer y/n");
            }
        }

        userInput.close();
    }

    /**
     * Round till BlackJack.
     */
    private void roundPlay(Scanner userInput) {
        try {
            deckForPlayer = new Hand();
            deckForDealer = new Hand();

            dealInitialCards(deckForPlayer, deckForDealer);
            showInitialHands(deckForPlayer, deckForDealer);

            // Check for instant win conditions (blackjacks)
            if (checkInstantWin()) {
                return;
            }

            Decision.playerDecision(deckForPlayer, deckForGame, userInput);

            if (deckForPlayer.isBust()) {
                winDealer++;
                ScoreDisplay.displayBust(winPlayer, winDealer);
                return;
            }

            // Dealer's turn
            dealerTurn();

            if (deckForDealer.isBust()) {
                winPlayer++;
                ScoreDisplay.displayYouWin(winPlayer, winDealer);
                return;
            }

            // Compare hands
            compareHands();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("Final Score: " + winPlayer + ":" + winDealer);
            playFlag = false;
        }
    }

    /**
     * Checks for instant win conditions (blackjacks).
     *
     * @return true if instant win condition occurred
     */
    private boolean checkInstantWin() {
        if (deckForDealer.hasBlackjack()) {
            if (deckForPlayer.hasBlackjack()) {
                System.out.println("It's a draw! Both have Blackjack!");
                winDealer++;
                winPlayer++;
                ScoreDisplay.displayDraw(winPlayer, winDealer);
            } else {
                System.out.println("Dealer's hand:");
                System.out.println(deckForDealer);
                winDealer++;
                ScoreDisplay.displayDealerBlackjack(winPlayer, winDealer);
            }
            return true;
        }

        if (deckForPlayer.hasBlackjack()) {
            winPlayer++;
            ScoreDisplay.displayYouWin(winPlayer, winDealer);
            return true;
        }

        return false;
    }

    /**
     * Dealer's turn to take cards.
     */
    private void dealerTurn() {
        int dealerSum = deckForDealer.valueInHands();

        if (dealerSum < 17) {
            while (deckForDealer.valueInHands() < 17) {
                deckForDealer.takeForRound(deckForGame);
                System.out.println("Dealer opened a card. His cards:");
                System.out.println(deckForDealer);
                System.out.println(deckForDealer.valueInHands());
            }
        } else {
            System.out.println("Dealer's cards:");
            System.out.println(deckForDealer);
            System.out.println(deckForDealer.valueInHands());
        }
    }

    /**
     * Compares player and dealer hands to determine winner.
     */
    private void compareHands() {
        int playerSum = deckForPlayer.valueInHands();
        int dealerSum = deckForDealer.valueInHands();

        if (dealerSum > playerSum) {
            winDealer++;
            ScoreDisplay.displayYouLose(winPlayer, winDealer);
        } else if (dealerSum < playerSum) {
            winPlayer++;
            ScoreDisplay.displayYouWin(winPlayer, winDealer);
        } else {
            winDealer++;
            winPlayer++;
            ScoreDisplay.displayDraw(winPlayer, winDealer);
        }
    }

    /**
     * Deals initial two cards to both player and dealer.
     *
     * @param dealer dealer's deck.
     * @param player player's deck.
     */
    private void dealInitialCards(Hand player, Hand dealer) {
        for (int i = 0; i < 2; i++) {
            player.takeForRound(deckForGame);
            dealer.takeForRound(deckForGame);
        }
    }

    /**
     * Shows initial hands.
     *
     * @param player player's hand.
     * @param dealer dealer's hand.
     */
    private void showInitialHands(Hand player, Hand dealer) {
        System.out.println("Your hand:");
        System.out.println(player);
        System.out.println("Your score: " + player.valueInHands());

        System.out.println("\nDealer's hand:");
        System.out.println(dealer.getCard(0) + ", <Hidden Card>");
    }
}