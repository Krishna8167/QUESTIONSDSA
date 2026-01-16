import java.util.*;

/* ---------- Complex Number Class ---------- */
class Complex {
    double real, imag;

    Complex(double r, double i) {
        this.real = r;
        this.imag = i;
    }

    double magnitude() {
        return Math.sqrt(real * real + imag * imag);
    }

    Complex add(Complex c) {
        return new Complex(this.real + c.real, this.imag + c.imag);
    }

    double distance(Complex c) {
        return Math.sqrt(Math.pow(real - c.real, 2) + Math.pow(imag - c.imag, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Complex)) return false;
        Complex c = (Complex) o;
        return real == c.real && imag == c.imag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }

    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }
}

/* ---------- Main DS Heavy Program ---------- */
public class ComplexDSSystem {

    public static void main(String[] args) {

        List<Complex> list = Arrays.asList(
                new Complex(3, 4),
                new Complex(1, 1),
                new Complex(5, 12),
                new Complex(0, 2),
                new Complex(3, 4),
                new Complex(8, 15)
        );

        /* ---------- 1. HashMap Frequency Count ---------- */
        Map<Complex, Integer> freqMap = new HashMap<>();
        for (Complex c : list)
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        System.out.println("Frequency Map:");
        freqMap.forEach((k, v) -> System.out.println(k + " → " + v));

        /* ---------- 2. TreeSet Sorted by Magnitude ---------- */
        TreeSet<Complex> sortedSet = new TreeSet<>(
                Comparator.comparingDouble(Complex::magnitude)
        );
        sortedSet.addAll(list);

        System.out.println("\nSorted by Magnitude:");
        sortedSet.forEach(System.out::println);

        /* ---------- 3. PriorityQueue (Top K Largest) ---------- */
        int k = 3;
        PriorityQueue<Complex> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.magnitude(), a.magnitude())
        );
        maxHeap.addAll(list);

        System.out.println("\nTop " + k + " Largest Complex Numbers:");
        for (int i = 0; i < k; i++)
            System.out.println(maxHeap.poll());

        /* ---------- 4. Graph Construction ---------- */
        Map<Complex, List<Complex>> graph = new HashMap<>();
        double threshold = 5.0;

        for (Complex c1 : list) {
            graph.putIfAbsent(c1, new ArrayList<>());
            for (Complex c2 : list) {
                if (!c1.equals(c2) && c1.distance(c2) <= threshold) {
                    graph.get(c1).add(c2);
                }
            }
        }

        /* ---------- 5. BFS Traversal ---------- */
        System.out.println("\nBFS Traversal:");
        bfs(list.get(0), graph);
    }

    /* ---------- BFS ---------- */
    static void bfs(Complex start, Map<Complex, List<Complex>> graph) {
        Set<Complex> visited = new HashSet<>();
        Queue<Complex> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Complex curr = queue.poll();
            System.out.println(curr);

            for (Complex neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
