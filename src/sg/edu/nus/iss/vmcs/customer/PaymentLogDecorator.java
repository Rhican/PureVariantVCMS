package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.log.VMCSLogger;

/**
 * This class is used for coin reception with audit trail.
 * @author SPLE Team 04
 *
 */
public class PaymentLogDecorator extends PaymentDecorator {
	
	private VMCSLogger logger = new VMCSLogger();
	
	/**
	 * Constructor to create object.
	 * @param component is CoinReceptionComponent
	 */
	public PaymentLogDecorator(PaymentComponent component) {
		super(component);
	}
	
	/**
	 * Commence receiving a series of Coins&#46;  To do this the Coin Receiver
	 * instructs the Coin Input Box to become activated&#46;  It also updates the Total
	 * Money Inserted Display on the Customer Panel.
	 */
//	public void startReceiver() {
//		logger.debug("start receiver.");
//		super.startReceiver();
//	}
	
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
	 * @param weight the weight of the coin received&#46;
	 */
	public void makePayment(String paymentInfo){
		logger.debug("receive coin paymentInfo="+paymentInfo);
		super.makePayment(paymentInfo);
	}

	/**
	 * This method will activate the Coin Input Box so that further coins
	 * can be received.
	 */
	public void continueReceive(){
		logger.debug("continue receive.");
		super.continueReceive();
	}
	
	/**
	 * Instruct the Cash Store to update its totals and then re-set the Total
	 * Money Inserted Display to zero.
	 * @return return TRUE if cash has been stored, else return FALSE.
	 */
	public boolean storeCash(){
		logger.debug("store cash.");
		return super.storeCash();
	}
	
	/**
	 * This method will deactivate the Coin Input Box in order to stop 
	 * receiving coins.
	 */
	public void stopReceive() {
		logger.debug("stop receive.");
		super.stopReceive();
	}
	
	/**
	 * This method handles the refunding of Coins entered so far to 
	 * the Customer.
	 */
	public void refundCash() {
		logger.debug("refund cash.");
		super.refundCash();
	}
	
	/**
	 * This method reset the coin received input.
	 */
	public void resetReceived(){
		logger.debug("reset received.");
		super.resetReceived();
	}
	
	/**
	 * This method activates or deactivates the Coin Input Box.
	 * @param active TRUE to activate, FALSE to deactivate the Coin Input Box.
	 */
	public void setActive(boolean active){
		logger.debug("set active active="+active);
		super.setActive(active);
	}
}
