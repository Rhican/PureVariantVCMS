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
	
	// For log payment variable point.
	public static boolean vCashPayment = true;
	
	// For log payment variable point.
	public static boolean vCardPayment = true;

	// For Product-Store variable point.
	// -- Disabled, this variant point will use method level control
//	public static boolean vDrinkStore = true;
//	public static boolean vSnackStore = true;
}
