import java.util.*;

class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Enqueue operation
    public void enqueue(int x) {
        s1.push(x);
    }

    // Dequeue operation
    public int dequeue() {
        if (s2.isEmpty()) {
            // Transfer all elements from s1 → s2
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    // Peek front element
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    // Check if queue is empty
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Front: " + q.peek());   // 10
        System.out.println("Dequeue: " + q.dequeue()); // 10
        System.out.println("Front: " + q.peek());   // 20
        System.out.println("Empty: " + q.empty());  // false
    }
}
