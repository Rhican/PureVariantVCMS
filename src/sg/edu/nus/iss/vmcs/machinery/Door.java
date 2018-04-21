/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.machinery;

/**
 * This object represents the door of the vending machine&#46; It is opened so that the
 * machine can be re-stocked with cans and cash.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class Door {
	private boolean isClosed;

	/**
	 * This constructor creates an instance of Door object.
	 */
	public Door() {
		isClosed = true;
	}

	/**
	 * This method sets the state of the Door.
	 * @param isClosed TRUE to close the door, FALSE to open the door.
	 */
	public void setState(boolean isClosed) {
		this.isClosed = isClosed;
	}

	/**
	 * This method determine whether the door is closed.
	 * @return TRUE if the door is open, otherwise FALSE.
	 */
	public boolean isDoorClosed() {
		return isClosed;
	}
}//End of class Door