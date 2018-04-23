package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class DrinksStoreItemTest extends TestCase{
	//private String propertyFilename=System.getProperty("propertyFilename");
	private String propertyFilename= "vmcs.properties";
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testDrinksStoreItemConstructor() throws Exception{
		ItemsBrand drinksBrand=new ItemsBrand("XYZ",80);
		int qty1=10;
		//Act
		DrinksStoreItem drinksStoreItem=new DrinksStoreItem(drinksBrand,qty1);
		StoreObject storeObject=drinksStoreItem.getContent();
		int qty2=drinksStoreItem.getQuantity();
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
		DrinksStore drinkStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinkStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinkStoreItem=(DrinksStoreItem)drinkStore.getStoreItem(i);
			ItemsBrand drinksBrand1=new ItemsBrand();
			//Act setContent
			drinkStoreItem.setContent(drinksBrand1);
			//Act getContent
			ItemsBrand drinksBrand2=(ItemsBrand)drinkStoreItem.getContent();
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
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			ItemsBrand drinksBrand1=new ItemsBrand();
			drinksStoreItem.setContent(drinksBrand1);
			int qty1=12;
			//Act setQuantity
			drinksStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=drinksStoreItem.getQuantity();
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
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			ItemsBrand drinksBrand1=(ItemsBrand)drinksStoreItem.getContent();
			//Act store
			drinksStoreItem.store();
			ItemsBrand drinksBrand2=(ItemsBrand)drinksStoreItem.getContent();
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
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty1=drinksStoreItem.getQuantity();
			//Act decrement
			drinksStoreItem.decrement();
			int qty2=drinksStoreItem.getQuantity();
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
		DrinksStore drinksStore=(DrinksStore)storeController.getStore(Store.DRINK);
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty1=drinksStoreItem.getQuantity();
			//Act increment
			drinksStoreItem.increment();
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2-1);
		}
	}
}//End of class DrinkStoreItemTest