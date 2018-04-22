/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.util.WarningDisplay;

/**
 * This boundary object enables a snack to be displayed and selected&#46; 
 * It also displays the stock availability of the snack&#46;
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class SnackSelectionItem extends Panel{
	private Button btn=new Button("");
	private Label lbl=new Label();
	private WarningDisplay wnd=new WarningDisplay("Not in Stock");
	
	private int snackIdentifier=-1;
	private String name="";
	private int price=0;
	private int quantity=0;
	private boolean isActive=false;
	private boolean isWarningOn=false;
	
	/**
	 * This constructor creates an instance of the SnackSelectionItem&#46;
	 * @param snackName the name of the snack&#46;
	 * @param snackPrice the price of the snack&#46;
	 * @param quantity the quantity of the snack&#46;
	 * @param isActive the active status of the snack&#46;
	 */
	public SnackSelectionItem(int snackIdentifier, String snackName, int snackPrice, int quantity, boolean isActive, boolean isWarningOn){
		this.setSnackIdentifier(snackIdentifier);
		this.setName(snackName);
		this.setPrice(snackPrice);
		this.setState(isActive);
		this.setItemState(isWarningOn);
		init();
	}

	/**
	 * This method initialize the GUI.
	 */
	private void init(){
		btn.setLabel(name);
		lbl.setText(""+price+" C");
		
		lbl.setBackground(Color.lightGray);
		lbl.setFocusable(false);
		lbl.setPreferredSize(new Dimension(50,24));
		setLayout(new GridBagLayout());
		add(btn,new GridBagConstraints(0,0,1,1,1.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			    new Insets(5,10,0,0),10,4));
		add(lbl,new GridBagConstraints(1,0,1,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.NONE,
			    new Insets(5,15,0,0),50,0));
		add(wnd,new GridBagConstraints(2,0,1,1,0.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.NONE,
			    new Insets(5,4,0,0),10,0));
	}
	
	/**
	 * This method attaches a listener to the item.
	 * @param listener the action listener.
	 */
	public void addListener(ActionListener listener){
		btn.addActionListener(listener);
	}
	
	/**
	 * This method remove the listener from the item.
	 * @param listener the action listener.
	 */
	public void removeListener(ActionListener listener){
		btn.removeActionListener(listener);
	}
	
	/**
	 * This method sets the snack identifier.
	 * @param snackIdentifier the snack identifier.
	 */
	public void setSnackIdentifier(int snackIdentifier) {
		this.snackIdentifier = snackIdentifier;
	}

	/**
	 * This method returns the snack identifier.
	 * @return the snack identifier.
	 */
	public int getSnackIdentifier() {
		return snackIdentifier;
	}

	/**
	 * This method sets the name on the Snack Selection Item.
	 * @param name the name of the snack.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the name of the Snack Selection Item.
	 * @return String the name of the snack.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the price on the Snack Selection Item.
	 * @param price the price of the snack
	 */
	public void setPrice(int price) {
		this.price = price;
		lbl.setText(price+" C");
	}

	/**
	 * This method returns the price on the Snack Selection Item.
	 * @return int the price of the snack.
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * This method sets the quantity on the Snack Selection Item.
	 * @param quantity the quantity of the snack.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		if(this.quantity>0){
			this.setItemState(false);
		}
		else{
			this.setItemState(false);
		}
	}

	/**
	 * This method returns the quantity of the Snack Selection Item.
	 * @return quantity of the snack.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * This method activates or deactivates the snack selection item button&#46;
	 * This method also display "OUT OF STOCK" messages where appropriate.
	 * @param isOn TRUE to turn on the warning message and disable the button.
	 */
	public void setItemState(boolean isOn){
		this.isWarningOn=isOn;
		btn.setEnabled(!isOn);
		btn.setBackground(this.getBackground());
		wnd.setState(isOn);
	}
	
	/**
	 * This method returns the item state of the SnackSelectionItem&#46;
	 * This method determine whether the warning message is On or Off.
	 * @return TRUE if the warning message is On, otherwise, FALSE.
	 */
	public boolean getItemState(){
		return this.isWarningOn;
	}

	/**
	 * This method sets the state of the SnackSelectionItem&#46;
	 * @param active the active status of the SnackSelectionItem;
	 * TRUE to activate it, FALSE to deactive it.
	 */
	public void setState(boolean active) {
		this.isActive=active;
		btn.setEnabled(active);
		btn.setBackground(this.getBackground());
	}

	/**
	 * This method returns the state of the SnackSelectionItem&#46;
	 * @return TRUE if the state is active, otherwise, FALSE.
	 */
	public boolean getState() {
		return isActive;
	}
}//End of class SnackSelectionItem