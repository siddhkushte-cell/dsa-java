package com.java.sid.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList list;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    void setUp() {
        list = new LinkedList();
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void newListShouldHaveNullHead() {
        assertNull(list.head);
    }

    @Test
    void addFirstShouldSetHeadOnEmptyList() {
        list.addFirst(10);

        assertEquals(10, list.head.data);
        assertNull(list.head.next);
    }

    @Test
    void addFirstShouldInsertAtHead() {
        list.addFirst(10);
        list.addFirst(20);

        assertEquals(20, list.head.data);
        assertEquals(10, list.head.next.data);
    }

    @Test
    void addFirstShouldMaintainReverseInsertionOrder() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertEquals(30, list.head.data);
        assertEquals(20, list.head.next.data);
        assertEquals(10, list.head.next.next.data);
        assertNull(list.head.next.next.next);
    }

    @Test
    void printShouldOutputElementsFromHeadToTail() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.print();

        String[] lines = outputCapture.toString().trim().split("\\R");
        assertEquals(3, lines.length);
        assertEquals("30", lines[0]);
        assertEquals("20", lines[1]);
        assertEquals("10", lines[2]);
    }

    @Test
    void printOnEmptyListShouldProduceNoOutput() {
        list.print();

        assertEquals("", outputCapture.toString().trim());
    }

    @Test
    void addLastShouldSetHeadOnEmptyList() {
        list.addLast(10);

        assertEquals(10, list.head.data);
        assertNull(list.head.next);
    }

    @Test
    void addLastShouldAppendToEnd() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void addLastShouldMaintainInsertionOrder() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertEquals(30, list.head.next.next.data);
        assertNull(list.head.next.next.next);
    }

    @Test
    void addLastAfterAddFirstShouldAppendAfterHead() {
        list.addFirst(20);
        list.addLast(30);

        assertEquals(20, list.head.data);
        assertEquals(30, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void addLastShouldProduceCorrectPrintOrder() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.print();

        String[] lines = outputCapture.toString().trim().split("\\R");
        assertEquals(3, lines.length);
        assertEquals("10", lines[0]);
        assertEquals("20", lines[1]);
        assertEquals("30", lines[2]);
    }

    @Test
    void deleteShouldRemoveHeadNode() {
        list.addFirst(20);
        list.addFirst(10);

        list.delete(10);

        assertEquals(20, list.head.data);
        assertNull(list.head.next);
    }

    @Test
    void deleteHeadShouldMakeListEmptyForSingleElement() {
        list.addFirst(10);

        list.delete(10);

        assertNull(list.head);
    }

    @Test
    void deleteShouldRemoveMiddleNode() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.delete(20);

        assertEquals(10, list.head.data);
        assertEquals(30, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void deleteShouldRemoveTailNode() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.delete(30);

        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void deleteShouldOnlyRemoveFirstOccurrence() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(10);

        list.delete(10);

        assertEquals(20, list.head.data);
        assertEquals(10, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void deleteShouldLeaveListUnchangedWhenKeyNotFound() {
        list.addLast(10);
        list.addLast(20);

        list.delete(99);

        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void deleteOnEmptyListShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> list.delete(10));
    }

    @Test
    void searchShouldReturnFalseOnEmptyList() {
        assertFalse(list.search(10));
    }

    @Test
    void searchShouldReturnTrueWhenKeyIsHead() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertTrue(list.search(10));
    }

    @Test
    void searchShouldReturnTrueWhenKeyIsInMiddle() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertTrue(list.search(20));
    }

    @Test
    void searchShouldReturnTrueWhenKeyIsTail() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertTrue(list.search(30));
    }

    @Test
    void searchShouldReturnFalseWhenKeyNotFound() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertFalse(list.search(99));
    }

    @Test
    void searchShouldReturnTrueWhenDuplicatesExist() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(10);

        assertTrue(list.search(10));
    }

    @Test
    void mainShouldRunWithoutError() {
        assertDoesNotThrow(() -> LinkedList.main(new String[]{}));
    }
}
