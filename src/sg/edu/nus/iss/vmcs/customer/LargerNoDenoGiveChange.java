/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object manages the giving of change(Larger number denomination) to the Customer.
 * @author SPLE Team 04
 */
public class LargerNoDenoGiveChange implements GiveChangeStrategy {
	private TransactionController txCtrl; 

	/**
	 * The constructor creates an instance of the object.
	 * @param txCtrl the TransactionController
	 */
	public LargerNoDenoGiveChange(TransactionController txCtrl){
		this.txCtrl=txCtrl;
	}
	
	/**
	 * This method manages the issuing of change to the Customer.
	 * @param changeRequired
	 * @return return TRUE if give change use case success, otherwise, return FALSE.
	 */
	public boolean giveChange(int changeRequired){
		if(changeRequired==0)
			return true;
		try{
			int changeBal=changeRequired;
			MainController mainCtrl=txCtrl.getMainController();
			StoreController storeCtrl=mainCtrl.getStoreController();
			int cashStoreSize=storeCtrl.getStoreSize(Store.CASH); 
			for(int i=cashStoreSize-1;i>=0;i--){
				StoreItem cashStoreItem=storeCtrl.getStore(Store.CASH).getStoreItem(i);
				int quantity=cashStoreItem.getQuantity();
				Coin coin=(Coin)cashStoreItem.getContent();
				int value=coin.getValue();
				int quantityRequired=0;
				while(changeBal>0&&changeBal>=value&&quantity>0){
					changeBal-=value;
					quantityRequired++;
					quantity--;
				}
				txCtrl.getMainController().getMachineryController().giveChange(i,quantityRequired);
			}
			txCtrl.getCustomerPanel().setChange(changeRequired-changeBal);
			if(changeBal>0)
				txCtrl.getCustomerPanel().displayChangeStatus(true);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
}