import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, total;
    static int[][] map, check;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    total++;
                }
            }
        }

        int time = 0;
        while (total != 0) {
            visited = new boolean[n][m];
            check = new int[n][m];
            // 빙산이 얼마나 녹을 지 측정
            checkMeltCnt();

            //빙산 녹이기
            meltingIce();

            //섬의 개수 파악
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            time++;
            //두 덩어리가 됏다면 break
            if (cnt >= 2) {
                System.out.println(time);
                System.exit(0);
            }
        }

        System.out.println(0);
    }

    private static void bfs(int row, int col) {
        Queue<Ice> que = new LinkedList<>();
        que.offer(new Ice(row, col));

        visited[row][col] = true;

        while (!que.isEmpty()) {
            Ice curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위 벗어나거나 바다거나 방문했다면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol] || map[drow][dcol] == 0)
                    continue;

                visited[drow][dcol] = true;
                que.offer(new Ice(drow, dcol));
            }
        }
    }

    private static void checkMeltCnt() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //해당 지점이 빙산이 있으면 주변 바다 개수 세기
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int drow = i + dr[k];
                        int dcol = j + dc[k];

                        //범위 벗어나는지 확인
                        if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || map[drow][dcol] != 0) continue;

                        check[i][j]++;
                    }
                }
            }
        }

    }

    private static void meltingIce() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    map[i][j] -= check[i][j];
                    if (map[i][j] <= 0) {
                        map[i][j] = 0;
                        total--;
                    }


                }
            }
        }
    }

    static class Ice {
        int row, col;

        public Ice(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}