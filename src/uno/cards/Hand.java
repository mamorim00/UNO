package uno.cards;

import cs.cscollections.lists.UnorderedList;
import java.util.Iterator;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : UNO_Model
//  @ File Name : Hand.java
//  @ Date : 4/5/2015
//  @ Author : S. Sigman
//
//


/**
 * <p>The Hand class provides a simple Hand mechnaism for an UNO
 * game.  The cards in a hand may be accessed using either
 * the iterator method or using a for-each loop.</p>  
 * 
 * <p><b>NOTE:</b> It is usefull to search a Hand using the iterator
 * (or for-each loop) to find a particualr card.  However,
 * in order to remove that card from the Hand you must use
 * the remove(target) method.  For example: To find the first
 * yellow card in a hand: <br>
 * <pre><code>
 * Hand myHand = ...
 * UNO_Card firstYellow = null;
 * boolean found = false;
 * for(UNO_Card card: myHand)
 * {
 *     if (!found &amp;&amp; card.getColor()== UNO_Card.YELLOW)
 *     {
 *         firstYellow = card;
 *         myHand.remove(card);
 *         found = true;
 *     }
 *  }
 *  </code>
 *  </pre>
 */

public class Hand implements Iterable<UNO_Card>
{
    // Storage for the cards in the hand.
	private UnorderedList<UNO_Card> cards;
	
	/**
	 * This method constructs an empty hand of cards.
	 */
	public Hand()
	{
	    cards = new UnorderedList();
	}
	
	/**
	 * addCard adds the specified card as the top card in the Hand.
	 * Hands of cards are unordered.
	 * 
	 * @param card The card to add to the hand.
	 */
	public void addCard(UNO_Card card)
	{
	    cards.addToFront(card);
	}
	
	/**
	 * This method returns the first or top card in the Hand.
	 * 
	 * @return The top card in the hand.
	 */
	public UNO_Card getFirstCard()
	{
	   return cards.first();
	}
	
	/**
	 * This method returns the bottom or last card in the Hand.
	 * 
	 * @return The bottom (last) card in the Hand.
	 */
	public UNO_Card getLastCard()
	{
	   return cards.last();
	}
	
	/**
	 * This method removes the specified card from the hand.  The
	 * hand should be checked by using the iterator method or a 
	 * for-each loop to determine the presence of the card before
	 * calling this method.  If the card is not in the hand, remove
	 * returns a null reference.  If the hand contains multiple
	 * instances of the specified card, only the first instance is
	 * removed from the list.
	 * 
	 * @param card The card to remove from the hand.
	 * @return The card reomved from the hand.
	 */
	public UNO_Card remove(UNO_Card card) 
	{
	    return cards.remove(card);
	}
	
	/**
	 * This method returns the number of cards in the player's
	 * hand.
	 * 
	 * @return The number of cards in the hand.
	 */
	public int numberOfCards()
	{
	    return cards.size();
	}
	
	/**
	 * This method returns an iterator over UNO Cards.  It may be used
	 * to explore the cards in the hand and provides the basis for 
	 * processing an hand using an for-each loop.
	 * 
	 * @return An iterator over the UNO Cards in the Hand.
	 */
	public Iterator<UNO_Card> iterator()
	{
	   return cards.iterator();
	}
}
