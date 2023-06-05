/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimerecordapp;

/**
 *
 * @author sambridgman
 */
public class Charge {
    private String chargeTitle;
    private String description;
    
    public Charge(String charge, String description) { //constructor with desc
        this.chargeTitle = charge;
        this.description = description;
    }
    public Charge(String charge) { //constructor with no desc
        this.chargeTitle = charge;
        this.description = "No description given.";
    }
    //getters
    public String getCharge() {
        return this.chargeTitle;
    }
    public String getDesc() {
        return this.description;
    }
    //setter
    public void setDesc(String desc) {
        this.description = desc;
    }
    public void setCharge(String charge) {
        this.chargeTitle = charge;
    }
    
    @Override
    public String toString() { //to string for charge obj
        String ts = "";
        return ts += this.chargeTitle + ":\nDescription of Crime:\n" + this.description;
    }
}
