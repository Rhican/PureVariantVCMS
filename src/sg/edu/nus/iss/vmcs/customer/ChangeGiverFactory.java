package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

public class ChangeGiverFactory {
	public static GiveChangeStrategy createChangeGiver(TransactionController txCtrl) {
		if (VariantPointConstants.vLargerNoDenoGiveChange) {
			return new LargerNoDenoGiveChange(txCtrl);
		} else {
			return new BalanceNoDenoGiveChange(txCtrl);
		}
	}
}
