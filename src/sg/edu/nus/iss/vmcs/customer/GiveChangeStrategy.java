package sg.edu.nus.iss.vmcs.customer;

/**
 * This class is abstract for give change strategy.
 * @author SPLE Team 04
 */
public interface GiveChangeStrategy {
	/**
	 * Calculate give change and giving change from cash store.
	 * @param changeRequired amount of change required
	 * @return
	 */
	public boolean giveChange(int changeRequired);
}
