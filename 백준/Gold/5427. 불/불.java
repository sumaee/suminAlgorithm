import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h, answer;
    static Character[][] map;

    static Queue<Locate> fires, sg;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fires = new LinkedList<>();
            sg = new LinkedList<>();

            map = new Character[h][w];
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '*') fires.offer(new Locate(i, j));
                    else if (map[i][j] == '@') sg.offer(new Locate(i, j, 0));
                }
            }

            answer = 0;
            if (!bfs()) {
                sb.append("IMPOSSIBLE");
            } else {
                sb.append(answer);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean bfs() {
        //상근이가 움직이지 않을 때까지 진행
        while (!sg.isEmpty()) {
            //불먼저 이동
            int fireCnt = fires.size();
            for (int i = 0; i < fireCnt; i++) {
                Locate currFire = fires.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = currFire.row + dr[j];
                    int dcol = currFire.col + dc[j];

                    //범위를 벗어나거나 이미 불이거나 벽이라면 패스
                    if (!isPossible(drow, dcol) || map[drow][dcol] == '@' || map[drow][dcol] == '#') continue;

                    map[drow][dcol] = '@';
                    fires.offer(new Locate(drow, dcol));
                }
            }

            //상근이 이동
            int sgCnt = sg.size();
            for (int i = 0; i < sgCnt; i++) {
                Locate currSg = sg.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = currSg.row + dr[j];
                    int dcol = currSg.col + dc[j];

                    //범위를 벗어나면 탙출 성공
                    if (!isPossible(drow, dcol)) {
                        answer = currSg.time + 1;
                        return true;
                    }

                    if (map[drow][dcol] == '@' || map[drow][dcol] == '#' || map[drow][dcol] == '*') continue;

                    map[drow][dcol] = '*';
                    sg.offer(new Locate(drow, dcol, currSg.time + 1));
                }
            }
        }

        return false;
    }

    private static boolean isPossible(int row, int col) {
        return row >= 0 && col >= 0 && row < h && col < w;
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