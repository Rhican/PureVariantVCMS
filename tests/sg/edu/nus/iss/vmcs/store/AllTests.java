package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestSuite;

/**
 * This objectives of the JUnit testing is to perform isolation unit testing.
 * Testing the individual behavior of the classes in this package.
 * 
 * The behavior testing is organized into 3As; Arrange, Act and Assert.
 * 
 * @author Team SE16T5E
 *
 */
public class AllTests {
	public static TestSuite suite() throws Exception {
		  TestSuite suite=new TestSuite("Store Tests");
		  suite.addTestSuite(CashStoreItemTest.class);
		  suite.addTestSuite(CashStoreTest.class);
		  suite.addTestSuite(CoinTest.class);
		  suite.addTestSuite(DrinksBrandTest.class);
		  suite.addTestSuite(DrinksStoreItemTest.class);
		  suite.addTestSuite(DrinksStoreTest.class);
		  suite.addTestSuite(StoreControllerTest.class);
		  suite.addTestSuite(StoreItemTest.class);
		  suite.addTestSuite(StoreObjectTest.class);
		  return suite;
	}
}//End of class AllTests