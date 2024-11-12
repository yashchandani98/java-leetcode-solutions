package heap;

import java.util.*;
public class InterviewBitMaximumSumCombinations {
    class Pair {
        int sum;
        int i;
        int j;

        Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    public int[] solve(int[] A, int[] B, int C) {
        // Sort both arrays in descending order to prioritize largest sums
        Arrays.sort(A);
        Arrays.sort(B);

        int N = A.length;
        int[] result = new int[C];

        // Max heap to store pairs of sums and their indices
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.sum - a.sum);

        // Set to keep track of visited index pairs
        HashSet<String> visited = new HashSet<>();

        // Start with the largest possible sum (A[N-1] + B[N-1])
        maxHeap.add(new Pair(A[N-1] + B[N-1], N-1, N-1));
        visited.add((N-1) + "," + (N-1));

        for (int k = 0; k < C; k++) {
            // Extract the largest sum from the heap
            Pair current = maxHeap.poll();
            result[k] = current.sum;

            int i = current.i;
            int j = current.j;

            // Push the next pair (i-1, j) if not already visited
            if (i - 1 >= 0 && !visited.contains((i-1) + "," + j)) {
                maxHeap.add(new Pair(A[i-1] + B[j], i-1, j));
                visited.add((i-1) + "," + j);
            }

            // Push the next pair (i, j-1) if not already visited
            if (j - 1 >= 0 && !visited.contains(i + "," + (j-1))) {
                maxHeap.add(new Pair(A[i] + B[j-1], i, j-1));
                visited.add(i + "," + (j-1));
            }
        }

        return result;
    }
}
