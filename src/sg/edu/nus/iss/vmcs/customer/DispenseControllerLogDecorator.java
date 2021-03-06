/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.log.VMCSLogger;

/**
 * This control object is for handling the dispense drink use case with audit trail.
 * @author SPLE Team04
 */
public class DispenseControllerLogDecorator extends DispenseControllerDecorator {
	private VMCSLogger logger = new VMCSLogger();
	
    /**
     * This constructor creates an instance of the object.
     * @param component the DispenseComponent
     */
    public DispenseControllerLogDecorator(DispenseComponent component){
    	super(component);
    }
    
    /**
     * This method updates the whole Drink Selection Box with current names, stocks and prices.
     */
	public void updateDrinkPanel(){
		logger.debug("update drink panel.");
		super.updateDrinkPanel();
	}
	
	/**
     * This method is used to display the latest stock and price information on the Drink Selection Box.
	 * @param index
	 */
	public void updateDrinkSelection(int index){
		logger.debug("update drink selection index="+index);
		super.updateDrinkSelection(index);
	}
	
	/**
	 * This method will be used to activate or deactivate (as indicated through a parameter)
	 * the Drink Selection Box so that transactions can continue or will be disallowed.
	 * @param allow TRUE to activate, FALSE to deactivate the Drink Selection Box.
	 */
	public void allowSelection(boolean allow){
		logger.debug("allow selection allow="+allow);
		super.allowSelection(allow);
	}
	
	/**
	 * This method will be used to instruct the Can Collection Box to remove the 
	 * drinks can shape or drink brand name from being displayed.
	 */
	public void ResetCan(){
		logger.debug("reset can");
		super.ResetCan();
	}
	
	/**
	 * This method will be used to dispense a drink&#46;  It will:
	 * <br>
	 * 1- Instruct the Drinks Store to dispense the drink&#46; It will also instruct the
	 * Can Collection Box to display a can shape&#46;
	 * <br>
	 * 2- Instruct the Store Controller to update the Drink Store Display on the
	 * Machinery Simulator Panel&#46;
	 * <br>
	 * 3- In case of fault detection, it will send a "fault detected" response to the 
	 * Transaction Controller&#46;
	 * @param selectedBrand the selected brand&#46;
	 */
	public boolean dispenseDrink(int selectedBrand){
		logger.debug("dispense drink selectedBrand="+selectedBrand);
		return super.dispenseDrink(selectedBrand);
	}
}//End of class DispenseControllerLogDecorator