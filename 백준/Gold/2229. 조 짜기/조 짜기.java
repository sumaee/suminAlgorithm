import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] arr = new int[n + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int j = i; j >= 1; j--) {
                max = Math.max(arr[j], max);
                min = Math.min(arr[j], min);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

        System.out.println(dp[n]);
    }
}
