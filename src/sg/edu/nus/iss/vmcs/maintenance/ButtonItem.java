/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.*;
import java.awt.event.*;

/**
 * This boundary object displays one item in a ButtonItemDisplay.&#46;
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class ButtonItem extends Panel {
	/**This constant attribute denotes the default length of the button label*/
	public final static int DEFAULT_LEN = 5;
	/**This constant attribute denotes the flow layout for button item content*/
	public final static int FLOW = 1;
	/**This constant attribute denotes the grid layout for button item content*/
	public final static int GRID = 2;
	/**This constant attribute denotes the default color of the button*/
	public final static Color DT_COLOR = Color.darkGray;
	/**This constant attribute denotes the activated color of the button*/
	public final static Color ACT_COLOR = Color.white;

	private Button btn;
	private Label value;

	/**
	 * This constructor create an instance of ButtonItem object.
	 * @param bn the button name.
	 * @param l the button length.
	 * @param lt the layout.
	 */
	public ButtonItem(String bn, int l, int lt) {
		btn = new Button(bn);
		value = new Label("          ");
		value.setBackground(Color.white);

		if (lt == FLOW)
			this.setLayout(new FlowLayout());
		else if (lt == GRID)
			this.setLayout(new GridLayout(1, 2));

		this.add(btn);
		this.add(value);
	}

	/**
	 * This method attach a listener to ButtonItem.
	 * @param l the ActionListener.
	 */
	public void addListener(ActionListener l) {
		btn.addActionListener(l);
	}

	/**
	 * This method set the action command of the Button.
	 * @param cmd the action command.
	 */
	public void setActionCommand(String cmd) {
		btn.setActionCommand(cmd);
	}

	/**
	 * This method set the active status of the ButtonItem.
	 * @param st the status of the ButtonItem.
	 */
	public void setActive(boolean st) {
		btn.setEnabled(st);
		value.setEnabled(st);
	}

	/**
	 * This method set the text background of the label of the ButtonItem.
	 * @param cl the color of the text background.
	 */
	public void setTextBackground(Color cl) {
		value.setBackground(cl);
	}

	/**
	 * This method clear the label of the ButtonItem and reset the text background.
	 */
	public void clear() {
		setTextBackground(DT_COLOR);
		value.setText("");
	}

	/**
	 * This method set the value of the label of the ButtonItem.
	 * @param vl the value of the label of the ButtonItem.
	 */
	public void setValue(int vl) {
		String sqt;

		sqt = String.valueOf(vl);
		value.setBackground(ACT_COLOR);
		value.setText(sqt);
	}

	/**
	 * This method set the value of the label of the ButtonItem.
	 * @param vl the value of the label of the ButtonItem.
	 */
	public void setValue(String vl) {
		value.setBackground(ACT_COLOR);
		value.setText(vl);
	}
}//End of class ButtonItem