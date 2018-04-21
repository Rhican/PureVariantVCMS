package sg.edu.nus.iss.vmcs.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CoinTest extends TestCase{
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{	
	}
	
	@Test
	public void testCoinConstructor() throws Exception{
		int value=100;
		double weight=55;
		//Act
		Coin coin=new Coin(value,weight);
		//Assert
		assertEquals(coin.getValue(),value);
		assertEquals(coin.getWeight(),weight);
	}
	
	@Test
	public void testSetGetValue() throws Exception{
		Coin coin=new Coin();
		int value1=10;
		//Act setValue
		coin.setValue(value1);
		//Act getValue
		int value2=coin.getValue();
		//Assert
		assertEquals(value1,value2);
	}
	
	@Test
	public void testSetGetWeight() throws Exception{
		Coin coin=new Coin();
		double weight1=20;
		//Act setWeight
		coin.setWeight(weight1);
		//Act getWeight
		double weight2=coin.getWeight();
		//Assert
		assertEquals(weight1,weight2);
	}
}//End of class CoinTest