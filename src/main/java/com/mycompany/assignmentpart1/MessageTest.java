/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentpart1;

/**
 *
 * @author Student
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageTest {
    
    @Test
    public void testCheckRecipientCellValid() {
        Message msg = new Message(0, "+27718693002", "Hi there");
        int result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        Message msg = new Message(0, "08575975889", "Hi there");
        int result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testCheckMessageLengthValid() {
        Message msg = new Message(0, "+27718693002", "Hello world");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthExceeds() {
        // create 260 character message
        String longMessage = "a".repeat(260);
        Message msg = new Message(0, "+27718693002", longMessage);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("exceeds 250 characters"));
    }

    @Test
    public void testCheckMessageIDLength() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertTrue(msg.checkMessageID(), "MessageID should be <= 10 chars");
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testCreateMessageHash() {
        // Message: "Hi there" -> should be XX:0:HITHERE format
        Message msg = new Message(0, "+27718693002", "Hi there");
        String hash = msg.createMessageHash();
        // Hash format: first2 digits of ID : num : FIRSTLAST
        assertTrue(hash.contains(":0:HITHERE") || hash.contains(":0:HITHERE".toUpperCase()));
        assertTrue(hash.contains(":"));
    }

    @Test
    public void testCreateMessageHashExampleFromBrief() {
        // Test from assignment brief: "Hi Mike, Can you join us for dinner tonight"
        // Expected hash pattern: 00:0:HITONIGHT or similar
        Message msg = new Message(0, "+27718693002", "Hi Mike, Can you join us for dinner tonight");
        String hash = msg.createMessageHash();
        assertTrue(hash.endsWith("HITONIGHT") || hash.endsWith("HITONIGHT".toUpperCase()) || hash.contains("HI") && hash.contains("TONIGHT"));
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
    
    @Test
    public void testTotalMessages() {
        // Simulates the counter in AssignmentPart1
        int total = 3;
        int sent = 0;
        Message m1 = new Message(sent++, "+27718693002", "Message 1");
        Message m2 = new Message(sent++, "+27718693002", "Message 2");
        assertEquals(2, sent);
        assertTrue(m1.checkMessageID());
        assertTrue(m2.checkMessageID());
    }
}

