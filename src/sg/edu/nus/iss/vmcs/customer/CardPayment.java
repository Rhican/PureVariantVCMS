/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

/**
 * This control object manages the card payment.
 * @author SPLE Team 04
 */
public class CardPayment implements PaymentController {
	private TransactionController txCtrl;
	
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the transaction controller.
	 */
	public CardPayment(TransactionController txCtrl){
		this.txCtrl=txCtrl;
	}
	
	public void startReceiver(){
		txCtrl.getCustomerPanel().setCardDetectorActive(true);
		txCtrl.getCustomerPanel().setTotalMoneyInserted(0);
	}
	
	public void makePayment(String payInfo){
		boolean isPaymentSuccess  = txCtrl.getMainController().getCardPaymentCtrl().makePaymentTransaction(txCtrl.getPrice(), payInfo);
		if(!isPaymentSuccess){
			txCtrl.getCustomerPanel().displayInvalidCoin("Payment Unsuccessful");
			txCtrl.getCustomerPanel().setChange("");
			//txCtrl.getCustomerPanel().setCoinInputBoxActive(false);
		}
		else{
			txCtrl.getCustomerPanel().hideInvalidCoin();
			txCtrl.processMoneyReceived(txCtrl.getPrice());
		}
	}

	public void continueReceive(){
		// no action
	}
	
	public boolean storeCash(){
		// no action
		return true;
	}
	
	public void stopReceive(){
		// no action
	}
	
	/**
	 * This method handles the refunding of Coins entered so far to 
	 * the Customer.
	 */
	public void refundCash(){
		// no action
	}
	
	/**
	 * This method reset the coin received input.
	 */
	public void resetReceived(){
		// no action
	}
	
	/**
	 * This method activates or deactivates the Coin Input Box.
	 * @param active TRUE to activate, FALSE to deactivate the Coin Input Box.
	 */
	public void setActive(boolean active){
		txCtrl.getCustomerPanel().setCardDetectorActive(active);
		if (VariantPointConstants.vCashPayment) {
			txCtrl.getCustomerPanel().setCoinInputBoxActive(!active);
		}
	}
}