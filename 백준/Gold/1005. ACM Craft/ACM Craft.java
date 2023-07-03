import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, w;
    static int[] times, input, total;
    static List<Integer>[] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 건물의 개수
            k = Integer.parseInt(st.nextToken()); // 건설 순서 규칙 개수

            times = new int[n]; // 건물들 건설의 소요시간
            total = new int[n]; // 해당 건물을 짓기 위해 걸리는 총 소요시간
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
                total[i] = times[i];
            }

            building = new List[n];
            for (int i = 0; i < n; i++) {
                building[i] = new ArrayList<>();
            }

            input = new int[n];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken()) - 1;
                int nxt = Integer.parseInt(st.nextToken()) - 1;

                building[first].add(nxt);
                input[nxt]++;
            }

            w = Integer.parseInt(br.readLine());
            sb.append(topologicalSort()).append("\n");
        }
        System.out.println(sb);
    }

    private static int topologicalSort() {
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (input[i] == 0) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int curr = que.poll();

            if (curr == w - 1) break;

            for (int nxt : building[curr]) {
                total[nxt] = Math.max(total[nxt], total[curr] + times[nxt]);
                input[nxt]--;

                if (input[nxt] == 0) {
                    que.offer(nxt);
                }
            }
        }

        return total[w - 1];
    }
}