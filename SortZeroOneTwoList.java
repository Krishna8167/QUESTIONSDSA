class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class SortZeroOneTwoList {

    public static Node sortList(Node head) {
        if (head == null || head.next == null) return head;

        Node zeroDummy = new Node(0), oneDummy = new Node(0), twoDummy = new Node(0);
        Node zero = zeroDummy, one = oneDummy, two = twoDummy;

        Node current = head;

        while (current != null) {
            if (current.data == 0) {
                zero.next = current;
                zero = zero.next;
            } else if (current.data == 1) {
                one.next = current;
                one = one.next;
            } else {
                two.next = current;
                two = two.next;
            }
            current = current.next;
        }

        // Connect three lists
        zero.next = oneDummy.next != null ? oneDummy.next : twoDummy.next;
        one.next = twoDummy.next;
        two.next = null;

        return zeroDummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " → ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(1);
        head.next.next = new Node(0);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(0);

        System.out.println("Original list:");
        printList(head);

        Node sorted = sortList(head);

        System.out.println("Sorted list:");
        printList(sorted);
    }
}
