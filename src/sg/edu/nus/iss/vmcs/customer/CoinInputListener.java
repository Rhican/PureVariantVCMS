/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

/**
 * This control object implements the coin denomination selection
 * (button presses by the customer) on the Customer Panel when coins
 * are entered for a drink to be dispensed&#46; Action will be performed in
 * corresponding to the button pressed.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CoinInputListener implements ActionListener{
	private TransactionController txController;
	
	private VariantPointConstants VariantPointConstants = new VariantPointConstants();
	/**
	 * This constructor creates an instance of the Coin Input Listener
	 * @param coinReceiver the Coin Receiver
	 */
	public CoinInputListener(TransactionController txController){
		this.txController = txController;
	}
	
	/**
	 * This method performs actions in response to the coin input button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		CoinButton coinButton=(CoinButton)ev.getSource();
		if (txController.getPaymentDecorator() == null) {
			
			/**
			 * PV:IFCOND(pv:hasFeature('LogPayment'))
			 */
			txController.setPaymentDecorator(new PaymentLogDecorator(new CoinReceiver(txController)));
			/**
		     * PV:ELSECOND 
		     */
			txController.setPaymentDecorator(new PaymentDecorator(new CoinReceiver(txController)));
			/**
		     * PV:ENDCOND 
		     */
		}
		txController.getPaymentDecorator().makePayment(String.valueOf(coinButton.getWeight()));
	}
}//End of class CoinInputListener