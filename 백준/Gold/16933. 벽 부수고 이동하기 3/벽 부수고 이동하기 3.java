import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, answer;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        answer = -1;
        bfs();
        System.out.println(answer);

    }

    private static void bfs() {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(0, 0, 1, 0, 0));

        boolean[][][][] visited = new boolean[n][m][k+1][2];
        visited[0][0][0][0] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            if (curr.row == n - 1 && curr.col == m - 1) {
                answer = curr.moveCnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if(drow <0 || dcol<0 || drow>=n || dcol >=m) continue;

                if (map[drow][dcol] == 0) {
                    if (curr.day == 0 && !visited[drow][dcol][curr.breakCnt][curr.day + 1]) {
                        visited[drow][dcol][curr.breakCnt][curr.day + 1] = true;
                        que.offer(new Locate(drow, dcol, curr.moveCnt + 1, curr.breakCnt, curr.day + 1));
                    } else if (curr.day == 1 && !visited[drow][dcol][curr.breakCnt][curr.day - 1]) {
                        visited[drow][dcol][curr.breakCnt][curr.day - 1] = true;
                        que.offer(new Locate(drow, dcol, curr.moveCnt + 1, curr.breakCnt, curr.day - 1));
                    }
                } else {
                    if (curr.day == 0 && curr.breakCnt < k && !visited[drow][dcol][curr.breakCnt + 1][curr.day + 1]) {
                        visited[drow][dcol][curr.breakCnt + 1][curr.day + 1] = true;
                        que.offer(new Locate(drow, dcol, curr.moveCnt + 1, curr.breakCnt + 1, curr.day + 1));
                    } else if (curr.day == 1 && curr.breakCnt < k && !visited[drow][dcol][curr.breakCnt][curr.day - 1]) {
                        visited[drow][dcol][curr.breakCnt][curr.day - 1] = true;
                        que.offer(new Locate(curr.row, curr.col, curr.moveCnt + 1, curr.breakCnt, curr.day - 1));
                    }
                }
            }
        }
    }
    static class Locate {
        int row, col, moveCnt, breakCnt, day;

        public Locate(int row, int col, int moveCnt, int breakCnt, int day) {
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
            this.breakCnt = breakCnt;
            this.day = day;
        }
    }
}