import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] cnt = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt[i], 5001);
            cnt[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            cnt[a][b] = 1;
            cnt[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    cnt[start][end] = Math.min(cnt[start][end], cnt[start][k] + cnt[k][end]);
                }
            }
        }

        int count = 5001;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                sum += cnt[i][j];
            }

            if (sum < count) {
                count = sum;
                answer = i;
            }
        }

        System.out.println(answer + 1);
    }
}
