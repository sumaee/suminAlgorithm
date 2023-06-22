import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //스도쿠 채우기
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findAnswer(0, 0);
    }

    private static void findAnswer(int row, int col) {
        //한줄을 다 채웠으면 다음
        if (col == 9) {
            findAnswer(row + 1, 0);
            return;
        }

        //스도쿠가 다 채워지면 출력하고 끝내버리기
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int[] r : board) {
                for (int c : r) {
                    sb.append(c).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        //빈칸이라면 확인
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isPossible(row, col, i)) {
                    board[row][col] = i;
                    findAnswer(row, col + 1);
                }
            }

            //포문에 안걸리고 여기 왔다는 것은 되는 숫자가 없다는 것이므로 0으로 바꾸고 끝내기
            board[row][col] = 0;
            return;
        }
        findAnswer(row, col + 1);
    }

    private static boolean isPossible(int row, int col, int num) {
        //작은 사각형 확인
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        //가로세로줄 확인
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        return true;
    }
}
