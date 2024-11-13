import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] list;
    static int[] time;
    static int[] cnt;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        time = new int[n + 1];
        cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int before = Integer.parseInt(st.nextToken());

                if (before == -1) break;

                list[before].add(i);
                cnt[i]++;
            }
        }

        topologicalSort();
    }

    private static void topologicalSort() {
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                que.offer(i);
            }
        }

        int[] answer = new int[n + 1];
        while (!que.isEmpty()) {
            int curr = que.poll();
            answer[curr] += time[curr];
            
            for (int nxt : list[curr]) {
                cnt[nxt]--;

                answer[nxt] = Math.max(answer[nxt], answer[curr]);

                if (cnt[nxt] == 0) {
                    que.offer(nxt);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}