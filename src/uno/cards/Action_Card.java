package uno.cards;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : UNO_Model
//  @ File Name : Action_Card.java
//  @ Date : 4/1/2015
//  @ Author : S. Sigman
//
//




/** 
 * <p>The Action_Card class provides an UNO action card. The color of
 * the card may be one of the following: </p>
 * <ul>
 * <li>BLUE,</li> 
 * <li>RED, </li> 
 * <li>YELLOW,</li> 
 * <li>GREEN, or</li> 
 * <li>BLACK</li>
 * </ul>.
 * <p>Each Action_Card has one of the following actions:</p>
 * <ul>
 * <li>DRAW_2, </li>
 * <li>REVERSE, </li>
 * <li>SKIP,</li> 
 * <li>WILD, or</li>
 * <li>WILD_DRAW_4.</li>  
 * </ul> 
 * WILD and WILD_DRAW_4 cards must be 
 * BLACK. Only WILD and WILD_DRAW_4 cards may be BLACK.
 */
public class Action_Card extends UNO_Card
{
    /** 
     * Constant defining a DRAW 2 card.
     */
    public static int DRAW_2 = 0;
    
    /** 
     * Constant defining a REVERSE card.
     */
    public static int REVERSE = 1;
    
    /** 
     * Constant defining a SKIP card.
     */
    public static int SKIP = 2;
    
    /** 
     * Constant defining a WILD card.
     */
    public static int WILD = 3;
    
    /** 
     * Constant defining a WILD DRAW 4 card.
     */
    public static int WILD_DRAW_4 = 4;
    
    /** 
     * Specifies the type of action for an instance of the card.
     */
    private int action;
    
    /** 
     * Create an UNO Action Card.
     * 
     * @param clor The color of the card.
     * @param act The type of action for this instance.
     */
    public Action_Card(int clor, int act)
    {
        super(clor);
        action = act;
    }
    
    /** 
     * Returns the type of action this card represents.
     * 
     * @return The type of action represented by this card.
     */
    public int getAction()
    {
         return action;
    }
    
    /** 
     * Compares two action cards.  Two action cards are equal if their
     * actions are equal.  Otherwise, they are unequal.  
     * 
     * @param other The action card to compare.
     * @return 0 id the two cards are of the same action type. Otherswise,
     *         1 is returned.
     */
    public int compareTo(UNO_Card other)
    {
        if (!(other instanceof Action_Card))
          return 1;  
        Action_Card theOtherCard = (Action_Card)other;
        if(this.action == theOtherCard.getAction())
          return 0;
        else
          return 1;
    }
    
    /**
     * toString returns a string description of the card.
     */
    public String toString()
    {
        return "Action Card - color: " + getColorAsString() +
                              " action: " + getActionAsString();
    }
    
    /**
     * getActionAsString is a utility function that returns the 
     * action type of the card as a string.
     * 
     * @return The action type of the card rendered as a String.
     */
    private String getActionAsString()
    {
        String cardAction = "";
        if (action == DRAW_2)
          cardAction = "DRAW 2";
        else if(action == REVERSE)
          cardAction = "REVERSE";
        else if(action == SKIP)
          cardAction = "SKIP";
        else if (action == WILD) 
          cardAction = "WILD";
        else if (action == WILD_DRAW_4)
          cardAction = "WILD DRAW 4";
        return cardAction;
    }
}
