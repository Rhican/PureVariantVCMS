/*
 *
 */
package sg.edu.nus.iss.vmcs.system;


import sg.edu.nus.iss.vmcs.store.*;

/**
 * This control object manages the initialization of the SnacksStore
 *
 * @version 2018 
 * @author Zehua
 */

public class SnackPropertyLoader extends FilePropertyLoader {

	private static final String NAME_LABEL     = "Name";
	private static final String PRICE_LABEL    = "Price";
	private static final String QUANTITY_LABEL = "Quantity";

	/**
	 * This constructor creates an instance of the SnackPropertyLoader object.
	 * @param filen the file name of the snack property file.
	 */
	public SnackPropertyLoader(String filen) {
		super(filen);
	}

	/**
	 * This method reads the data from the hash table and creates a SnackStoreItem.
	 * @param index the index of the store item.
	 * @return StoreItem the store item of the given index.
	 */
	public StoreItem getItem(int index) {
		int idx = index + 1;
		ItemsBrand brand = new ItemsBrand();

		String name = new String(NAME_LABEL + idx);
		String value = getValue(name);
		brand.setName(value);

		name = new String(PRICE_LABEL + idx);
		value = getValue(name);
		brand.setPrice(Integer.parseInt(value));

		name = new String(QUANTITY_LABEL + idx);
		value = getValue(name);
		int qty = Integer.parseInt(value);

		SnacksStoreItem item = new SnacksStoreItem(brand, qty);
		return item;

	}

	/**
	 * This method updates the hash table with the data from the SnackStoreItem.
	 * @param index the index of the store item.
	 * @param snacksItem the store item of the given index.
	 */
	public void setItem(int index, StoreItem snacksItem) {
		int idx = index + 1;

		SnacksStoreItem item = (SnacksStoreItem) snacksItem;
		ItemsBrand brand = (ItemsBrand) item.getContent();
		String itn = new String(NAME_LABEL + idx);
		setValue(itn, brand.getName());

		itn = new String(PRICE_LABEL + idx);
		setValue(itn, String.valueOf(brand.getPrice()));

		itn = new String(QUANTITY_LABEL + idx);
		setValue(itn, String.valueOf(item.getQuantity()));

	}
}//End of class SnackPropertyLoader