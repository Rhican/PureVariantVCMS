/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

/**
 * This entity object stores the name of the drink brand and its price&#46; There can be
 * as many drink brands as desire, for a particular configuration&#46;
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
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
public class DrinksBrand extends StoreObject {
	private int price;

	/**
	 * This constructor creates an instance of DrinksBrand object.
	 */
	public DrinksBrand() {
	}

	/**
	 * This Constructor creates an instance of the DrinksBrand object&#46;
	 * @param name the name of the drinks brand&#46;
	 * @param price the price of the drinks brand&#46;
	 */
	public DrinksBrand(String name, int price) {
		this.price = price;
		this.name = name;
	}

	/**
	 * This method sets the price of the DrinksBrand.
	 * @param p the price of the drinks brand.
	 */
	public void setPrice(int p) {
		price = p;
	}

	/**
	 * This method returns the price of thr DrinksBrand.
	 * @return the price of the drinks brand.
	 */
	public int getPrice() {
		return (price);
	}
}//End of class DrinksBrand