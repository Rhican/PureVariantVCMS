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
 * This control object monitors the events in the Price Display and performs actions
 * in response to data being entered into the text field.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class PriceDisplayListener implements ActionListener {
	private MaintenanceController mctrl;
	private int type = Store.DRINK;

	/**
	 * This constructor creates an instance of PriceDisplayListener object.
	 * @param mc the MaintenanceController.
	 */
	public PriceDisplayListener(MaintenanceController mc) {
		mctrl = mc;
	}
	
	/**
	 * This constructor creates an instance of PriceDisplayListener object.
	 * @param mc the MaintenanceController.
	 */
	public PriceDisplayListener(MaintenanceController mc, int type) {
		mctrl = mc;
		this.type = type;
	}
	
	/**
	 * This method performs actions in response to price being entered.
	 */
	public void actionPerformed(ActionEvent e) {
		TextField txt;

		String sp;
		int ip;

		txt = (TextField) e.getSource();
		sp = txt.getText();

		ip = Integer.parseInt(sp);

		mctrl.setPrice(ip, type);
	}
}//End of class PriceDisplayListener