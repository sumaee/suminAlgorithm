import java.lang.*;
import java.util.*;

class Solution {
    static boolean[][] board;
    static int answer;
    public int solution(int n) {
         board = new boolean[n][n];
        answer = 0;

        chess(0, n);
        return answer;

    }

    private static void chess(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            //퀸을 놓을 수 있는 자리인지 아닌지 확인 true: 놓을수 없음/ false: 놓을수 있음
            if (isPossible(row, i, n)) {
                continue;
            }

            board[row][i] = true;
            chess(row + 1, n);
            board[row][i] = false;
        }
    }

    private static boolean isPossible(int row, int col, int n) {
        //해당 위치에서 위의 방향에 퀸이 존재하는지 확인
        for (int i = row; i >= 0; i--) {
            //존재한다면 true반환
            if (board[i][col]) {
                return true;
            }
        }

        //오른쪽 대각선 위로 확인
        int dc = col + 1;
        int dr = row - 1;
        while (dr >= 0 && dc < n) {
            if (board[dr--][dc++]) return true;
        }

        //왼쪽 대각선 위로 확인
        dc = col - 1;
        dr = row - 1;
        while (dr >= 0 && dc >= 0) {
            if (board[dr--][dc--]) return true;
        }

        return false;
    }
}
