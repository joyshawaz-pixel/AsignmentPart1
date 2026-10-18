/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class AssignmentPart1 {
    public static void main(String[] args) {
        Main loginSystem = new Main();
        
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Enter Username (must contain _ and <=5 chars):");
        String password = JOptionPane.showInputDialog("Enter Password (8+ chars, capital, number, special):");
        String cellNumber = JOptionPane.showInputDialog("Enter Cell Number with + (e.g. +27718693002):");

        String regResult = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
        JOptionPane.showMessageDialog(null, regResult);
        if (!regResult.contains("successfully")) return;

        String enteredUsername = JOptionPane.showInputDialog("Login - Enter Username:");
        String enteredPassword = JOptionPane.showInputDialog("Login - Enter Password:");

        boolean isLoggedIn = loginSystem.loginUser(enteredUsername, enteredPassword);
        JOptionPane.showMessageDialog(null, loginSystem.returnLoginStatus(isLoggedIn));
        if (!isLoggedIn) return;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        int totalToSend = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you wish to send?"));

        boolean running = true;
        int totalSent = 0;
        while (running) {
            String menu = "Welcome to QuickChat\nChoose:\n1) Send Messages\n2) Show recently sent messages\n3) Quit";
            int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            if (choice == 3) {
                running = false;
            } else if (choice == 2) {
                JOptionPane.showMessageDialog(null, "Coming Soon - Part 3 Feature");
            } else if (choice == 1) {
                for (int i = 0; i < totalToSend; i++) {
                    String recipient = JOptionPane.showInputDialog("Message " + (i+1) + "/" + totalToSend + "\nEnter recipient (+27...):");
                    String messageText = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                    
                    Message msg = new Message(i, recipient, messageText);

                    String recCheck = msg.checkRecipientCellFormatted();
                    if (!recCheck.equals("Cell phone number successfully captured.")) {
                        JOptionPane.showMessageDialog(null, recCheck);
                        i--;
                        continue;
                    }
                    
                    String lenCheck = msg.checkMessageLength();
                    if (!lenCheck.equals("Message ready to send.")) {
                        JOptionPane.showMessageDialog(null, lenCheck);
                        i--;
                        continue;
                    }

                    int action = Integer.parseInt(JOptionPane.showInputDialog("1) Send\n2) Disregard\n3) Store"));
                    JOptionPane.showMessageDialog(null, msg.SentMessage(action));

                    if (action == 1 || action == 3) {
                        storeAsJson(msg);
                        JOptionPane.showMessageDialog(null, 
                            "MessageID: " + msg.getMessageID() + "\n" +
                            "Message Hash: " + msg.getMessageHash() + "\n" +
                            "Recipient: " + msg.getRecipient() + "\n" +
                            "Message: " + msg.getMessageContent());
                        totalSent++;
                    }
                }
                JOptionPane.showMessageDialog(null, "Total sent/stored: " + totalSent);
                running = false;
            }
        }
    }

    public static void storeAsJson(Message msg) {
        try {
            FileWriter writer = new FileWriter("storedMessages.json", true);
            String json = "{\n  \"messageID\": \"" + msg.getMessageID() + "\",\n  \"messageHash\": \"" + msg.getMessageHash() + "\",\n  \"recipient\": \"" + msg.getRecipient() + "\",\n  \"message\": \"" + msg.getMessageContent().replace("\"", "\\\"") + "\"\n},\n";
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing JSON");
        }
    }
}