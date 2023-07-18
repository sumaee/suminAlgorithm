import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][n + 1];
        //한자리 수는 모두 팰린드롬
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        //2자리 수는 2자리가 모두 같아야 팰린드롬
        for (int i = 1; i <= n - 1; i++) {
            if (arr[i] == arr[i + 1]) dp[i][i + 1] = 1;
        }

        //3자리 이상은 시작점과 끝점이 같아야하고 그 사이의 인덱스 값이 펠린드롬이어야 팰린드롬
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1] == 1) dp[j][j + i] = 1;
            }
        }
        
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(dp[start][end]).append("\n");
        }
        System.out.println(sb);
    }
}
