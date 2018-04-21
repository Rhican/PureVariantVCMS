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
 * This control object monitors the Total Cash Button on the MaintenancePanel and informs
 * the MaintenanceController when it is pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class TotalCashButtonListener implements ActionListener {
	MaintenanceController mctrl;

	/**
	 * This constructor creates an instance of TotalCashButtonListener.
	 * @param mc the MaintenanceController.
	 */
	public TotalCashButtonListener(MaintenanceController mc) {
		mctrl = mc;
	}
	
	/**
	 * This method performs actions in response to the Total Cash Button being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		mctrl.getTotalCash();
	}
}//End of class TotalCashButtonListener