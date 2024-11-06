import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] money = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[m + 1];
        Arrays.fill(dp, new BigInteger("-1"));

        //현재 가진 돈
        for (int i = 0; i < dp.length; i++) {
            //기준 돈
            for (int j = 0; j <= i; j++) {
                dp[i] = dp[i].max(dp[j]);
                //추가할 돈
                for (int k = 0; k < n; k++) {
                    if (money[k] + j <= i) {
                        String str = (dp[j].equals(new BigInteger("-1")) ? "" : dp[j].toString()) + k;
                        dp[i] = dp[i].max(new BigInteger(str));
                    }
                }
            }
        }

        System.out.println(dp[m]);
    }


}