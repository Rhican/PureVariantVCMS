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

/**
 * This control object implements the coin denomination selection
 * (button presses by the customer) on the Customer Panel when coins
 * are entered for a drink to be dispensed&#46; Action will be performed in
 * corresponding to the button pressed.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CoinInputListener implements ActionListener{
	CoinReceiver coinReceiver;
	
	/**
	 * This constructor creates an instance of the Coin Input Listener
	 * @param coinReceiver the Coin Receiver
	 */
	public CoinInputListener(CoinReceiver coinReceiver){
		this.coinReceiver=coinReceiver;
	}
	
	/**
	 * This method performs actions in response to the coin input button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		CoinButton coinButton=(CoinButton)ev.getSource();
		coinReceiver.receiveCoin(coinButton.getWeight());
	}
}//End of class CoinInputListener