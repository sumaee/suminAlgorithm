import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Cloud> clouds;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //첫 구름 생성 위치
        clouds = new LinkedList<>();
        clouds.offer(new Cloud(n - 1, 0));
        clouds.offer(new Cloud(n - 1, 1));
        clouds.offer(new Cloud(n - 2, 0));
        clouds.offer(new Cloud(n - 2, 1));

        //방향 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int move = Integer.parseInt(st.nextToken());

            visited = new boolean[n][n];

            //구름 이동
            moveClouds(dir, move);
            //구름이 사라지고 물복사 버그 마법
            magic();
            //구름 생성위치 찾기
            createClouds();
        }

        //물 양 세기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += map[i][j];
            }
        }
        System.out.println(answer);

    }

    private static void createClouds() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //구름이 사라진 자리가 아니고 물이 2이상있다면 구름 생성
                if (!visited[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    clouds.offer(new Cloud(i, j));
                }
            }
        }

    }

    private static void magic() {
        while (!clouds.isEmpty()) {
            Cloud cloud = clouds.poll();
            int cnt = 0;

            visited[cloud.row][cloud.col] = true;
            for (int i = 1; i < 8; i += 2) {
                int drow = cloud.row + dr[i];
                int dcol = cloud.col + dc[i];
                //범위 벗어나면 안됨
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n) continue;

                if (map[drow][dcol] > 0) {
                    cnt++;
                }
            }

            map[cloud.row][cloud.col] += cnt;
        }
    }

    private static void moveClouds(int dir, int move) {
        for (Cloud cloud : clouds) {
            cloud.row = (n + cloud.row + dr[dir] * (move % n)) % n;
            cloud.col = (n + cloud.col + dc[dir] * (move % n)) % n;
            map[cloud.row][cloud.col]++;
        }
    }

    static class Cloud {
        int row, col;

        public Cloud(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}