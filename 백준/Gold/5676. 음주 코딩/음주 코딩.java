import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());

                //양수면 1, 음수면 -1, 0은 0으로 저장
                if (num > 0) num = 1;
                else if (num < 0) num = -1;
                arr[i] = num;
            }

            int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int size = (int) Math.pow(2, h);
            tree = new int[size];
            init(1, 1, n);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String check = st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());

                //변경이라면
                if (check.equals("C")) {
                    if (num < 0) num = -1;
                    else if (num > 0) num = 1;
                    arr[idx] = num;
                    update(1, 1, n, idx, num);
                }

                //곱셈이라면
                else {
                    long result = getValue(1, 1, n, idx, (int) num);
                    if (result == 0) {
                        sb.append(0);
                    } else if (result < 0) {
                        sb.append("-");
                    } else {
                        sb.append("+");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static long getValue(int node, int start, int end, int left, int right) {
        //범위를 벗어난다면
        if (left > end || right < start) {
            return 1;
        }

        //범위 안에 있다면
        if (left <= start && right >= end) {
            return tree[node];
        }

        long leftChild = getValue(node * 2, start, (start + end) / 2, left, right);
        long rightChild = getValue(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

        return leftChild * rightChild;
    }

    private static void update(int node, int start, int end, int idx, int num) {
        //범위가 벗어난다면
        if (idx < start || idx > end) {
            return;
        }

        if (start == end) {
            tree[node] = num;
        } else {
            update(node * 2, start, (start + end) / 2, idx, num);
            update(node * 2 + 1, (start + end) / 2 + 1, end, idx, num);

            tree[node] = tree[node * 2] * tree[node * 2 + 1];
        }
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);

            tree[node] = tree[node * 2] * tree[node * 2 + 1];
        }
    }

}