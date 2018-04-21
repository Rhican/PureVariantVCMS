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

import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.store.StoreObject;
import sg.edu.nus.iss.vmcs.system.MainController;

/**
 * This interface object is part of the Customer Panel&#46; It is used to enter
 * Coins into the vending machine.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CoinInputBox extends Panel{
	private CoinButton[] btnCoinButton;
	
	private TransactionController txCtrl;
	
	/**
	 * This constructor creates an instance of the object.
	 * @param cctrl the TransactionController.
	 */
	public CoinInputBox(TransactionController cctrl){
		this.txCtrl=cctrl;
		MainController mainCtrl=cctrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		int cashStoreSize=storeCtrl.getStoreSize(Store.CASH);
		StoreItem[] cashStoreItems=storeCtrl.getStore(Store.CASH).getItems();
		
		btnCoinButton=new CoinButton[cashStoreSize+1];
		CoinInputListener coinInputListener=new CoinInputListener(txCtrl.getCoinReceiver());
		
		setLayout(new GridBagLayout());
		for(int i=0;i<cashStoreItems.length;i++){
			StoreItem storeItem=cashStoreItems[i];
			CashStoreItem cashStoreItem=(CashStoreItem)storeItem;
			StoreObject storeObject=cashStoreItem.getContent();
			Coin coin=(Coin)storeObject;
			String coinName=coin.getName();
			int coinValue=coin.getValue();
			double coinWeight=coin.getWeight();
			btnCoinButton[i]=new CoinButton(coinName,coinValue,coinWeight);
			btnCoinButton[i].addActionListener(coinInputListener);
			add(btnCoinButton[i],new GridBagConstraints(i,1,1,1,1.0,0.0,
				    GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
				    new Insets(0,0,0,0),10,8));
		}
		btnCoinButton[cashStoreSize]=new CoinButton("Invalid",-1,CashStore.INVALID_COIN_WEIGHT);
		btnCoinButton[cashStoreSize].addActionListener(coinInputListener);
		add(btnCoinButton[cashStoreSize],new GridBagConstraints(cashStoreSize,1,1,1,1.0,0.0,
			    GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
			    new Insets(0,0,0,0),10,8));
	}
	
	/**
	 * This method activates the Coin Input Box if the parameter is TRUE&#46;
	 * Otherwise, the Coin Input Box is deactivated.
	 * @param active TRUE to activate the CoinInputBox, otherwise, deactivate the CoinInputBox.
	 */
	public void setActive(boolean active){
		if(btnCoinButton!=null){
			for(int i=0;i<btnCoinButton.length;i++){
				btnCoinButton[i].setEnabled(active);
			}
		}
	}
}//CoinInputBox