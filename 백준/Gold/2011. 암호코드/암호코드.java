import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        //1행은 한자리로 나올 수 있는 경우의수, 2행은 2자리로 묶었을 때 나올 수 있는 경우의수
        int[][] dp = new int[2][str.length() + 1];

        dp[0][0] = 1;
        dp[0][1] = 1;

        int mod = 1000000;

        for (int i = 2; i <= str.length(); i++) {
            char pre = str.charAt(i - 2);
            char now = str.charAt(i - 1);

            //만약 지금 보는 숫자가 0이라면
            if (now == '0') {
                //이전 것도 0이었을 때 00이 말이 안되므로 0 출력
                if (pre == '0') {
                    System.out.println(0);
                    return;
                }
                //0외의 다른 숫자들이라면
                else {
                    //현재 숫자 하나가지고는 만들 수 있는 문자가 없음
                    dp[0][i] = 0;
                    //두자리로 판단했을 경우 이전 숫자가 1이거나 2일 때만 가능함
                    if (pre == '1' || pre == '2') {
                        dp[1][i] = (dp[0][i - 2] + dp[1][i - 2]) % mod;
                    }
                }
            }
            //0 외의 숫자가 들어왔다면
            else {
                //현재 숫자가 한자리로 이루어졌을 때 나올 수있는 경우는 이전 경우의 수들을 더해준 것임
                dp[0][i] = (dp[0][i - 1] + dp[1][i - 1]) % mod;

                //두자리 판단하려할 때 이전 숫자가 1이라면
                if (pre == '1') {
                    //현재 숫자가 0~9까지 모두 가능하므로 두칸 전의 경우의 수를 더하기
                    dp[1][i] = (dp[0][i - 2] + dp[1][i - 2]) % mod;
                }
                //이전 숫자가 2라면 현재 숫자가 0~6이어야지만 가능함
                else if (pre == '2' && now <= '6') {
                    dp[1][i] = (dp[0][i - 2] + dp[1][i - 2]) % mod;
                }
                //이외의 숫자들이라면 두자리는 불가능 하므로
                else {
                    dp[1][i] = 0;
                }
            }
        }

        System.out.println((dp[1][str.length()] + dp[0][str.length()]) % mod);
    }
}
