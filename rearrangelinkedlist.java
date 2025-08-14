class ZigZagLinkedList {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
    }

    public void zigZagReorder() {
        boolean flag = true; // true means "<" relation expected
        Node current = head;

        while (current != null && current.next != null) {
            if (flag) { // Expect current < next
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
            } else { // Expect current > next
                if (current.data < current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
            }
            flag = !flag; // Flip expectation
            current = current.next;
        }
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ZigZagLinkedList list = new ZigZagLinkedList();
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(8);
        list.add(6);
        list.add(2);
        list.add(1);

        System.out.println("Original list:");
        list.printList();

        list.zigZagReorder();

        System.out.println("Zig-Zag list:");
        list.printList();
    }
}
