class Solution {
    static boolean isWinO, isWinX;
    public int solution(String[] board) {
        int oCnt = 0;
        int xCnt = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    oCnt++;
                } else if (board[i].charAt(j) == 'X') {
                    xCnt++;
                }
            }
        }

        isWinO = false;
        isWinX = false;
        isWin(board);

        //만약 o의 개수 = x의 개수라면
        if (oCnt == xCnt) {
            //둘다 성공을 못했을 경우거나 x만 성공을 했을 경우에는 게임이 이뤄질수 있으므로 1 반환
            if ((!isWinX && !isWinO) || (isWinX && !isWinO)) return 1;
        }
        //만약 O의 개수 -1 =X의 개수라면
        else if (oCnt - 1 == xCnt) {
            //둘다 실패거나 o만 성공을 했을 경우에만 게임이 이뤄질 수 있음
            if ((!isWinX && !isWinO) || (!isWinX && isWinO)) return 1;
        }

        //두개의 차이가 1개 이상으로 난다면 이뤄질 수 없는 게임임
        return 0;
    }

    private static void isWin(String[] board) {
        for (int i = 0; i < board.length; i++) {
            String str = board[i];

            //가로로 한줄이 공격을 성공했다면
            if (str.equals("OOO")) isWinO = true;
            else if (str.equals("XXX")) isWinX = true;

            //세로로 한줄 공격에 성공을 했다면
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') isWinO = true;
                else if (board[0].charAt(i) == 'X') isWinX = true;
            }
        }

        //대각선 확인
        if ((board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2))
                || (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0))) {
            if (board[1].charAt(1) == 'O') isWinO = true;
            else if (board[1].charAt(1) == 'X') isWinX = true;
        }

    }
}