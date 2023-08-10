import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean isWinO, isWinX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String game = br.readLine();

            //end면 끝
            if (game.equals("end")) break;

            //게임 상황 받으면서 O의 개수와 X의 개수 먼저 세기
            int oCnt = 0;
            int xCnt = 0;
            String[] board = new String[3];
            int idx = 0;
            for (int i = 0; i < 3 * 3; i++) {
                if (i < 3) {
                    board[i] = game.substring(idx, idx + 3);
                    idx += 3;
                }
                if (game.charAt(i) == 'O') oCnt++;
                else if (game.charAt(i) == 'X') xCnt++;
            }

            //O가 이겼는지 X가 이겼는지 판단먼저 하기
            isWinO = false;
            isWinX = false;
            whoWin(board);

            //O의 개수와 X의 개수가 같다면
            if (oCnt == xCnt) {
                //둘다 공격 성공을 못했고 O의 개수와 X의 개수가 9개로 게임판이 꽉찼으면 invalid
                if ((!isWinX && !isWinO) && oCnt + xCnt < 9) sb.append("invalid");
                    // 둘다 공격 성공을 못했거나 O만 공격이 성공했을 경우는 valid
                else if ((!isWinX && !isWinO) || (isWinO && !isWinX)) sb.append("valid");
                else sb.append("invalid");
            }

            //X의 개수가 O보다 한개 더 많다면
            else if (xCnt - 1 == oCnt) {
                //둘다 공격 성공을 못했고 O의 개수와 X의 개수가 9개로 게임판이 꽉찼으면 invalid
                if ((!isWinX && !isWinO) && oCnt + xCnt < 9) sb.append("invalid");
                    //X만 공격에 성공을 하거나 둘다 실패했을 경우 valie
                else if ((isWinX && !isWinO) || (!isWinX && !isWinO)) sb.append("valid");
                else sb.append("invalid");
            }

            //위의 경우 말고 다른 경우는 다 불가능
            else {
                sb.append("invalid");
            }
            sb.append("\n");

        }

        System.out.println(sb);

    }

    private static void whoWin(String[] board) {
        //가로 세로 한줄씩 확인
        for (int i = 0; i < board.length; i++) {
            String line = board[i];

            //만약 가로 한줄 전체가 OOO 거나 XXX 일 때 판단
            if (line.equals("OOO")) isWinO = true;
            else if (line.equals("XXX")) isWinX = true;

            //세로로 한줄 전체가 판단
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                //OOO라면
                if (board[0].charAt(i) == 'O') isWinO = true;
                    //XXX라면
                else if (board[0].charAt(i) == 'X') isWinX = true;
            }
        }

        //대각선 확인
        if ((board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2))
                || (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0))) {
            //OOO라면
            if (board[1].charAt(1) == 'O') isWinO = true;
                //XXX라면
            else if (board[1].charAt(1) == 'X') isWinX = true;
        }
    }
}
