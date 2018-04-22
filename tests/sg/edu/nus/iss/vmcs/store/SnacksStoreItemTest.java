package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.MainController;

public class SnacksStoreItemTest extends TestCase{
	private String propertyFilename= "vmcs.properties";
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testSnacksStoreItemConstructor() throws Exception{
		SnacksBrand snacksBrand=new SnacksBrand("XYZ",80);
		int qty1=10;
		//Act
		SnacksStoreItem snacksStoreItem=new SnacksStoreItem(snacksBrand,qty1);
		StoreObject storeObject=snacksStoreItem.getContent();
		int qty2=snacksStoreItem.getQuantity();
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
		SnacksStore snackStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snackStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			SnacksStoreItem snackStoreItem=(SnacksStoreItem)snackStore.getStoreItem(i);
			SnacksBrand snacksBrand1=new SnacksBrand();
			//Act setContent
			snackStoreItem.setContent(snacksBrand1);
			//Act getContent
			SnacksBrand snacksBrand2=(SnacksBrand)snackStoreItem.getContent();
			//Assert
			assertNotNull(snacksBrand2);
			assertSame(snacksBrand1,snacksBrand2);
		}
	}

	@Test
	public void testSetGetQuantity() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			SnacksStoreItem snacksStoreItem=(SnacksStoreItem)snacksStore.getStoreItem(i);
			SnacksBrand snacksBrand1=new SnacksBrand();
			snacksStoreItem.setContent(snacksBrand1);
			int qty1=12;
			//Act setQuantity
			snacksStoreItem.setQuantity(qty1);
			//Act getQuantity
			int qty2=snacksStoreItem.getQuantity();
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
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			SnacksStoreItem snacksStoreItem=(SnacksStoreItem)snacksStore.getStoreItem(i);
			SnacksBrand snacksBrand1=(SnacksBrand)snacksStoreItem.getContent();
			//Act store
			snacksStoreItem.store();
			SnacksBrand snacksBrand2=(SnacksBrand)snacksStoreItem.getContent();
			//Assert
			assertNotNull(snacksBrand2);
			assertSame(snacksBrand1,snacksBrand2);
		}
	}
	
	@Test
	public void testDecrement() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		storeController.initialize();
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			SnacksStoreItem snacksStoreItem=(SnacksStoreItem)snacksStore.getStoreItem(i);
			int qty1=snacksStoreItem.getQuantity();
			//Act decrement
			snacksStoreItem.decrement();
			int qty2=snacksStoreItem.getQuantity();
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
		SnacksStore snacksStore=(SnacksStore)storeController.getStore(Store.SNACK);
		int storeSize=snacksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			SnacksStoreItem snacksStoreItem=(SnacksStoreItem)snacksStore.getStoreItem(i);
			int qty1=snacksStoreItem.getQuantity();
			//Act increment
			snacksStoreItem.increment();
			int qty2=snacksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2-1);
		}
	}
}//End of class SnackStoreItemTest