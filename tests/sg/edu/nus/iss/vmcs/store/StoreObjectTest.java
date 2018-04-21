package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreObjectTest extends TestCase{
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testStoreObjectConstructor() throws Exception{
		//Act
		String name="ABC";
		StoreObject storeObject=new StoreObject(name);
		//Assert
		assertNotNull(storeObject);
		assertEquals(storeObject.getName(),name);
	}
	
	@Test
	public void testSetGetName() throws Exception{
		StoreObject storeObject=new StoreObject();
		String name1="ABC";
		//Act setName
		storeObject.setName(name1);
		//Act getName
		String name2=storeObject.getName();
		//Assert
		assertEquals(name1,name2);
	}
}//End of class StoreObjectTest