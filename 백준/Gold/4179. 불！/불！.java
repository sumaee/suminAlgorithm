import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int r, c, answer;
    static Character[][] map;
    static Queue<Node> fire, jh;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new Character[r][c];
        fire = new LinkedList<>();
        jh = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'F') {
                    fire.offer(new Node(i, j));
                } else if (map[i][j] == 'J') {
                    jh.offer(new Node(i, j, 0));
                }

            }
        }

        answer = 0;
        if (!bfs()) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static boolean bfs() {
        while (!jh.isEmpty()) {

            int fireCnt = fire.size();
            for (int i = 0; i < fireCnt; i++) {
                Node currFire = fire.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = currFire.row + dr[j];
                    int dcol = currFire.col + dc[j];

                    if (!isPossible(drow, dcol) || map[drow][dcol] == 'F' || map[drow][dcol] == '#') continue;
                    map[drow][dcol] = 'F';
                    fire.offer(new Node(drow, dcol));
                }
            }

            int jhCnt = jh.size();
            for (int i = 0; i < jhCnt; i++) {
                Node currjh = jh.poll();
                for (int j = 0; j < 4; j++) {
                    int drow = currjh.row + dr[j];
                    int dcol = currjh.col + dc[j];

                    //범위를 벗어났다는 탈출 성공
                    if (!isPossible(drow, dcol)) {
                        answer = currjh.cnt + 1;
                        return true;
                    }

                    if (map[drow][dcol] == 'F' || map[drow][dcol] == '#' || map[drow][dcol] == 'J') continue;
                    map[drow][dcol] = 'J';
                    jh.offer(new Node(drow, dcol, currjh.cnt + 1));
                }
            }
        }

        return false;
    }

    private static boolean isPossible(int drow, int dcol) {
        return drow >= 0 && dcol >= 0 && drow < r && dcol < c;
    }

    static class Node {
        int row, col, cnt;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Node(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
}