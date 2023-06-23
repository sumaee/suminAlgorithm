import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static boolean[] visited;
    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 번호

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start].add(end);
            arr[end].add(start);
        }
        //정점이 여러개일 경우 작은 것부터 뽑기 위한 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(arr[i]);
        }
        
        visited = new boolean[n + 1];
        dfs(v);
        sb.append("\n");

        visited = new boolean[n + 1];
        bfs(v);

        System.out.println(sb);
    }

    private static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");
        for (int i = 0; i < arr[start].size(); i++) {
            if (!visited[arr[start].get(i)]) {
                dfs(arr[start].get(i));
            }
        }
    }

    private static void bfs(int start) {
        visited[start] = true;
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            int curr = que.poll();
            sb.append(curr).append(" ");

            for (int i = 0; i < arr[curr].size(); i++) {
                int nxt = arr[curr].get(i);
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    que.offer(nxt);
                }

            }

        }
    }

}