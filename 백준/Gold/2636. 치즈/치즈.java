import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] cheese;
    static boolean[][] visited;
    static List<Integer> cheeseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];
        cheeseCnt = new ArrayList<>();

        //우선 치즈 채우면서 초기 치즈 개수 세기
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());

                if (cheese[i][j] == 1) cnt++;
            }
        }

        cheeseCnt.add(cnt);

        //치즈 개수가 0이 될때까지 돌리기
        while (true) {
            visited = new boolean[n][m];
            cnt = 0;
            //모서리에 있는 치즈 찾기
            findCheese(0, 0);

            //모서리 치즈 녹이기
            meltingCheese();

            //남아있는 치즈 개수 세기
            for (int[] row : cheese) {
                for (int col : row) {
                    if (col == 1) cnt++;
                }
            }

            cheeseCnt.add(cnt);

            if (cnt == 0) break;
        }

        System.out.println(cheeseCnt.size() - 1 + "\n" + cheeseCnt.get(cheeseCnt.size() - 2));
    }


    private static void findCheese(int row, int col) {
        //0, 0부터 시작해 1을 만나면 해당 지점을 -1로 바꿔주기
        Queue<Cheese> que = new LinkedList<>();
        que.add(new Cheese(row, col));
        visited[row][col] = true;

        while (!que.isEmpty()) {
            Cheese curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 방문한 곳이라면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || visited[drow][dcol]) continue;

                //해당 지점이 치즈가 있는 부분이라면 -1로 변경
                if (cheese[drow][dcol] == 1) {
                    cheese[drow][dcol] = -1;
                } else if (cheese[drow][dcol] == 0) {
                    que.offer(new Cheese(drow, dcol));
                }
                visited[drow][dcol] = true;
            }
        }
    }

    private static void meltingCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cheese[i][j] == -1) cheese[i][j] = 0;
            }
        }
    }
}

class Cheese {
    int row, col;

    public Cheese(int row, int col) {
        this.row = row;
        this.col = col;
    }
}