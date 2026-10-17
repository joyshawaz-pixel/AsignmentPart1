/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
import javax.swing.JOptionPane;
public class Main2 {
 
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        String numInput = JOptionPane.showInputDialog("How many messages do you wish to send?");
        int totalToSend = Integer.parseInt(numInput);
        int sentCount = 0;

        while (true) {
            String menu = "Choose:\n1) Send Messages\n2) Show recently sent messages\n3) Quit";
            String choiceStr = JOptionPane.showInputDialog(menu);
            if (choiceStr == null) break;
            int choice = Integer.parseInt(choiceStr);

            if (choice == 3) break;
            if (choice == 2) {
                JOptionPane.showMessageDialog(null, "Coming Soon.");
                continue;
            }
            if (choice == 1) {
                if (sentCount >= totalToSend) {
                    JOptionPane.showMessageDialog(null, "Limit reached: " + totalToSend);
                    continue;
                }

                String recipient = JOptionPane.showInputDialog("Enter recipient (e.g. +27718693002):");
                String messageText = JOptionPane.showInputDialog("Enter message:");

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

                String actionStr = JOptionPane.showInputDialog("1) Send Message\n2) Disregard\n3) Store");
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
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Total messages sent: " + sentCount);
    }
}   

