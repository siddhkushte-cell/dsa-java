package com.java.sid.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void shouldCreateNodeWithDefaultValues() {
        Node node = new Node();

        assertEquals(0, node.data);
        assertNull(node.next);
    }

    @Test
    void shouldStoreDataValue() {
        Node node = new Node();
        node.data = 42;

        assertEquals(42, node.data);
    }

    @Test
    void shouldLinkToNextNode() {
        Node first = new Node();
        Node second = new Node();
        first.data = 1;
        second.data = 2;

        first.next = second;

        assertSame(second, first.next);
        assertEquals(2, first.next.data);
    }

    @Test
    void shouldFormChainOfNodes() {
        Node head = new Node();
        Node middle = new Node();
        Node tail = new Node();

        head.data = 10;
        middle.data = 20;
        tail.data = 30;

        head.next = middle;
        middle.next = tail;
        tail.next = null;

        assertEquals(10, head.data);
        assertEquals(20, head.next.data);
        assertEquals(30, head.next.next.data);
        assertNull(head.next.next.next);
    }

    @Test
    void shouldTraverseLinkedNodes() {
        Node head = new Node();
        Node second = new Node();
        Node third = new Node();

        head.data = 100;
        second.data = 200;
        third.data = 300;

        head.next = second;
        second.next = third;

        int count = 0;
        int sum = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            sum += temp.data;
            temp = temp.next;
        }

        assertEquals(3, count);
        assertEquals(600, sum);
    }

    @Test
    void shouldHandleSingleNode() {
        Node node = new Node();
        node.data = 5;
        node.next = null;

        assertEquals(5, node.data);
        assertNull(node.next);
    }
}
