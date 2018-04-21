/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

/**
 * This entity object represents a column of coins in the vending machine&#46;
 * It can store a coin of any particular type&#46; There can be as many CashStoreItem
 * as desire in the vending machine&#46; There can be more than one CashStoreItem storing
 * the same or different types of Coin This number will be configurable&#46; This will
 * be implemented as an instance of StoreItem&#46;
 *
 * @see CashStore
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CashStoreItem extends StoreItem {

	/**
	 * This constructor creates an instance of {@link CashStoreItem} object.
	 * @param coin the coin associated with this CashStoreItem.
	 * @param qty the quantity of the Coin.
	 */
	public CashStoreItem(Coin coin, int qty) {
		super((StoreObject) coin, qty);
	}
}//End of class CashStoreItem