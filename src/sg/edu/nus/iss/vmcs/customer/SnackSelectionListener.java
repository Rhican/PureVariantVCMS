/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.store.Store;

/**
 * This control object monitors the selection of snack (snack brand) by a customer in the Customer Panel.
 * It performs an action in corresponding to the snack selected.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class SnackSelectionListener implements ActionListener{
	private TransactionController txCtrl;
	private int snackIdentifier=-1;
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the TransactionController&#46;
	 */
	public SnackSelectionListener(TransactionController txCtrl, int snackIdentifier){
		this.txCtrl=txCtrl;
		this.snackIdentifier=snackIdentifier;
	}
	
	/**
	 * This method performs actions in response to the snack button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		Object obj=ev.getSource();
		Button btn=(Button)obj;
		btn.requestFocus();
		txCtrl.startTransaction(snackIdentifier, Store.SNACK);
		btn.setBackground(Color.yellow);
	}
}//End of class SnackSelectionListener