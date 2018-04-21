/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.event.*;

/**
 * This control object monitors the Exit Button and performs actions in response to the
 * button being pressed.
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ExitButtonListener implements ActionListener {
	private MaintenanceController mctrl;

	/**
	 * This constructor creates an instance of ExitButtonListener object.
	 * @param mc the MaintenanceController.
	 */
	public ExitButtonListener(MaintenanceController mc) {
		mctrl = mc;
	}
	
	/**
	 * This method performs actions in response to the Exit Button being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		mctrl.logoutMaintainer();
	}
}//End of class ExitButtonListener