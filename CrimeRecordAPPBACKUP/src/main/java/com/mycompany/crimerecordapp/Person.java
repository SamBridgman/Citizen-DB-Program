/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimerecordapp;

/**
 *
 * @author sambridgman
 */

//person obj is a person with no criminal record for the database
public class Person implements PersonRecord {
    protected String fName;
    protected String lName;
    protected String name; // full name
    
    public Person(String firstName, String lastName) {
        this.fName = firstName;
        this.lName = lastName;
        this.name = lName + ", " + fName;
    }
    
    @Override
    public String getProfile() { // for interface
        return this.fName + " " + this.lName + ": Clean Record";
    }
    
    public String getName() {
        return this.name;
    }
    @Override
    public String getFName() {    
        return this.fName;
    }
    @Override
    public String getLName() {    
        return this.lName;
    }
}
