import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, max, answer;
    static int[][] map, temp;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        answer = 0;
        for (int i = 0; i < max; i++) {
            //물에 잠긴 곳 체크 -> 잠긴곳 1, 안잠긴 곳 0
            temp = new int[n][n];
            checkMap(i);
            visited = new boolean[n][n];
            //안전 구역 확인
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (temp[j][k] == 0 && !visited[j][k]) {
                        cnt++;
                        bfs(j, k);
                    }
                }
            }

            answer = Math.max(cnt, answer);
        }

        System.out.println(answer);
    }

    private static void bfs(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col));

        visited[row][col] = true;
        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위 벗어나거나 이미 방문했거나 물에 잠겼다면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= n || visited[drow][dcol] || temp[drow][dcol] == 1)
                    continue;

                visited[drow][dcol] = true;
                que.offer(new Locate(drow, dcol));
            }
        }
    }

    private static void checkMap(int rain) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= rain) {
                    temp[i][j] = 1;
                }
            }
        }
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}