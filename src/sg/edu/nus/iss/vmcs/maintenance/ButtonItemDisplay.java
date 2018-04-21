/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.event.*;
import java.awt.*;

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This boundary object is to display stock information when a button is pressed.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ButtonItemDisplay extends Panel {
	private ButtonItem items[];
	private int len;
	private Label lb;

	/**
	 * This constructor creates an instance of ButtonItemDisplay object.
	 * @param title the title of the label.
	 * @param sitem the array of StoreObject.
	 * @param length the length of StoreObject array.
	 */
	public ButtonItemDisplay(String title, StoreItem sitem[], int length) {

		len = length;
		Panel tp1 = new Panel();

		lb = new Label(title);
		tp1.add(lb);

		this.setLayout(new GridLayout(0, 1));

		this.add(tp1);

		int i;
		items = new ButtonItem[len];

		for (i = 0; i < len; i++) {
			StoreObject ob = sitem[i].getContent();

			items[i] =
				new ButtonItem(
					ob.getName(),
					ButtonItem.DEFAULT_LEN,
					ButtonItem.GRID);
			this.add(items[i]);
		}
	}

	/**
	 * This method attaches a listener to the ButtonItemDisplay.
	 * @param l the ActionListener.
	 */
	public void addListener(ActionListener l) {
		int i;
		for (i = 0; i < len; i++) {
			items[i].addListener(l);
			items[i].setActionCommand(String.valueOf(i));
		}
	}

	/**
	 * This method activates the ButtonItemDisplay if the parameter is TRUE.
	 * Otherwise, the ButtonItemDisplay is deactivated.
	 * @param st the status of the ButtonItemDisplay.
	 */
	public void setActive(boolean st) {
		int i;
		lb.setEnabled(st);
		for (i = 0; i < len; i++) {
			items[i].setActive(st);
		}
	}

	/**
	 * This method clear the button items.
	 */
	public void clear() {
		int i;
		for (i = 0; i < len; i++) {
			items[i].clear();
		}
	}

	/**
	 * This method displays a quantity on to a specific ButtonItem&#46;
	 * @param idx the index of the specific button item&#46;
	 * @param qty the quantity of the specific item&#46;
	 * @throws VMCSException if idx is greater or equal than total 
	 * number of button item&#46;
	 */
	public void displayQty(int idx, int qty) throws VMCSException {
		if (idx >= len)
			throw new VMCSException("ButtomDisplay.setQty", "Index over flow");

		items[idx].setValue(qty);
	}
}//End of class ButtonItemDisplay.