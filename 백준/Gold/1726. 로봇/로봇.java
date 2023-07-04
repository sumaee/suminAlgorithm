import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[] dr = {0, 0, 1, -1}; // 동서남북
    static int[] dc = {1, -1, 0, 0}; // 동서남북
    static int answer, finRow, finCol, finDir, n, m;
    static int[][] factory;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 세로길이
        n = Integer.parseInt(st.nextToken()); //가로길이

        //공장 궤도 입력
        factory = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                factory[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int stRow = Integer.parseInt(st.nextToken()) - 1;
        int stCol = Integer.parseInt(st.nextToken()) - 1;
        int stDir = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        finRow = Integer.parseInt(st.nextToken()) - 1;
        finCol = Integer.parseInt(st.nextToken()) - 1;
        finDir = Integer.parseInt(st.nextToken()) - 1;

        //동서남북으로 이동하는 경우
        visited = new boolean[m][n][4];
        bfs(stRow, stCol, stDir);
        System.out.println(answer);

    }

    private static void bfs(int stRow, int stCol, int stDir) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(stRow, stCol, stDir, 0));
        visited[stRow][stCol][stDir] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            //도착지라면 끝내기
            if (curr.row == finRow && curr.col == finCol && curr.dir == finDir) {
                answer = curr.cnt;
                return;
            }

            //바라보고 있는 방향으로 그냥 움직인다면
            for (int i = 1; i <= 3; i++) {
                int drow = curr.row + (dr[curr.dir] * i);
                int dcol = curr.col + (dc[curr.dir] * i);


                //범위 체크
                if (drow < 0 || drow >= m || dcol < 0 || dcol >= n || visited[drow][dcol][curr.dir]) continue;
                //만약 움직이려는 위치가 벽이라면 갈 수 없으므로 끝냄
                if (factory[drow][dcol] == 1) break;

                visited[drow][dcol][curr.dir] = true;
                que.offer(new Locate(drow, dcol, curr.dir, curr.cnt + 1));
            }

            //방향전환
            for (int i = 0; i < 4; i++) {
                if (curr.dir == i || visited[curr.row][curr.col][i]) continue;

                int turn = 1;
                //반대를 보려고 하는 경우는 한번 더 더해주기
                if (curr.dir == 0 && i == 1) turn++;
                else if (curr.dir == 1 && i == 0) turn++;
                else if (curr.dir == 2 && i == 3) turn++;
                else if (curr.dir == 3 && i == 2) turn++;


                visited[curr.row][curr.col][i] = true;
                que.offer(new Locate(curr.row, curr.col, i, curr.cnt + turn));
            }
        }
    }

    static class Locate {
        int row, col, dir, cnt;

        public Locate(int row, int col, int dir, int cnt) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cnt = cnt;
        }
    }


}
