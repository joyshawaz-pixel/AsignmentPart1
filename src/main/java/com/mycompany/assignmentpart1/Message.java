/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
import java.util.Random;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

  
public class Message {
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageContent;
    private String messageHash;

    public Message(int numMessagesSent, String recipient, String messageContent) {
        this.messageID = generateMessageID();
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        // +27718693002 = VALID, 08575975889 = INVALID
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 13) {
            boolean allDigits = true;
            for (int i = 1; i < recipient.length(); i++) {
                if (!Character.isDigit(recipient.charAt(i))) {
                    allDigits = false;
                    break;
                }
            }
            if (allDigits) {
                return "Cell phone number successfully captured.";
            }
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {
        // NO split() - no array
        String trimmed = messageContent.trim();
        
        // Get first word
        int firstSpace = trimmed.indexOf(' ');
        String firstWord = firstSpace == -1 ? trimmed : trimmed.substring(0, firstSpace);
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");

        // Get last word
        int lastSpace = trimmed.lastIndexOf(' ');
        String lastWord = lastSpace == -1 ? trimmed : trimmed.substring(lastSpace + 1);
        lastWord = lastWord.replaceAll("[^a-zA-Z?]", "").replace("?", "");

        String firstTwo = messageID.substring(0, 2);
        return (firstTwo + ":" + numMessagesSent + ":" + (firstWord + lastWord)).toUpperCase();
    }

    public String SentMessage(int choice) {
        if (choice == 1) return "Message successfully sent.";
        if (choice == 2) return "Press 0 to delete the message.";
        if (choice == 3) return "Message successfully stored.";
        return "Invalid choice.";
    }

    // print without arrays - reads from file
    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("storedMessages.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            return "No messages stored.";
        }
        return sb.toString();
    }

    public int returnTotalMessages() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("storedMessages.json"))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            return 0;
        }
        return count;
    }

    // store in JSON without JSONArray
    public void storeMessage() {
        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            // Manual JSON object per line
            String json = "{\"MessageID\":\"" + messageID + "\","
                        + "\"MessageHash\":\"" + messageHash + "\","
                        + "\"Recipient\":\"" + recipient + "\","
                        + "\"Message\":\"" + messageContent.replace("\"", "'") + "\"}";
            file.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }

    public String checkMessageLength() {
        if (messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }
}
    