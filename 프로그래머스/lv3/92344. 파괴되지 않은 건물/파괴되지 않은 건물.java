class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for (int[] info : skill) {
            int r1 = info[1];
            int c1 = info[2];
            int r2 = info[3];
            int c2 = info[4];
            int attackOrReco = info[5] * (info[0] == 1 ? -1 : 1);

            sum[r1][c1] += attackOrReco;
            sum[r2 + 1][c1] += attackOrReco * (-1);
            sum[r1][c2 + 1] += attackOrReco * (-1);
            sum[r2 + 1][c2 + 1] += attackOrReco;
        }


        //각 행에 대해 가로로 누적합
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        //각 열에 대해 세로로 누적합
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 1; j < board.length; j++) {
                sum[j][i] += sum[j - 1][i];
            }
        }

        //파괴되지 않은 건물 체크
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += sum[i][j];

                if (board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}