import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] node;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        node = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            node[num1].add(num2);
            node[num2].add(num1);
        }

        parents = new int[n + 1];
        bfs(n);
        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        while (!que.isEmpty()) {
            int curr = que.poll();

            for (int child : node[curr]) {
                if (visited[child]) continue;

                visited[child] = true;
                parents[child] = curr;
                que.offer(child);
            }
        }
    }

}
