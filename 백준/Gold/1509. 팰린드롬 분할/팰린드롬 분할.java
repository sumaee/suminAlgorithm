import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        boolean[][] check = new boolean[str.length + 1][str.length + 1];
        int[] dp = new int[str.length + 1];

        //펠린드롬 체크
        //길이가 i~j인 문자열의 팰린드롬 채크
        for (int i = 1; i <= str.length; i++) {
            for (int j = i; j <= str.length; j++) {
                boolean palin = true;

                int start = i - 1;
                int end = j - 1;
                while (start <= end) {
                    if (str[start++] != str[end--]) {
                        palin = false;
                        break;
                    }
                }

                //팰린드롬이라면
                if (palin) {
                    check[i][j] = true;
                }
            }
        }

        //팰린드롬 분할 개수 구하기
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= str.length; i++) {
            for (int j = 1; j <= i; j++) {
                if (check[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[str.length]);
    }
}
