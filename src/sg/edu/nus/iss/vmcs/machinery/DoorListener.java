/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.machinery;

import java.awt.*;
import java.awt.event.*;

/**
 * This control object monitors the door status request (close door) when the Controller
 * uses the MachinerySimulatorPanel&#46; It performs an action in response to the request.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class DoorListener implements ItemListener {
	private MachineryController mctrl;

	/**
	 * This constructor creates an instance of DoorListener.
	 * @param mc the MachineryController.
	 */
	public DoorListener(MachineryController mc) {
		mctrl = mc;
	}
	
	/**
	 * This method performs actions in response to the door state being changed.
	 */
	public void itemStateChanged(ItemEvent e) {
		Checkbox cb;

		cb = (Checkbox) e.getSource();

		mctrl.setDoorState(cb.getState());
	}
}//End of class DoorListener