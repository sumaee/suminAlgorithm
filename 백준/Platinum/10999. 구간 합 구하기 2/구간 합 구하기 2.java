import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree, arr, tempTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n];
        tree = new long[4 * n];
        tempTree = new long[4 * n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(1, 0, n - 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            long startIdx = Long.parseLong(st.nextToken()) - 1;
            long endIdx = Long.parseLong(st.nextToken()) - 1;

            if (check == 1) {
                long value = Long.parseLong(st.nextToken());
                update(1, 0, n - 1, startIdx, endIdx, value);
            } else {
                sb.append(sum(1, 0, n - 1, startIdx, endIdx)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update(int node, int start, int end, long left, long right, long value) {
        update_lazy(node, start, end);

        if (left > end || right < start) {
            return;
        }

        //lazy 계산 수행
        if (left <= start && right >= end) {
            tree[node] += (end - start + 1) * value;
            if (start != end) {
                tempTree[node * 2] += value;
                tempTree[node * 2 + 1] += value;
            }
            return;
        }

        update(node * 2, start, (start + end) / 2, left, right, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update_lazy(int node, int start, int end) {
        if (tempTree[node] != 0) {
            tree[node] += (end - start + 1) * tempTree[node];
            if (start != end) {
                tempTree[node * 2] += tempTree[node];
                tempTree[node * 2 + 1] += tempTree[node];
            }
            tempTree[node] = 0;
        }
    }

    private static long sum(int node, int start, int end, long left, long right) {
        update_lazy(node, start, end);

        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        long leftSum = sum(node * 2, start, (start + end) / 2, left, right);
        long rightSum = sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return leftSum + rightSum;
    }
}