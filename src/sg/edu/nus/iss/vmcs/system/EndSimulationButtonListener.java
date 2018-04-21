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
 * This control object monitors the End Simulation Button and performs actions in response to the 
 * Button being pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class EndSimulationButtonListener implements ActionListener {
	private MainController mctrl;
	
	/**
	 * This constructor creates an instance of the EndSimulationButtonListener object.
	 * @param mc the MainController.
	 */
	public EndSimulationButtonListener(MainController mc) {
		mctrl = mc;
	}
	
	/**
	 * This method performs actions in response to the End Simulation Button 
	 * being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		mctrl.closeDown();
	}
}//End of class EndSimulationButtonListener