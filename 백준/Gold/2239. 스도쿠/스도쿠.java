import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0, 0);
    }

    private static void dfs(int row, int col) {
        //9번째 행까지 다 채웠으면
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int[] r : board) {
                for (int c : r) {
                    sb.append(c);
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        //9번째 열까지 다 찼으면 다음 행으로 넘어가기
        if (col == 9) {
            dfs(row + 1, 0);
            return;
        }

        //빈칸이라면 채우기
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                //해당 숫자가 들어갈 수 있다면 채우고 다음으로 넘어가기
                if (isPossible(i, row, col)) {
                    board[row][col] = i;
                    dfs(row, col + 1);
                }
            }

            board[row][col] = 0;
            return;
        }

        dfs(row, col + 1);
    }

    private static boolean isPossible(int num, int row, int col) {
        //세로와 가로 확인
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }

            if (board[row][i] == num) {
                return false;
            }
        }

        //작은 사각형 확인
        int rowIdx = 3 * (row / 3);
        int colIdx = 3 * (col / 3);
        for (int i = rowIdx; i < rowIdx + 3; i++) {
            for (int j = colIdx; j < colIdx + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
