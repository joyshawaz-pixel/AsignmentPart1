/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
import javax.swing.JOptionPane;

public class AssignmentPart1 {
    
    public static void main(String[] args) {
        // Part 1 - Registration / Login
        Main loginSystem = new Main();
        
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Enter Username (must contain _ and <=5 chars):");
        String password = JOptionPane.showInputDialog("Enter Password (8+ chars, capital, number, special):");
        String cellNumber = JOptionPane.showInputDialog("Enter Cell Phone Number with international code (e.g. +27718693002):");

        String regResult = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
        JOptionPane.showMessageDialog(null, regResult);

        if (!regResult.contains("successfully")) {
            return; // Stop if registration failed
        }

        // Login
        String enteredUsername = JOptionPane.showInputDialog("Login - Enter Username:");
        String enteredPassword = JOptionPane.showInputDialog("Login - Enter Password:");

        boolean isLoggedIn = loginSystem.loginUser(enteredUsername, enteredPassword);
        String loginStatus = loginSystem.returnLoginStatus(isLoggedIn);
        JOptionPane.showMessageDialog(null, loginStatus);

        if (!isLoggedIn) {
            return; // Stop if login failed
        }

        // Part 2 - QuickChat Starts HERE - only if login successful
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        String numInput = JOptionPane.showInputDialog("How many messages do you wish to send?");
        int totalToSend = Integer.parseInt(numInput);
        int sentCount = 0;

        while (true) {
            String menu = "Choose:\n1) Send Messages\n2) Show recently sent messages\n3) Quit";
            String choiceStr = JOptionPane.showInputDialog(menu);
            if (choiceStr == null) break;
            int choice = Integer.parseInt(choiceStr);

            if (choice == 3) {
                break;
            }
            if (choice == 2) {
                JOptionPane.showMessageDialog(null, "Coming Soon.");
                continue;
            }
            if (choice == 1) {
                if (sentCount >= totalToSend) {
                    JOptionPane.showMessageDialog(null, "You have reached your limit of " + totalToSend + " messages.");
                    continue;
                }

                String recipient = JOptionPane.showInputDialog("Enter recipient (e.g. +27718693002):");
                String messageText = JOptionPane.showInputDialog("Enter message (max 250 chars):");

                Message msg = new Message(sentCount, recipient, messageText);

                String recipientCheck = msg.checkRecipientCell();
                if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                    JOptionPane.showMessageDialog(null, recipientCheck);
                    continue;
                }

                String lengthCheck = msg.checkMessageLength();
                if (!lengthCheck.equals("Message ready to send.")) {
                    JOptionPane.showMessageDialog(null, lengthCheck);
                    continue;
                }

                String actionStr = JOptionPane.showInputDialog("Choose:\n1) Send Message\n2) Disregard (Press 0 to delete)\n3) Store to send later");
                int action = Integer.parseInt(actionStr);

                JOptionPane.showMessageDialog(null, msg.SentMessage(action));

                if (action == 1) {
                    msg.storeMessage();
                    String display = "MessageID: " + msg.getMessageID() + "\n" +
                                     "MessageHash: " + msg.getMessageHash() + "\n" +
                                     "Recipient: " + msg.getRecipient() + "\n" +
                                     "Message: " + msg.getMessageContent();
                    JOptionPane.showMessageDialog(null, display);
                    sentCount++;
                } else if (action == 3) {
                    msg.storeMessage();
                    sentCount++;
                    JOptionPane.showMessageDialog(null, "Message stored.");
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Total messages sent: " + sentCount);
    }
}

  
