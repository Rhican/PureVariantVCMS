package sg.edu.nus.iss.vmcs.store;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import sg.edu.nus.iss.vmcs.VariantPointConstants;
import sg.edu.nus.iss.vmcs.customer.CardPayment;
import sg.edu.nus.iss.vmcs.customer.CardPaymentController;
import sg.edu.nus.iss.vmcs.customer.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.CoinReceiver;
import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.DispenseControllerDecorator;
import sg.edu.nus.iss.vmcs.customer.PaymentDecorator;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulationController;
import sg.edu.nus.iss.vmcs.util.VMCSConstants;

/**
 * Test class for payment.
 * @author SPLE Team 04
 *
 */
public class PaymentControllerTest extends TestCase {
	@Mock
	CustomerPanel custPanel;
	@Mock
	DispenseControllerDecorator dispenseCtrl;
	@Mock
	PaymentDecorator paymentDecorator;
	@Mock
	ChangeGiver changeGiver;
	@Mock
	SimulationController simulatorCtrl;
	@Mock
	MachineryController machineryCtrl;
	@Mock
	MaintenanceController maintenanceCtrl;
	@Mock
	StoreController storeCtrl;
	@Mock
	CardPaymentController cardPaymentCtrl;

	MainController mainCtrl;
	TransactionController txCtrl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mainCtrl = new MainController(simulatorCtrl, machineryCtrl, maintenanceCtrl, txCtrl
										, storeCtrl, VMCSConstants.PROP_VMCS);
		mainCtrl.initialize();
		txCtrl = new TransactionController(mainCtrl, custPanel, dispenseCtrl, changeGiver, paymentDecorator);
	}

	@Test
	public void testMakePaymentWithCash() {
		String _50C = "35.0";
		// Only cash mode available for payment.
		CoinReceiver coinReceiver = new CoinReceiver(txCtrl);
		PaymentDecorator payment = new PaymentDecorator(coinReceiver);
		txCtrl.setPaymentDecorator(payment);
		VariantPointConstants.vCardPayment = false;
		VariantPointConstants.vCashPayment = true;
		txCtrl.setPrice(50);
		payment.makePayment(_50C);
		assertTrue(coinReceiver.getTotalInserted() == 0);
		
		// Test not enough coin input.
		txCtrl.setPaymentDecorator(payment);
		txCtrl.setPrice(100);
		payment.makePayment(_50C);
		assertTrue(coinReceiver.getTotalInserted() == 50);

		// Both card and cash mode available for payment.
		txCtrl.setPaymentDecorator(payment);
		VariantPointConstants.vCardPayment = true;
		VariantPointConstants.vCashPayment = true;
		txCtrl.setPrice(50);
		payment.makePayment(_50C);
		assertTrue(coinReceiver.getTotalInserted() == 0);
		
		// Test not enough coin input.
		txCtrl.setPaymentDecorator(payment);
		txCtrl.setPrice(100);
		payment.makePayment(_50C);
		assertTrue(coinReceiver.getTotalInserted() == 50);
	}

	@Test
	public void testMakePaymentWithCard() {
		// Only card mode available for payment.
		CardPayment cardPayment = new CardPayment(txCtrl);
		PaymentDecorator payment = new PaymentDecorator(cardPayment);
		txCtrl.setPaymentDecorator(payment);
		VariantPointConstants.vCardPayment = true;
		VariantPointConstants.vCashPayment = false;
		txCtrl.setPrice(50);
		payment.makePayment("EZ Link card 1");
		assertTrue(cardPayment.isPaymentSuccess());
		
		// Both card and cash mode available for payment.
		txCtrl.setPaymentDecorator(payment);
		VariantPointConstants.vCardPayment = true;
		VariantPointConstants.vCashPayment = true;
		txCtrl.setPrice(50);
		payment.makePayment("EZ Link card 1");
		assertTrue(cardPayment.isPaymentSuccess());
	}
}
