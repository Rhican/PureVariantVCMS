/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

/**
 * This object stores the weight and value of each type of Coin, and hence enables the 
 * machine to recognize each Coin entered.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class Coin extends StoreObject {
    private int value;
    private double weight;

    /**
     * This constructor creates an instance of the Coin object.
     */
    public Coin () {
    }
    
    /**
     * This constructor creates an instance of the Coin object.
     * @param value the value of the Coin.
     * @param weight the weight of the Coin.
     */
    public Coin (int value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    /**
     * This method sets the value of the Coin.
     * @param v the value of the Coin.
     */
    public void setValue(int v){
      value = v;
    }

    /**
     * This method sets the weight of the Coin.
     * @param wt the weight of the Coin.
     */
    public void setWeight(double wt){
      weight = wt;
    }

    /**
     * This method returns the weight of the Coin.
     * @return the weight of the Coin.
     */
    public double getWeight () {
        return (weight);
    }

    /**
     * This method returns the value of the Coin.
     * @return the value of the Coin.
     */
    public int getValue () {
        return (value);
    }
  }//End of class Coin