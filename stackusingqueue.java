import java.util.*;

class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // Push operation
    public void push(int x) {
        q2.add(x); // step 1: put new element into q2

        // step 2: move all elements from q1 → q2
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // step 3: swap references (q1 becomes q2, q2 becomes empty)
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Pop operation
    public int pop() {
        return q1.remove();
    }

    // Top operation
    public int top() {
        return q1.peek();
    }

    // Empty check
    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack st = new MyStack();
        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println("Top: " + st.top());   // 30
        System.out.println("Pop: " + st.pop());   // 30
        System.out.println("Top: " + st.top());   // 20
        System.out.println("Empty: " + st.empty()); // false
    }
}
