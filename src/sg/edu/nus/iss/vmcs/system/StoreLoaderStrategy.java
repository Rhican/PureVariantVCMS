package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sg.edu.nus.iss.vmcs.store.PropertyLoader;
import sg.edu.nus.iss.vmcs.store.Store;

public class StoreLoaderStrategy {

	private static Map<StoreSaleItemType, FilePropertyLoader> itemLoaders= new HashMap<StoreSaleItemType, FilePropertyLoader>();
	
	public enum StoreSaleItemType {
		Drink(Store.DRINK),
		Snack(Store.SNACK);
		
		private int value;    

	    private StoreSaleItemType(int value) {
	    	this.value = value;
	    }
	
	    public int getValue() {
	    	return value;
	    }
	}
	
	public PropertyLoader GetLoader(StoreSaleItemType itemType) {
		if (!itemLoaders.containsKey(itemType)) {
			InitialiseLoader(itemType);
		}
		
		return (itemLoaders.containsKey(itemType))
				? itemLoaders.get(itemType) 
				: null;
	}
	
	private void InitialiseLoader(StoreSaleItemType itemType)
	{
		FilePropertyLoader loader;
		switch(itemType)
		{			
		case Drink:
			loader = CreateDrinkPropertyLoader();
			break;
			
		case Snack:
			loader = CreateSnackPropertyLoader();
			break;
		default:
			return;
		}
		if (loader == null) return;
		
		try {
			loader.initialize();
			itemLoaders.put(itemType, loader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Create Drink Property Loader when Drink Store feature is Enabled
	 *  	Variant Point: Product\DrinkStore(Enable) 
	 * @return (Drink) File Property Loader
	 */
	private FilePropertyLoader CreateDrinkPropertyLoader() {
		// get the drink file from the environment property file;
		FilePropertyLoader loader = null;
		
		/**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		String file = Environment.getDrinkPropFile();
		loader = new DrinkPropertyLoader(file);
		/**
	     * PV:ENDCOND 
	     */
		
		return loader;
	}
	
	/*
	private FilePropertyLoader CreateDrinkPropertyLoader() {		
		return null;
	}
	*/
	
	/**
	 * Create Snack Property Loader when Snack Store feature is Enabled
	 *  	Variant Point: Product\SnackStore(Enable) 
	 * @return (Snack) File Property Loader
	 */
	private FilePropertyLoader CreateSnackPropertyLoader() {
		// get the snack file from the environment property file;
		FilePropertyLoader loader = null;
		
		/**
		 * PV:IFCOND(pv:hasFeature('Snack'))
		 */
		String file = Environment.getSnackPropFile();
		loader = new SnackPropertyLoader(file);
		/**
	     * PV:ENDCOND 
	     */
		
		return loader;
	}
}
