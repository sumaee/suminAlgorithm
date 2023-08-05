import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Info>[] list;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());


            int n = Integer.parseInt(st.nextToken()); // 방의 개수
            int m = Integer.parseInt(st.nextToken()); // 비밀통로 개수

            list = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            //비밀 통로 정보 입력
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int len = Integer.parseInt(st.nextToken());

                list[start].add(new Info(end, len));
                list[end].add(new Info(start, len));
            }

            int peopleNum = Integer.parseInt(br.readLine());
            sum = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < peopleNum; i++) {
                int locate = Integer.parseInt(st.nextToken());
                dijkstra(locate, n);
            }

            //최단 거리 방뽑기
            int min = Integer.MAX_VALUE;
            int answer = 0;
            for (int i = 1; i <= n; i++) {
                if (sum[i] < min) {
                    min = sum[i];
                    answer = i;
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start, int n) {
        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.len - o2.len);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        que.offer(new Info(start, 0));
        dist[start] = 0;

        while (!que.isEmpty()) {
            Info curr = que.poll();

            for (Info nxt : list[curr.idx]) {
                if (dist[nxt.idx] > curr.len + nxt.len) {
                    dist[nxt.idx] = curr.len + nxt.len;

                    que.offer(new Info(nxt.idx, dist[nxt.idx]));
                }
            }
        }

        for (int j = 1; j <= n; j++) {
            sum[j] += dist[j];
        }
    }

    static class Info {
        int idx, len;

        public Info(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }
}
