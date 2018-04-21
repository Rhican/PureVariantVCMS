/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This control object monitors the Transfer Cssh Button and performs the required action
 * in response to the button being pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class TransferCashButtonListener implements ActionListener {
	private MaintenanceController mctrl;
	
	/**
	 * This constructor creates an instance of TransferCashButtonListener object.
	 * @param mc the MaintenanceController.
	 */
	public TransferCashButtonListener(MaintenanceController mc) {
		mctrl = mc;
	}
	
	/**
	 * This method performs actions in response to the Transfer Cash Button being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		mctrl.transferAll();
	}
}