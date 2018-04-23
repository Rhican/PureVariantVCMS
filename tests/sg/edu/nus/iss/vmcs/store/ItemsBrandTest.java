package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemsBrandTest extends TestCase{
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{		
	}
	
	@Test
	public void testDrinksBrandConstructor() throws Exception{
		String name="coca";
		int price=120;
		//Act
		ItemsBrand drinksBrand=new ItemsBrand(name,price);
		//Assert
		assertEquals(drinksBrand.getName(),name);
		assertEquals(drinksBrand.getPrice(),price);
	}

	@Test
	public void testSetGetName() throws Exception{
		ItemsBrand drinksBrand=new ItemsBrand();
		String name1="XYZ";
		//Act setName
		drinksBrand.setName(name1);
		//Act getName
		String name2=drinksBrand.getName();
		//Assert
		assertEquals(name1,name2);
	}
	
	@Test
	public void testSetGetPrice() throws Exception{
		ItemsBrand drinksBrand=new ItemsBrand();
		int price1=80;
		drinksBrand.setPrice(price1);
		int price2=drinksBrand.getPrice();
		assertEquals(price1,price2);
	}
}//DrinksBrandTest