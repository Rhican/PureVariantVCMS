/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

import sg.edu.nus.iss.vmcs.store.SnacksBrand;
import sg.edu.nus.iss.vmcs.store.SnacksStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.system.MainController;

/**
 * This interface object is part of the Customer Panel&#46; It is used by the Customer to select a snack.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class SnackSelectionBox extends Panel{
	private SnackSelectionItem snackSelectionItems[];
	private TransactionController txCtrl;
	
	/**Array of integers providing identifiers for each selection button.*/
	
	
	public int Count() {
		return snackSelectionItems.length;
	}
	
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the Transaction Controller
	 */
	public SnackSelectionBox(TransactionController txCtrl){
		this.txCtrl=txCtrl;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		int snackStoreSize=storeCtrl.getStoreSize(Store.SNACK);
		StoreItem[] snackStoreItems=storeCtrl.getStore(Store.SNACK).getItems();
		
		snackSelectionItems=new SnackSelectionItem[snackStoreSize];
		
		setLayout(new GridBagLayout());
		for(int i=0;i<snackStoreItems.length;i++){
			StoreItem storeItem=snackStoreItems[i];
			SnacksStoreItem snacksStoreItem=(SnacksStoreItem)storeItem;
			StoreObject storeObject=snacksStoreItem.getContent();
			SnacksBrand snacksBrand=(SnacksBrand)storeObject;
			String snacksName=snacksBrand.getName();
			int snacksPrice=snacksBrand.getPrice();
			int snacksQuantity=snacksStoreItem.getQuantity();
			snackSelectionItems[i]=new SnackSelectionItem(i,snacksName,snacksPrice,snacksQuantity,true,false);
			snackSelectionItems[i].addListener(new SnackSelectionListener(txCtrl,i));
			add(snackSelectionItems[i],new GridBagConstraints(0,i,1,1,1.0,0.0,
				    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
				    new Insets(5,0,0,0),10,0));  
		}
	}
	
	/**
	 * This method updates the stock status, name and price of a snack brand based on the values received.
	 * @param brand the brand of the snack
	 * @param quantity the quantity of the snack
	 * @param price the price of the snack
	 * @param name the name of the 
	 */
	public void update(int brand, int quantity, int price, String name){
		if(snackSelectionItems==null||snackSelectionItems.length==0){
			return;
		}
		SnackSelectionItem item=snackSelectionItems[brand];
		item.setQuantity(quantity);
		item.setPrice(price);
		item.setName(name);
	}
	
	/**
	 * This method will activate or deactivate the snack selection buttons.
	 * @param active TRUE to activate, FALSE to deactivate the snack selection buttons.
	 */
	public void setActive(boolean active){
		if(snackSelectionItems==null||snackSelectionItems.length==0)
			return;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		for(int i=0;i<snackSelectionItems.length;i++){
			SnackSelectionItem item=snackSelectionItems[i];
			StoreItem storeItem=storeCtrl.getStoreItem(Store.SNACK, i);
			int quantity=storeItem.getQuantity();
			item.setState(active);
		}
	}
	
	/**
	 * This method activates or deactivates the snack selection buttons on the Customer Panel.
	 * @param index the index of the snack brand.
	 * @param active TRUE to activate, FALSE to deactivate the snack selection buttons.
	 */
	public void setState(int index, boolean active){
		if(snackSelectionItems==null||snackSelectionItems.length==0||index<-1||index>=snackSelectionItems.length)
			return;
		snackSelectionItems[index].setState(active);
	}
	
	/**
	 * This method activates or deactivates the snack selection buttons on the Customer Panel.
	 * This method also displays OUT OF STOCK messages where appropriate.
	 * @param index the index of the snack brand.
	 * @param active TRUE to activate, FALSE to deactivate the snack selection buttons.
	 */
	public void setItemState(int index, boolean active){
		if(snackSelectionItems==null||snackSelectionItems.length==0)
			return;
		snackSelectionItems[index].setItemState(active);
	}
	
	
}//SnackSelectionBox