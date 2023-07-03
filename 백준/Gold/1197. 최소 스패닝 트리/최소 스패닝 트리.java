import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v, e;
    static List<Node7>[] node;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        node = new List[v];
        visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());

            node[start].add(new Node7(end, size));
            node[end].add(new Node7(start, size));
        }

        System.out.println(prim(0));
    }

    private static int prim(int start) {
        int sum = 0;
        PriorityQueue<Node7> que = new PriorityQueue<>((o1, o2) -> o1.size - o2.size);

        que.offer(new Node7(start, 0));
        while (!que.isEmpty()) {
            Node7 curr = que.poll();

            if (visited[curr.idx]) continue;

            visited[curr.idx] = true;
            sum += curr.size;

            for (Node7 nxt : node[curr.idx]) {
                if (!visited[nxt.idx]) {
                    que.offer(new Node7(nxt.idx, nxt.size));
                }
            }
        }

        return sum;
    }
}

class Node7 {
    int idx, size;

    public Node7(int idx, int size) {
        this.idx = idx;
        this.size = size;
    }
}
