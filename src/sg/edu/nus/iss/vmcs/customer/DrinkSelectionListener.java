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

/**
 * This control object monitors the selection of drink (drink brand) by a customer in the Customer Panel.
 * It performs an action in corresponding to the drink selected.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class DrinkSelectionListener implements ActionListener{
	private TransactionController txCtrl;
	private int drinkIdentifier=-1;
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the TransactionController&#46;
	 */
	public DrinkSelectionListener(TransactionController txCtrl, int drinkIdentifier){
		this.txCtrl=txCtrl;
		this.drinkIdentifier=drinkIdentifier;
	}
	
	/**
	 * This method performs actions in response to the drink button being pressed.
	 */
	public void actionPerformed(ActionEvent ev){
		Object obj=ev.getSource();
		Button btn=(Button)obj;
		btn.requestFocus();
		txCtrl.startTransaction(drinkIdentifier);
		btn.setBackground(Color.yellow);
	}
}//End of class DrinkSelectionListener