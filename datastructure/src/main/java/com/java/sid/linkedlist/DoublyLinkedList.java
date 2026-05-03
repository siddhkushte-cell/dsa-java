package com.java.sid.linkedlist;

import java.util.logging.Logger;

/**
 * A doubly linked list node holding an integer value.
 *
 * <p>Each node maintains references to both its predecessor and successor,
 * enabling O(1) traversal in either direction from a known node.
 */
class DoublyNode
{
    /** The integer value stored in this node. */
    int data;

    /** Reference to the previous node, or {@code null} if this is the head. */
    DoublyNode prev;

    /** Reference to the next node, or {@code null} if this is the tail. */
    DoublyNode next;
}

/**
 * A doubly linked list of integers.
 *
 * <p>Each node holds references to both its predecessor ({@code prev}) and
 * successor ({@code next}), allowing traversal in both directions. New elements
 * are inserted at the head in O(1) time.
 *
 * <pre>
 *   DoublyLinkedList list = new DoublyLinkedList();
 *   list.addFirst(10);
 *   list.addFirst(20); // list: 20 <-> 10
 * </pre>
 */
public class DoublyLinkedList {

    private static final Logger LOGGER = Logger.getLogger(DoublyLinkedList.class.getName());
    private static final String INSERT_MSG = "Inserting Element: ";

    /** The first node of the list, or {@code null} if the list is empty. */
    DoublyNode head;

    /**
     * Inserts a new element at the beginning of the list.
     *
     * <p>The new node's {@code prev} is always {@code null}. If the list is
     * non-empty, the existing head's {@code prev} is updated to point back to
     * the new node. O(1) time.
     *
     * @param val the integer value to insert
     */
    public void addFirst(int val) {
        DoublyNode newNode = new DoublyNode();

        newNode.data = val;

        if (head == null) {
            newNode.next = null;
            newNode.prev = null;
        } else {
            newNode.prev = null;
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
    }

    /**
     * Appends a new element at the end of the list.
     *
     * <p>If the list is empty the new node becomes the head; otherwise the list
     * is traversed to find the tail, the new node is linked after it, and its
     * {@code prev} is set to the old tail. O(n) time.
     *
     * @param val the integer value to append
     */
    public void addLast(int val) {
        DoublyNode newNode = new DoublyNode();
        newNode.data = val;

        if (head == null) {
            newNode.next = null;
            newNode.prev = null;
            head = newNode;
        } else {
            DoublyNode last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
            newNode.prev = last;
            newNode.next = null;
        }
    }

    /**
     * Prints all elements in both forward (head to tail) and backward
     * (tail to head) order, each preceded by a direction label.
     *
     * <p>Output format:
     * <pre>
     *   Forward Traversal
     *   &lt;element&gt;
     *   ...
     *   Backward Traversal
     *   &lt;element&gt;
     *   ...
     * </pre>
     */
    @SuppressWarnings("java:S106")
    public void print()
    {
        DoublyNode temp = head;
        DoublyNode last = null;

        System.out.println("Forward Traversal");
        while(temp != null)
        {
            System.out.println(temp.data);
            if(temp.next == null)
                last = temp;
            temp = temp.next;
        }

        System.out.println("Backward Traversal");
        temp = last;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.prev;
        }
    }

    /**
     * Returns {@code true} if the list contains a node whose {@code data} equals {@code key}.
     *
     * <p>Traverses the list from head to tail. Returns {@code false} immediately
     * if the list is empty. O(n) time.
     *
     * @param key the value to search for
     * @return {@code true} if found, {@code false} otherwise
     */
    public boolean search(int key)
    {
        DoublyNode temp = head;

        while(temp != null)
        {
            if(temp.data == key)
                return true;
            temp = temp.next;
        }

        return false;
    }

    /**
     * Removes the first node whose {@code data} equals {@code key}.
     *
     * <p>Handles all positions: head, tail, and middle. Bidirectional links of
     * the neighboring nodes are updated accordingly. If the list is empty or
     * {@code key} is not found, the list is left unchanged. O(n) time.
     *
     * @param key the value to remove
     */
    public void delete(int key)
    {
        if(head == null)
            return;

        DoublyNode temp = head;

        while(temp != null && temp.data != key)
        {
            temp = temp.next;
        }

        if(temp == null)
            LOGGER.warning("Key Not Found: " + key);
        else if(temp == head)
        {
            head = head.next;
            if(head != null)
                head.prev = null;
        }
        else if(temp.next == null)
            temp.prev.next = null;
        else
        {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    /**
     * Demonstrates basic usage of {@link DoublyLinkedList}.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        DoublyLinkedList list = new DoublyLinkedList();

        LOGGER.info(INSERT_MSG + 10);
        list.addFirst(10);
        LOGGER.info(INSERT_MSG + 20);
        list.addFirst(20);
        LOGGER.info(INSERT_MSG + 30);
        list.addLast(30);
        LOGGER.info(INSERT_MSG + 40);
        list.addLast(40);

        list.print();

        LOGGER.info(() -> "Searching Element 30: " + (list.search(30) ? "Found" : "Not Found"));
        LOGGER.info(() -> "Searching Element 100: " + (list.search(100) ? "Found" : "Not Found"));

        int key = 20;
        LOGGER.info(() -> "Deleting Element: " + key);
        list.delete(key);

        LOGGER.info("After Deletion:");
        list.print();
    }
}
