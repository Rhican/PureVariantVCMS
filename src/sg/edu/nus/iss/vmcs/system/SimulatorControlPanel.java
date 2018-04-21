/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

import java.awt.*;
import java.awt.event.*;

/**
 * This panel is displayed when the system is started up&#46; It enables the user (the Controller) to:
 * <br>
 * 1- Start (switch on);
 * <br>
 * 2- Stop (switch off);
 * <br>
 * 3- Activate the Customer Panel;
 * <br>
 * 4- Activate the Maintenance Panel;
 * <br>
 * 5- Activate the Machinery Simulator Panel;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class SimulatorControlPanel extends Frame {
	private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	private int frameX=0;
	private int frameY=0;
	private int frameWidth=300;
	private int frameHeight=400;
	private int screenWidth=screen.width;
	private int screenHeight=screen.height;
	
	/**This constant attribute denotes simulation begins*/
    public static final int SIMUL_BEGIN    = 0;
    /**This constant attribute denotes simulation ends*/
    public static final int SIMUL_END      = 1;
    /**This constant attribute denotes activates Maintainer panel*/
    public static final int ACT_MAINTAINER = 2;
    /**This constant attribute denotes activates Machinery panel*/
    public static final int ACT_MACHINERY  = 3;
    /**This constant attribute denotes activates customer panel*/
    public static final int ACT_CUSTOMER   = 4;
    
    private static final int NUM_BUTTONS   = 5;

    private static final String title = "Simulation Control Panel";

    private static final String L_SIMUL_BEGIN    = "Begin Simulation";
    private static final String L_SIMUL_END      = "End Simulation";
    private static final String L_ACT_MAINTAINER = "Activate Maintainer Panel";
    private static final String L_ACT_MACHINERY  = "Activate Machinery Panel";
    private static final String L_ACT_CUSTOMER  = "Activate Customer Panel";

    private MainController          mainCtrl;
    private SimulationController    simulationCtrl;

    private Button buttons[] = new Button [NUM_BUTTONS];

    private Font   titleFont  = new Font ("Helvetica", Font.BOLD, 18);
    private Font   buttonFont = new Font ("Helvetica", Font.BOLD, 14);

    /**
     * This constructor creates an instance of the SimulatorControlPanel object.
     * @param controller the SimulationController.
     */
    public SimulatorControlPanel (SimulationController controller) {
        super (title);

        this.simulationCtrl = controller;
        this.mainCtrl = simulationCtrl.getMainController();

        setLayout(new GridLayout(0, 1));

        add (createPanelLabel());

        addButton(SIMUL_BEGIN, L_SIMUL_BEGIN,
		          new BeginSimulationButtonListener(simulationCtrl));
        addButton(ACT_CUSTOMER,  L_ACT_CUSTOMER,
		          new ActivateCustomerPanelButtonListener(simulationCtrl));
        addButton(ACT_MAINTAINER, L_ACT_MAINTAINER,
		          new ActivateMaintainerPanelButtonListener(simulationCtrl));
        addButton(ACT_MACHINERY,  L_ACT_MACHINERY,
		          new ActivateMachineryPanelButtonListener(simulationCtrl));
        addButton(SIMUL_END,  L_SIMUL_END,
		          new EndSimulationButtonListener(mainCtrl));

        pack();
        frameWidth=this.getWidth();
        frameHeight=this.getHeight();
        frameX=(screenWidth-frameWidth)/2;
        frameY=(screenHeight-frameHeight)/2;
        setBounds(frameX,frameY,frameWidth, frameHeight);

        addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
                simulationCtrl.getMainController().closeDown();
                dispose();
            }
	});
    }

    /**
     * Display the Simulator Control Panel and its constituent objects.
     */
    public void display(){
      this.setVisible(true);
    }

    /**
     * This method creates the panel label.
     * @return the panel label.
     */
    private Label createPanelLabel() {
        Label l = new Label (title);
        l.setBackground(Color.blue);
        l.setForeground(Color.white);
        l.setFont(titleFont);
        return (l);
    }

    /**
     * This methods add button to the panel.
     * @param id the button index.
     * @param label the label of the button.
     * @param l the action listener for the button.
     */
    private void addButton (int id, String label, ActionListener l) {
        Button b = new Button (label);
        b.setBackground(Color.white);
        b.setForeground(Color.blue);
        b.setFont(buttonFont);
        b.addActionListener(l);
        buttons[id] = b;
        add (b);
    }

    /**
     * This method set the state of the simulation.
     * @param isOn TRUE to turn on the simulation, FALSE to turn off the simulation.
     */
    public void setSimulationActive (boolean isOn) {
        setButtonState (SIMUL_BEGIN,   !isOn);
        setButtonState (ACT_MAINTAINER, isOn);
        setButtonState (ACT_MACHINERY,  isOn);
        setButtonState (ACT_CUSTOMER,   isOn);
        setButtonState (SIMUL_END,      isOn);
    }

    /**
     * This method set the button state&#46;
     * @param id the index of the button&#46;
     * @param state the state of the button&#46; TRUE to enable it, FALSE to disabled it&#46;
     */
    public void setButtonState (int id, boolean state) {
        buttons[id].setEnabled (state);
    }

    /**
     * Close down and dispose the simulation control panel.
     */
    public void closeDown () {
        dispose ();
    }

    /**
     * This method stop the main controller.
     */
    public void stop(){
        mainCtrl.closeDown();
    }

    /**
     * This method set the button state&#46;
     * @param id the index of the button&#46;
     * @param state the state of the button&#46; TRUE to enable it, FALSE to disabled it&#46;
     */
    public void setActive (int id, boolean state) {
        setButtonState(id, state);
    }
}//End of class SimulatorControlPanel