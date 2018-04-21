/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;
import sg.edu.nus.iss.vmcs.util.VMCSException;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

/**
 * This panel simulates the vending machines maintainer control panel&#46;
 * It will enable the user (the Maintainer) to:
 * <br>
 * 1- Log-on using a password;
 * <br>
 * 2- Display number of Coins of each denomination in the CashStore;
 * <br>
 * 3- Display total value of cash in the system;
 * <br>
 * 4- Display total number of cans of each DrinksBrand held in the DrinsStore;
 * <br>
 * 5- Change the price of any DrinksBrand;
 * <br>
 * 6- Collect all cash held in they system;
 * <br> 
 * 7- Formally exit from the panel&#46;
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class MaintenancePanel extends Dialog {
	private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	private int frameX=0;
	private int frameY=0;
	private int frameWidth=300;
	private int frameHeight=400;
	private int screenWidth=screen.width;
	private int screenHeight=screen.height;
	
	/**This constant attribute denote the working status of the MaintenancePanel*/
	public final static int WORKING = 1;
	/**This constant attribute denote the password status of the MaintenancePanel*/
	public final static int PSWD    = 2;
	/**This constant attribute denote the dialog status of the MaintenancePanel*/
	public final static int DIALOG  = 3;

	private static final String TITLE = "Maintenance Panel";
	private LabelledDisplay password;
	private LabelledDisplay collectCash;
	private Button exitBtn;
	private CoinDisplay cDisplay; // need to be access from other class.
	private DrinkDisplay dDisplay; // need to be access from other class.
	private ButtonItem totalCash;
	private Button transferCash;
	private WarningDisplay validPswd;
	private WarningDisplay invalidPswd;
	private MaintenanceController mctrl;

	/**
	 * This constructor creates an instance of MaintenancePanel object.
	 * @param fr the parent frame.
	 * @param mc the MaintenanceController.
	 */
	public MaintenancePanel(Frame fr, MaintenanceController mc) {
		super(fr, TITLE, false);

		mctrl = mc;

		// north part
		Label lb = new Label(TITLE);
		lb.setFont(new Font("Helvetica", Font.BOLD, 24));
		Panel tp1 = new Panel();
		tp1.add(lb);

		Panel tpn = new Panel();
		tpn.setLayout(new GridLayout(0, 1));

		password = new LabelledDisplay("Password:", 30, LabelledDisplay.FLOW);
		PasswordListener pl = new PasswordListener(mc.getAccessManager());
		password.addListener(pl);

		Panel tp3 = new Panel();
		validPswd = new WarningDisplay("Valid Password");
		invalidPswd = new WarningDisplay("Invalid Password");
		tp3.add(validPswd);
		tp3.add(invalidPswd);
		tpn.add(tp1);
		tpn.add(password);
		tpn.add(tp3);

		// center part
		Panel tpc = new Panel();
		tpc.setLayout(new GridLayout(0, 1));

		cDisplay = new CoinDisplay(mctrl);
		dDisplay = new DrinkDisplay(mctrl);

		Panel tp5 = new Panel();
		tp5.setLayout(new GridLayout(0, 1));

		totalCash = new ButtonItem("Show Total Cash Held", 5, ButtonItem.FLOW);
		TotalCashButtonListener tl;

		tl = new TotalCashButtonListener(mctrl);
		totalCash.addListener(tl);

		transferCash = new Button("Press to Collect All Cash");
		transferCash.addActionListener(new TransferCashButtonListener(mctrl));

		Panel tp6 = new Panel();
		tp6.setLayout(new FlowLayout());
		tp6.add(transferCash);

		collectCash =
			new LabelledDisplay("Collect Cash:", 5, LabelledDisplay.FLOW);
		exitBtn = new Button("Press Here when Finished");
		exitBtn.addActionListener(new ExitButtonListener(mctrl));

		tp5.add(totalCash);
		tp5.add(tp6);
		tp5.add(collectCash);
		tp5.add(exitBtn);
		tpc.setLayout(new BorderLayout());
		Panel pp = new Panel();
		pp.setLayout(new GridLayout(1, 2));
		pp.add(cDisplay);
		pp.add(dDisplay);
		tpc.add("Center", pp);
		tpc.add("South", tp5);

		this.setLayout(new BorderLayout());
		this.add("North", tpn);
		this.add("Center", tpc);

		pack();
		frameWidth=this.getWidth();
        frameHeight=this.getHeight();
        frameX=(screenWidth-frameWidth);
        frameY=0;
        setBounds(frameX,frameY,frameWidth, frameHeight);
	}

	/**
	 * Display the MaintenancePanel&#46; This will be achieved by displaying the frame of
	 * the panel and then sending messages to its constituent objects instructing them to:
	 * <br>
	 * 1- Display themselves;
	 * <br>
	 * 2- Set initial values;
	 * <br>
	 * 3- Deactivate all constituent objects except Password Box and Cash Collection 
	 * Tray Display.
	 */
	public void display() {
		this.setVisible(true);
	}

	/**
	 * This method remove the panel objects and the maintenance panel from the display.
	 */
	public void closeDown() {
		dispose();

	}

	/**
	 * This method returns the CoinDisplay.
	 * @return the CoinDisplay.
	 */
	public CoinDisplay getCoinDisplay() {
		return cDisplay;
	}

	/**
	 * This method returns the DrinksDisplay.
	 * @return the DrinksDisplay.
	 */
	public DrinkDisplay getDrinksDisplay() {
		return dDisplay;
	}

	/**
	 * This method displays a message indicating the VALID or INVALID password status.
	 * @param st if TRUE then highlight VALID password status, otherwise, highlight
	 * INVALID password status.
	 */
	public void displayPasswordState(boolean st) {
		if (st == true) {
			validPswd.setState(true);
			invalidPswd.setState(false);
		} else {
			validPswd.setState(false);
			invalidPswd.setState(true);
		}

	}

	/**
	 * This method activates or deactivtes the MaintenancePanel and its component objects.
	 * @param comp the component to set active status.
	 * @param st the active status.
	 */
	public void setActive(int comp, boolean st) {
		switch (comp) {
			case DIALOG :
				setActive(PSWD, st);
				setActive(WORKING, !st);
				validPswd.setState(false);
				invalidPswd.setState(false);
				break;
			case WORKING :
				collectCash.setActive(st);
				cDisplay.setActive(st);
				dDisplay.setActive(st);
				totalCash.setActive(st);
				transferCash.setEnabled(st);
				break;
			case PSWD :
				password.setActive(st);
				break;
		}
	}

	/**
	 * This method returns the current drinks index.
	 * @return the current drinks index.
	 */
	public int getCurIdx() {
		return dDisplay.getCurIdx();
	}

	/**
	 * This method displays the received value as the total cash held in the CashStore of the
	 * vending machine.
	 * @param tc the total cash.
	 */
	public void displayTotalCash(int tc) {
		String stc;

		stc = new String(tc + " C");
		totalCash.setValue(stc);
	}

	/**
	 * This method displays the amount of money to be issued on the Cash Collection
	 * Tray Display.
	 * @param cc the collected cash.
	 */
	public void displayCoins(int cc) {
		collectCash.setValue(cc);
	}

	/**
	 *  Use when machinery simulator panel changes qty;
	 *  It is used to automatically update the displayed quantity in maintenance panel&#46;
	 *  It is called by Maintenance Controller&#46;
	 *  Not required in requirement&#46;
	 *  @throws VMCSException if fail to update quantity display.
	 */
	public void updateQtyDisplay(int type, int idx, int qty)
		throws VMCSException {
		if (type == Store.CASH) {
			cDisplay.displayQty(idx, qty);
		} else
			dDisplay.displayQty(idx, qty);
	}

	/**
	 * When transfer all button is pushed, the current display needs to be updated&#46;
	 * not required in requirement&#46;
	 * @throws VMCSException if fail to update quantity display.
	 */
	public void updateCurrentQtyDisplay(int type, int qty)
		throws VMCSException {
		int curIdx;
		if (type == Store.CASH)
			curIdx = cDisplay.getCurIdx();
		else
			curIdx = dDisplay.getCurIdx();
		updateQtyDisplay(type, curIdx, qty);
	}

	/**
	 * This method initiate the collect cash.
	 */
	public void initCollectCash() {
		collectCash.setValue("");
	}

	/**
	 * This method initiate the total cash.
	 */
	public void initTotalCash() {
		totalCash.setValue("");
	}

	/**
	 * This method clear the password field.
	 */
	public void clearPassword() {
		password.setValue("");
	}

	/**
	 * This method display the price for the DrinkDisplay.
	 * @param price the price of the Drinks.
	 */
	public void displayPrice(int price) {
		dDisplay.getPriceDisplay().setValue(price + "C");
	}
}//End of class MaintenancePanel