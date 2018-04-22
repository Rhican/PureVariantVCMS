/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.event.*;

import sg.edu.nus.iss.vmcs.store.Store;

import java.awt.*;

/**
 * This control object monitors the events in the DrinkDisplay and performs actions
 * in response to the buttons being pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ItemDisplayListener implements ActionListener {
	private MaintenanceController mCtrl;
	private int type = Store.DRINK;
	/**
	 * This constructor creates an instance of the DrinkDisplayListener object.
	 * @param mctrl the MaintenanceController
	 */
	public ItemDisplayListener(MaintenanceController mctrl) {
		mCtrl = mctrl;
	}
	
	/**
	 * This constructor creates an instance of the DrinkDisplayListener object.
	 * @param mctrl the MaintenanceController
	 */
	public ItemDisplayListener(MaintenanceController mctrl, int type) {
		mCtrl = mctrl;
		this.type = type;
	}
	
	/**
	 * This method performs actions in response to the drinks display button being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		Button btn;
		String cmd;
		int idx;

		btn = (Button) e.getSource();
		cmd = btn.getActionCommand();
		idx = Integer.parseInt(cmd);

		mCtrl.displayItems(idx, type);
	}
}//End of class DrinkDisplayListener