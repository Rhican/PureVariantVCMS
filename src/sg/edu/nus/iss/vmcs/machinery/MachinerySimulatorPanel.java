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

import sg.edu.nus.iss.vmcs.store.*;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

/**
 * This panel simulates the physical actions of the maintainer&#46;
 * It enables the user to:
 * <br>
 * 1- Display and enter new values for the number of cans of each DrinksBrand held by the
 * DrinksStore;
 * <br>
 * 2- Display and enter new values for the number of coins of each denomination held by
 * the CashStore;
 * <br>
 * 3- Display whether the vending machine Door is unlocked, and enable it to be locked.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class MachinerySimulatorPanel extends Dialog {
	private static final String TITLE = "Machinery Panel";

	private StoreViewer cashDisplay;
	private StoreViewer drinksDisplay;
	private Checkbox doorDisplay;
	private StoreController storeCtrl;
	private MachineryController machineryCtrl;

	/**
	 * This constructor creates an instance of MachinerySimulatorPanel.
	 * @param fr the parent frame.
	 * @param machCtrl the MachineryController.
	 */
	public MachinerySimulatorPanel(Frame fr, MachineryController machCtrl) {
		super(fr, TITLE, false);

		machineryCtrl = machCtrl;
		storeCtrl = machineryCtrl.getMainController().getStoreController();

		Label lb = new Label(TITLE);
		lb.setFont(new Font("Helvetica", Font.BOLD, 24));
		lb.setAlignment(Label.CENTER);

		cashDisplay = new StoreViewer(Store.CASH, storeCtrl);
		drinksDisplay = new StoreViewer(Store.DRINK, storeCtrl);

		Panel tp = new Panel();
		tp.setLayout(new GridLayout(0, 1));
		tp.add(cashDisplay);
		tp.add(drinksDisplay);

		Panel dp = new Panel();
		doorDisplay = new Checkbox();
		doorDisplay.addItemListener(new DoorListener(machineryCtrl));
		doorDisplay.setLabel("Door Locked");
		dp.add(doorDisplay);

		this.setLayout(new BorderLayout());
		this.add("North", lb);
		this.add("Center", tp);
		this.add("South", dp);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				machineryCtrl.closeMachineryPanel();
			}
		});
	}

	/**
	 * Display the MachinerySimulatorPanel&#46; This will achieved by displaying
	 * the frame of the panel and then sending messages to its constituent objects
	 * instructing them to:
	 * <br>
	 * 1- Display themselves;
	 * <br>
	 * 2- Set initial values;
	 * <br>
	 * 3- Deactivate themselves.
	 */
	public void display() {
		this.setVisible(true);
	}

	/**
	 * Remove the panel objects and MachinerySimulatorPanel from the display.
	 */
	public void closeDown() {
		dispose();
	}

	/**
	 * This method returns the CashDisplay:StoreViewer.
	 * @return the CashDisplay:StoreViewer.
	 */
	public StoreViewer getCashStoreDisplay() {
		return cashDisplay;
	}

	/**
	 * This method returns the DrinksDisplay:StoreViewer.
	 * @return the DrinksDisplay:StoreViewer.
	 */
	public StoreViewer getDrinksStoreDisplay() {
		return drinksDisplay;
	}

	/**
	 * This method set the door state to Open or Closed.
	 * @param state TRUE to set the door state to open, otherwise, set the door state to close.
	 */
	public void setDoorState(boolean state) {
		doorDisplay.setState(state);
		this.setActive(!state);

	}

	/**
	 * This method refreshes the cash display and drinks display.
	 */
	public void refresh(){
		cashDisplay.update();
		drinksDisplay.update();
	}
	
	/**
	 * THis method activates or deactivates the MachinerySimulatorPanel and its component objects.
	 * @param state TRUE to activate, FALSE to deactivate.
	 */
	public void setActive(boolean state) {
		cashDisplay.setActive(state);
		drinksDisplay.setActive(state);
		doorDisplay.setEnabled(state);
	}
}//End of class MachinerySimulatorPanel