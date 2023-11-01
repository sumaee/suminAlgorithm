import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int r, c, answer;
    static Queue<Locate> waters, s;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        waters = new LinkedList<>();
        s = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'S') {
                    s.offer(new Locate(i, j, 0));
                } else if (map[i][j] == '*') {
                    waters.offer(new Locate(i, j));
                }
            }
        }

        answer = INF;
        bfs();
        System.out.println(answer == INF ? "KAKTUS" : answer);
    }

    private static void bfs() {
        while (!s.isEmpty()) {
            //물 퍼뜨리기
            int len = waters.size();
            for (int i = 0; i < len; i++) {
                Locate currWater = waters.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = currWater.row + dr[j];
                    int dcol = currWater.col + dc[j];

                    if (drow < 0 || dcol < 0 || drow >= r || dcol >= c)
                        continue;

                    if (map[drow][dcol] == '.') {
                        map[drow][dcol] = '*';
                        waters.offer(new Locate(drow, dcol));
                    }
                }
            }

            //고슴도치 이동
            len = s.size();
            for (int i = 0; i < len; i++) {
                Locate currS = s.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = currS.row + dr[j];
                    int dcol = currS.col + dc[j];

                    //물로 차있거나 벽이라면 패스
                    if (drow < 0 || dcol < 0 || drow >= r || dcol >= c || map[drow][dcol] == 'X' || map[drow][dcol] == '*' || map[drow][dcol] == 'S')
                        continue;

                    //만약 다음 지점이 D라면 끝
                    if (map[drow][dcol] == 'D') {
                        answer = Math.min(answer, currS.time + 1);
                        return;
                    } else {
                        map[drow][dcol] = 'S';
                        s.offer(new Locate(drow, dcol, currS.time + 1));
                    }
                }
            }

        }
    }
    static class Locate {
        int row, col, time;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Locate(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}