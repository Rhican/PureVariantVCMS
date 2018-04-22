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
import sg.edu.nus.iss.vmcs.util.*;

/**
 * This interface object is part of the MaintenancePanel&#46; It is used by the Maintainer to 
 * display the total number of cans of each DrinksBrand that are currently held in the
 * DrinksStore&#46; This object contains buttons which represent (and to select) the DrinksBrand
 * served by the vending machine.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ItemDisplay extends Panel {
	public final static String TITLE_DRINK = "Quantity of Drinks Available";
	public final static String TITLE_SNACK = "Quantity of Snacks Available";

	private StoreController storeCtrl;
	private MaintenanceController mCtrl;
	private ButtonItemDisplay bi;
	private LabelledDisplay price;
	private int curIdx; //current displayed item index;
	private int type = Store.DRINK;
	/**
	 * This constructor creates an instance of the DrinkDisplay object.
	 * @param mctrl the MaintenanceController.
	 */
	public ItemDisplay(MaintenanceController mctrl, int type) {
		mCtrl = mctrl;
		this.type = type;
		storeCtrl = mCtrl.getMainController().getStoreController();

		this.setLayout(new BorderLayout());
		int len;
		len = storeCtrl.getStoreSize(this.type);
		StoreItem[] items = storeCtrl.getStoreItems(this.type);

		bi = new ButtonItemDisplay((type == Store.DRINK) ? TITLE_DRINK : TITLE_SNACK, items, len);

		bi.addListener(new ItemDisplayListener(mCtrl, type));
		bi.clear();
		price = new LabelledDisplay("Brand Price", 4, LabelledDisplay.FLOW);

		PriceDisplayListener pdl;

		pdl = new PriceDisplayListener(mCtrl);
		price.addListener(pdl);
		Panel tp = new Panel();
		tp.setLayout(new FlowLayout(FlowLayout.CENTER));
		tp.add(bi);
		curIdx = 0;
		this.add("Center", tp);
		this.add("South", price);
		price.setEnabled(false);
	}

	/**
	 * This method returns the LabelledDisplay of the price.
	 * @return LabelledDisplay of the price.
	 */
	public LabelledDisplay getPriceDisplay() {
		return price;
	}

	/**
	 * This method set the active status of the LabelledDisplay and ButtonItemDisplay.
	 * @param st the active status of the LabelledDisplay and ButtonItemDisplay.
	 */
	public void setActive(boolean st) {
		price.setActive(st);
		bi.setActive(st);
	}

	/**
	 * This method displays the stock value received for the currently selected brand.
	 * This display will be done on a text field associated with the DrinkDisplay object.
	 * @param idx the index of the stock.
	 * @param qty the quantity of the stock.
	 * @throws VMCSException if fail to display quantity.
	 */
	public void displayQty(int idx, int qty) throws VMCSException {
		curIdx = idx;
		bi.clear();
		price.setEnabled(true);
		bi.displayQty(idx, qty);
	}

	/**
	 * This method returns the current index.
	 * @return the current index.
	 */
	public int getCurIdx() {
		return curIdx;
	}
	
	public boolean isEmpty() {
		return bi.Count() == 0;
	}
}//End of class DrinkDisplay