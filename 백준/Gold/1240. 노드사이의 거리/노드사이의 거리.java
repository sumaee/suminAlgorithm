import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node>[] nodes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            nodes[start].add(new Node(end, len));
            nodes[end].add(new Node(start, len));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int firstIdx = Integer.parseInt(st.nextToken());
            int secondIdx = Integer.parseInt(st.nextToken());
            visited = new boolean[n + 1];
            sb.append(bfs(firstIdx, secondIdx)).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int startIdx, int endIdx) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(startIdx, 0));

        visited[startIdx] = true;

        int len = 0;
        while (!que.isEmpty()) {
            Node curr = que.poll();

            //도달했다면 끝
            if (curr.idx == endIdx) {
                len = curr.len;
                break;
            }

            for (Node nxt : nodes[curr.idx]) {
                if (!visited[nxt.idx]) {
                    visited[nxt.idx] = true;
                    que.offer(new Node(nxt.idx, curr.len + nxt.len));
                }
            }
        }

        return len;
    }

    static class Node {
        int idx, len;

        public Node(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }
}
