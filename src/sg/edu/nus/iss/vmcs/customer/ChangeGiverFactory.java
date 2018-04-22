package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

/**
 * This class used as a factory to generate Give Change strategy object.
 * @author SPLE Team 04
 */
public class ChangeGiverFactory {
	public static GiveChangeStrategy createChangeGiver(TransactionController txCtrl) {
		if (VariantPointConstants.vLargerNoDenoGiveChange) {
			return new LargerNoDenoGiveChange(txCtrl);
		} else {
			return new BalanceNoDenoGiveChange(txCtrl);
		}
	}
}
