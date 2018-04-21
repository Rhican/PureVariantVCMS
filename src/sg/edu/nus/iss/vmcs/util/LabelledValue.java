/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;

/**
 * This boundary object displays an updatable label next to a fixed label.
 * @version 1.0 2008-10-01
 * @author Team SE16T5E
 */

public class LabelledValue extends Panel {
	private Label lbl1;
	private Label lbl2;

	/**
	 * This constructor creates an instance of LabelledValue object.
	 * @param name the label name.
	 * @param value the value of the updatable label.
	 * @param width the width of the updatable label.
	 */
	public LabelledValue(String name, String value, int width) {
		lbl1=new Label(name);
		lbl2=new Label(value);
		
		lbl1.setAlignment(Label.CENTER);
		lbl2.setBackground(Color.lightGray);
		
		lbl2.setPreferredSize(new Dimension(width,24));
		setLayout(new GridBagLayout());
		add(lbl1,new GridBagConstraints(0,0,1,1,0.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.NONE,
			    new Insets(5,0,0,0),1,0));  
		add(lbl2,new GridBagConstraints(1,0,1,1,0.0,0.0,
			    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			    new Insets(5,10,0,0),width,0));
	}
	
	/**
	 * This method set the value to the text field in the LabelledValue&#46;
	 * @param i the value to be set to the text field&#46;
	 */
	public void setValue(String i) {
		lbl2.setText(i);
	}

	/**
	 * This method set the value to the text field in the LabelledValue&#46;
	 * @param i the value to be set to the text field&#46;
	 */
	public void setValue(int i) {
		lbl2.setText(""+i);
	}

	/**
	 * This method returns the value in the updatable label.
	 * @return the value in the updatabale lable.
	 */
	public String getValue() {
		return lbl2.getText();
	}
	
	/**
	 * This method sets the text field background color in the LabelledValue.
	 * @param c the color of the background.
	 */
	public void setTextBackground(Color c) {
		lbl2.setBackground(c);
	}

	/**
	 * This method sets the text field foreground in the LabelledValue.
	 * @param c the color of the foreground.
	 */
	public void setTextForeground(Color c) {
		lbl2.setForeground(c);
	}
}//End of class LabelledValue