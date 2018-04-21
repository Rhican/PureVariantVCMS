/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.maintenance;

import sg.edu.nus.iss.vmcs.system.*;

/**
 * This entity object stores the correct password.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class Password {

	/**
	 * This constructor creates and instance of the Password object.
	 */
	public Password() {
	}

	/**
	 * This method  compare the received Password with the authorized password string stored
	 * in the object, and then return a message to the AccessManager saying whether the
	 * password is valid or invalid.
	 * @param psi the received password.
	 * @return TRUE if password is valid, otherwise, FALSE.
	 */
	public boolean validatePassword(String psi) {
		// the correct password is obtained from the property file.
		// The input password psi is compared to the correct one from the property file.
		if (psi.compareTo(Environment.getPassword()) == 0)
			return true;
		else
			return false;
	}
}//End of class Passowrd