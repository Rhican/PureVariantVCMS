/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JToggleButton;


import sg.edu.nus.iss.vmcs.VariantPointConstants;
import sg.edu.nus.iss.vmcs.system.SimulatorControlPanel;
import sg.edu.nus.iss.vmcs.util.LabelledValue;
import sg.edu.nus.iss.vmcs.util.WarningDisplay;

/**
 * This class Customer Panel display the GUI interactive with the Customer&#46;
 * 
 * This panel simulates the vending machine's customer interface panel&#46; It will
 * enable the user (Customer) to:
 * <ol>
 * <li>
 * insert coins;
 * </li>
 * <li>
 * select Brands;
 * </li>
 * <li>
 * terminate Transaction&#46;  
 * </li> 
 * </ol>
 * It will also provide the following display functions;
 * <ol>
 * <li>
 * display total money inserted.
 * </li>
 * <li>
 * indicate coin not valid.
 * </li>
 * <li>
 * indicate no change available.
 * </li>
 * <li>
 * display the value of the change to be collected.
 * </li>
 * <li>
 * display an icon representing the dispensed drink.
 * </li>
 * </ol>
 *
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class CustomerPanel extends Dialog {
	private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	private int frameX=0;
	private int frameY=0;
	private int frameWidth=300;
	private int frameHeight=400;
	private int screenWidth=screen.width;
	private int screenHeight=screen.height;
	
	private static final String TITLE = "Customer Panel";
	private TransactionController txCtrl;

	private Panel pan0=new Panel(), pan1 = new Panel();
    private Label lblTitle=new Label("VMCS Soft Drinks Dispenser");
    private Label lblEnterCoins=new Label("Enter Coins Here");

    /**
	 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
	 */
    private CoinInputBox coinInputBox;
	/**
     * PV:ENDCOND 
     */
	
	/**
	 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
	 */
    private CardDetector cardDetector;
	/**
     * PV:ENDCOND 
     */
    
    /**
	 * PV:IFCOND(pv:hasFeature('Drink'))
	 */
    private DrinkSelectionBox drinkSelectionBox;
	/**
     * PV:ENDCOND 
     */
	
	/**
	 * PV:IFCOND(pv:hasFeature('Snack'))
	 */
    private SnackSelectionBox snackSelectionBox;
	/**
     * PV:ENDCOND 
     */
    
    private WarningDisplay wndInvalidCoin=new WarningDisplay("Invalid Coin");
    private LabelledValue lbdTotalMoneyInserted=new LabelledValue("Total Money Inserted:","0 C",50);
    private WarningDisplay wndNoChangeAvailable=new WarningDisplay("No Change Available");
    private Button btnTerminate=new Button("Terminate and Return Cash");
    private LabelledValue lbdCollectCoins=new LabelledValue("Collect Coins:","0 C",50);
    private LabelledValue lbdCollectCan=new LabelledValue("Collect Can Here:","",100);
    
    private VariantPointConstants VariantPointConstants = new VariantPointConstants();
    
    /**
     * This constructor creates an instance of the Customer Panel&#46; It further
     * creates Invalid Coin Display, No Change Available Display, Refund/ Change
     * Tray Display, Total Money Inserted Display, Coin Input Box, Drink Selection
     * Box, Can Collection Box and Terminate Button.
     * 
     * @param fr the parent frame
     * @param ctrl the Transaction Controller
     */
	public CustomerPanel(Frame fr, TransactionController ctrl) {
		super(fr, TITLE, false);
		
		txCtrl = ctrl;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				txCtrl.getMainController().getSimulatorControlPanel().setButtonState(SimulatorControlPanel.ACT_CUSTOMER,true);
				dispose();
				txCtrl.nullifyCustomerPanel();
			}
		});
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
		 */
		coinInputBox=new CoinInputBox(txCtrl);
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
		 */
		cardDetector=new CardDetector(txCtrl);
		/**
	     * PV:ENDCOND 
	     */
		
		 /**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		drinkSelectionBox=new DrinkSelectionBox(txCtrl);
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('Snack'))
		 */
		snackSelectionBox =new SnackSelectionBox(txCtrl);
		/**
	     * PV:ENDCOND 
	     */
			
		TerminateButtonListener terminateButtonListener=new TerminateButtonListener(txCtrl);
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
		 */
		coinInputBox.setActive(false);
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
		 */
		cardDetector.setActive(false);
		/**
	     * PV:ENDCOND 
	     */
		
		btnTerminate.addActionListener(terminateButtonListener);
		
		lblTitle.setAlignment(Label.CENTER);
		lblTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
		
		pan0.setLayout(new GridBagLayout());
		int i = 0;
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
		 */
		pan0.add(lblEnterCoins,new GridBagConstraints(0,i++,1,1,1.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		pan0.add(coinInputBox,new GridBagConstraints(0,i++,0,1,1.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(2,0,0,0),10,0));
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
		 */
		pan0.add(cardDetector,new GridBagConstraints(0,i++,1,1,1.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		/**
	     * PV:ENDCOND 
	     */
		
		pan0.add(wndInvalidCoin,new GridBagConstraints(0,i++,1,1,1.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));

		pan0.add(lbdTotalMoneyInserted,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		
		pan1.setLayout(new GridBagLayout());
		
		 /**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		if (drinkSelectionBox.Count() > 0) {
			drinkSelectionBox.setActive(true);
			pan1.add(drinkSelectionBox,new GridBagConstraints(0,0,0,1,0.0,0.0,
				    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
				    new Insets(5,0,0,0),10,0));
		}
		/**
	     * PV:ENDCOND 
	     */
		
		/**
		 * PV:IFCOND(pv:hasFeature('Snack'))
		 */
		if (snackSelectionBox.Count() > 0) {
			snackSelectionBox.setActive(true);
			pan1.add(snackSelectionBox,new GridBagConstraints(0,0,0,1,0.0,0.0,
				    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
				    new Insets(5,0,0,0),10,0));
		}
		/**
	     * PV:ENDCOND 
	     */
		
					
		/**
		 * PV:IFCOND(pv:hasFeature('Drink') AND pv:hasFeature('Snack'))
		 */
		if (drinkSelectionBox.Count() > 0 && snackSelectionBox.Count() >0)
		{
			JToggleButton jtb = new JToggleButton("snacks");
			jtb.addItemListener(new ItemListener() {
			   public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  drinkSelectionBox.setVisible(false);
			    	  snackSelectionBox.setVisible(true);
			    	  jtb.setText("drinks");
			      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
			    	  drinkSelectionBox.setVisible(true);
			    	  snackSelectionBox.setVisible(false);
			    	  jtb.setText("snacks");
			      }
			   }
			});
			pan1.add(jtb,new GridBagConstraints(0,1,0,1,0.0,0.0,
				    GridBagConstraints.EAST,GridBagConstraints.NONE,
				    new Insets(5,0,0,0),10,0));
		}
		/**
	     * PV:ENDCOND 
	     */

		pan0.add(pan1,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		
		pan0.add(wndNoChangeAvailable,new GridBagConstraints(0,5,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));

		/**
		 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
		 */
		pan0.add(wndNoChangeAvailable,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		/**
	     * PV:ENDCOND 
	     */
		
		pan0.add(btnTerminate,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.NONE,
			    new Insets(5,0,0,0),10,0));
		pan0.add(lbdCollectCoins,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(5,0,0,0),10,0));
		pan0.add(lbdCollectCan,new GridBagConstraints(0,i++,0,1,0.0,0.0,
			    GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
			    new Insets(2,0,20,0),10,0));
		
		setLayout(new BorderLayout());
	    add(lblTitle,BorderLayout.NORTH);
	    add(pan0,BorderLayout.CENTER);
	    
		pack();
		frameWidth=this.getWidth();
        frameHeight=this.getHeight();
        frameX=(screenWidth-frameWidth)/2;
        frameY=(screenHeight-frameHeight)/2;
        setBounds(frameX,frameY,frameWidth, frameHeight);
	}

	/**
	 * Display the Customer Panel&#46; This will be achieved by displaying the frame
	 * of the panel and then sending messages to its constituent objects 
	 * instructing them to display themselves.
	 */
	public void display() {
		this.setVisible(true);
	}

	/**
	 * This method sets the total money inserted and update the display.
	 * @param i the total money inserted.
	 */
	public void setTotalMoneyInserted(int i){
		lbdTotalMoneyInserted.setValue(i+ " C");
	}
	
	/**
	 * This method accumulative adds the money inserted&#46; 
	 * @param i the money to be added to the accumulative total money inserted.
	 * @return the accumulative total money inserted.
	 */
	public int addMoney(int i){
		int intTotal=getTotalMoneyInserted();
		intTotal+=i;
		setTotalMoneyInserted(intTotal);
		return intTotal;
	}
	
	/**
	 * This method returns the accumulative total money inserted.
	 * @return the accumulative total money inserted.
	 */
	public int getTotalMoneyInserted(){
		String strTotal=lbdTotalMoneyInserted.getValue();
		strTotal=strTotal.replace('C', ' ').trim();
		int intTotal=0;
		try{
			intTotal=Integer.parseInt(strTotal);
		}
		catch(NumberFormatException ex){
			intTotal=0;
		}
		return intTotal;
	}
	
	/**
	 * This method sets the change to be display.
	 * @param i the change.
	 */
	public void setChange(int i){
		lbdCollectCoins.setValue(i+" C");
	}
	
	/**
	 * This method sets the change to be display.
	 * @param s the change.
	 */
	public void setChange(String s){
		if(s!=null&&!s.trim().equals(""))
			s=s+" C";
		lbdCollectCoins.setValue(s);
	}
	
	/**
	 * This method return the change displayed on the CustomerPanel.
	 * @return the change.
	 */
	public String getChange(){
		return lbdCollectCoins.getValue().replace('C', ' ').trim();
	}
	
	/**
	 * This method sets the can name to the collect tray.
	 * @param name the name of the can.
	 */
	public void setCan(String name){
		lbdCollectCan.setValue(name);
	}
	
	/**
	 * This method return the name of the can.
	 * @return the name of the can&#46; 
	 */
	public String getCan(){
		return lbdCollectCan.getValue();
	}
	
	/**
	 * This method resets the total money inserted display on the CustomerPanel.
	 */
	public void resetTotalInserted(){
		setTotalMoneyInserted(0);
	}
	
	/**
	 * This method resets the change display on the CustomerPanel.
	 */
	public void resetChange(){
		setChange("");
	}
	
	/**
	 * This method resets the drink can display at the collection tray.
	 */
	public void resetCan(){
		setCan("");
	}
	
	/**
	 * Remove the Customer Panel from the display.
	 */
	public void closeDown() {
		dispose();
	}

	/**
	 * This method turning On or Off the "Invalid Coin" highlight.
	 * @param isOn TRUE to turn on the highlight, otherwise, turn off the highlight.
	 */
	public void displayInvalidCoin(String text){
		wndInvalidCoin.setLabel(text);
		wndInvalidCoin.setState(true);
	}
	
	public void hideInvalidCoin(){
		wndInvalidCoin.setState(false);
	}
	
	/**
	 * This method turning On or Off the "No Change Available" highlight.
	 * @param isOn TRUE to turn on the highlight, otherwise, turn off the highlight.
	 */
	public void displayChangeStatus(boolean isOn){
		wndNoChangeAvailable.setState(isOn);
	}
	
	/**
	 * This method activates or deactivates the DrinkSelectionBox in the CustomerPanel.
	 * @param active the active status of the DrinkSelectionBox; TRUE to activate,
	 * FALSE to deactivate it.
	 */
	public void setDrinkSelectionBoxActive(boolean active){
		
		/**
		 * PV:IFCOND(pv:hasFeature('Drink'))
		 */
		drinkSelectionBox.setActive(active);
		drinkSelectionBox.setActive(active);
		/**
	     * PV:ENDCOND 
	     */
	}
	
	/**
	 * This method activates or deactivates the CoinInputBox.
	 * @param active the active status of the CoinInputBox; TRUE to activate,
	 * FALSE to deactivate it.
	 */
	
	/**
	 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
	 */
	public void setCoinInputBoxActive(boolean active){
		coinInputBox.setActive(active);
	}
	/**
     * PV:ENDCOND 
     */
	
	/**
	 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
	 */
	public void setCardDetectorActive(boolean active){
		cardDetector.setActive(active);
	}
	/**
     * PV:ENDCOND 
     */
	
	
	/**
	 * This method activates or deactivates the Terminate Button
	 * @param active the active status of the Terminate Button; TRUE to activate,
	 * FALSE to deactivate it.
	 */
	public void setTerminateButtonActive(boolean active){
		btnTerminate.setEnabled(active);
	}
	
	/**
	 * This method returns the CoinInputBox in the CustomerPanel.
	 * @return the CoinInputBox display in the CustomerPanel.
	 */
	/**
	 * PV:IFCOND(pv:hasFeature('PaymentByCash'))
	 */
	public CoinInputBox getCoinInputBox(){
		return coinInputBox;
	}
	/**
     * PV:ENDCOND 
     */
	
	/**
	 * PV:IFCOND(pv:hasFeature('PaymentByCard'))
	 */
	public CardDetector getCardDetector(){
		return cardDetector;
	}
	/**
     * PV:ENDCOND 
     */
	
	
	/**
	 * PV:IFCOND(pv:hasFeature('Drink'))
	 */
	/**
	 * This method returns the DrinkSelectionBox in the CustomerPanel.
	 * @return the DrinkSelectionBox in the CustomerPanel.
	 */
	public DrinkSelectionBox getDrinkSelectionBox(){
		return drinkSelectionBox;
	}
	/**
     * PV:ENDCOND 
     */
	
	/**
	 * PV:IFCOND(pv:hasFeature('Snack'))
	 */
	
	/**
	 * This method returns the DrinkSelectionBox in the CustomerPanel.
	 * @return the DrinkSelectionBox in the CustomerPanel.
	 */
	public SnackSelectionBox getSnackSelectionBox(){
		return snackSelectionBox;
	}
	/**
     * PV:ENDCOND 
     */
	
	
	/**
	 * This method activates or deactivates the Customer Panel and its component
	 * objects.
	 * 
	 * @param comp the component to be activated or deactivated.
	 * @param st the active status; TRUE to activate, FALSE to deactivate.
	 */
	public void setActive(int comp, boolean st) {
		Component c=this.getComponent(comp);
		c.setEnabled(st);
	}
}//End of class CustomerPanel