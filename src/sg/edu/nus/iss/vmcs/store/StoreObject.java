/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

/**
 * This entity object represents the generic object that is contained in a {@link StoreItem}.
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */

public class StoreObject {
	String name;

	/**
	 * This constructor creates an instance of StoreObject object.
	 */
	public StoreObject() {
	}

	/**
	 * This constructor creates an instance of StoreObject object.
	 * @param n the name of the StoreObject.
	 */
	public StoreObject(String n) {
		name = n;
	}

	/**
	 * This method returns the name of the StoreObject.
	 * @return the name of the StoreObject.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the namber of the StoreObject.
	 * @param n the name of the StoreObject.
	 */
	public void setName(String n) {
		name = n;
	}
}//End of class StoreObject