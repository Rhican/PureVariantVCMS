package sg.edu.nus.iss.vmcs.store;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import sg.edu.nus.iss.vmcs.VariantPointConstants;
import sg.edu.nus.iss.vmcs.customer.CardPaymentController;
import sg.edu.nus.iss.vmcs.customer.ChangeGiver;
import sg.edu.nus.iss.vmcs.customer.CustomerPanel;
import sg.edu.nus.iss.vmcs.customer.DispenseComponent;
import sg.edu.nus.iss.vmcs.customer.PaymentComponent;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulationController;
import sg.edu.nus.iss.vmcs.util.VMCSConstants;

/**
 * Test class for Give change strategy.
 * @author SPLE Team 04
 *
 */
public class ChangeGiverTest extends TestCase {
	@Mock
	CustomerPanel custPanel;
	@Mock
	DispenseComponent dispenseCtrl;
	@Mock
	PaymentComponent paymentReceiver;
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
	ChangeGiver cg;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mainCtrl = new MainController(simulatorCtrl, machineryCtrl, maintenanceCtrl, txCtrl
										, storeCtrl, VMCSConstants.PROP_VMCS);
		Environment.initialize(VMCSConstants.PROP_VMCS);
		txCtrl = new TransactionController(mainCtrl, custPanel, dispenseCtrl, cg, paymentReceiver);
		cg = new ChangeGiver(txCtrl);
	}

	@Test
	public void testLargerNoDenoGiveChange() {
		VariantPointConstants.vLargerNoDenoGiveChange = true;
		assertTrue(cg.giveChange(50));
		assertTrue(cg.giveChange(0));
	}

	@Test
	public void testBalanceNoDenoGiveChange() {
		VariantPointConstants.vLargerNoDenoGiveChange = false;
		assertTrue(cg.giveChange(50));
		assertTrue(cg.giveChange(0));
	}
}
