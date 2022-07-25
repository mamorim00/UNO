package uno.cards;

import cs.cscollections.lists.UnorderedList;
import java.util.Iterator;
import java.util.Random;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : UNO_Model
//  @ File Name : Deck.java
//  @ Date : 4/5/2015
//  @ Author : S. Sigman
//
//



/**
 * <p>Deck provides a standard deck of 108 UNO cards.  The deck is initially
 * shuffled, but may be reshuffled at any time. Functionality for the deck is
 * provided as follows:</p>
 * <ul>
 *  <li>shuffle() : shuffle the deck.</li>
 *  <li>dealCard() : deals the top card from the deck. The card is removed from
 *                 the deck.</li>
 *  <li>iterator() : returns an iterator over the deck that returns UNO Cards.</li>
 *  </ul>
 *  
 *  <p>The cards in the deck may also be processed using a for-each loop.  For
 *  example, the cards in the deck can be printed as follows:</p>
 *  <pre><code>
 *  Deck deck = new Deck(); // creates a shuffled deck
 *  for(UNO_Card card: deck)
 *     System.out.print(card);
 *  </code></pre>
 *  
 * <p>The load Deck method generates a deck of 108 UNO cards.  The number of cards is as
 * follows: </p>
 * <ul>
 *  <li>4 zeros one each of RED, BLUE, GREEN and YELLOW</li>
 *  <li>2 of each of the other face cards (1 - 9) for each color RED, BLUE, GREEN, YELLOW</li>
 *  <li>2 each of the action cards DRAW 2, SKIP, and REVERSE in each of the colors RED,
 *      BLUE, GREEN, YELLOW</li>
 *  <li>4 WILD cards</li>
 *  <li>4 WILD DRAW 4 cards</li>
 *  </ul>
 *  
 */
public class Deck implements Iterable<UNO_Card>
{
	private UnorderedList<UNO_Card> cards;  // The list of cards
	
	/**
	 * Constructs a new shuffled deck of 108 UNO_Cards.
	 */
	public Deck()
    {
        // make the cards object
        cards = new UnorderedList();
        
        // load the cards into the deck
        loadDeck();
        
        // shuffle the deck
        shuffle();
	
	}
	
	/**
	 * This method uses the Fischer-Yates algorithm to shuffle the UNO Deck.
	 * 
	 * @see <a href='http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle'>
	 *     Fischer-Yates Shuffle</a>
	 */
	public void shuffle()
	{
	    int numCards = cards.size();
	    
	    // Create a Random number generator
	    Random rand = new Random();
	    
	    // Choose a card randomly chosen between the top of the deck and
	    // the bottom of the deck. Exchange the randomly chosen card with
	    // bottom card.  Consider the card at the bottom shuffled into its
	    // final position and repeat the process using the cards from the
	    // top of the deck to the next to bottom card.  Continue in this
	    // fashion until you shuffle the top two cards.
	    for (int i=numCards-1; i > 0; i--)
	    {
	        // choose a random card
	        int randCardIndex = rand.nextInt(i);
	        
	        // remove the random card and the last card not shuffled
	        UNO_Card hold = cards.remove(randCardIndex);
	        UNO_Card lastNotShuffled = cards.remove(i-1); // last position changed on removal
	        
	        // exchange the two cards
	        cards.add(lastNotShuffled, randCardIndex);
	        cards.add(hold,i);
	    }
	
	}
	
	/**
	 * This method deals one card from the top of the deck.  The card is
	 * reomved from the deck and returned by the method.
	 * 
	 * @return The top card on the deck.
	 */
	public UNO_Card dealCard()
	{
	   return cards.removeFirst();
	}
	
	/**
	 * This method allows a card to be added to the top of the UNO Deck.  
	 * It is intended to be used after one or more cards have been removed
	 * from the deck and one needs to be returned.  For instance, 
	 * in an UNO game after the cards are dealt to players, the deck 
	 * typically becomes the discard pile.  This method allows discarded
	 * cards to be returned to the deck.
	 * 
	 * @param card The card to return to the deck.
	 */
	public void addCard(UNO_Card card) 
	{
	    cards.addToFront(card);
	}
	
	/**
	 * This method returns the number of cards in the Deck.
	 * 
	 * @return The number of cards in the Deck.
	 */
	public int numberOfCards()
	{
	    return cards.size();
	}
	
	/**
	 * This method returns an iterator over UNO_Cards that may be 
	 * used to iterate over the Deck of cards.  Think of this 
	 * as thumbing through the cards.
	 * 
	 * @return An iterator over the deck of UNO_Cards.
	 */
	public Iterator<UNO_Card> iterator()
	{
	    return cards.iterator();
	}
	
	// Utility methods
	
	// The load Deck method generates a deck of 108 UNO cards.  The number of cards is as
	// follows:
	//  - 4 zeros one each of RED, BLUE, GREEN and YELLOW
	//  - 2 of each of the other face cards (1 - 9) for each color RED, BLUE, GREEN, YELLOW
	// - 2 each of the action cards DRAW 2, SKIP, and REVERSE in each of the colors RED,
	//      BLUE, GREEN, YELLOW
	//  - 4 WILD cards
	//  - 4 WILD DRAW 4 cards
	//
	private void loadDeck()
	{
	    // Generate the zeros for each color
	    for (int clr=UNO_Card.BLUE; clr < UNO_Card.BLACK; clr++)
	    {
	        Face_Card zero = new Face_Card(clr, 0);
	        cards.addToFront(zero);
	    }
	    
	    // Generate the remaining Face Cards - two of each number for each color
	    for (int clr=UNO_Card.BLUE; clr < UNO_Card.BLACK; clr++)
	    {
	        for (int num = 1; num < 10; num ++)
	        {
	            Face_Card card = new Face_Card(clr, num);
	            Face_Card card2 = new Face_Card(clr, num);
	            cards.addToFront(card);
	            cards.addToFront(card2);
	        }
	    }
	    
	    // Generate the Action Cards - two of each for each color
	    for (int clr=UNO_Card.BLUE; clr < UNO_Card.BLACK; clr++)
	    {
	        for (int act = Action_Card.DRAW_2; act < Action_Card.WILD; act++)
	        {
	            Action_Card card = new Action_Card(clr, act);
	            Action_Card card2 = new Action_Card(clr, act);
	            cards.addToFront(card);
	            cards.addToFront(card2);
	        }
	    }
	    
	    // Generate the WILD and WILD DRAW 4 cards - two of each
	    for (int noWild = 0; noWild < 4; noWild++)
	    {
	        Action_Card wCard = new Action_Card(UNO_Card.BLACK, Action_Card.WILD);
	        Action_Card w4Card = new Action_Card(UNO_Card.BLACK, Action_Card.WILD_DRAW_4);
	        cards.addToFront(wCard);
	        cards.addToFront(w4Card);
	    }
     }
}
