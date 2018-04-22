/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import sg.edu.nus.iss.vmcs.VariantPointConstants;

/**
 * This class is used for detecting card payment.
 * @author SPLE Team04
 *
 */
public class CardDetector extends Panel implements WindowListener,ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Button cardDetectorButton;
	
	private TransactionController txCtrl;
	
	/**
	 * This constructor creates an instance of the object.
	 * @param cctrl the TransactionController.
	 */
	public CardDetector(TransactionController cctrl){
		this.txCtrl=cctrl;

		cardDetectorButton=new Button("Card Payment");
		cardDetectorButton.addActionListener(this);
		add(cardDetectorButton);
	}
	
	/**
	 * This method activates the Coin Input Box if the parameter is TRUE&#46;
	 * Otherwise, the Coin Input Box is deactivated.
	 * @param active TRUE to activate the CoinInputBox, otherwise, deactivate the CoinInputBox.
	 */
	public void setActive(boolean active){
		if(cardDetectorButton!=null){
			cardDetectorButton.setEnabled(active);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (txCtrl.getPaymentReceiver() == null) {
			txCtrl.setPaymentReceiver(
					VariantPointConstants.vLogPayment
						? new PaymentLogDecorator(new CardPayment(txCtrl))
						: new PaymentDecorator(new CardPayment(txCtrl)));
		}
		txCtrl.getPaymentReceiver().makePayment("Dummy EZ Link Info");
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
}