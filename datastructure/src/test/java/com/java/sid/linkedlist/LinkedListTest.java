package com.java.sid.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void mainShouldPrintHeader() {
        LinkedList.main(new String[]{});

        String output = outputCapture.toString();
        assertTrue(output.contains("The LinkedList Elements Are:"));
    }

    @Test
    void mainShouldPrintAllThreeElements() {
        LinkedList.main(new String[]{});

        String output = outputCapture.toString();
        assertTrue(output.contains("10"));
        assertTrue(output.contains("20"));
        assertTrue(output.contains("30"));
    }

    @Test
    void mainShouldPrintElementsInOrder() {
        LinkedList.main(new String[]{});

        String output = outputCapture.toString();
        int pos10 = output.indexOf("10");
        int pos20 = output.indexOf("20");
        int pos30 = output.indexOf("30");

        assertTrue(pos10 < pos20, "10 should appear before 20");
        assertTrue(pos20 < pos30, "20 should appear before 30");
    }

    @Test
    void mainShouldPrintExactlyFourLines() {
        LinkedList.main(new String[]{});

        String output = outputCapture.toString().trim();
        String[] lines = output.split("\\R");

        assertEquals(4, lines.length, "Expected 1 header line + 3 data lines");
    }

    @Test
    void mainShouldProduceExpectedOutput() {
        LinkedList.main(new String[]{});

        String output = outputCapture.toString().trim();
        String[] lines = output.split("\\R");

        assertEquals("The LinkedList Elements Are:", lines[0]);
        assertEquals("10", lines[1]);
        assertEquals("20", lines[2]);
        assertEquals("30", lines[3]);
    }
}
