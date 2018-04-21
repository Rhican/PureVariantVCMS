/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.machinery;

import java.awt.event.*;
import java.awt.*;

/**
 * This control object monitors data entered into a StoreViewer, when the Controller uses
 * the MachinerySimulatorPanel&#46; When data is entered, it initiates the process to store the data.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
import sg.edu.nus.iss.vmcs.store.StoreController;

public class StoreViewerListener implements ActionListener {
	private int type;
	private int item;
	private StoreController storeCtrl;

	/**
	 * This constructor creates an instance of StoreViewerListener object.
	 * @param type the type of store.
	 * @param item the store item.
	 * @param sctrl the StoreController.
	 */
	public StoreViewerListener(int type, int item, StoreController sctrl) {
		this.type = type;
		this.item = item;
		storeCtrl = sctrl;
	}
	
	/**
	 * This method performs actions in response to the data being entered to the store viewer.
	 */
	public void actionPerformed(ActionEvent e) {
		TextField vf;
		int qty;
		String sqty;

		vf = (TextField) e.getSource();
		sqty = vf.getText();
		qty = Integer.parseInt(sqty);
		storeCtrl.changeStoreQty(type, item, qty);
	}
}//End of class StoreViewerListener