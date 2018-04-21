/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

/**
 * This control object manages and controls entry to the system by the Maintainer.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class AccessManager {
	private MaintenanceController mctrl;
	private Password pswd;
	private boolean loginState = false;

	/**
	 * This constructor creates an instance of AccessManager object and one instance of
	 * Password object.
	 * @param mc the MaintenanceController.
	 */
	public AccessManager(MaintenanceController mc) {
		mctrl = mc;
		pswd = new Password();
	}
	
	/**
	 * This method close down the Access Manager.
	 */
	public void closeDown() {
	}

	/**
	 * Send a received password string to the Password object for validation&#46; Then
	 * instruct the Password Box to become deactivated&#46;
	 * @param ps the password to be verified&#46;
	 */
	public void processPassword(String ps) {
		boolean psr;

		psr = pswd.validatePassword(ps);
		loginState = psr;
		mctrl.loginMaintainer(psr);
	}

	/**
	 * This method returns the login state.
	 * @return TRUE if login, otherwise, FALSE.
	 */
	public boolean getLoginState() {
		return loginState;
	}

	/**
	 * This method logout the access.
	 */
	public void logout() {
		loginState = false;
	}
}//End of class AccessManager