import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int[][] check = new int[m][n];
        char[][] boards = new char[m][n];

        //2차원 배열에 넣기
        for (int i = 0; i < m; i++) {
            String str = board[i];
            for (int j = 0; j < n; j++) {
                char ch = str.charAt(j);
                boards[i][j] = ch;
            }
        }

        int answer = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char character = boards[i][j];
                    //빈칸이면 다음 진행
                    if (character == '-') continue;
                    //오른쪽, 오른쪽 아래 대각선, 아래 값이 같으면 check 변경
                    if (boards[i][j + 1] == character && boards[i + 1][j + 1] == character && boards[i + 1][j] == character) {
                        check[i][j] = 1;
                        check[i][j + 1] = 1;
                        check[i + 1][j + 1] = 1;
                        check[i + 1][j] = 1;
                        flag = true;
                    }
                }
            }
            //블록 깨고 옮기기
            answer += block(check, boards, m, n);
            //방문 기록 초기화
            check = new int[m][n];
        }


        return answer;
    }

    private int block(int[][] check, char[][] boards, int m, int n) {
        int cnt = 0;
        //깨진 블록 없애기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] == 1) {
                    boards[i][j] = '*';
                }
            }
        }

        //블록 옮기기
        for (int j = 0; j < n; j++) {
            Queue<Character> que = new LinkedList<>();
            for (int i = m - 1; i >= 0; i--) {
                if (boards[i][j] != '*') {
                    que.offer(boards[i][j]);
                } else {
                    cnt++;
                }
            }

            int idx = m - 1;
            while (!que.isEmpty()) {
                boards[idx--][j] = que.poll();
            }

            //남은 곳 빈칸으로 채우기
            for (int i = idx; i >= 0; i--) {
                boards[i][j] = '-';
            }
        }
        return cnt;
    }
}