import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int[][] apart;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1}; // 상하좌우
    static int count;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        apart = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                apart[i][j] = str.charAt(j) - '0';
            }
        }

        int apartCnt = 0;
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (apart[i][j] == 1 && !visited[i][j]) {
                    count = 1;
                    dfs(i, j);
                    counts.add(count);
                    apartCnt++;
                }
            }
        }

        Collections.sort(counts);
        sb.append(apartCnt).append("\n");
        for (int i = 0; i < counts.size(); i++) {
            sb.append(counts.get(i)).append("\n");
        }

        System.out.println(sb);

    }

    private static void dfs(int row, int col) {
        visited[row][col] = true;

        //상하좌우 확인
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //해당 범위가 aprat 범위를 벗어난다면 다음
            if (drow < 0 || drow >= n || dcol < 0 || dcol >= n) continue;

            //벗어나지 않고 방문하지 않은 아파트라면 연결된 부분 확인
            if (apart[drow][dcol] == 1 && !visited[drow][dcol]) {
                count++;
                dfs(drow, dcol);
            }
        }
    }
}
