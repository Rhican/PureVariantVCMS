/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;
import sg.edu.nus.iss.vmcs.util.MessageDialog;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object handles the system maintenance use case.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class MaintenanceController {
	private MainController mCtrl;
	private MaintenancePanel mpanel;
	private AccessManager am;

	/**
	 * This constructor creates an instance of the MaintenanceController.
	 * @param mctrl the MainController.
	 */
	public MaintenanceController(MainController mctrl) {
		mCtrl = mctrl;
		am = new AccessManager(this);
	}

	/**
	 * This method returns the MainController.
	 * @return the MainController.
	 */
	public MainController getMainController() {
		return mCtrl;
	}

	/**
	 * This method setup the maintenance panel and display it.
	 */
	public void displayMaintenancePanel() {
		SimulatorControlPanel scp = mCtrl.getSimulatorControlPanel();
		if (mpanel == null)
			mpanel = new MaintenancePanel((Frame) scp, this);
		mpanel.display();
		mpanel.setActive(MaintenancePanel.DIALOG, true);
		// setActive of password, invalid and valid display.
	}

	/**
	 * This method returns the AccessManager.
	 * @return the AccessManager.
	 */
	public AccessManager getAccessManager() {
		return am;
	}

	/**
	 * This method sets whether the maintainer login&#46;
	 * When the MaintenanceController receives a message saying that the Maintainer has
	 * successfully logged-in, the following will occur;
	 * <br>
	 * 1- Messages will be sent to activate the following elements of the MaintenancePanel:
	 * CoinDisplay, DrinkDisplay, TotalCashDisplay, ExitButton, Transfer Cash Button and
	 * Total Cash Button&#46;
	 * <br>
	 * 2- The Door will be instructed to set its status as unlocked, and then inform the 
	 * MachinerySimulatorPanel to update the Door Status Display&#46;
	 * <br>
	 * 3- The SimulatorControlPanel will be instructed to deactivate the
	 * ActivateCustomerPanelButton&#46;
	 * <br>
	 * 4- The TransactionController will be instructed to terminate and suspend
	 * Customer Transactions&#46 Operation TerminateTransaction of TransactionController
	 * will be used to accomplish this&#46
	 * @param st If TRUE then login successfully, otherwise login fails.
	 */
	public void loginMaintainer(boolean st) {
		mpanel.displayPasswordState(st);
		mpanel.clearPassword();
		if (st == true) {
			// login successful
			mpanel.setActive(MaintenancePanel.WORKING, true);
			mpanel.setActive(MaintenancePanel.PSWD, false);
			MachineryController machctrl = mCtrl.getMachineryController();
			machctrl.setDoorState(false);
			//Terminate customer transaction
			mCtrl.getTransactionController().terminateTransaction();
		}
	}

	/**
	 * This method will be used to get the total number of coins of a selected denomination&#46
	 * This method invoked in CoinDisplayListener.
	 * @param idx the index of the Coin.
	 */
	public void displayCoin(int idx) {
		StoreController sctrl = mCtrl.getStoreController();
		CashStoreItem item;
		try {
			item = (CashStoreItem) sctrl.getStoreItem(Store.CASH, idx);
			mpanel.getCoinDisplay().displayQty(idx, item.getQuantity());
		} catch (VMCSException e) {
			System.out.println("MaintenanceController.displayCoin:" + e);
		}

	}

	/**
	 * This method will get the drink stock value and prices (for a specific brand) for
	 * display&#46
	 * This method invoked in DrinkDisplayListener.
	 * @param idx the index of the drinks.
	 */
	public void displayDrinks(int idx) {
		StoreController sctrl = mCtrl.getStoreController();
		DrinksStoreItem item;
		try {
			item = (DrinksStoreItem) sctrl.getStoreItem(Store.DRINK, idx);
			DrinksBrand db = (DrinksBrand) item.getContent();
			mpanel.getDrinksDisplay().displayQty(idx, item.getQuantity());
			mpanel.displayPrice(db.getPrice());
		} catch (VMCSException e) {
			System.out.println("MaintenanceController.displayDrink:" + e);
		}

	}

	/**
	 * This method invoked by PriceDisplayListener.
	 * @param pr the price of the drinks.
	 */
	public void setPrice(int pr) {
		StoreController sctrl = mCtrl.getStoreController();
		int curIdx = mpanel.getCurIdx();
		sctrl.setPrice(curIdx, pr);
		mpanel.getDrinksDisplay().getPriceDisplay().setValue(pr + "C");
	}

	/**
	 * This method sends the total cash held in the CashStore to the MaintenancePanel&#46
	 * This method is invoked by the TotalCashButtonListener.
	 */
	public void getTotalCash() {
		StoreController sctrl = mCtrl.getStoreController();
		int tc = sctrl.getTotalCash();
		mpanel.displayTotalCash(tc);

	}

	/**
	 * This method is to facilitate the transfer of all cash in CashStore to the maintainer&#46
	 * This method is invoked by the TransferCashButtonListener&#46
	 * It get all the cash from store and set store cash 0.
	 */
	public void transferAll() {
		StoreController sctrl = mCtrl.getStoreController();
		MachineryController machctrl = mCtrl.getMachineryController();

		int cc; // coin quantity;

		try {
			cc = sctrl.transferAll();
			mpanel.displayCoins(cc);
			machctrl.displayCoinStock();
			// the cash qty current is displayed in the Maintenance panel needs to be update to be 0;
			// not required.
			mpanel.updateCurrentQtyDisplay(Store.CASH, 0);
		} catch (VMCSException e) {
			System.out.println("MaintenanceController.transferAll:" + e);
		}
	}

	/**
	 * This method is invoked by the StoreViewerListener.
	 * @param type the type of the Store.
	 * @param idx the index of the StoreItem.
	 * @param qty the quantity of the StoreItem.
	 */
	public void changeStoreQty(char type, int idx, int qty) {
		//StoreController sctrl = mCtrl.getStoreController();
		try {
			mpanel.updateQtyDisplay(type, idx, qty);
			mpanel.initCollectCash();
			mpanel.initTotalCash();
		} catch (VMCSException e) {
			System.out.println("MaintenanceController.changeStoreQty:" + e);
		}
	}

	/**
	 * When the MaintenanceController receives a message saying that the Maintainer
	 * has correctly logged-out, the following will occur:
	 * <br>
	 * 1- Check the door status of Door to determine whether the vending machine
	 * door is locked&#46 if the door is unlocked, then the exit request is ignored&#46
	 * <br>
	 * 2- Re-set the MaintenancePanel (initial values set, buttons activated/ deactivated)&#46
	 * <br>
	 * 3- Update the CustomerPanel and permit Customer transaction to re-start&#46
	 * This method is invoked by the exit button listener.
	 */
	public void logoutMaintainer() {

		MachineryController machctrl = mCtrl.getMachineryController();

		boolean ds = machctrl.isDoorClosed();

		if (ds == false) {
			MessageDialog msg =
				new MessageDialog(
					mpanel,
					"Please Lock the Door before You Leave");
			msg.setLocation(500, 500);
			return;
		}

		mpanel.setActive(MaintenancePanel.DIALOG, true);
		
		//Refresh Customer Panel
		CustomerPanel custPanel=mCtrl.getTransactionController().getCustomerPanel();
		if(custPanel==null){
			mCtrl.getSimulatorControlPanel().setActive(SimulatorControlPanel.ACT_CUSTOMER, true);
		}
		else{
			mCtrl.getTransactionController().refreshCustomerPanel();
		}
	}

	/**
	 * This method will close down the maintenance functions of the vending machine&#46
	 * This method close down the MaintenancePanel.
	 */
	public void closeDown() {
		if (mpanel != null)
			mpanel.closeDown();
	}
}//End of class MaintenanceController