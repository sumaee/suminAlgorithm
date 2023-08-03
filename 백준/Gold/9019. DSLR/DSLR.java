import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String[] command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            command = new String[10001];
            Arrays.fill(command, "");

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bfs(start, end);
            sb.append(command[end]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int start, int end) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[10001];

        que.offer(start);
        visited[start] = true;

        while (!que.isEmpty() && !visited[end]) {
            int curr = que.poll();
            String currCommand = command[curr];

            int d = (curr * 2) % 10000;
            int s = (curr == 0) ? 9999 : curr - 1;
            int l = (curr % 1000) * 10 + (curr / 1000);
            int r = (curr % 10) * 1000 + (curr / 10);

            if (!visited[d]) {
                visited[d] = true;
                command[d] = currCommand + "D";
                que.offer(d);
            }

            if (!visited[s]) {
                visited[s] = true;
                command[s] = currCommand + "S";
                que.offer(s);
            }

            if (!visited[l]) {
                visited[l] = true;
                command[l] = currCommand + "L";
                que.offer(l);
            }

            if (!visited[r]) {
                visited[r] = true;
                command[r] = currCommand + "R";
                que.offer(r);
            }
        }
    }

}

