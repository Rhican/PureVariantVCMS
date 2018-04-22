package sg.edu.nus.iss.vmcs;

/**
 * For Attribute level control
 * @author Kyaw Min Thu
 *
 */
public class VariantPointConstants {
	// For log item dispensing variable point.
	public static boolean vLogItemDispensing = true;
	
	// For log payment variable point.
	public static boolean vLogPayment = true;
	
	// For cash payment variable point.
	public static boolean vCashPayment = true;
	
	// For card payment variable point.
	public static boolean vCardPayment = true;
	
	// For larger number denomination give change variable point. 
	// If false, it will be balance number of denomination change giver.
	public static boolean vLargerNoDenoGiveChange = true;

	// For Display "Low Stock" variable point.
	public static boolean vShowLowStock = true;
		
	// For Product-Store variable point.
	// -- Disabled, this variant point will use method level control
//	public static boolean vDrinkStore = true;
//	public static boolean vSnackStore = true;
}
