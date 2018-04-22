package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SnacksBrandTest extends TestCase{
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{		
	}
	
	@Test
	public void testSnacksBrandConstructor() throws Exception{
		String name="M & Ms";
		int price=120;
		//Act
		SnacksBrand snacksBrand=new SnacksBrand(name,price);
		//Assert
		assertEquals(snacksBrand.getName(),name);
		assertEquals(snacksBrand.getPrice(),price);
	}

	@Test
	public void testSetGetName() throws Exception{
		SnacksBrand snacksBrand=new SnacksBrand();
		String name1="XYZ";
		//Act setName
		snacksBrand.setName(name1);
		//Act getName
		String name2=snacksBrand.getName();
		//Assert
		assertEquals(name1,name2);
	}
	
	@Test
	public void testSetGetPrice() throws Exception{
		SnacksBrand snacksBrand=new SnacksBrand();
		int price1=80;
		snacksBrand.setPrice(price1);
		int price2=snacksBrand.getPrice();
		assertEquals(price1,price2);
	}
}//SnacksBrandTest