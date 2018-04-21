/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.DrinksBrand;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object is for handling the dispense drink use case.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class DispenseController {
    private TransactionController txCtrl;
    private int selection=0;
	
    /**
     * This constructor creates an instance of the object.
     * @param txCtrl the Transaction Controller
     */
    public DispenseController(TransactionController txCtrl){
    	this.txCtrl=txCtrl;
    }
    
    /**
     * This method updates the whole Drink Selection Box with current names, stocks and prices.
     */
	public void updateDrinkPanel(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
		updateDrinkSelection(-1);
		int storeSize=txCtrl.getMainController().getStoreController().getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=txCtrl.getMainController().getStoreController().getStoreItem(Store.DRINK,i);
			int quantity=storeItem.getQuantity();
			DrinksBrand drinksBrand=(DrinksBrand)storeItem.getContent();
			String name=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			custPanel.getDrinkSelectionBox().update(i, quantity, price, name);
		}
	}
	
	/**
     * This method is used to display the latest stock and price information on the Drink Selection Box.
	 * @param index
	 */
	public void updateDrinkSelection(int index){
		this.selection=index;
	}
	
	/**
	 * This method will be used to activate or deactivate (as indicated through a parameter)
	 * the Drink Selection Box so that transactions can continue or will be disallowed.
	 * @param allow TRUE to activate, FALSE to deactivate the Drink Selection Box.
	 */
	public void allowSelection(boolean allow){
		MainController mainCtrl=txCtrl.getMainController();
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
		DrinkSelectionBox drinkSelectionBox=custPanel.getDrinkSelectionBox();
		StoreController storeCtrl=mainCtrl.getStoreController();
		int storeSize=storeCtrl.getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			drinkSelectionBox.setState(i,allow);
			StoreItem storeItem=storeCtrl.getStoreItem(Store.DRINK, i);
			int quantity=storeItem.getQuantity();
			if(quantity==0)
				drinkSelectionBox.setItemState(i,true);
		}
	}
	
	/**
	 * This method will be used to instruct the Can Collection Box to remove the 
	 * drinks can shape or drink brand name from being displayed.
	 */
	public void ResetCan(){
		selection=-1;
		txCtrl.getCustomerPanel().resetCan();
	}
	
	/**
	 * This method will be used to dispense a drink&#46;  It will:
	 * <br>
	 * 1- Instruct the Drinks Store to dispense the drink&#46; It will also instruct the
	 * Can Collection Box to display a can shape&#46;
	 * <br>
	 * 2- Instruct the Store Controller to update the Drink Store Display on the
	 * Machinery Simulator Panel&#46;
	 * <br>
	 * 3- In case of fault detection, it will send a "fault detected" response to the 
	 * Transaction Controller&#46;
	 * @param selectedBrand the selected brand&#46;
	 */
	public boolean dispenseDrink(int selectedBrand){
		try{
			txCtrl.getMainController().getMachineryController().dispenseDrink(selectedBrand);
			MainController mainCtrl=txCtrl.getMainController();
			StoreController storeCtrl=mainCtrl.getStoreController();
			StoreItem drinkStoreItem=storeCtrl.getStore(Store.DRINK).getStoreItem(selectedBrand);
			StoreObject storeObject=drinkStoreItem.getContent();
			DrinksBrand drinksBrand=(DrinksBrand)storeObject;
			String drinksName=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			int quantity=drinkStoreItem.getQuantity();
			txCtrl.getCustomerPanel().setCan(drinksName);
			updateDrinkSelection(selectedBrand);
			txCtrl.getCustomerPanel().getDrinkSelectionBox().update(selectedBrand, quantity, price, drinksName);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
}//End of class DispenseController