/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.util.ArrayList;

import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.store.CashStore;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object manages the input and storage of Coins.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CoinReceiver {
	private TransactionController txCtrl;
	
	/**List of the Coins entered during the transaction.*/
	private ArrayList arlCoins;
	/**Total amount of money entered so far during current transaction.*/
	private int totalInserted;
	
	/**
	 * This constructor creates an instance of the object.
	 * @param txCtrl the transaction controller.
	 */
	public CoinReceiver(TransactionController txCtrl){
		this.txCtrl=txCtrl;
		arlCoins=new ArrayList();
		setTotalInserted(0);
	}
	
	/**
	 * Commence receiving a series of Coins&#46;  To do this the Coin Receiver
	 * instructs the Coin Input Box to become activated&#46;  It also updates the Total
	 * Money Inserted Display on the Customer Panel.
	 */
	public void startReceiver(){
		txCtrl.getCustomerPanel().setCoinInputBoxActive(true);
		txCtrl.getCustomerPanel().setTotalMoneyInserted(0);
	}
	
	/**
	 * When a coin is received the following will occur:
	 * <ol>
	 * <li>
	 * The Coin Input Box will be instructed to become deactivated&#46;
	 * </li>
	 * <li>
	 * The weight of the Coin will be send to the object Coin for it to 
	 * determine the denomination and value&#46;
	 * </li>
	 * <li>
	 * The Total Money Inserted Display will be updated&#46;
	 * </li>
	 * <li>
	 * If an invalid coin is entered, the Invalid Coin Display will be
	 * instructed to display INVALID COIN&#46;
	 * </li>
	 * <li>
	 * The Transaction Controller will be informed to process the current
	 * transaction based on the money received&#46;
	 * </li>
	 * </ol>
	 * @param weight the weight of the coin received&#46;
	 */
	public void receiveCoin(double weight){
		CashStore cashStore=(CashStore)txCtrl.getMainController().getStoreController().getStore(Store.CASH);
		Coin coin=cashStore.findCoin(weight);
		if(coin==null){
			txCtrl.getCustomerPanel().displayInvalidCoin(true);
			txCtrl.getCustomerPanel().setChange("Invalid Coin");
			//txCtrl.getCustomerPanel().setCoinInputBoxActive(false);
		}
		else{
			txCtrl.getCustomerPanel().setCoinInputBoxActive(false);
			int value=coin.getValue();
			txCtrl.getCustomerPanel().displayInvalidCoin(false);
			arlCoins.add(coin);
			setTotalInserted(getTotalInserted() + value);
			//int total=txCtrl.getCustomerPanel().addMoney(value);
			txCtrl.getCustomerPanel().setTotalMoneyInserted(getTotalInserted());
			txCtrl.getCustomerPanel().setChange("");
			txCtrl.processMoneyReceived(getTotalInserted());
		}
	}

	/**
	 * This method will activate the Coin Input Box so that further coins
	 * can be received.
	 */
	public void continueReceive(){
		txCtrl.getCustomerPanel().setCoinInputBoxActive(true);
	}
	
	/**
	 * Instruct the Cash Store to update its totals and then re-set the Total
	 * Money Inserted Display to zero.
	 * @return return TRUE if cash has been stored, else return FALSE.
	 */
	public boolean storeCash(){
		MachineryController machineryCtrl=txCtrl.getMainController().getMachineryController();
		try{
			for(int i=0;i<arlCoins.size();i++){
				Coin coin=(Coin)arlCoins.get(i);
				machineryCtrl.storeCoin(coin);
			}
			resetReceived();
			txCtrl.getCustomerPanel().setTotalMoneyInserted(0);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
	
	/**
	 * This method will deactivate the Coin Input Box in order to stop 
	 * receiving coins.
	 */
	public void stopReceive(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null){
			return;
		}
		custPanel.setCoinInputBoxActive(false);
	}
	
	/**
	 * This method handles the refunding of Coins entered so far to 
	 * the Customer.
	 */
	public void refundCash(){
		if(getTotalInserted()==0)
			return;
		txCtrl.getCustomerPanel().setChange(getTotalInserted());
		txCtrl.getCustomerPanel().setTotalMoneyInserted(0);
		txCtrl.getCustomerPanel().displayInvalidCoin(false);
		resetReceived();
	}
	
	/**
	 * This method reset the coin received input.
	 */
	public void resetReceived(){
		arlCoins=new ArrayList();
		setTotalInserted(0);
	}
	
	/**
	 * This method activates or deactivates the Coin Input Box.
	 * @param active TRUE to activate, FALSE to deactivate the Coin Input Box.
	 */
	public void setActive(boolean active){
		txCtrl.getCustomerPanel().setCoinInputBoxActive(active); 
	}

	/**
	 * This method sets the total money inserted.
	 * @param totalInserted the total money inserted.
	 */
	public void setTotalInserted(int totalInserted) {
		this.totalInserted = totalInserted;
	}

	/**
	 * This method returns the total money inserted.
	 * @return the total money inserted.
	 */
	public int getTotalInserted() {
		return totalInserted;
	}
}//End of class CoinReceiver