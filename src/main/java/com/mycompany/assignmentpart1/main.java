/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class main {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasNum = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");

        return hasUpper && hasNum && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        // Checks format for South African / international standards per assignment guidelines
        String saRegex = "^\\+?[0-9]{10,13}$"; 
        return cellPhoneNumber != null && Pattern.matches(saRegex, cellPhoneNumber) && cellPhoneNumber.length() <= 10;
    }

    public String registerUser(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Username successfully captured.\n" +
               "Password successfully captured.\n" +
               "Cell phone number successfully added.\n" +
               "User has been registered successfully.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null && enteredUsername.equals(this.username) && 
               enteredPassword != null && enteredPassword.equals(this.password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

    

