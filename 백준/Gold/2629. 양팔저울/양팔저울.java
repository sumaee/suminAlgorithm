import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[31][15001];
        dfs(0, 0);

        int testCase = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < testCase; i++) {
            int tc = Integer.parseInt(st.nextToken());
            if (tc > 15000) sb.append("N").append(" ");
            else if (dp[n][tc] == 1) sb.append("Y").append(" ");
            else sb.append("N").append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int idx, int w) {
        if (dp[idx][w] == 1) return;

        dp[idx][w] = 1;
        if (idx == n) return;

        //추 하나 그대로 했을 때
        dfs(idx + 1, w);
        //현재 추에 다음거 추가
        dfs(idx + 1, w + arr[idx + 1]);
        //반대쪽 편에 추 추가했을 때
        dfs(idx + 1, Math.abs(w - arr[idx + 1]));
    }
}
