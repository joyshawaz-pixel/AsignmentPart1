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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageContent) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    public String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        String full = String.valueOf(id);
        if (full.length() > 10) {
            full = full.substring(0, 10);
        }
        return full;
    }

    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    public int checkRecipientCell() {
        if (this.recipient == null) {
            return 0;
        }
        String trimmed = this.recipient.trim();
        boolean startsPlus = trimmed.startsWith("+");
        boolean correctLength = trimmed.length() >= 11 && trimmed.length() <= 13;
        boolean allDigitsAfterPlus = true;
        if (startsPlus && trimmed.length() > 1) {
            String afterPlus = trimmed.substring(1);
            for (int i = 0; i < afterPlus.length(); i++) {
                char c = afterPlus.charAt(i);
                if (c < '0' || c > '9') {
                    allDigitsAfterPlus = false;
                }
            }
        } else {
            allDigitsAfterPlus = false;
        }
        if (startsPlus && correctLength && allDigitsAfterPlus) {
            return 1;
        }
        return 0;
    }

    public String checkRecipientCellFormatted() {
        if (checkRecipientCell() == 1) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        int len = this.messageContent.length();
        if (len <= 250) {
            return "Message ready to send.";
        } else {
            int excess = len - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    // NO ARRAYS - uses indexOf and lastIndexOf only
    public String createMessageHash() {
        String trimmed = this.messageContent.trim();
        String firstWord;
        String lastWord;
        
        int firstSpace = trimmed.indexOf(" ");
        if (firstSpace == -1) {
            firstWord = trimmed;
            lastWord = trimmed;
        } else {
            firstWord = trimmed.substring(0, firstSpace);
            int lastSpace = trimmed.lastIndexOf(" ");
            lastWord = trimmed.substring(lastSpace + 1);
        }
        
        // clean non-letters from first/last for hash
        String cleanFirst = "";
        for (int i = 0; i < firstWord.length(); i++) {
            char c = firstWord.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                cleanFirst = cleanFirst + c;
            }
        }
        String cleanLast = "";
        for (int i = 0; i < lastWord.length(); i++) {
            char c = lastWord.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                cleanLast = cleanLast + c;
            }
        }
        if (cleanFirst.equals("")) {
            cleanFirst = firstWord;
        }
        if (cleanLast.equals("")) {
            cleanLast = lastWord;
        }
        
        String idPart = this.messageID;
        if (idPart.length() >= 2) {
            idPart = idPart.substring(0, 2);
        }
        String rawHash = idPart + ":" + this.messageNumber + ":" + cleanFirst + cleanLast;
        return rawHash.toUpperCase();
    }

    public String SentMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else {
            return "Message successfully stored.";
        }
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("storedMessages.txt", true);
            writer.write(this.messageID + "," + this.messageHash + "," + this.recipient + "," + this.messageContent + "\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message.");
        }
    }

    public String getMessageID() { return this.messageID; }
    public String getMessageHash() { return this.messageHash; }
    public String getRecipient() { return this.recipient; }
    public String getMessageContent() { return this.messageContent; }
}
