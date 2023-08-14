import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, answer;
    static int[][] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dfs(0, 1, 0);
        System.out.println(answer);
    }

    private static void dfs(int row, int col, int dir) {
        if (row == n - 1 && col == n - 1) {
            answer++;
            return;
        }

        //파이프가 놓인 방향에 따라 움직일 수 있는 게 다름
        //파이프가 가로로 놓여있다면
        if (dir == 0) {
            //파이프를 가로로 한칸 움직일 수 있고 해당 위치가 빈칸일때
            if (col + 1 < n && house[row][col + 1] == 0) {
                dfs(row, col + 1, 0);
            }
        }
        //파이프가 세로로 놓여있을 떄
        else if (dir == 1) {
            //파이프를 세로로 한칸 움직일 수 있고 해당 위치가 빈칸일때
            if (row + 1 < n && house[row + 1][col] == 0) {
                dfs(row + 1, col, 1);
            }
        }
        //파이프가 대각으로 놓여있다면
        else if (dir == 2) {
            //파이프를 가로로 놓을 수 있고 해당 위치가 빈칸일때
            if (col + 1 < n && house[row][col + 1] == 0) {
                dfs(row, col + 1, 0);
            }

            //파이프를 세로로 놓을 수 있고 해당 위치가 빈칸일때
            if (row + 1 < n && house[row + 1][col] == 0) {
                dfs(row + 1, col, 1);
            }
        }

        //모든 경우에서 대각으로 놓을 수 있는 경우가 있으므로
        if (row + 1 < n && col + 1 < n && house[row + 1][col + 1] == 0 && house[row][col + 1] == 0 && house[row + 1][col] == 0) {
            dfs(row + 1, col + 1, 2);
        }
    }
}
