import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, startX, startY, endX, endY;
    static Locate[] markets;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine());

            //시작점
            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            //편의점 정보
            markets = new Locate[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                markets[i] = new Locate(x, y);
            }

            //도착점
            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            visited = new boolean[n];
            sb.append(bfs() ? "happy" : "sad").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs() {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(startX, startY));

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            //현재 위치에서 도착지까지 갈 수 있는 지 확인
            int dist = Math.abs(curr.x - endX) + Math.abs(curr.y - endY);
            if (dist <= 1000) return true;

            //마켓을 돌면서 방문하지 않은 곳이면서 현재 위치에서 갈 수 있는 곳 체크
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dist = Math.abs(markets[i].x - curr.x) + Math.abs(markets[i].y - curr.y);
                    if (dist <= 1000) {
                        visited[i] = true;
                        que.offer(markets[i]);
                    }
                }

            }
        }

        return false;
    }

    static class Locate {
        int x, y;

        public Locate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}