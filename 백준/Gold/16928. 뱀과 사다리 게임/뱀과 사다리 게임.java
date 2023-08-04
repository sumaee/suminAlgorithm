import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사다리 정보
        int m = Integer.parseInt(st.nextToken()); // 뱀 정보

        map = new int[101];
        for (int i = 1; i <= 100; i++) {
            map[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map[start] = end;
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map[start] = end;
        }

        System.out.println(bfs(1));
    }

    private static int bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        int[] cnt = new int[101];
        que.offer(start);
        cnt[start] = 0;

        while (!que.isEmpty()) {
            int curr = que.poll();

            //주사위로 갈수있는 길 기록
            for (int i = 1; i <= 6; i++) {
                int nxt = curr + i;

                //100을 넘어가거나 이미 방문을 했던 곳이라면 패스
                if (nxt > 100) continue;

                //방문하지 않았다면 방문
                if (cnt[map[nxt]] == 0) {
                    que.offer(map[nxt]);
                    cnt[map[nxt]] = cnt[curr] + 1;
                }
                //방문 값이 100이라면 끝
                if (nxt == 100) return cnt[100];
            }
        }
        return 0;
    }
}
