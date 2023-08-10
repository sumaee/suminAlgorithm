import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] cheese, air;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];

        //우선 치즈 채우면서 초기 치즈 개수 세기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //치즈 개수가 0이 될때까지 돌리기
        int cnt;
        int time = 0;
        while (true) {
            visited = new boolean[n][m];
            air = new int[n][m];
            cnt = 0;
            //모서리에 있는 치즈 찾기
            findCheese(0, 0);

            //공기가 닿은 곳이 2이상인 부분 녹이기
            meltingCheese();

            //남아있는 치즈 개수 세기
            for (int[] row : cheese) {
                for (int col : row) {
                    if (col == 1) cnt++;
                }
            }
            time++;
            if (cnt == 0) break;
        }
        System.out.println(time);
    }


    private static void findCheese(int row, int col) {
        //0, 0부터 시작해 1을 만났을 때 해당 1의 자리가 0을 두번 이상 만나는지 확인 후 -1로 바꿔주기
        Queue<Cheese> que = new LinkedList<>();
        que.add(new Cheese(row, col));

        while (!que.isEmpty()) {
            Cheese curr = que.poll();

            if (visited[curr.row][curr.col]) continue;
            visited[curr.row][curr.col] = true;

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 방문한 곳이라면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m) continue;

                //해당 지점이 치즈가 있는 부분이
                if (cheese[drow][dcol] == 1) {
                    air[drow][dcol]++;
                } else if (cheese[drow][dcol] == 0) {
                    que.offer(new Cheese(drow, dcol));
                }
            }
        }
    }

    private static void meltingCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (air[i][j] >= 2) cheese[i][j] = 0;
            }
        }
    }

    static class Cheese {
        int row, col;

        public Cheese(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

