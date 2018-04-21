package sg.edu.nus.iss.vmcs.customer;

/**
 * This class is use for coin reception.
 * @author SPLE Team 06
 *
 */
public interface PaymentComponent {

	
	/**
	 * Commence receiving a series of Coins&#46;  To do this the Coin Receiver
	 * instructs the Coin Input Box to become activated&#46;  It also updates the Total
	 * Money Inserted Display on the Customer Panel.
	 */
//	public void startReceiver();
	
	/**
	 * When a coin is received the following will occur:
	 * <ol>
	 * <li>
	 * The Coin Input Box will be instructed to become deactivated&#46;
	 * </li>
	 * <li>
	 * The weight of the Coin will be send to the object Coin for it to 
	 * determine the denomination and value&#46;
	 * </li>
	 * <li>
	 * The Total Money Inserted Display will be updated&#46;
	 * </li>
	 * <li>
	 * If an invalid coin is entered, the Invalid Coin Display will be
	 * instructed to display INVALID COIN&#46;
	 * </li>
	 * <li>
	 * The Transaction Controller will be informed to process the current
	 * transaction based on the money received&#46;
	 * </li>
	 * </ol>
	 * @param paymentInfo of the coin received or card info&#46;
	 */
	public void makePayment(String paymentInfo);

	/**
	 * This method will activate the Coin Input Box so that further coins
	 * can be received.
	 */
	public void continueReceive();
	
	/**
	 * Instruct the Cash Store to update its totals and then re-set the Total
	 * Money Inserted Display to zero.
	 * @return return TRUE if cash has been stored, else return FALSE.
	 */
	public boolean storeCash();
	
	/**
	 * This method will deactivate the Coin Input Box in order to stop 
	 * receiving coins.
	 */
	public void stopReceive();
	
	/**
	 * This method handles the refunding of Coins entered so far to 
	 * the Customer.
	 */
	public void refundCash();
	
	/**
	 * This method reset the coin received input.
	 */
	public void resetReceived();
	
	/**
	 * This method activates or deactivates the Coin Input Box.
	 * @param active TRUE to activate, FALSE to deactivate the Coin Input Box.
	 */
	public void setActive(boolean active);
}
