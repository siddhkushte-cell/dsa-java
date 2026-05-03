package com.java.sid.linkedlist;

import java.util.logging.Logger;

/**
 * A singly linked list node holding an integer value.
 */
class Node
{
    /** The integer value stored in this node. */
    int data;

    /** Reference to the next node in the list, or {@code null} if this is the last node. */
    Node next;
}

/**
 * A singly linked list of integers.
 *
 * <p>Elements are stored as {@link Node} objects chained via {@code next} references.
 * New elements are always inserted at the head, giving O(1) insertion.
 *
 * <pre>
 *   LinkedList list = new LinkedList();
 *   list.addFirst(10);
 *   list.addFirst(20); // list: 20 -> 10
 * </pre>
 */
public class LinkedList {

    private static final Logger LOGGER = Logger.getLogger(LinkedList.class.getName());
    private static final String INSERT_MSG = "Inserting Element: ";

    /** The first node of the list, or {@code null} if the list is empty. */
    Node head;

    /**
     * Inserts a new element at the beginning of the list.
     *
     * @param val the integer value to insert
     */
    public void addFirst(int val)
    {
        Node newNode = new Node();
        newNode.data = val;
        newNode.next = head;

        head = newNode;
    }

    /**
     * Prints each element of the list to standard output, one per line,
     * traversing from head to tail.
     */
    @SuppressWarnings("java:S106")
    public void print()
    {
        Node temp = head;

        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * Appends a new element at the end of the list.
     *
     * <p>If the list is empty the new node becomes the head; otherwise the list
     * is traversed to find the tail and the node are linked there. O(n) time.
     *
     * @param val the integer value to append
     */
    public void addLast(int val)
    {
        Node newNode = new Node();
        newNode.data = val;
        newNode.next = null;

        if(head == null)
            head = newNode;
        else {
            Node lastNode = head;

            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            lastNode.next = newNode;
        }
    }

    /**
     * Removes the first node whose {@code data} equals {@code key}.
     *
     * <p>If the head matches, the head pointer is advanced to the next node.
     * Otherwise, the list is traversed until the predecessor of the target node
     * is found and the target is unlinked. If {@code key} is not present the
     * list is unchanged.
     *
     * @param key the value to remove
     * @throws NullPointerException if the list is empty
     */
    public void delete(int key)
    {
        if(head.data == key)
        {
            head = head.next;
        }
        else
        {
            Node temp = head;

            while(temp.next != null)
            {
                if(temp.next.data == key)
                {
                    temp.next = temp.next.next;
                    break;
                }
                else
                    temp = temp.next;
            }
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
        Node temp = head;

        while(temp != null)
        {
            if(temp.data == key)
                return true;
            temp = temp.next;
        }

        return false;
    }

    /**
     * Demonstrates basic usage of {@link LinkedList}.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        LOGGER.info(INSERT_MSG + 10);
        list.addFirst(10);
        LOGGER.info(INSERT_MSG + 20);
        list.addFirst(20);
        LOGGER.info(INSERT_MSG + 30);
        list.addFirst(30);
        LOGGER.info(INSERT_MSG + 40);
        list.addFirst(40);
        LOGGER.info(INSERT_MSG + 50);
        list.addLast(50);
        LOGGER.info(INSERT_MSG + 60);
        list.addLast(60);

        LOGGER.info("The LinkedList Elements Are [Before Deletion]:");
        list.print();

        LOGGER.info("Deleting Element: 30");
        list.delete(30);

        LOGGER.info("The LinkedList Elements Are [After Deletion]:");
        list.print();

        LOGGER.info(() -> "Searching for 20: " + list.search(20));
    }
}
