package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class SnacksStoreTest extends TestCase{
	//private String propertyFilename=System.getProperty("propertyFilename");
	private String propertyFilename= "vmcs.properties";
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testSnacksStoreConstructor() throws Exception{
		//SnacksStore constructor does nothing
		SnacksStore snacksStore=new SnacksStore();
		//Assert
		assertNotNull(snacksStore);
	}
	
	@Test
	public void testSetGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		//Act getStoreSize
		int storeSize=snacksStore.getStoreSize();
		//Act setStoreSize
		snacksStore.setStoreSize(storeSize);
		//Assert
		assertEquals(storeSize,snacksStore.getStoreSize());
	}

	@Test
	public void testGetItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		//Act getItems
		StoreItem[] storeItems=snacksStore.getItems();
		//Assert
		assertTrue((storeItems==null||storeItems.length>=0));
	}

	@Test
	public void testAddItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		ItemsBrand snacksBrand=new ItemsBrand();
		//SnacksStoreItem snacksStoreItem=new SnacksStoreItem(snacksBrand,1);
		StoreItem snacksStoreItem=new StoreItem(snacksBrand,1);
		//Act addItem
		snacksStore.addItem(storeSize, snacksStoreItem);
		int storeSize1=snacksStore.getStoreSize();
		//Assert
		assertEquals(storeSize,storeSize1);
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		//Act getStoreItem
		StoreItem storeItem=snacksStore.getStoreItem(-1);
		//Assert
		assertNull(storeItem);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			storeItem=snacksStore.getStoreItem(i);
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
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem snacksStoreItem=snacksStore.getStoreItem(i);
			StoreObject storeObject1=snacksStoreItem.getContent();
			//Act findObject
			StoreObject storeObject2=snacksStore.findObject(storeObject1.getName());
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
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem snacksStoreItem=snacksStore.getStoreItem(i);
			int qty1=snacksStoreItem.getQuantity();
			//Act setQuantity
			snacksStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=snacksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}
}//End of class SnacksStoreTest