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
 * This control object monitors the Terminate Button on the Customer Panel and informs the Transaction
 * Controller when it is pressed.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class TerminateButtonListener implements ActionListener{
	private TransactionController txCtrl;
	
	/**
	 * This constructor creates an instance of the Terminate Button Listener.
	 * @param txCtrl the Transaction Controller.
	 */
	public TerminateButtonListener(TransactionController txCtrl){
		this.txCtrl=txCtrl;
	}
	
	/**
	 * This method performs actions in response to the terminate button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		txCtrl.cancelTransaction();
	}
}//End of class TerminateButtonListener