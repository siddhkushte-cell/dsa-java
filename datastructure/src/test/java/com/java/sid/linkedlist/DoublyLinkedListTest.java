package com.java.sid.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    private DoublyLinkedList list;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList();
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
        assertNull(list.head.prev);
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
    void addFirstShouldHaveNullPrevOnHead() {
        list.addFirst(10);
        list.addFirst(20);

        assertNull(list.head.prev);
    }

    @Test
    void addFirstShouldSetPrevLinkOnOldHead() {
        list.addFirst(10);
        list.addFirst(20);

        assertEquals(20, list.head.next.prev.data);
    }

    @Test
    void addFirstShouldMaintainBidirectionalLinks() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // forward: 30 -> 20 -> 10
        assertEquals(30, list.head.data);
        assertEquals(20, list.head.next.data);
        assertEquals(10, list.head.next.next.data);
        assertNull(list.head.next.next.next);

        // backward via prev links
        assertNull(list.head.prev);
        assertEquals(30, list.head.next.prev.data);
        assertEquals(20, list.head.next.next.prev.data);
    }

    @Test
    void addFirstShouldSetNullNextOnTail() {
        list.addFirst(10);
        list.addFirst(20);

        assertNull(list.head.next.next);
    }

    @Test
    void printShouldOutputForwardAndBackwardTraversal() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.print();

        String[] lines = outputCapture.toString().trim().split("\\R");
        assertEquals(8, lines.length);
        assertEquals("Forward Traversal", lines[0]);
        assertEquals("30", lines[1]);
        assertEquals("20", lines[2]);
        assertEquals("10", lines[3]);
        assertEquals("Backward Traversal", lines[4]);
        assertEquals("10", lines[5]);
        assertEquals("20", lines[6]);
        assertEquals("30", lines[7]);
    }

    @Test
    void printOnEmptyListShouldOutputOnlyHeaders() {
        list.print();

        String[] lines = outputCapture.toString().trim().split("\\R");
        assertEquals(2, lines.length);
        assertEquals("Forward Traversal", lines[0]);
        assertEquals("Backward Traversal", lines[1]);
    }

    @Test
    void printSingleElementShouldShowSameValueInBothDirections() {
        list.addFirst(42);

        list.print();

        String[] lines = outputCapture.toString().trim().split("\\R");
        assertEquals(4, lines.length);
        assertEquals("Forward Traversal", lines[0]);
        assertEquals("42", lines[1]);
        assertEquals("Backward Traversal", lines[2]);
        assertEquals("42", lines[3]);
    }

    // --- addLast ---

    @Test
    void addLastShouldSetHeadOnEmptyList() {
        list.addLast(10);

        assertEquals(10, list.head.data);
        assertNull(list.head.prev);
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
    void addLastShouldSetPrevLinkOnNewTail() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(10, list.head.next.prev.data);
    }

    @Test
    void addLastShouldMaintainBidirectionalLinks() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        // forward: 10 -> 20 -> 30
        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertEquals(30, list.head.next.next.data);
        assertNull(list.head.next.next.next);

        // backward via prev links
        assertNull(list.head.prev);
        assertEquals(10, list.head.next.prev.data);
        assertEquals(20, list.head.next.next.prev.data);
    }

    // --- search ---

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

        assertFalse(list.search(99));
    }

    // --- delete ---

    @Test
    void deleteShouldDoNothingOnEmptyList() {
        assertDoesNotThrow(() -> list.delete(10));
        assertNull(list.head);
    }

    @Test
    void deleteShouldRemoveOnlyElement() {
        list.addLast(10);

        list.delete(10);

        assertNull(list.head);
    }

    @Test
    void deleteShouldRemoveHeadNode() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.delete(10);

        assertEquals(20, list.head.data);
        assertNull(list.head.prev);
    }

    @Test
    void deleteShouldRemoveMiddleNode() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.delete(20);

        assertEquals(10, list.head.data);
        assertEquals(30, list.head.next.data);
        assertEquals(10, list.head.next.prev.data);
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
    void deleteShouldLeaveListUnchangedWhenKeyNotFound() {
        list.addLast(10);
        list.addLast(20);

        list.delete(99);

        assertEquals(10, list.head.data);
        assertEquals(20, list.head.next.data);
        assertNull(list.head.next.next);
    }

    @Test
    void mainShouldRunWithoutError() {
        assertDoesNotThrow(() -> DoublyLinkedList.main(new String[]{}));
    }
}
