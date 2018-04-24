package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

/**
 * This class used as a factory to generate Give Change strategy object.
 * @author SPLE Team 04
 */
public class ChangeGiverFactory {
	private static VariantPointConstants VariantPointConstants = new VariantPointConstants();
	
	public static GiveChangeStrategy createChangeGiver(TransactionController txCtrl) {
		
		GiveChangeStrategy strategy = null;
		
		/**
		 * PV:IFCOND(pv:hasFeature('LargerNumberOfDenomination'))
		 */
		strategy = new LargerNoDenoGiveChange(txCtrl);
		/**
	     * PV:ELSEIFCOND(pv:hasFeature('BalanceNumberOfDenomination')) 
	     */
		strategy = new BalanceNoDenoGiveChange(txCtrl);
		/**
	     * PV:ENDCOND 
	     */
		
		return strategy;
	}
}
