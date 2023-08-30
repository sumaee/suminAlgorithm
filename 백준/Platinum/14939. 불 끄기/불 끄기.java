import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] rooms = new int[10][10];
    static int[][] temp = new int[10][10];
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            String line = br.readLine();
            for (int j = 0; j < 10; j++) {
                char light = line.charAt(j);

                if (light == '#') rooms[i][j] = 0;
                else if (light == 'O') rooms[i][j] = 1;
            }
        }

        //0000000000 ~ 1111111111 순회
        int answer = Integer.MAX_VALUE;
        int result;
        for (int i = 0; i < 1024; i++) {
            //방 복사
            result = 0;
            for (int row = 0; row < 10; row++) {
                temp[row] = rooms[row].clone();
            }

            //첫번째 줄 불 켜기
            for (int j = 1; j <= 10; j++) {
                int num = 1024;
                num = num >> j;
                if ((i & num) > 0) {
                    result++;
                    turn(0, j - 1);
                }
            }

            //바로 윗행의 불이 켜져있다면 불 끄기
            for (int j = 1; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (temp[j - 1][k] == 1) {
                        result++;
                        turn(j, k);
                    }
                }
            }

            //마지막 행 불켜진지 확인
            boolean check = true;
            for (int col = 0; col < 10; col++) {
                if (temp[9][col] == 1) {
                    check = false;
                    break;
                }
            }

            if (check) {
                answer = Math.min(answer, result);
            }
        }

        System.out.println(answer);
    }

    private static void turn(int row, int col) {
        for (int i = 0; i < 5; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위를 벗어나면 패스
            if (drow < 0 || dcol < 0 || drow >= 10 || dcol >= 10) continue;

            //불바꾸기
            temp[drow][dcol] ^= 1;

        }
    }
}
