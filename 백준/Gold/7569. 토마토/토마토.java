import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] dh = {1, -1};
    static int n, m, h;
    static int[][][] tomatoes;
    static Queue<Tomato> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomatoes = new int[h][n][m];
        que = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    int num = Integer.parseInt(st.nextToken());
                    tomatoes[i][j][k] = num;

                    if (num == 1) {
                        que.offer(new Tomato(j, k, i, 0));
                    }
                }
            }
        }

        bfs();

    }

    private static void bfs() {
        int answer = 0;
        while (!que.isEmpty()) {
            Tomato curr = que.poll();

            //상하좌우 토마토 익히기
            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 토마토가 없다면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || tomatoes[curr.layer][drow][dcol] == -1) continue;

                if (tomatoes[curr.layer][drow][dcol] == 0) {
                    que.offer(new Tomato(drow, dcol, curr.layer, curr.day + 1));
                    tomatoes[curr.layer][drow][dcol] = 1;
                }
            }

            //위아래 토마토 익히기
            for (int i = 0; i < 2; i++) {
                int dhigh = curr.layer + dh[i];

                //범위를 벗어나거나 토마토가 없다면 패스
                if (dhigh < 0 || dhigh >= h || tomatoes[dhigh][curr.row][curr.col] == -1) continue;

                if (tomatoes[dhigh][curr.row][curr.col] == 0) {
                    que.offer(new Tomato(curr.row, curr.col, dhigh, curr.day + 1));
                    tomatoes[dhigh][curr.row][curr.col] = 1;
                }
            }

            answer = Math.max(answer, curr.day);
        }

        //0이 있다면 익힐 수 없음
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (tomatoes[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Tomato {
    int row, col, layer, day;

    public Tomato(int row, int col, int layer, int day) {
        this.row = row;
        this.col = col;
        this.layer = layer;
        this.day = day;
    }
}