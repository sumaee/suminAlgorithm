import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] notes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        notes = new int[n];
        for (int i = 0; i < n; i++) {
            notes[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n][m + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int answer = dfs(1, notes[0] + 1);
        System.out.println(answer);
    }

    private static int dfs(int idx, int len) {
        //한줄씩 다 썻다면 마지막 줄이므로 0을 리턴
        if (idx == n) {
            return 0;
        }

        //현재가 갱신이 안된 곳이라면 버리기
        if (dp[idx][len] != Integer.MAX_VALUE) return dp[idx][len];

        //다음 줄에 쓰는 경우 현재 남은 빈칸을 더해주기
        int result = dfs(idx + 1, notes[idx] + 1) + ((int) Math.pow(m - len + 1, 2));

        //지금 줄에 이어 쓰는 경우 지금 이름을 적었을 때 넘어가지 않는지 확인후 갱신
        if (len + notes[idx] <= m) {
            result = Math.min(dfs(idx + 1, len + notes[idx] + 1), result);
        }

        return dp[idx][len] = result;
    }
}
