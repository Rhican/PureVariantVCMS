/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This interface object is part of the Maintenance Panel&#46; It is used by the Maintainer to
 * display the total number of Coins of each denomination that are currently held in the
 * CashStore&#46; The object contains buttons to represent (and select) the Coin denominations
 * used in the vending machine&#46;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CoinDisplay extends Panel {
	public final static String TITLE = "Quantity of Coins Available";

	private StoreController storeCtrl;
	private MaintenanceController mCtrl;
	private ButtonItemDisplay bi;
	private int len;
	private int curIdx;

	/**
	 * This constructor creates an instance of CoinDisplay object.
	 * @param mctrl the MainController.
	 */
	public CoinDisplay(MaintenanceController mctrl) {
		mCtrl = mctrl;
		storeCtrl = mCtrl.getMainController().getStoreController();

		len = storeCtrl.getStoreSize(Store.CASH);
		StoreItem[] items = storeCtrl.getStoreItems(Store.CASH);

		bi = new ButtonItemDisplay(TITLE, items, len);

		bi.addListener(new CoinDisplayListener(mCtrl));

		bi.clear();

		this.add(bi);

	}

	/**
	 * This method activates the CoinDisplay if the parameter is TRUE&#46; Otherwise,
	 * the CoinDisplay is deactivated.
	 * @param st the active status of the CoinDisplay.
	 */
	public void setActive(boolean st) {
		bi.setActive(st);
	}

	/**
	 * Display the quantity of selected coin, clear other display.
	 * @throws VMCSException if fail to display quantity.
	 */
	public void displayQty(int idx, int qty) throws VMCSException {
		curIdx = idx;
		bi.clear();
		bi.displayQty(idx, qty);
	}

	/**
	 * Get the current displayed coin index&#46 This is used for updating when store MachinerySimulatorPanel is changed.
	 * Not required.
	 */
	public int getCurIdx() {
		return curIdx;
	}
}//End of class CoinDisplay