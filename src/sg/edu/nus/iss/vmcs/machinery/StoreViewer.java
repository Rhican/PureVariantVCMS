/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.machinery;

import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.*;

/**
 * This boundary object displays the contents of a store (DrinksStore or CashStore) and
 * allows them to be changed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class StoreViewer extends Panel {
	private LabelledDisplay viewItems[];
	private StoreController storeCtrl;
	
	private int type;
	
	/**This constant attribute holds the cash view title*/
	public static final String CASH_VIEW_TITLE = "Quantity of Coins Available";
	/**This constant attribute holds the drink view title*/
	public static final String DRINK_VIEW_TITLE = "Quantity of Drinks Available";

	/**
	 * This constructor creates an instance of StoreViewer object.
	 * @param ti the type of the store.
	 * @param sctrl the StoreController.
	 */
	public StoreViewer(int ti, StoreController sctrl) {
		
		storeCtrl = sctrl;
		type = ti;
		
		String title = null;
		switch (type) {
		case Store.CASH:
			title = CASH_VIEW_TITLE;
			break;
		case Store.DRINK:
			title = DRINK_VIEW_TITLE;
			break;
		}

		Panel pl = new Panel(new FlowLayout(FlowLayout.LEFT));
		pl.add(new Label(title));

		int sSize = storeCtrl.getStoreSize(type);
		viewItems = new LabelledDisplay[sSize];

		StoreItem[] storeItem = storeCtrl.getStoreItems(type);
		this.setLayout(new GridLayout(0, 1));
		this.add(pl);

		for (int i = 0; i < sSize; i++) {
			String name = storeItem[i].getContent().getName();
			viewItems[i] = new LabelledDisplay(name,
						LabelledDisplay.DEFAULT,
						LabelledDisplay.GRID);
			viewItems[i].addListener(
                        new StoreViewerListener(type, i, storeCtrl));
			this.add(viewItems[i]);
		}
		
		update();
	}

	/**
	 * Update the display fields with the data provided.
	 */
	public void update () {
		StoreItem[] storeItem = storeCtrl.getStoreItems(type);
		for (int i = 0; i < storeItem.length; i++) {
			int val = storeItem[i].getQuantity();
			String sval = String.valueOf(val);
			viewItems[i].setValue(sval);
		}
	}

	/**
	 * Update the display fields with data provided.
	 * @param idx the index of the store item.
	 * @param qty the quantity of the store item.
	 * @throws VMCSException if fail index is greater or equal to store size.
	 */
	public void update(int idx, int qty) throws VMCSException {
		int sSize = storeCtrl.getStoreSize(type);
		if (idx >= sSize)
			throw new VMCSException("StoreViewer.update", "index overflow");
		viewItems[idx].setValue(qty);
	}

	/**
	 * This method close down the store viewer.
	 */
	public void closeDown() {
	}

	/**
	 * This method activates the StoreDisplay if the parameter is TRUE&#46; 
	 * Otherwise, the StoreDisplay is deactivated.
	 * @param state TRUE to enabled the store viewer, otherwise, disabled the store viewer.
	 */
	public void setActive(boolean state) {
		this.setEnabled(state);
	}
}//End of class StoreViewer