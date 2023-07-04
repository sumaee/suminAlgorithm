import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, h);

        tree = new Node[size];
        init(1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int minIdx = Integer.parseInt(st.nextToken()) - 1;
            int maxIdx = Integer.parseInt(st.nextToken()) - 1;

            Node result = findValue(1, 0, n - 1, minIdx, maxIdx);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }
        System.out.println(sb);
    }

    private static void init(int node, int start, int end) {
        //leaf 노드라면 값 넣기
        if (start == end) {
            tree[node] = new Node(arr[start], arr[start]);
        }
        //leaf노드가 아니라면 채워 넣기
        else {
            // 왼쪽 노드 채우기
            init(node * 2, start, (start + end) / 2);
            //오른쪽 노드 채우기
            init(node * 2 + 1, (start + end) / 2 + 1, end);

            //최대 최소 트리 채우기
            tree[node] = new Node(Math.min(tree[node * 2].min, tree[node * 2 + 1].min), Math.max(tree[node * 2].max, tree[node * 2 + 1].max));
        }
    }

    private static Node findValue(int node, int start, int end, int minIdx, int maxIdx) {
        //범위 안에 있는 경우
        if (minIdx <= start && end <= maxIdx) {
            return tree[node];
        }

        //범위 밖에 있는 경우
        if (minIdx > end || maxIdx < start) {
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        //왼쪽 값
        Node left = findValue(node * 2, start, (start + end) / 2, minIdx, maxIdx);
        //오른쪽 값
        Node right = findValue(node * 2 + 1, (start + end) / 2 + 1, end, minIdx, maxIdx);

        return new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
    }

    static class Node {
        int min, max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
