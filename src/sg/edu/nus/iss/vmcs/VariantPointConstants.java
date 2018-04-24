package sg.edu.nus.iss.vmcs;

/**
 * For Attribute level control
 * @author Kyaw Min Thu
 *
 */
public class VariantPointConstants {
	
	private boolean vLogItemDispensing; // For log item dispensing variable point.
	private boolean vLogPayment; // For log payment variable point.
	private boolean vCashPayment; // For cash payment variable point.
	private boolean vCardPayment; // For card payment variable point.
	private boolean vLargerNoDenoGiveChange; // For larger number denomination give change variable point. // If false, it will be balance number of denomination change giver.
	private boolean vShowLowStock; // For Display "Low Stock" variable point.
	private boolean vDrinkStore; // For Product-Store Drink variable point.
	private boolean vSnackStore; // For Product-Store Snack variable point.
	
	public VariantPointConstants() {
		/**
		 * PV:IFCOND(pv:hasFeature('LogDispenseProduct'))
		 */
		vLogItemDispensing = true;
		/**
	     * PV:ELSECOND 
	     */
		vLogItemDispensing = false;
		/**
	     * PV:ENDCOND 
	     */
		
		
		/**
		 * PV:IFCOND(pv:hasFeature('LogPayment'))
		 */
		vLogPayment = true;
		/**
	     * PV:ELSECOND 
	     */
		vLogPayment = false;
		/**
	     * PV:ENDCOND 
	     */
		
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
		 */
		vCashPayment = true;
		/**
	     * PV:ELSECOND 
	     */
		vCashPayment = false;
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
		 */
		vCardPayment = true;
		/**
	     * PV:ELSECOND 
	     */
		vCardPayment = false;
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('LargerNumberOfDenomination'))
		 */
		vLargerNoDenoGiveChange = true;
		/**
	     * PV:ELSECOND 
	     */
		vLargerNoDenoGiveChange = false;
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('LowInStock'))
		 */
		vShowLowStock = true;
		/**
	     * PV:ELSECOND 
	     */
		vShowLowStock = false;
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		vDrinkStore = true;
		/**
	     * PV:ELSECOND 
	     */
		vDrinkStore = false;
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('Snack'))
		 */
		vSnackStore = true;
		/**
	     * PV:ELSECOND 
	     */
		vSnackStore = false;
		/**
	     * PV:ENDCOND 
	     */
	}
	
	public boolean isvLogItemDispensing() {
		return vLogItemDispensing;
	}

	public boolean isvLogPayment() {
		return vLogPayment;
	}

	public boolean isvCashPayment() {
		return vCashPayment;
	}

	public boolean isvCardPayment() {
		return vCardPayment;
	}

	public boolean isvLargerNoDenoGiveChange() {
		return vLargerNoDenoGiveChange;
	}

	public boolean isvShowLowStock() {
		return vShowLowStock;
	}

	public boolean isvDrinkStore() {
		return vDrinkStore;
	}

	public boolean isvSnackStore() {
		return vSnackStore;
	}

}
