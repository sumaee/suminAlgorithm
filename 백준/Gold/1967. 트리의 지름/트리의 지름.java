import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, ans;
    static int maxLenNode;
    static boolean[] visited;
    static List<Node>[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        node = new List[n];
        for (int i = 0; i < n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parentIdx = Integer.parseInt(st.nextToken()) - 1;
            int childIdx = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            node[parentIdx].add(new Node(childIdx, w));
            node[childIdx].add(new Node(parentIdx, w));
        }

        //루트 노드인 1번 부터 제일 먼 길이 찾기
        maxLenNode = 0;
        bfs(0);
        //루트노드와 제일 긴 거리를 찾기
        bfs(maxLenNode);

        System.out.println(ans);
    }

    private static void bfs(int start) {
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[n];
        int maxLen = 0;

        que.offer(new Node(start, 0));
        while (!que.isEmpty()) {
            Node curr = que.poll();

            if (visited[curr.idx]) continue;
            visited[curr.idx] = true;

            //최대 길이 갱신
            if (curr.w > maxLen) {
                maxLenNode = curr.idx;
                maxLen = curr.w;
            }

            for (Node nxt : node[curr.idx]) {
                if (visited[nxt.idx]) continue;

                que.offer(new Node(nxt.idx, curr.w + nxt.w));
            }
        }
        ans = Math.max(ans, maxLen);
    }

    static class Node {
        int idx, w;

        public Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}
