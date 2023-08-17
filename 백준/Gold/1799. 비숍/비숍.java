import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, blackCnt, whiteCnt;
    static int[][] boards;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        boards = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                boards[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //흑색 자리에 둘수 있는 개수 탐색
        visited = new boolean[n][n];
        checkBlack(0, 0, 0);

        //백색 자리에 둘수 있는 개수 탐색
        visited = new boolean[n][n];
        checkWhite(0, 1, 0);

        System.out.println(blackCnt + whiteCnt);
    }

    private static void checkWhite(int row, int col, int cnt) {
        if (col >= n) {
            row += 1;
            col = row % 2 == 0 ? 1 : 0;
        }

        if (row >= n) {
            whiteCnt = Math.max(whiteCnt, cnt);
            return;
        }

        if (isPossible(row, col)) {
            visited[row][col] = true;
            checkWhite(row, col + 2, cnt + 1);
            visited[row][col] = false;
        }

        checkWhite(row, col + 2, cnt);
    }

    private static void checkBlack(int row, int col, int cnt) {
        if (col >= n) {
            row += 1;
            col = row % 2 == 0 ? 0 : 1;
        }

        if (row >= n) {
            blackCnt = Math.max(blackCnt, cnt);
            return;
        }


        if (isPossible(row, col)) {
            visited[row][col] = true;
            checkBlack(row, col + 2, cnt + 1);
            visited[row][col] = false;
        }

        checkBlack(row, col + 2, cnt);
    }

    private static boolean isPossible(int row, int col) {
        //해당 위치가 0이라면 놓을 수 없음
        if (boards[row][col] == 0) return false;

        //위 왼 대각선에 이미 놓여있는지 확인
        int drow = row;
        int dcol = col;
        while (drow >= 0 && dcol >= 0) {
            if (visited[drow][dcol]) return false;
            drow--;
            dcol--;
        }

        //위 오 대각선에 놓여있는지 확인
        drow = row;
        dcol = col;
        while (drow >= 0 && dcol < n) {
            if (visited[drow][dcol]) return false;
            drow--;
            dcol++;
        }

        //아래 왼 대각선에 놓여있는지 확인
        drow = row;
        dcol = col;
        while (drow < n && dcol >= 0) {
            if (visited[drow][dcol]) return false;
            drow++;
            dcol--;
        }

        //아래 오른 대각선에 놓여있는지 확인
        drow = row;
        dcol = col;
        while (drow < n && dcol < n) {
            if (visited[drow][dcol]) return false;
            drow++;
            dcol++;
        }

        return true;
    }
}
