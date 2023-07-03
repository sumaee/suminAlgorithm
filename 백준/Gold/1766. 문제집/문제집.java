import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static List<Integer>[] problems;
    static int[] input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        problems = new List[n];
        input = new int[n];
        for (int i = 0; i < n; i++) {
            problems[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int nxt = Integer.parseInt(st.nextToken()) - 1;

            problems[first].add(nxt);
            input[nxt]++;
        }

        topologicalSort();
        System.out.println(sb);
    }

    private static void topologicalSort() {
        PriorityQueue<Integer> que = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (input[i] == 0) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int curr = que.poll();
            sb.append(curr + 1).append(" ");

            for (int nxt : problems[curr]) {
                input[nxt]--;

                if (input[nxt] == 0) {
                    que.offer(nxt);
                }
            }
        }
    }
}
