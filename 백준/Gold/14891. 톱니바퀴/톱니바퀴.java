import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] gears;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gears = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                gears[i][j] = str.charAt(j) - '0';
            }
        }

        int rotationCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotationCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            //가운데 마주보는 톱니가 같은 극인지확인
            boolean[] isSame = new boolean[3];
            for (int j = 0; j < 3; j++) {
                if (gears[j][2] == gears[j + 1][6]) {
                    isSame[j] = true;
                }
            }

            //일단 현재 돌려야하는 톱니 돌리기
            rotation(gearNum, dir);

            //다른 극인 경우 회전
            //현재 기어의 왼쪽 확인
            for (int j = gearNum - 1; j >= 0; j--) {
                //같은 극이라면 볼필요가 없음
                if (!isSame[j]) {
                    if ((gearNum - j) % 2 == 1) {
                        rotation(j, dir * -1);
                    } else {
                        rotation(j, dir);
                    }
                } else {
                    break;
                }
            }

            //현재 기어의 오른쪽 확인
            for (int j = gearNum; j < 3; j++) {
                //같은 극이라면 볼필요가 없음
                if (!isSame[j]) {
                    if (((j + 1) - gearNum) % 2 == 1) {
                        rotation(j + 1, dir * -1);
                    } else {
                        rotation(j + 1, dir);
                    }
                } else {
                    break;
                }
            }
        }


        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += gears[i][0] * Math.pow(2, i);
        }
        System.out.println(answer);
    }

    private static void rotation(int gearNum, int dir) {
        //반시계방향이라면
        if (dir == -1) {
            int tmp = gears[gearNum][0];
            for (int i = 0; i < 7; i++) {
                gears[gearNum][i] = gears[gearNum][i + 1];
            }
            gears[gearNum][7] = tmp;
        }
        //시계방향이라면
        else {
            int tmp = gears[gearNum][7];
            for (int i = 7; i >= 1; i--) {
                gears[gearNum][i] = gears[gearNum][i - 1];
            }
            gears[gearNum][0] = tmp;
        }
    }
}
