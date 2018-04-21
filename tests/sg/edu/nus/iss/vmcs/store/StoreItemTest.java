package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class StoreItemTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testStoreItemConstructor() throws Exception{
		String name="ABC";
		int qty=10;
		StoreObject storeObject=new StoreObject(name);
		//Act
		StoreItem storeItem=new StoreItem(storeObject,qty);
		//Assert
		assertNotNull(storeItem.getContent());
		assertEquals(qty,storeItem.getQuantity());
	}
	
	@Test
	public void testSetGetContent() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		Store store=(Store)storeController.getStore(Store.CASH);
		int storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			Coin coin1=new Coin();
			//Act setContent
			storeItem.setContent(coin1);
			//Act getContent
			Coin coin2=(Coin)storeItem.getContent();
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
		store=(Store)storeController.getStore(Store.DRINK);
		storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			DrinksBrand drinksBrand1=new DrinksBrand("A",65);
			//Act setContent
			storeItem.setContent(drinksBrand1);
			//Act getContent
			DrinksBrand drinksBrand2=(DrinksBrand)storeItem.getContent();
			//Assert
			assertNotNull(drinksBrand2);
			assertSame(drinksBrand1,drinksBrand2);
		}
	}

	@Test
	public void testSetGetQuantity() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		Store store=(Store)storeController.getStore(Store.CASH);
		int storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			Coin coin1=new Coin();
			storeItem.setContent(coin1);
			int qty1=12;
			//Act setQuantity
			storeItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=storeItem.getQuantity();
			//Assert
			assertSame(qty1,qty2);
		}
		store=(Store)storeController.getStore(Store.DRINK);
		storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			DrinksBrand drinksBrand1=new DrinksBrand("A",65);
			storeItem.setContent(drinksBrand1);
			int qty1=14;
			//Act setQuantity
			storeItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=storeItem.getQuantity();
			//Assert
			assertSame(qty1,qty2);
		}
	}

	@Test
	public void testStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		Store store=(Store)storeController.getStore(Store.CASH);
		int storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			Coin coin1=(Coin)storeItem.getContent();
			//Act store
			storeItem.store();
			Coin coin2=(Coin)storeItem.getContent();
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
		store=(Store)storeController.getStore(Store.DRINK);
		storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			DrinksBrand drinksBrand1=(DrinksBrand)storeItem.getContent();
			//Act store
			storeItem.store();
			DrinksBrand drinksBrand2=(DrinksBrand)storeItem.getContent();
			//Assert
			assertNotNull(drinksBrand2);
			assertSame(drinksBrand1,drinksBrand2);
		}	
	}
	
	@Test
	public void testDecrement() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		Store store=(Store)storeController.getStore(Store.CASH);
		int storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			int qty1=storeItem.getQuantity();
			//Act decrement
			storeItem.decrement();
			int qty2=storeItem.getQuantity();
			//In the case new quantity is negative, it will be set to 0
			//Therefore, qty1=0 may be equals to qty2=0
			//Assert
			if(!(qty1>=qty2)){
				fail();
			}
		}
		store=(Store)storeController.getStore(Store.DRINK);
		storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			int qty1=storeItem.getQuantity();
			//Act decrement
			storeItem.decrement();
			int qty2=storeItem.getQuantity();
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
		Store store=(Store)storeController.getStore(Store.CASH);
		int storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			int qty1=storeItem.getQuantity();
			//Act increment
			storeItem.increment();
			int qty2=storeItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2-1);
		}
		store=(Store)storeController.getStore(Store.DRINK);
		storeSize=store.getStoreSize();
		for(int i=0;i<storeSize;i++){
			StoreItem storeItem=(StoreItem)store.getStoreItem(i);
			int qty1=storeItem.getQuantity();
			//Act increment
			storeItem.increment();
			int qty2=storeItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2-1);
		}
	}
}//End of class StoreItemTest