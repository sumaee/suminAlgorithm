import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        //10의 16승을 이진수로 나타내면 54자리가 나옴
        dp = new long[55];
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = 2 * dp[i - 1] + (1L << i);
        }

        //누적합의 결과 구하기
        System.out.println(sum(b) - sum(a - 1));

    }

    private static long sum(long num) {
        long cnt = num & 1;

        //해당 숫자보다 작은 2의 n을 만들 수 있는 n의 최솟값 구하기
        int size = (int) (Math.log(num) / Math.log(2));

        for (int i = size; i > 0; i--) {
            if ((num & (1L << i)) != 0L) {
                //num-(1L<<i) +1 : 지정된 1이 반복 사용될 개수
                cnt += dp[i - 1] + (num - (1L << i) + 1);
                //비트 이동
                num -= (1L << i);
            }
        }

        return cnt;
    }

}
