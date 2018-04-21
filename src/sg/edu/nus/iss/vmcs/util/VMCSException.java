/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.util;

/**
 * This is the VMCS application base exception.
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class VMCSException extends Exception {

	/**
	 * This constructor creates an instance of VMCSException.
	 */
	public VMCSException() {
	}

	/**
	 * This constructor creates an instance of VMCSException.
	 * @param location the location of the exception.
	 * @param msg the message of the exception.
	 */
	public VMCSException(String location, String msg) {
		super(location + " :" + msg);
	}

	/**
	 * This constructor creates an instance of VMCSException.
	 * @param e the VMCSException.
	 */
	public VMCSException(VMCSException e) {
		super(e.getMessage());
	}
}// VMCSException