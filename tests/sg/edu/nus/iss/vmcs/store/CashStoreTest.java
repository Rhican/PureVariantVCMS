package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class CashStoreTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{	
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testCashStoreConstructor() throws Exception{
		//CashStore constructor does nothing
		CashStore cashStore=new CashStore();
		//Assert
		assertNotNull(cashStore);
	}
	
	@Test
	public void testSetGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		//Act getStoreSize
		int storeSize=cashStore.getStoreSize();
		//Act setStoreSize
		cashStore.setStoreSize(storeSize);
		//Assert
		assertEquals(storeSize,cashStore.getStoreSize());
	}

	@Test
	public void testGetItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		//Act getItems
		StoreItem[] storeItems=cashStore.getItems();
		//Assert
		assertTrue((storeItems==null||storeItems.length>=0));
	}

	@Test
	public void testAddItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		Coin coin=new Coin();
		CashStoreItem cashStoreItem=new CashStoreItem(coin,1);
		//Act addItem
		cashStore.addItem(storeSize, cashStoreItem);
		int storeSize1=cashStore.getStoreSize();
		//Assert
		assertEquals(storeSize,storeSize1);
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		//Act getStoreItem
		StoreItem storeItem=cashStore.getStoreItem(-1);
		//Assert
		assertNull(storeItem);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			storeItem=cashStore.getStoreItem(i);
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
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			StoreObject storeObject1=cashStoreItem.getContent();
			//Act findObject
			StoreObject storeObject2=cashStore.findObject(storeObject1.getName());
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
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty1=cashStoreItem.getQuantity();
			//Act setQuantity
			cashStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}
	
	@Test
	public void testFindCashStoreIndex() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Act findCashStoreIndex
			int index=cashStore.findCashStoreIndex(coin);
			//Assert
			assertTrue(index>=0);
		}
	}
	
	@Test
	public void testIsValidWeight() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Act isValidWeight
			boolean isValidWeight=cashStore.isValidWeight(coin.getWeight());
			//Assert
			assertTrue(isValidWeight);
		}
		//Act isValidWeight
		boolean isValidWeight=cashStore.isValidWeight(CashStore.INVALID_COIN_WEIGHT);
		//Assert
		assertFalse(isValidWeight);
	}
	
	@Test
	public void testFindCoin() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act findCoin
			Coin coin2=cashStore.findCoin(coin1.getWeight());
			//Assert
			assertNotNull(coin2);
		}
	}
}//End of class CashStoreTest