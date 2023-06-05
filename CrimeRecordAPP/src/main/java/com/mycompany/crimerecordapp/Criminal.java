/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimerecordapp;

/**
 *
 * @author sambridgman
 */
public class Criminal extends Person implements PersonRecord { //inheritance and interface
    protected Charge charge;
    
    public Criminal(String fName, String lName, Charge charge) { //includes charge obj for a parameter
        super(fName, lName);
        this.charge = charge;
    }
    @Override //from interaface
    public String getProfile() {
        return this.fName + " " + this.lName + ":\nCharged with " + charge.toString();
    }
    @Override
    public String getFName() {    
        return this.fName;
    }
    public String getCharge() {
        return this.charge.getCharge();
    }
}
