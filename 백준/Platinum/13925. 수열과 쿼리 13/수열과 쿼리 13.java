import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static Tree[] tree;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new Tree[n * 4];
        for (int i = 0; i < n * 4; i++) {
            tree[i] = new Tree(0);
        }
        init(1, 0, n - 1);
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            if (check == 1) {
                query1(1, 0, n - 1, left, right, Long.parseLong(st.nextToken()));
            } else if (check == 2) {
                query2(1, 0, n - 1, left, right, Long.parseLong(st.nextToken()));
            } else if (check == 3) {
                query2(1, 0, n - 1, left, right, 0);
                query1(1, 0, n - 1, left, right, Long.parseLong(st.nextToken()));
            } else {
                sb.append(query4(1, 0, n - 1, left, right)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node].num = arr[start];
            return;
        }

        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node].num = (tree[node * 2].num + tree[node * 2 + 1].num) % MOD;
    }

    private static void query1(int node, int start, int end, int left, int right, long value) {
        updateLazy(node, start, end);

        if (end < left || start > right) {
            return;
        }

        if (end <= right && start >= left) {
            tree[node].num = (tree[node].num + (end - start + 1) * value) % MOD;
            if (start != end) {
                tree[node * 2].lazyPlus = (tree[node * 2].lazyPlus + value) % MOD;
                tree[node * 2 + 1].lazyPlus = (tree[node * 2 + 1].lazyPlus + value) % MOD;
            }
            return;
        }
        query1(node * 2, start, (start + end) / 2, left, right, value);
        query1(node * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
        tree[node].num = (tree[node * 2].num + tree[node * 2 + 1].num) % MOD;
    }

    private static void query2(int node, int start, int end, int left, int right, long value) {
        updateLazy(node, start, end);

        if (end < left || start > right) {
            return;
        }

        if (end <= right && start >= left) {
            tree[node].num = (tree[node].num * value) % MOD;
            if (start != end) {
                tree[node * 2].lazyPlus = (tree[node * 2].lazyPlus * value) % MOD;
                tree[node * 2 + 1].lazyPlus = (tree[node * 2 + 1].lazyPlus * value) % MOD;

                tree[node * 2].lazyMulti = (tree[node * 2].lazyMulti * value) % MOD;
                tree[node * 2 + 1].lazyMulti = (tree[node * 2 + 1].lazyMulti * value) % MOD;
            }
            return;
        }
        query2(node * 2, start, (start + end) / 2, left, right, value);
        query2(node * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
        tree[node].num = (tree[node * 2].num + tree[node * 2 + 1].num) % MOD;

    }


    private static long query4(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);

        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && right >= end) {
            return tree[node].num;
        }

        long lsum = query4(node * 2, start, (start + end) / 2, left, right);
        long rsum = query4(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return (lsum + rsum) % MOD;
    }

    private static void updateLazy(int node, int start, int end) {
        if (tree[node].lazyMulti != 1) {
            tree[node].num = (tree[node].num * tree[node].lazyMulti) % MOD;

            if (start != end) {
                tree[node * 2].lazyPlus = (tree[node * 2].lazyPlus * tree[node].lazyMulti) % MOD;
                tree[node * 2 + 1].lazyPlus = (tree[node * 2 + 1].lazyPlus * tree[node].lazyMulti) % MOD;

                tree[node * 2].lazyMulti = (tree[node * 2].lazyMulti * tree[node].lazyMulti) % MOD;
                tree[node * 2 + 1].lazyMulti = (tree[node * 2 + 1].lazyMulti * tree[node].lazyMulti) % MOD;
            }

            tree[node].lazyMulti = 1;
        }

        if (tree[node].lazyPlus != 0) {
            tree[node].num = (tree[node].num + (end - start + 1) * tree[node].lazyPlus) % MOD;
            if (start != end) {
                tree[node * 2].lazyPlus = (tree[node * 2].lazyPlus + tree[node].lazyPlus) % MOD;
                tree[node * 2 + 1].lazyPlus = (tree[node * 2 + 1].lazyPlus + tree[node].lazyPlus) % MOD;
            }
            tree[node].lazyPlus = 0;
        }
    }

    static class Tree {
        long num, lazyPlus, lazyMulti;

        public Tree(long num) {
            this.num = num;
            this.lazyPlus = 0L;
            this.lazyMulti = 1L;
        }
    }
}