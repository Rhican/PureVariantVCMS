package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class CashStoreItemTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testCashStoreItemConstructor() throws Exception{
		Coin coin=new Coin(50,100);
		int qty1=10;
		//Act
		CashStoreItem cashStoreItem=new CashStoreItem(coin,	qty1);
		StoreObject storeObject=cashStoreItem.getContent();
		int qty2=cashStoreItem.getQuantity();
		//Assert
	    assertNotNull(storeObject);
	    assertEquals(qty1,qty2);
	}
	
	@Test
	public void testSetGetContent() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=new Coin();
			cashStoreItem.setContent(coin1);
			Coin coin2=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
	}

	@Test
	public void testSetGetQuantity() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=new Coin();
			cashStoreItem.setContent(coin1);
			int qty1=12;
			//Act setQuantity
			cashStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}

	@Test
	public void testStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act store
			cashStoreItem.store();
			Coin coin2=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
	}
	
	@Test
	public void testDecrement() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty1=cashStoreItem.getQuantity();
			//Act decrement
			cashStoreItem.decrement();
			int qty2=cashStoreItem.getQuantity();
			//In the case new quantity is negative, it will be set to 0
			//Therefore, qty1=0 may be equals to qty2=0
			//Assert
			if(!(qty1>=qty2)){
				fail();
			}
		}
	}

	@Test
	public void testIncrement() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore(Store.CASH);
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty1=cashStoreItem.getQuantity();
			//Act increment
			cashStoreItem.increment();
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2-1);
		}
	}
}//End of class CashStoreItemTest