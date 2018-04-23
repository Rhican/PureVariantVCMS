/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import sg.edu.nus.iss.vmcs.system.FilePropertyLoader;
import sg.edu.nus.iss.vmcs.system.StoreLoaderStrategy;
import sg.edu.nus.iss.vmcs.system.StoreLoaderStrategy.StoreSaleItemType;

/**
 * This control object manages changes in CashStore attributes and 
 * the DrinksStore attributes.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li, Zehua
 */
public class StoreController {
	private static Map<Integer, Store> stores= new HashMap<Integer, Store>();
	
	private CashStore cStore() 
	{
		return (CashStore) stores.get(Store.CASH);
	};
	
	private DrinksStore dStore() 
	{
		return (DrinksStore) stores.get(Store.DRINK);
	};
	
	private SnacksStore sStore() 
	{
		return (SnacksStore) stores.get(Store.SNACK);
	};

	private PropertyLoader cashLoader;
	private PropertyLoader drinksLoader = null;
	private PropertyLoader snacksLoader = null;

	/**
	 * This constructor creates an instance of StoreController object.
	 * @param cashLoader the cash loader.
	 * @param storeLoaderStrategy the store loader strategy.
	 */
	public StoreController(
		PropertyLoader cashLoader,
		StoreLoaderStrategy storeLoaderStrategy) {
		this.cashLoader = cashLoader;
		
		this.drinksLoader = storeLoaderStrategy.GetLoader(StoreSaleItemType.Drink);
		this.snacksLoader = storeLoaderStrategy.GetLoader(StoreSaleItemType.Snack);
	}

	/**
	 * This method instantiate the {@link CashStore}, {@link DrinksStore} and initialize it.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	public void initialize() throws IOException {
		initializeStores();
	}

	/**
	 * This method initiates the initialization of the {@link DrinkStore}, {@link SnacksStore} and {@link CashStore}
	 * from data read-in from an input file.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	private void initializeStores() throws IOException {
		initializeCashStore();
		initializeDrinkStore();
		initializeSnackStore();
	}

	/**
	 * This method initialize the {@link DrinksStore}.
	 * @throws IOException if fail to initialize drinks store; reading properties.
	 */
	private void initializeDrinkStore() throws IOException {
		
		stores.put(Store.DRINK, new DrinksStore());
		int numOfItems = (drinksLoader != null) ? drinksLoader.getNumOfItems() : 0;
		dStore().setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = dStore().findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			dStore().addItem(i, item);
		}
	}
	
	/**
	 * This method initialize the {@link SnacksStore}.
	 * @throws IOException if fail to initialize snacks store; reading properties.
	 */
	private void initializeSnackStore() throws IOException {

		stores.put(Store.SNACK, new SnacksStore());
		int numOfItems = (snacksLoader != null) ? snacksLoader.getNumOfItems() : 0;
		sStore().setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            SnacksStoreItem item = (SnacksStoreItem) snacksLoader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = sStore().findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			sStore().addItem(i, item);
		}
	}

	/**
	 * This method initialize the {@link CashStore}.
	 * @throws IOException if fail to initialize cash store; reading properties.
	 */
	private void initializeCashStore() throws IOException {
		stores.put(Store.CASH, new CashStore());
		
		// get the cash file from the environment property file;
		int numOfItems = cashLoader.getNumOfItems();
		cStore().setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
		    CashStoreItem item = (CashStoreItem) cashLoader.getItem(i);
			cStore().addItem(i, item);
		}
	}

	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and
	 * update the display on the Machinery Simulator Panel.
	 * @param c the Coin to be stored.
	 */
	public void storeCoin(Coin c) {
		int idx = cStore().findCashStoreIndex(c);
		CashStoreItem item;
		item = (CashStoreItem) this.getStoreItem(Store.CASH, idx);
		item.increment();
	}

	/**
	 * This method return the total size of the {@link Store} of the given type of {@link Store}.
	 * @param type the type of the Store (either CASH, SNACK or DRINK).
	 * @return the size of the store of the given type of Store.
	 */
	public int getStoreSize(int type) {
		Store store = stores.get(type);
		return (store != null) ? store.getStoreSize() : 0;
	}

	/**
	 * This method returns an array of {@link StoreItem} of the given type of {@link Store}.
	 * @param type the type of Store.
	 * @return an array of StoreItem.
	 */
	public StoreItem[] getStoreItems(int type) {
		Store store = stores.get(type);
		return (store != null) ? store.getItems() : new StoreItem[0];
	}

	/**
	 * This method will either:
	 * <br>
	 * - instruct the {@link CashStore} to update the quantity of a {@link Coin} denomination to new
	 * value supplied and update the total cash held in the {@link CashStore}; or
	 * <br>
	 * - instruct the {@link DrinksStore} to update the drinks stock for a {@link DrinksBrand} required
	 * to a new value supplied.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @param qty the quantity of the StoreItem.
	 */
	public void changeStoreQty(int type, int idx, int qty) {
			System.out.println("StoreController.changeStoreQty: type:"+ type+ " qty:"+ qty);
			Store store = stores.get(type);
			if (store != null) 
				store.setQuantity(idx, qty);
	}

	/**
	 * This method returns the {@link StoreItem} with the given {@link Store} type and index of {@link StoreItem}.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @return the StoreItem.
	 */
	public StoreItem getStoreItem(int type, int idx) {
		Store store = stores.get(type);
		return (store != null) ? store.getStoreItem(idx) : null;
	}

	/**
	 * This method will sets the price for the {@link StoreItem} with the given index and price.
	 * @param idx the index of the StoreItem.
	 * @param pr the price of the StoreItem.
	 */
	public void setPrice(int idx, int pr, int type)  {
		switch(type)
		{
			case Store.DRINK: 
				setSnackPrice(idx, pr);
				break;
			case Store.SNACK:
				setDrinkPrice(idx, pr);
				break;
			default:
				return;
		}
	}
	
	private void setDrinkPrice(int idx, int pr)  {
		if (dStore() == null) return;
		
		DrinksStoreItem item;

		item = (DrinksStoreItem) dStore().getStoreItem(idx);
		DrinksBrand bd;

		bd = (DrinksBrand) item.getContent();

		bd.setPrice(pr);
	}
	
	private void setSnackPrice(int idx, int pr)  {
		if (sStore() == null) return;
		SnacksStoreItem item;

		item = (SnacksStoreItem) sStore().getStoreItem(idx);
		SnacksBrand sd;

		sd = (SnacksBrand) item.getContent();

		sd.setPrice(pr);
	}

	/**
	 * This method returns the total number of cash held in the {@link CashStore}.
	 * @return the total number of cash held.
	 */
	public int getTotalCash(){
		int i;
		int size;

		size = cStore().getStoreSize();
		CashStoreItem item;
		int qty;
		int val;
		int tc = 0;
		Coin c;

		for (i = 0; i < size; i++) {
			item = (CashStoreItem) cStore().getStoreItem(i);
			qty = item.getQuantity();
			c = (Coin) item.getContent();
			val = c.getValue();
			tc = tc + qty * val;
		}
		return tc;
	}

	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
	 * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
	 * @return the number of cash transfered.
	 */
	public int transferAll()  {
		int i;
		int cc = 0; // coin quauntity;
		int size = cStore().getStoreSize();

		CashStoreItem item;
		for (i = 0; i < size; i++) {
			item = (CashStoreItem) cStore().getStoreItem(i);
			cc = cc + item.getQuantity();
			item.setQuantity(0);
		}

		return cc;
	}

	/**
	 * This method will close down the store management function of the vending machine.
	 * This involves saving the attributes of the stores to the property file.
	 * @throws IOException if fail to save cash properties and drinks properties.
	 */
	public void closeDown() throws IOException {
		// save back cash property;
		saveCashProperties();
        saveDrinksProperties();
        saveSnacksProperties();
	}

	/**
	 * This method saves the attributes of the {@link CashStore} to the input file.
	 * @throws IOException if fail to save cash properties.
	 */
	private void saveCashProperties() throws IOException {
		int size = cStore().getStoreSize();
		cashLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			cashLoader.setItem(i, cStore().getStoreItem(i));
		}
		cashLoader.saveProperty();
	}

	/**
	 * This method saves the attributes of the {@link DrinksStore} to the input file.
	 * It saves the drink property when simulation is ended.
	 * @throws IOException if fail to save drinks properties.
	 */
	private void saveDrinksProperties() throws IOException {
		if (dStore() == null) return;
		int size = dStore().getStoreSize();
		drinksLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			drinksLoader.setItem(i, dStore().getStoreItem(i));
		}
		drinksLoader.saveProperty();
	}
	
	/**
	 * This method saves the attributes of the {@link SnacksStore} to the input file.
	 * It saves the snack property when simulation is ended.
	 * @throws IOException if fail to save snacks properties.
	 */
	private void saveSnacksProperties() throws IOException {
		if (sStore() == null) return;
		int size = sStore().getStoreSize();
		snacksLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			snacksLoader.setItem(i, sStore().getStoreItem(i));
		}
		snacksLoader.saveProperty();
	}

	/**
	 * This method instructs the {@link DrinksStore} to dispense one drink, and then updates the 
	 * {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It returns TRUE or FALSE to indicate whether dispensing
	 * was successful&#46;
	 * @param idx the index of the drinks to be dispensed&#46;
	 */
	public void dispenseDrink(int idx)  {
		DrinksStoreItem item;
		item = (DrinksStoreItem) getStoreItem(Store.DRINK, idx);
		item.decrement();
	}

	/**
	 * This method returns a {@link Store} of a specified type (i&#46;e&#46; Cash or Drink)&#46;
	 * @param type the type of Store&#46;
	 * @return the Store of the specified type&#46;
	 */
	public Store getStore(int type) {
		return stores.get(type);
	}

	/**
	 * This method instructs the {@link CashStore} to issue a number of {@link Coin} of a specific
	 * denomination, and then updates the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It return TRUE
	 * or FALSE to indicate whether the change issue was successful&#46;
	 * @param idx the index of the Coin&#46;
	 * @param numOfCoins the number of Coin to deduct&#46; 
	 */
	public void giveChange(int idx, int numOfCoins)  {
		CashStoreItem item;
		item = (CashStoreItem) getStoreItem(Store.CASH, idx);
		for (int i = 0; i < numOfCoins; i++)
			item.decrement();
	}
}//End of class StoreController