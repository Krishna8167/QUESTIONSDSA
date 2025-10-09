import java.util.*;

public class TopKFrequentWords {
    public static List<String> topKFrequent(String[] words, int k) {
        // Count frequencies
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) count.put(word, count.getOrDefault(word, 0) + 1);

        // Use a min-heap with custom comparator
        PriorityQueue<String> heap = new PriorityQueue<>(
            (a, b) -> count.get(a).equals(count.get(b)) ? b.compareTo(a) : count.get(a) - count.get(b)
        );

        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll(); // maintain top k
        }

        // Extract result in reverse order
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll());
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"i","love","leetcode","i","love","coding"};
        int k = 2;
        System.out.println(topKFrequent(words, k)); // Output: [i, love]
    }
}
