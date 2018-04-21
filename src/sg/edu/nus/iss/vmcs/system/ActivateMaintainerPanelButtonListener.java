/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

package sg.edu.nus.iss.vmcs.system;

import java.awt.event.*;

/**
 * This control object monitors the ActivateMaintainerPanelButton and performs actions in
 * response to the button being pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ActivateMaintainerPanelButtonListener implements ActionListener {

	private SimulationController ctrl;

	/**
	 * This constructor creates an instance of the ActivateMaintainerPanelButtonListener
	 * @param ct the SimulationController
	 */
	public ActivateMaintainerPanelButtonListener(SimulationController ct) {
		ctrl = ct;
	}
	
	/**
	 * This method performs actions in response to the Activate Maintainer Panel Button 
	 * being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		ctrl.setupMaintainer();
	}
}//End of class ActivateMaintainerPanelButtonListener