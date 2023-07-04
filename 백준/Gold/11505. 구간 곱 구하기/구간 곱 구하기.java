import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, h);

        tree = new long[size];
        init(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            long num = Integer.parseInt(st.nextToken());

            if (check == 1) {
                arr[idx] = num;
                update(1, 1, n, idx, num);
            } else {
                sb.append(findValue(1, 1, n, idx, (int) num)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long findValue(int node, int start, int end, int left, int right) {
        //범위를 벗어나면 아웃
        if (left > end || right < start) {
            return 1;
        }

        //범위 안에 있다면
        if (left <= start && right >= end) {
            return tree[node];
        }

        long leftChild = findValue(node * 2, start, (start + end) / 2, left, right);
        long rightChild = findValue(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

        return (leftChild * rightChild) % 1000000007;
    }

    private static void update(int node, int start, int end, int idx, long num) {
        //범위가 벗어난다면
        if (idx < start || idx > end) {
            return;
        }

        if (start == end) {
            tree[node] = num;
        } else {
            update(node * 2, start, (start + end) / 2, idx, num);
            update(node * 2 + 1, (start + end) / 2 + 1, end, idx, num);

            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
        }


    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);

            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
        }
    }
}
