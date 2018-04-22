/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

/**
 * This control object is for handling the dispense drink use case.
 * @author SPLE Team04
 */
public class DispenseControllerDecorator implements DispenseComponent {
    private DispenseComponent component;
	
    /**
     * This constructor creates an instance of the object.
     * @param component the DispenseComponent
     */
    public DispenseControllerDecorator(DispenseComponent component){
    	this.component = component;
    }
    
    /**
     * This method updates the whole Drink Selection Box with current names, stocks and prices.
     */
	public void updateDrinkPanel(){
		component.updateDrinkPanel();
	}
	
	/**
     * This method is used to display the latest stock and price information on the Drink Selection Box.
	 * @param index
	 */
	public void updateDrinkSelection(int index){
		component.updateDrinkSelection(index);
	}
	
	/**
	 * This method will be used to activate or deactivate (as indicated through a parameter)
	 * the Drink Selection Box so that transactions can continue or will be disallowed.
	 * @param allow TRUE to activate, FALSE to deactivate the Drink Selection Box.
	 */
	public void allowSelection(boolean allow){
		component.allowSelection(allow);
	}
	
	/**
	 * This method will be used to instruct the Can Collection Box to remove the 
	 * drinks can shape or drink brand name from being displayed.
	 */
	public void ResetCan(){
		component.ResetCan();
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
		return component.dispenseDrink(selectedBrand);
	}
	
	/**
	 * This method will be used to dispense a snack&#46;  It will:
	 * <br>
	 * 1- Instruct the Drinks Store to dispense the snack&#46; It will also instruct the
	 * Can Collection Box to display a can shape&#46;
	 * <br>
	 * 2- Instruct the Store Controller to update the Snack Store Display on the
	 * Machinery Simulator Panel&#46;
	 * <br>
	 * 3- In case of fault detection, it will send a "fault detected" response to the 
	 * Transaction Controller&#46;
	 * @param selectedSnack the selected snack&#46;
	 */
	public boolean dispenseSnack(int selectedSnack){
		return component.dispenseSnack(selectedSnack);
	}
}//End of class DispenseControllerDecorator