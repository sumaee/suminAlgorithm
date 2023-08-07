import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node>[] tree;
    static int v, maxLenNode, ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        v = Integer.parseInt(br.readLine());
        tree = new List[v + 1];
        for (int i = 1; i <= v; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int parentIdx = Integer.parseInt(st.nextToken());
            int childIdx = Integer.parseInt(st.nextToken());
            while (childIdx != -1) {
                int len = Integer.parseInt(st.nextToken());

                tree[parentIdx].add(new Node(childIdx, len));
                tree[childIdx].add(new Node(parentIdx, len));

                childIdx = Integer.parseInt(st.nextToken());
            }
        }

        //1번 노드에서 제일 먼 길이 찾기
        maxLenNode = 0;
        bfs(1);
        //제일 먼 곳에서 더 먼 곳 찾기
        bfs(maxLenNode);

        System.out.println(ans);

    }

    private static void bfs(int start) {
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[v + 1];
        int maxLen = 0;

        que.offer(new Node(start, 0));
        while (!que.isEmpty()) {
            Node curr = que.poll();

            if (visited[curr.idx]) continue;
            visited[curr.idx] = true;

            if (curr.len > maxLen) {
                maxLenNode = curr.idx;
                maxLen = curr.len;
            }

            for (Node nxt : tree[curr.idx]) {
                if (visited[nxt.idx]) continue;

                que.offer(new Node(nxt.idx, curr.len + nxt.len));
            }
        }

        ans = Math.max(ans, maxLen);
    }


    static class Node {
        int idx, len;

        public Node(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }
}
