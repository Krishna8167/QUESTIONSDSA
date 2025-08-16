import java.util.*;

public class RearrangeString {
    static class Key {
        char ch;
        int freq;
        Key(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public static String rearrange(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }

        PriorityQueue<Key> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                pq.add(new Key((char)(i + 'a'), count[i]));
            }
        }

        StringBuilder result = new StringBuilder();
        Key prev = new Key('#', -1);

        while (!pq.isEmpty()) {
            Key k = pq.poll();
            result.append(k.ch);

            if (prev.freq > 0) {
                pq.add(prev);
            }

            k.freq--;
            prev = k;
        }

        // If the rearranged string length is different, not possible
        if (result.length() != str.length()) {
            return "Not Possible";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String str = "aaabbc";
        System.out.println("Rearranged String: " + rearrange(str));
    }
}
