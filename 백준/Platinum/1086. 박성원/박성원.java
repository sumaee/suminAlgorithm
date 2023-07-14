import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, k;
    static String[] nums;
    static int totalBit;
    static long[][] dp;
    static int[][] mods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = new String[n];
        for (int i = 0; i < n; i++) {
            nums[i] = br.readLine();
        }

        k = Integer.parseInt(br.readLine());

        //방문해야하는 지점들
        totalBit = (1 << n) - 1;
        dp = new long[1 << n][k];
        mods = new int[n][k];

        for (int i = 0; i < n; i++) {
            Arrays.fill(mods[i], -1);
        }
        for (int i = 0; i <= totalBit; i++) {
            Arrays.fill(dp[i], -1);
        }

        long answer = dfs(0, 0);

        if (answer == 0) {
            System.out.println("0/1");
        } else {
            long total = 1;
            for (int i = 2; i <= n; i++) {
                total *= i;
            }
            long gcd = getGcd(total, answer);

            System.out.println(answer / gcd + "/" + total / gcd);
        }
    }

    private static long getGcd(long a, long b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }

    private static long dfs(int bit, int mod) {
        //이미 방문을 했다면
        if (dp[bit][mod] != -1) return dp[bit][mod];

        //모든 점을 방문하고 마지막이라면 나머지가 없다면 개수 증가해야하므로 1 반환
        if (bit == totalBit) return mod == 0 ? 1 : 0;

        long cnt = 0;
        for (int i = 0; i < n; i++) {
            int checkbit = 1 << i;
            //아직 방문을 안했다면
            if ((bit & checkbit) == 0) {
                //재귀값을 추가
                cnt += dfs(bit | checkbit, calMod(i, mod));
            }
        }

        return dp[bit][mod] = cnt;
    }

    private static int calMod(int idx, int mod) {
        //-1이 아니란 것은 이미 계산이 된것임
        if (mods[idx][mod] != -1) return mods[idx][mod];

        //현재 나머지
        int curr = mod;
        for (int i = 0; i < nums[idx].length(); i++) {
            curr *= 10;
            curr += (nums[idx].charAt(i) - '0');
            curr %= k;
        }

        return mods[idx][mod] = curr;
    }
}
