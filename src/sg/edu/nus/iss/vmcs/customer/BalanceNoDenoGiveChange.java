/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object manages the giving of change(Balance number denomination) to the Customer.
 * @author SPLE Team 04
 */
public class BalanceNoDenoGiveChange implements GiveChangeStrategy {
	private TransactionController txCtrl; 

	/**
	 * The constructor creates an instance of the object.
	 * @param txCtrl the TransactionController
	 */
	public BalanceNoDenoGiveChange(TransactionController txCtrl){
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
			if (changeRequired > Environment.getMinBalanceDenomination()) {
				Map<Integer, AvailableCoin> availableCoins = new HashMap<>();
				for(int i=cashStoreSize-1;i>=0;i--){
					StoreItem cashStoreItem=storeCtrl.getStore(Store.CASH).getStoreItem(i);
					int quantity=cashStoreItem.getQuantity();
					Coin coin=(Coin)cashStoreItem.getContent();
					if (quantity > 0) {
						availableCoins.put(coin.getValue(), new AvailableCoin(coin, i, quantity));
					}
				}
				List<Integer> availableCoinList = new ArrayList<>(availableCoins.keySet());
				Collections.sort(availableCoinList);
				for (int i=0; i<availableCoinList.size(); i++) {
					int coinValue = availableCoinList.get(i);
					int index = availableCoins.get(coinValue).getIndex();
					int quantity = availableCoins.get(coinValue).getQuantity();
					if (coinValue == 5 && changeBal%10 == 0) {
						continue;
					}
					int quantityRequired=0;
					boolean continueOuter = false;
					while(changeBal > 0){
						changeBal-=coinValue;
						quantityRequired++;
						quantity--;
						if (i+1 < availableCoinList.size() && changeBal>=availableCoinList.get(i+1)) {
							int remain = changeBal%availableCoinList.get(i+1);
							if (remain > 0 && remain < availableCoinList.get(i+1)) {
								continue; // Continue giving current note/coin.
							} else {
								continueOuter = true;
								break; // Give change larger note/coin.
							}
						} else if (quantity == 0) {
							break; // No more note/coin for give change.
						}
					}
					txCtrl.getMainController().getMachineryController().giveChange(index,quantityRequired);
					if (!continueOuter) {
						break;
					}
				}
			} else {
				changeBal = normalGiveChange(cashStoreSize, storeCtrl, changeBal);
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
	
	public int normalGiveChange(int cashStoreSize, StoreController storeCtrl, int changeBal) throws VMCSException {
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
		return changeBal;
	}
	
	public class AvailableCoin {
		private Coin coin;
		private int index;
		private int quantity;
		
		public AvailableCoin(Coin coin, int index, int quantity) {
			this.coin = coin;
			this.index = index;
			this.quantity = quantity;
		}
		
		public Coin getCoin() {
			return coin;
		}
		public void setCoin(Coin coin) {
			this.coin = coin;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
	}
}