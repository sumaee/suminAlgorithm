import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Num[] dp = new Num[41];
        dp[0] = new Num(1, 0);
        dp[1] = new Num(0, 1);
        //dp 채우기
        for (int i = 2; i < 41; i++) {
            dp[i] = new Num(dp[i - 1].zero + dp[i - 2].zero, dp[i - 1].one + dp[i - 2].one);
        }

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int num = Integer.parseInt(br.readLine());

            sb.append(dp[num].zero).append(" ").append(dp[num].one).append("\n");
        }
        System.out.println(sb);
    }
}

class Num {
    int zero, one;

    public Num(int zero, int one) {
        this.zero = zero;
        this.one = one;
    }
}
