package uno.player;


import java.util.Iterator;
import java.util.Random;
import uno.cards.Action_Card;
import uno.cards.Face_Card;
import uno.cards.Hand;
import uno.cards.UNO_Card;
import uno.uno_game.Game_Engine;



public class Player_MarinaM extends Player{

    // Name: Marina Amorim
    // Version: 11/22/2020
    private String name;



    public Player_MarinaM(Hand hand, Game_Engine dealer) {
        super(hand, dealer);
        // initializes the name as Marina Amorim to be used on whoAreYou()
        this.name = new String("M. Amorim");
    }
    public int play(UNO_Card card, boolean useColorInstead) {
        boolean current = false;
        int i;


        // check action cards
        if (card instanceof Action_Card) {
            Action_Card act = ((Action_Card) card);
            // check for draw 2
            if ((((Action_Card) card).getAction() == Action_Card.DRAW_2) && dealer.doIDraw()) {
                for(i=0; i < 2 ; i++){
                    // draw a card
                    UNO_Card myCard = dealer.drawCard();
                    // adds the card as the top card
                    hand.addCard(myCard);
                }
                dealer.notifyPass(this);
                return hand.numberOfCards();
            }


            // check for drawn 4 action card
            if ((((Action_Card) card).getAction() == Action_Card.WILD_DRAW_4 && dealer.doIDraw())) {
                {
                    for(i=0; i < 4 ; i++){
                        UNO_Card myCard = dealer.drawCard();
                        // adds the card as the top card
                        hand.addCard(myCard);
                    }
                    // passes
                    dealer.notifyPass(this);
                    return hand.numberOfCards();

                }
                // If the player does not have to drawn, check for the card color
            } else if((((Action_Card) card).getAction() == Action_Card.WILD_DRAW_4)) {
                // loops the hand and when the curr card has the same color, plays it
                for (UNO_Card curr : hand) {
                    if (dealer.getCurrent_Color() == curr.getColor()) {
                        dealer.discardCard(curr);
                        hand.remove(curr);
                        return hand.numberOfCards();
                    }
                }
            }
            // if the card on the center is wild, check for the color to play
            if (((Action_Card) card).getAction() == Action_Card.WILD) {
                for (UNO_Card curr : hand) {
                    if (dealer.getCurrent_Color() == curr.getColor()) {
                        dealer.discardCard(curr);
                        hand.remove(curr);
                        return hand.numberOfCards();
                    }
                }
            }


        }

        // check all cards in the hand
        for (UNO_Card curr : hand) {


            if (!current) {
                  // check for number first for green cards until the number of cards is four
//                if (card.getColor() == UNO_Card.GREEN && numberOfCards()> 4) {
//                    if ((curr instanceof Face_Card && card instanceof Face_Card) && (((Face_Card) curr).getFaceNumber() ==
//                            (((Face_Card) card).getFaceNumber()))) {
//                        dealer.discardCard(curr);
//                        hand.remove(curr);
//                        current = true;
//                    }
//                }
                // checking the color of card to play that card
                if (card.getColor() == curr.getColor()) {
                    dealer.discardCard(curr);
                    hand.remove(curr);
                    current = true;
                }
                // checking if the card in hand is an action card to play an action card if the color is not the same as that to be played
                else if ((curr instanceof Action_Card && card instanceof Action_Card) && (((Action_Card) curr).getAction() == (((Action_Card) card).getAction()))) {
                    dealer.discardCard(curr);
                    hand.remove(curr);
                    current = true;
                }
                // checking the card to see if the card in hand has the same number on the card is the same as the one to be played
                else if ((curr instanceof Face_Card && card instanceof Face_Card) && (((Face_Card) curr).getFaceNumber() ==
                        (((Face_Card) card).getFaceNumber()))) {
                    dealer.discardCard(curr);
                    hand.remove(curr);
                    current = true;
                }

            }
        }
        // if no card is found, check for a wild card to play
        if(!current){
            for (UNO_Card curr : hand) {
                if (curr.getColor()== Action_Card.BLACK) {
                    dealer.discardCard(curr);
                    hand.remove(curr);
                    current = true;
                }
            }
        }
        if (!current) {
            // if no moves are possible, pass
            UNO_Card myCard = dealer.drawCard();
            hand.addCard(myCard);
            //if you have three cards this will notify the dealer that the player has 3 cards
            dealer.notifyPass(this);
        }
        int j = this.hand.numberOfCards();
        if (j == 1) {
            this.dealer.notifyUNO(this);
        }

        if (j == 0) {
            this.dealer.notifyWon(this);
        }
        return hand.numberOfCards();

    }


    @Override
    public int scoreOnCards() {
        int score = 0;
        Iterator hand_iter = this.hand.iterator();

        while(true) {
            while(hand_iter.hasNext()) {
                UNO_Card curCard = (UNO_Card)hand_iter.next();
                if (curCard instanceof Face_Card) {
                    score += ((Face_Card)curCard).getFaceNumber();
                } else if (((Action_Card)curCard).getAction() != Action_Card.SKIP && ((Action_Card)curCard).getAction() != Action_Card.REVERSE && ((Action_Card)curCard).getAction() != Action_Card.DRAW_2) {
                    score += 50;
                } else {
                    score += 20;
                }
            }
            return score;
        }

    }



    public UNO_Card selectCardByColor(int color) {
        Iterator<UNO_Card> hand_iterator = this.hand.iterator();
        UNO_Card card2Play = null;

        while(hand_iterator.hasNext() && card2Play == null) {
            UNO_Card curCard = (UNO_Card)hand_iterator.next();
            if (curCard.getColor() == color) {
                card2Play = curCard;
            }
        }

        return card2Play;
    }

    @Override
    public int numberOfCards() {
        return super.numberOfCards();
    }

    @Override
    public String whoAreYou() {
        return name;
    }

    public static void main(String[] args) {

    }


}








