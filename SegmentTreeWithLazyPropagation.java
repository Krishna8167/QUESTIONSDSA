// Author: Kris Shar
// Topic: Advanced DSA - Segment Tree with Lazy Propagation
// Description: Efficient range sum query and range update using Lazy Propagation

import java.util.*;

public class SegmentTreeWithLazyPropagation {
    private int[] tree, lazy;
    private int n;

    public SegmentTreeWithLazyPropagation(int[] arr) {
        this.n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(arr, 2 * node + 1, start, mid);
        build(arr, 2 * node + 2, mid + 1, end);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range update with Lazy Propagation
    public void updateRange(int l, int r, int val) {
        updateRangeUtil(0, 0, n - 1, l, r, val);
    }

    private void updateRangeUtil(int node, int start, int end, int l, int r, int val) {
        // Apply any pending updates
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        // Out of range
        if (start > end || start > r || end < l)
            return;

        // Total overlap
        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * val;
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }

        // Partial overlap
        int mid = (start + end) / 2;
        updateRangeUtil(2 * node + 1, start, mid, l, r, val);
        updateRangeUtil(2 * node + 2, mid + 1, end, l, r, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range Query
    public int queryRange(int l, int r) {
        return queryRangeUtil(0, 0, n - 1, l, r);
    }

    private int queryRangeUtil(int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l)
            return 0;

        // Resolve pending updates
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        // Total overlap
        if (start >= l && end <= r)
            return tree[node];

        // Partial overlap
        int mid = (start + end) / 2;
        int left = queryRangeUtil(2 * node + 1, start, mid, l, r);
        int right = queryRangeUtil(2 * node + 2, mid + 1, end, l, r);
        return left + right;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTreeWithLazyPropagation seg = new SegmentTreeWithLazyPropagation(arr);

        System.out.println("Initial sum (0-5): " + seg.queryRange(0, 5));

        seg.updateRange(1, 3, 10);
        System.out.println("After adding 10 to range (1-3):");
        System.out.println("Sum (0-5): " + seg.queryRange(0, 5));
        System.out.println("Sum (1-3): " + seg.queryRange(1, 3));
    }
}
