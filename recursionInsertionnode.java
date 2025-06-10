/**
 * RecursiveLinkedListInsertion.java
 *
 * This program inserts a node at a specific position in a singly linked list using recursion.
 *
 * Author: [Your Name]
 * Date: [Today's Date]
 */

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class recursionInsertionnode {

    // Recursively insert a node at a given position
    public static Node insertRecursively(Node head, int data, int position) {
        // Base case: inserting at the head
        if (position == 0) {
            Node newNode = new Node(data);
            newNode.next = head;
            return newNode;
        }

        if (head == null) {
            throw new IllegalArgumentException("Position out of bounds.");
        }

        // Recurse for the next node
        head.next = insertRecursively(head.next, data, position - 1);
        return head;
    }

    // Utility method to print the linked list
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        Node head = null;

        head = insertRecursively(head, 10, 0); // Insert at head
        head = insertRecursively(head, 20, 1); // Insert at tail
        head = insertRecursively(head, 15, 1); // Insert in middle

        System.out.println("Linked List after recursive insertions:");
        printList(head);
    }
}

