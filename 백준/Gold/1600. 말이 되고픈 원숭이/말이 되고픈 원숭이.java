import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k, w, h;
    static int[][] map;
    static boolean[][][] visited;
    static int[] horseDr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] horseDc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col, 0, 0));

        visited[row][col][0] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            //현재 위치가 도착지점이라면 정답 리턴
            if (curr.row == h - 1 && curr.col == w - 1) return curr.moveCnt;

            //k가 남아있을 때 말처럼 움직인 동작 넣기
            if (curr.moveHorseCnt < k) {
                for (int i = 0; i < 8; i++) {
                    int drow = curr.row + horseDr[i];
                    int dcol = curr.col + horseDc[i];

                    //범위 벗어나고 해당 위치 방문했는지 확인
                    if (!isPossible(drow, dcol, curr.moveHorseCnt + 1)) continue;

                    visited[drow][dcol][curr.moveHorseCnt + 1] = true;
                    que.offer(new Locate(drow, dcol, curr.moveCnt + 1, curr.moveHorseCnt + 1));
                }
            }
            //말처럼 안움직였을 때 동작
            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (!isPossible(drow, dcol, curr.moveHorseCnt)) continue;

                visited[drow][dcol][curr.moveHorseCnt] = true;
                que.offer(new Locate(drow, dcol, curr.moveCnt + 1, curr.moveHorseCnt));
            }

        }

        return -1;
    }

    private static boolean isPossible(int drow, int dcol, int cnt) {
        return drow >= 0 && dcol >= 0 && drow < h && dcol < w && !visited[drow][dcol][cnt] && map[drow][dcol] == 0;
    }

    static class Locate {
        int row, col, moveCnt, moveHorseCnt;

        public Locate(int row, int col, int moveCnt, int moveHorseCnt) {
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
            this.moveHorseCnt = moveHorseCnt;
        }
    }
}