/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crimerecordapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


//this app uses concepts of, inheritance, obj aggregation, interfaces, polymorphism(?)


//this is the controller class
public class CriminalController{
    //adds person to database
    public static void addToDatabase(TextField fName, TextField lName, TextField charged, TextField desc) {
        try(PrintWriter fout = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter("dbC.txt", true))))
        {
            //if first name or last name is empty, throw error
            if(lName.getText().length() < 1 ||fName.getText().length() < 1) {
                throw new IOException();
            }
            //otherwise write person to db
            else{
                //write to db
                fout.write(lName.getText() + "," + fName.getText() + "," + charged.getText() + "," + desc.getText() + ",");
                fout.flush();
                
                //alert confirmation
                Alert success = new Alert(AlertType.CONFIRMATION);
                success.setContentText("SUCCESS:\nFirst Name: " + fName.getText() + "\n" + "Last Name: " + lName.getText() + "\n" + "Charges: " + charged.getText());
                //clears the textfields
                fName.clear();
                lName.clear();
                charged.clear();
                desc.clear();
                //success alter
                success.show();
                success.showAndWait();
                
            }
        }
        catch(IOException e)
        {
            //invalid info alert
            Alert error = new Alert(AlertType.WARNING);
            error.setContentText(" INVALID DATA: \n Enter First and last Name and Applicable Charges");
            error.show();
            error.showAndWait();
        }
    }
    //searches through the database
    public static PersonRecord[] searchDatabase(TextField searchfName, TextField searchlName) {
        PersonRecord arr[]; // array of interface personrecord
        arr = new PersonRecord[1]; // size
        try (BufferedReader in = new BufferedReader(new FileReader("dbC.txt"))) 
        {
            //scanner with delimiter
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            while(read.hasNext())
            {
                //assign values from fb
                String lName = read.next();
                String fName = read.next();
                String charge = read.next();
                String desc = read.next();
                //if name seached equals name in database
                if(fName.equalsIgnoreCase(searchfName.getText()) && lName.equalsIgnoreCase(searchlName.getText())) {
                    //this checks if person is criminal or not
                        //adds criminal to arr
                    arr[0] = new Criminal(fName, lName, new Charge(charge, desc));
                    return arr;
                }
                
            }
        }
        catch(IOException ex)
        {   
            System.out.println(ex);
        }
        return arr;
    }
    //deletes person from the database, similar to edit from database
    public static void deleteFromDatabase(String sFName, String sLName) {
        String tempFile = "temp.txt"; // temp file
        File oldFile = new File("dbC.txt"); // old file which is current db
        File newFile = new File(tempFile); // new file with name temp.txt
        
        
        try{
            //reader and writer stuff
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader("dbC.txt");
            BufferedReader br = new BufferedReader(fr);
            
            Scanner read = new Scanner(br);
            read.useDelimiter(",");
            
            while(read.hasNext()) {
               //assign values from curr db
                String lName = read.next();
                String fName = read.next();
                String charge = read.next();
                String desc = read.next();
                
                //System.out.println(fName + "First Name");
                //System.out.println(lName + "Last Name");
                
                if(fName.equalsIgnoreCase(sFName) && lName.equalsIgnoreCase(sLName)) {
                    System.out.println("sees name");
                    //this doesnt write the name of person to new file
                }
                else {
                    //write person to new file
                    pw.print(lName + "," + fName + "," + charge + "," + desc + ",");
                }
                
            }
            //flush and close 
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            //delete old db
            oldFile.delete();
            //temp
            File temp2 = new File("dbC.txt");
            //rename new file to db.txt
            newFile.renameTo(temp2);
            //confirmation alert
            Alert success = new Alert(AlertType.CONFIRMATION);
            success.setContentText("SUCCESS!\n" + sFName + " " + sLName + " has been deleted.");
            success.show();
            success.showAndWait();
            
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    //for editing a person in the database that includes new name, current names,
    public static void editDatabase(String sFName, String sLName, String sCharge, String sDesc, String currFName, String currLName) {
        String tempFile = "temp.txt";
        File oldFile = new File("dbC.txt"); // current db to old file
        File newFile = new File(tempFile); // create a new file with the name temp.txt
        
        try{
            //file writing and reading stuff
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader("dbC.txt");
            BufferedReader br = new BufferedReader(fr);
            
            Scanner read = new Scanner(br);
            read.useDelimiter(",");
            //if there is text
            while(read.hasNext()) {
                //assign values
                String lName = read.next();
                String fName = read.next();
                String charge = read.next();
                String desc = read.next();
                
                //System.out.println(fName + "First Name");
                //System.out.println(lName + "Last Name");
                
                // if name from the current database equals current persons name
                if(fName.equalsIgnoreCase(currFName) && lName.equalsIgnoreCase(currLName)) {
                    // if no entry for new name
                    if(sFName.length() < 1) {
                        sFName = fName; // keeps the name
                    }
                    if(sLName.length() < 1) {
                        sLName = lName; // keeps name
                    }
                    //reassign values
                    fName = sFName;
                    lName = sLName;
                    charge = sCharge;
                    desc = sDesc;
                    
                }
                //print to new file with updated information
                pw.print(lName + "," + fName + "," + charge + "," + desc + ",");
                
            }
            //flush and close everything
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            //delete the old db
            oldFile.delete();
            //temp file of db.txt
            File temp2 = new File("dbC.txt");
            //renames the new file db.txt
            newFile.renameTo(temp2);
            //confirmation alert
            Alert success = new Alert(AlertType.CONFIRMATION);
            success.setContentText(" SUCCESS!\n" + sFName + " " + sLName + " has been edited.");
            success.show();
            success.showAndWait();
            
    }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    //this is for reading the database, used in the refresh button
    public static TableView readDatabase(TableView table){
        try (BufferedReader in = new BufferedReader(new FileReader("dbC.txt"))) //reads database txt file
        {
            //loop through, adding strings to array
            //table.getItems().clear(); //clears the table
            
            //scanner with delimiter
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            
            while(read.hasNext()) // there is a next line
            {
                //assign the values
                String lName = read.next();
                String fName = read.next();
                String charge = read.next();
                String desc = read.next();
                //if charge has no value
                    //add criminal obj to table
                table.getItems().add(new Criminal(fName, lName, new Charge(charge, desc)));
                
            }
            read.close(); // close the read
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return table; //return table
    }
}
