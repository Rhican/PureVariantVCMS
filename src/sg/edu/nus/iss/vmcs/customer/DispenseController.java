/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.ItemsBrand;
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
public class DispenseController implements DispenseComponent {
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
			ItemsBrand drinksBrand=(ItemsBrand)storeItem.getContent();
			String name=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			/**
			 * PV:IFCOND(pv:hasFeature('Drink'))
			 */
			custPanel.getDrinkSelectionBox().update(i, quantity, price, name);
			/**
		     * PV:ENDCOND 
		     */	
		}
	}
	
	
	/**
     * This method updates the whole Snack Selection Box with current names, stocks and prices.
     */
	public void updateSnackPanel(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
		updateSnackSelection(-1);
		int storeSize=txCtrl.getMainController().getStoreController().getStoreSize(Store.SNACK);
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=txCtrl.getMainController().getStoreController().getStoreItem(Store.SNACK,i);
			int quantity=storeItem.getQuantity();
			ItemsBrand snacksBrand=(ItemsBrand)storeItem.getContent();
			String name=snacksBrand.getName();
			int price=snacksBrand.getPrice();
			/**
			 * PV:IFCOND(pv:hasFeature('Snack'))
			 */
			custPanel.getSnackSelectionBox().update(i, quantity, price, name);
			/**
		     * PV:ENDCOND 
		     */	
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
     * This method is used to display the latest stock and price information on the Snack Selection Box.
	 * @param index
	 */
	public void updateSnackSelection(int index) {
		this.selection = (index >= 0) 
				? index + 1000 // index offset, above 1000 for snacks
				: index;
	}
	
	/**
	 * This method will be used to activate or deactivate (as indicated through a parameter)
	 * the Drink Selection Box so that transactions can continue or will be disallowed.
	 * @param allow TRUE to activate, FALSE to deactivate the Drink Selection Box.
	 */
	public void allowSelection(boolean allow){
		StoreController storeCtrl;
		int storeSize = 0;
		MainController mainCtrl=txCtrl.getMainController();
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
		
		 /**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		DrinkSelectionBox drinkSelectionBox=custPanel.getDrinkSelectionBox();
		storeCtrl=mainCtrl.getStoreController();
		storeSize=storeCtrl.getStoreSize(Store.DRINK);
		for(int i=0;i<storeSize;i++){
			drinkSelectionBox.setState(i,allow);
			StoreItem storeItem=storeCtrl.getStoreItem(Store.DRINK, i);
			int quantity=storeItem.getQuantity();
			if(quantity==0)
				drinkSelectionBox.setItemState(i,true);
		}
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('Snack'))
		 */
		SnackSelectionBox snackSelectionBox=custPanel.getSnackSelectionBox();
		storeCtrl=mainCtrl.getStoreController();
		storeSize=storeCtrl.getStoreSize(Store.SNACK);
		for(int i=0;i<storeSize;i++){
			snackSelectionBox.setState(i,allow);
			StoreItem storeItem=storeCtrl.getStoreItem(Store.SNACK, i);
			int quantity=storeItem.getQuantity();
			if(quantity==0)
				snackSelectionBox.setItemState(i,true);
		}
		/**
	     * PV:ENDCOND 
	     */

		
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
			ItemsBrand drinksBrand=(ItemsBrand)storeObject;
			String drinksName=drinksBrand.getName();
			int price=drinksBrand.getPrice();
			int quantity=drinkStoreItem.getQuantity();
			txCtrl.getCustomerPanel().setCan(drinksName);
			updateDrinkSelection(selectedBrand);
			/**
			 * PV:IFCOND(pv:hasFeature('Drink'))
			 */
			txCtrl.getCustomerPanel().getDrinkSelectionBox().update(selectedBrand, quantity, price, drinksName);
			/**
		     * PV:ENDCOND 
		     */	
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
	
	/**
	 * This method will be used to dispense a snack&#46;  It will:
	 * <br>
	 * 1- Instruct the Drinks Store to dispense the snack&#46; It will also instruct the
	 * Can Collection Box to display a can shape&#46;
	 * <br>
	 * 2- Instruct the Store Controller to update the Snack Store Display on the
	 * Machinery Simulator Panel&#46;
	 * <br>
	 * 3- In case of fault detection, it will send a "fault detected" response to the 
	 * Transaction Controller&#46;
	 * @param selectedSnack the selected snack&#46;
	 */
	public boolean dispenseSnack(int selectedSnack){
		try{
			txCtrl.getMainController().getMachineryController().dispenseDrink(selectedSnack);
			MainController mainCtrl=txCtrl.getMainController();
			StoreController storeCtrl=mainCtrl.getStoreController();
			StoreItem snackStoreItem=storeCtrl.getStore(Store.SNACK).getStoreItem(selectedSnack);
			StoreObject storeObject=snackStoreItem.getContent();
			ItemsBrand snacksBrand=(ItemsBrand)storeObject;
			String snacksName=snacksBrand.getName();
			int price=snacksBrand.getPrice();
			int quantity=snackStoreItem.getQuantity();
			txCtrl.getCustomerPanel().setCan(snacksName);
			updateSnackSelection(selectedSnack);
			/**
			 * PV:IFCOND(pv:hasFeature('Snack'))
			 */
			txCtrl.getCustomerPanel().getSnackSelectionBox().update(selectedSnack, quantity, price, snacksName);
			/**
		     * PV:ENDCOND 
		     */
			
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
}//End of class DispenseController