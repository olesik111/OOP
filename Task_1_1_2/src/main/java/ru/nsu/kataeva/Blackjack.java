package ru.nsu.kataeva;

import java.util.Scanner;

/**
 * Main Blackjack game class.
 */
public class Blackjack {
    int playFlag;
    int winPlayer = 0;
    int winDealer = 0;
    Deck deckForGame;
    Deck deckForPlayer;
    Deck deckForDealer;

    /**
     * Initializes a new Blackjack game.
     */
    public Blackjack() {
        this.deckForGame = new Deck();
        this.deckForPlayer = new Deck();
        this.deckForDealer = new Deck();
        deckForGame.createDeck();
        deckForGame.shuffle();
    }

    /**
     * Initializes a new Blackjack game with a specific deck for testing.
     *
     * @param deck the deck to use.
     */
    public Blackjack(Deck deck) {
        this.deckForGame = deck;
        this.deckForPlayer = new Deck();
        this.deckForDealer = new Deck();
    }

    /**
     * Game loop for one deck.
     */
    public void game() {
        playFlag = 1;

        Scanner userInput = new Scanner(System.in);

        while (playFlag == 1) {
            System.out.println();
            System.out.println("New round? y/n");

            String answer = userInput.next();

            if (answer.equals("y")) {
                roundPlay(userInput);
            } else if (answer.equals("n")) {
                System.out.println("Good game!");
                playFlag = 0;
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
            deckForPlayer = new Deck();
            deckForDealer = new Deck();

            dealInitialCards(deckForPlayer, deckForDealer);
            showInitialHands(deckForPlayer, deckForDealer);

            Decision playerDecision = new Decision();
            Decision forDealer = new Decision();

            if (deckForDealer.checkForWin(deckForDealer)) {
                if (deckForPlayer.checkForWin(deckForPlayer)) {
                    System.out.println("It's a draw!");

                    winDealer++;
                    winPlayer++;
                } else {
                    System.out.println("You lost! Dealer has Blackjack!");
                    System.out.println("Dealer's hand:");
                    System.out.println(deckForDealer);

                    winDealer++;
                }

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }

            if (deckForPlayer.checkForWin(deckForPlayer)) {
                System.out.println("You won!");

                winPlayer++;

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }

            playerDecision.playerDecision(deckForPlayer, deckForGame, userInput);

            int playerSum = deckForPlayer.cardsInHand(deckForPlayer);

            if (playerSum > 21) {
                System.out.println("You lost! Bust!");

                winDealer++;

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }

            if (playerSum == 21) {
                System.out.println("You won!");

                winPlayer++;

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }

            int dealerSum = deckForDealer.cardsInHand(deckForDealer);

            if (dealerSum < 17) {
                while (deckForDealer.cardsInHand(deckForDealer) < 17) {
                    deckForDealer.takeForRound(deckForGame);

                    System.out.println("Dealer opened a card. His cards:");
                    System.out.println(deckForDealer);
                    System.out.println(deckForDealer.cardsInHand(deckForDealer));
                }
            } else {
                forDealer.less17Decision(deckForPlayer, deckForDealer, winDealer, winPlayer);

                System.out.println("Dealer's cards:");
                System.out.println(deckForDealer);
                System.out.println(deckForDealer.cardsInHand(deckForDealer));
            }

            dealerSum = deckForDealer.cardsInHand(deckForDealer);

            if (dealerSum > 21) {
                System.out.println("You won!");

                winPlayer++;

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }
            if (dealerSum == 21) {
                System.out.println("You lost!");

                winDealer++;

                System.out.println("Score: " + winPlayer + ":" + winDealer);
                return;
            }

            forDealer.less17Decision(deckForPlayer, deckForDealer, winDealer, winPlayer);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("Final Score: " + winPlayer + ":" + winDealer);
            playFlag = 0;
        }
    }

    /**
     * Deals initial two cards to both player and dealer.
     *
     * @param dealer dealer's deck.
     * @param player player's deck.
     */
    private void dealInitialCards(Deck player, Deck dealer) {
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
    private void showInitialHands(Deck player, Deck dealer) {
        System.out.println("Your hand:");
        System.out.println(player);
        System.out.println("Your score: " + player.cardsInHand(player));

        System.out.println("\nDealer's hand:");
        System.out.println(dealer.getCard(0) + ", <Hidden Card>");
    }

}
