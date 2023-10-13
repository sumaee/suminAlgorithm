import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, empty, answer;
    static int[][] map;
    static List<Virus> virus;
    static Virus[] activeVirus;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        virus = new ArrayList<>();
        empty = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                //바이러스가 있는 칸이면 바이러스 위치 기록
                if (map[i][j] == 2) {
                    virus.add(new Virus(i, j));
                }

                //빈칸 세기
                else if (map[i][j] == 0) {
                    empty++;
                }
            }
        }

        //빈칸이 없다면 0출력
        if (empty == 0) {
            System.out.println(0);
        } else {
            activeVirus = new Virus[m];
            answer = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    private static void dfs(int cnt, int idx) {
        if (cnt == m) {
            //바이러스 퍼뜨리기
            bfs(empty);
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            activeVirus[cnt] = virus.get(i);
            dfs(cnt + 1, i + 1);
        }
    }

    private static void bfs(int emptyCnt) {
        Queue<Virus> que = new LinkedList<>();
        visited = new boolean[n][n];
        //활성 바이러스 넣기
        for (Virus active : activeVirus) {
            que.offer(new Virus(active.row, active.col, 0));
            visited[active.row][active.col] = true;
        }

        while (!que.isEmpty()) {
            Virus curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위 벗어나는지 확인
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || visited[drow][dcol] || map[drow][dcol] == 1)
                    continue;

                //범위가 안벗어난다면 넓히기
                //해당 지역이 빈칸이라면 빈칸 개수 없애기
                if (map[drow][dcol] == 0) {
                    emptyCnt--;
                }

                //빈칸이 없다면 정답
                if (emptyCnt == 0) {
                    answer = Math.min(answer, curr.time + 1);
                    return;
                }

                //활성 바이러스가 있는 곳이라면
                visited[drow][dcol] = true;
                que.offer(new Virus(drow, dcol, curr.time + 1));
            }
        }
    }

    static class Virus {
        int row, col, time;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Virus(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;

        }
    }
}