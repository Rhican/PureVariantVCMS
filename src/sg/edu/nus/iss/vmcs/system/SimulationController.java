/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object represents the operating system user interface at which the VMCS
 * application can be launched.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class SimulationController {
	private SimulatorControlPanel scp = null;
	public  MainController        mCtrl = null;

	/**
	 * This constructor creates an instance of the SimulationController object.
	 * @param ctrl the MainController.
	 */
	public SimulationController(MainController ctrl) {
		mCtrl = ctrl;
		scp = new SimulatorControlPanel(this);
	}

	/**
	 * This method returns the SimulatorControlPanel.
	 * @return the SimulatorControlPanel.
	 */
	public SimulatorControlPanel getSimulatorControlPanel() {
		return (scp);
	}

	/**
	 * Setup the Simulator Control Panel&#46; This will be achieved as follows:
	 * <br>
	 * 1- Create and display the panel&#46;
	 * <br>
	 * 2- Activate the Begin Simulation Button&#46;
	 * <br>
	 * 3- Deactivate the rest of the buttons on the panel&#46;
	 */
	public void displaySimulatorControlPanel() {
		scp.display();
	}

	/**
	 * This method will instruct all four panels of the vending machine to close down
	 * when instructed by the MainController&#46;
	 */
	public void stop() {
		mCtrl.closeDown();
	}

	/**
	 * This method will instruct all four panels of the vending machine to close down
	 * when instructed by the MainController.
	 */
	public void closeDown() {
		scp.closeDown();
	}

	/**
	 * This method is triggered when the Begin Simulation Button is pressed on the
	 * SimulatorControlPanel&#46; It will start the simulation of the vending machine
	 * by activating the three panel buttons namely; Active Customer Panel Button,
	 * Activate Maintainer Panel Button, and Activate Machinery Simulator Button&#46;
	 */
	public void start() {
		scp.setSimulationActive(true);
		scp.setActive(SimulatorControlPanel.SIMUL_BEGIN, false);
	}

	/**
	 * If FALSE is passed the Begin Simulation Button is enabled and the other buttons
	 * of the SimulatorControlPanel are disabled&#46; If TRUE is passed the Begin Simulation
	 * Button is disabled and the other buttons of SimulatorControlPanel are enabled&#46; 
	 * @param vl the active status of the simulation&#46;
	 */
	public void setSimulationActive(boolean vl) {
		scp.setSimulationActive(vl);
	}

	/**
	 * Setup the Machinery Simulator Panel&#46; This will be achieved as follows:
	 * <br>
	 * 1- Deactivate the Activate Machinery Simulator Button&#46;
	 * <br>
	 * 2- Display Machinery Simulator Panel with initial values set&#46;
	 * <br>
	 * 3- Deactivate the panel&#46;
	 */
	public void setupSimulator() {
		//MaintenanceController maintenanceCtrl;
		//maintenanceCtrl = mCtrl.getMaintenanceController();
		MachineryController machCtrl;

		machCtrl = mCtrl.getMachineryController();
		scp.setActive(SimulatorControlPanel.ACT_MACHINERY, false);
		try {
			// activate when not login
			// always diaply the door locked; isOpen false
			machCtrl.displayMachineryPanel();

			// display drink stock;
			machCtrl.displayDrinkStock();

			// display coin quantity;
			machCtrl.displayCoinStock();

			machCtrl.displayDoorState();
		} catch (VMCSException e) {
			System.out.println("SimulationController.setupSimulator:" + e);
		}
	}

	/**
	 * Setup the Maintenance Panel&#46; This will be achieved as follows:
	 * <br>
	 * 1- Send a message to deactive the Activate Maintainer Panel Button&#46;
	 * <br>
	 * 2- Send a message to instruct all its constituent objects to display themselves,
	 * and also instruct them all, except for Password Box to become deactivated&#46;
	 */
	public void setupMaintainer() {
		MaintenanceController mctrl;
		mctrl = mCtrl.getMaintenanceController();
		scp.setActive(SimulatorControlPanel.ACT_MAINTAINER, false);
		mctrl.displayMaintenancePanel();
	}
	
	/**
	 * Setup the Customer Panel&#46; This will be achieved as follows:
	 * <br>
	 * 1- Send a message to deactivate the Activate Customer Panel Button&#46;
	 * <br>
	 * 2- Send a message to the Customer Panel instructing it to display all its
	 * constituent interface objects&#46;
	 * <br>
	 * 3- Display the current values for out of stock brands and prices&#46;
	 * <br>
	 * 4- Activate the No Change Available Display (if necessary)&#46;
	 */
	public void setupCustomer() {
		TransactionController cctrl;
		cctrl = mCtrl.getTransactionController();
		scp.setActive(SimulatorControlPanel.ACT_CUSTOMER, false);
		cctrl.displayCustomerPanel();
	}

	/**
	 * This method returns the MainController.
	 * @return the MainController.
	 */
	public MainController getMainController() {
		return mCtrl;
	}
}//End of class SimulationController
