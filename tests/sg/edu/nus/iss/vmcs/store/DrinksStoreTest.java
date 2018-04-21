package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class DrinksStoreTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testDrinksStoreConstructor() throws Exception{
		//DrinksStore constructor does nothing
		DrinksStore drinksStore=new DrinksStore();
		//Assert
		assertNotNull(drinksStore);
	}
	
	@Test
	public void testSetGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getStoreSize
		int storeSize=drinksStore.getStoreSize();
		//Act setStoreSize
		drinksStore.setStoreSize(storeSize);
		//Assert
		assertEquals(storeSize,drinksStore.getStoreSize());
	}

	@Test
	public void testGetItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getItems
		StoreItem[] storeItems=drinksStore.getItems();
		//Assert
		assertTrue((storeItems==null||storeItems.length>=0));
	}

	@Test
	public void testAddItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		DrinksBrand drinksBrand=new DrinksBrand();
		DrinksStoreItem drinksStoreItem=new DrinksStoreItem(drinksBrand,1);
		//Act addItem
		drinksStore.addItem(storeSize, drinksStoreItem);
		int storeSize1=drinksStore.getStoreSize();
		//Assert
		assertEquals(storeSize,storeSize1);
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		//Act getStoreItem
		StoreItem storeItem=drinksStore.getStoreItem(-1);
		//Assert
		assertNull(storeItem);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			storeItem=drinksStore.getStoreItem(i);
			//Assert
			assertNotNull(storeItem);
		}
	}

	@Test
	public void testFindObject() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			StoreObject storeObject1=drinksStoreItem.getContent();
			//Act findObject
			StoreObject storeObject2=drinksStore.findObject(storeObject1.getName());
			//Assert
			assertEquals(storeObject1,storeObject2);
		}
	}
	
	@Test
	public void testSetQuantity() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty1=drinksStoreItem.getQuantity();
			//Act setQuantity
			drinksStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}
}//End of class DrinksStoreTest