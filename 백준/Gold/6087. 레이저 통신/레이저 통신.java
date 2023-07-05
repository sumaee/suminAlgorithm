import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0}; // 시계 방향
    static int[] dc = {0, 1, 0, -1}; // 시계 방향
    static int answer, w, h;
    static Locate[] point;
    static char[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        point = new Locate[2];
        int idx = 0;
        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
                if (ch == 'C') {
                    point[idx++] = new Locate(i, j, -3, -1);
                }
            }
        }

        visited = new int[4][h][w];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < h; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        answer = Integer.MAX_VALUE;
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        PriorityQueue<Locate> que = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        que.offer(point[0]);

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            //도착지면 값 갱신
            if (curr.row == point[1].row && curr.col == point[1].col) {
                answer = curr.cnt;
                break;
            }

            //4방향 확인
            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //현재 방향과 같다면 거울개수 그대로 아니라면 거울 추가
                int nxtCnt = (curr.dir == i) ? curr.cnt : curr.cnt + 1;

                //범위를 벗어나거나 벽이거나 현재 방향과 반대 방향이라면 패스
                if (drow < 0 || drow >= h || dcol < 0 || dcol >= w || map[drow][dcol] == '*' || Math.abs(curr.dir - i) == 2)
                    continue;

                //가려는 길의 거울 개수가 이동했을 때의 거울 개수보다 많다면 갱신
                if (visited[i][drow][dcol] > nxtCnt) {
                    visited[i][drow][dcol] = nxtCnt;
                    que.offer(new Locate(drow, dcol, i, nxtCnt));
                }

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
