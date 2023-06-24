import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] prices = new int[n][3];
        int[][] sum = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                prices[i][j] = Integer.parseInt(st.nextToken());
            }

            if (i == n - 1) sum[i] = prices[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    sum[i][j] = Math.min(sum[i + 1][j + 1], sum[i + 1][j + 2]) + prices[i][j];
                } else if (j == 1) {
                    sum[i][j] = Math.min(sum[i + 1][j - 1], sum[i + 1][j + 1]) + prices[i][j];
                } else {
                    sum[i][j] = Math.min(sum[i + 1][j - 2], sum[i + 1][j - 1]) + prices[i][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, sum[0][i]);
        }

        System.out.println(answer);

    }
}
