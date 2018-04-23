/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

/**
 * This represent a column of snacks in the vending machine&#46; It can store a snack of any
 * particular brand type&#46; There can be as many {@link SnacksStoreItem} as desire in the
 * vending machine&#46; This number will be a configurable item for the vending machine&#46;
 * This will be implemented as an instance of StoreItem&#46;
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see ItemsBrand
 * @see DrinksStore
 * @see SnacksBrand
 * @see SnacksStore
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class SnacksStoreItem extends StoreItem {

	/**
	 * This constructor creates an instance of SnacksStoreItem object&#46;
	 * @param ob the snacks brand&#46;
	 * @param qty the snacks quantity&#46;
	 */
	public SnacksStoreItem(ItemsBrand ob, int qty) {
		super((StoreObject) ob, qty);
	}
}//End of class SnacksStoreItem