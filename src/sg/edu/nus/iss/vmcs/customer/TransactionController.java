/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.Frame;

import sg.edu.nus.iss.vmcs.VariantPointConstants;
import sg.edu.nus.iss.vmcs.store.ItemsBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;

/**
 * This control object coordinates the customer transactions for selection of a drink brand,
 * coin input, storage of coins and termination requests for ongoing transactions.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class TransactionController {
	private MainController mainCtrl;
	private CustomerPanel custPanel;
	private DispenseComponent dispenseCtrl;
	private ChangeGiver changeGiver;
	private PaymentDecorator paymentDecorator;

	/**Set to TRUE when change is successfully issued during the transaction.*/
	private boolean changeGiven=false;
	/**Set to TRUE when the drink/snack is successfully dispensed during the transaction.*/
	private boolean itemDispensed=false;
	/**Price of the selected drink/snack.*/
	private int price=0;
	/**Identifier of the selected drink/snack.*/
	private int selection=-1, selectedType = Store.DRINK;
	
	public TransactionController(MainController mainCtrl, CustomerPanel custPanel
			, DispenseComponent dispenseCtrl, ChangeGiver changeGiver, PaymentDecorator paymentDecorator) {
		this.mainCtrl = mainCtrl;
		this.custPanel = custPanel;
		this.dispenseCtrl = dispenseCtrl;
		this.changeGiver = changeGiver;
		this.paymentDecorator = paymentDecorator;
	}
		
	/**
	 * This constructor creates an instance of the TransactionController.
	 * @param mainCtrl the MainController.
	 */
	public TransactionController(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		dispenseCtrl= (VariantPointConstants.vLogItemDispensing) 
				? new DispenseControllerLogDecorator(new DispenseController(this))
				: new DispenseControllerDecorator(new DispenseController(this));
//		coinReceiver = (VariantPointConstants.vLogPayment) 
//				? new CoinReceptionLogDecorator(new CoinReceiver(this))
//				: new CoinReceptionDecorator(new CoinReceiver(this));
		changeGiver=new ChangeGiver(this);
	}

	/**
	 * This method returns the MainController.
	 * @return the MainController.
	 */
	public MainController getMainController() {
		return mainCtrl;
	}

	/**
	 * This method displays and initialize the CustomerPanel.
	 */
	public void displayCustomerPanel() {
		SimulatorControlPanel scp = mainCtrl.getSimulatorControlPanel();
	    custPanel = new CustomerPanel((Frame) scp, this);
		custPanel.display();
		dispenseCtrl.updateDrinkPanel();
		dispenseCtrl.allowSelection(true);
		changeGiver.displayChangeStatus();
		if (VariantPointConstants.vCashPayment) {
			custPanel.setCoinInputBoxActive(false);
		}
		if (VariantPointConstants.vCardPayment) {
			custPanel.setCardDetectorActive(false);
		}
	}
	
	/**
	 * This method will start the customer transaction&#46; It receives the identification
	 * for the selected drink brand (item) from the Customer Panel&#46; The following
	 * actions are performed in the method:
	 * <br>
	 * 1- The price of the selected item is obtained&#46;
	 * <br>
	 * 2- The Refund/ Change Tray Display is reset&#46;
	 * <br>
	 * 3- The Can Collection Box is reset&#46;
	 * <br>
	 * 4- The Drink/Snack Selection Box is deactivated to disallow the selection of further
	 * drinks when the transaction is in progress&#46;
	 * <br>
	 * 5- The Coin Receiver will be instructed to start receiving the coins&#46;
	 * @param identifier the drink brand item identifier.
	 */
	public void startTransaction(int identifier, int type){
		selectedType = type;
		if (type == Store.DRINK)
		{
			setSelection(identifier);
			StoreItem storeItem=mainCtrl.getStoreController().getStoreItem(Store.DRINK,identifier);
			ItemsBrand drinksBrand=(ItemsBrand)storeItem.getContent();
			setPrice(drinksBrand.getPrice());
		}
		else if (type == Store.SNACK)
		{
			setSelection(identifier);
			StoreItem storeItem=mainCtrl.getStoreController().getStoreItem(Store.SNACK,identifier);
			ItemsBrand snacksBrand=(ItemsBrand)storeItem.getContent();
			setPrice(snacksBrand.getPrice());
		}
		
		changeGiver.resetChange();
		dispenseCtrl.ResetCan();
		changeGiver.displayChangeStatus();
		dispenseCtrl.allowSelection(false);
		activatePaymentReceiver();
		custPanel.setTerminateButtonActive(true);
	}
	
	/**
	 * Commence receiving a series of Coins&#46;  To do this the Coin Receiver
	 * instructs the Coin Input Box to become activated&#46;  It also updates the Total
	 * Money Inserted Display on the Customer Panel.
	 */
	public void activatePaymentReceiver(){
		if (VariantPointConstants.vCashPayment) {
			custPanel.setCoinInputBoxActive(true);
			custPanel.setTotalMoneyInserted(0);
		}
		
		if (VariantPointConstants.vCardPayment) {
			custPanel.setCardDetectorActive(true);
			custPanel.setTotalMoneyInserted(0);
		}
	}
	
	/**
	 * This method processes the money received by the Coin Receiver during the progress
	 * of a transaction&#46;  The following actions are performed during this method:
	 * <br>
	 * 1- The current total money inserted is obtained from the Coin Receiver&#46;
	 * <br>
	 * 2- If the received money is more than or equal to the price of the drink, 
	 * method CompleteTransaction of the Transaction Controller is triggered&#46;
	 * <br>
	 * 3- If the received money is less than the price of the drink, the Coin Receiver
	 * is instructed to continue receiving the coin&#46;
	 * @param total the total money received&#46;
	 */
	public void processMoneyReceived(int total){
		if (VariantPointConstants.vCardPayment) {
			custPanel.setCardDetectorActive(false);
		}
		if (VariantPointConstants.vCashPayment) {
			custPanel.setCoinInputBoxActive(false);
		}
		if(total>=price)
			completeTransaction(total);
		else{
			paymentDecorator.continueReceive();
		}
	}
	
	/**
	 * This method is performed when the Transaction Controller is informed that coin
	 * entry is complete and the money received is sufficient to dispense the drink.
	 * The following actions are performed.
	 * <br>
	 * 1- Dispense the drink.
	 * <br>
	 * 2- Give change if necessary.
	 * <br>
	 * 3- Store the Coins that have been entered into the Cash Store.
	 * <br>
	 * 4- Reset the Drink Selection Box to allow further transactions.
	 */
	public void completeTransaction(int totalMoneyInserted){
		System.out.println("CompleteTransaction: Begin");

		if (selectedType == Store.SNACK)
			dispenseCtrl.dispenseSnack(selection);
		else
			dispenseCtrl.dispenseDrink(selection);
		//int totalMoneyInserted=coinReceiver.getTotalInserted();
		int change=totalMoneyInserted-price;
		if(change>0){
			changeGiver.giveChange(change);
		}
		else{
			getCustomerPanel().setChange(0);
		}
		paymentDecorator.storeCash();
		dispenseCtrl.allowSelection(true);
		
		refreshMachineryDisplay();
		paymentDecorator = null;
		if(custPanel!=null){
			custPanel.setTerminateButtonActive(false);
		}
		System.out.println("CompleteTransaction: End");
	}
	
	/**
	 * If the TransactionController is informed that a fault was discovered while
	 * dispensing a drink, giving change or storing Coins, it will use this method
	 * to deactivate the Drink Selection Box and instruct the CoinReceiver to refund the
	 * money inserted by the customer.
	 */
	public void terminateFault(){
		System.out.println("TerminateFault: Begin");
		dispenseCtrl.allowSelection(false);
		if (paymentDecorator != null) {
			paymentDecorator.refundCash();
		}
		refreshMachineryDisplay();
		paymentDecorator = null;
		System.out.println("TerminateFault: End");
	}
	
	/**
	 * If the TransactionController receivers a request to terminate the current
	 * transaction then following will occur:
	 * <br>
	 * 1- If there is no transaction in progress or coin input is completed then the 
	 * CustomerPanel will be instructed to deactivate the DrinkSelectionBox&#46;
	 * <br>
	 * 2- If coin input is not yet complete, the CoinReceiver will be instructed to stop
	 * coin input and refund the money entered so far&#46;
	 * <br>
	 * 3- The DrinkSelectionBox is then reset to allow further transactions&#46;
	 */
	public void terminateTransaction(){
		System.out.println("TerminateTransaction: Begin");
		dispenseCtrl.allowSelection(false);
		if (paymentDecorator != null) {
			paymentDecorator.stopReceive();
			paymentDecorator.refundCash();
		}
		if(custPanel!=null){
			custPanel.setTerminateButtonActive(false);
			if (VariantPointConstants.vCardPayment) {
				custPanel.setCardDetectorActive(false);
			}
			if (VariantPointConstants.vCashPayment) {
				custPanel.setCoinInputBoxActive(false);
			}
		}
		refreshMachineryDisplay();
		paymentDecorator = null;
		System.out.println("TerminateTransaction: End");
	}
	
	/**
	 * This method will cancel an ongoing customer transaction.
	 */
	public void cancelTransaction(){
		System.out.println("CancelTransaction: Begin");
		if (paymentDecorator != null) {
			paymentDecorator.stopReceive();
			paymentDecorator.refundCash();
		}
		if(custPanel!=null){
			custPanel.setTerminateButtonActive(false);
			if (VariantPointConstants.vCardPayment) {
				custPanel.setCardDetectorActive(false);
			}
			if (VariantPointConstants.vCashPayment) {
				custPanel.setCoinInputBoxActive(false);
			}
		}
		dispenseCtrl.allowSelection(true);
		refreshMachineryDisplay();
		paymentDecorator = null;
		System.out.println("CancelTransaction: End");
	}
	
	/**
	 * This method refreshes the CustomerPanel when maintainer logs-out.
	 */
	public void refreshCustomerPanel(){
		/*
		if(custPanel==null){
			mainCtrl.getSimulatorControlPanel().setActive(SimulatorControlPanel.ACT_CUSTOMER,true);
		}
		*/
		dispenseCtrl.updateDrinkPanel();
		dispenseCtrl.allowSelection(true);
		changeGiver.displayChangeStatus();
		custPanel.setTerminateButtonActive(true);
	}
	
	/**
	 * This method will close down the transaction control function of the vending
	 * machine.
	 */
	public void closeDown() {
		if (custPanel != null)
			custPanel.closeDown();
	}

	/**
	 * This method sets whether the change is given.
	 * @param changeGiven TRUE the change is given, otherwise FALSE.
	 */
	public void setChangeGiven(boolean changeGiven) {
		this.changeGiven = changeGiven;
	}

	/**
	 * This method returns whether the change is given.
	 * @return TRUE if the change is given, otherwise FALSE.
	 */
	public boolean isChangeGiven() {
		return changeGiven;
	}

	/**
	 * This method sets whether the drink is dispensed.
	 * @param drinkDispensed TRUE the drink is dispensed, otherwise, FALSE.
	 */
	public void setDrinkDispensed(boolean drinkDispensed) {
		this.itemDispensed = drinkDispensed;
	}

	/**
	 * This method returns whether the drink is dispensed.
	 * @return TRUE if the drink is dispensed, otherwise FALSE.
	 */
	public boolean isDrinkDispensed() {
		return itemDispensed;
	}

	/**
	 * This method sets the price of the selected drink.
	 * @param price the price of the selected drink.
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * This method returns the price of the selected drink.
	 * @return the price of the selected drink.
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * This method sets the selected drink index.
	 * @param selection the selected drink index.
	 */
	public void setSelection(int selection) {
		this.selection = selection;
	}

	/**
	 * This method returns the selected drink index.
	 * @return the selected drink index.
	 */
	public int getSelection() {
		return selection;
	}
	
	/**
	 * This method returns the CustomerPanel.
	 * @return the CustomerPanel.
	 */
	public CustomerPanel getCustomerPanel(){
		return custPanel;
	}
	
	/**
	 * This method returns the DispenseController.
	 * @return the DispenseController.
	 */
	public DispenseComponent getDispenseController(){
		return dispenseCtrl;
	}
	
	/**
	 * This method returns the ChangeGiver.
	 * @return the ChangeGiver.
	 */
	public ChangeGiver getChangeGiver(){
		return changeGiver;
	}
	
	/**
	 * This method returns the CoinReceiver.
	 * @return the CoinReceiver.
	 */
	public PaymentDecorator getPaymentDecorator(){
		return paymentDecorator;
	}
	
	public void setPaymentDecorator(PaymentDecorator paymentDecorator) {
		this.paymentDecorator = paymentDecorator;
	}
	
	/**
	 * This method refreshes the MachinerySimulatorPanel.
	 */
	public void refreshMachineryDisplay(){
		mainCtrl.getMachineryController().refreshMachineryDisplay();
		
	}
	
	/**
	 * This method will nullify reference to customer panel.
	 */
	public void nullifyCustomerPanel(){
		custPanel=null;
	}
}//End of class TransactionController