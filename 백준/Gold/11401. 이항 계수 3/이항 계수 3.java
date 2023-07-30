import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //이항계수 식의 n!
        long nFac = factorial(n);

        //이항계수 식의 r!(n-r)!mod (mod)
        long fac = factorial(k) * factorial(n - k) % MOD;

        long answer = nFac * find(fac, MOD - 2) % MOD;
        System.out.println(answer);
    }

    private static long find(long num, long idx) {
        //지수가 1이면 나머지 리턴
        if (idx == 1) {
            return num % MOD;
        }

        //지수를 절반 나눠서 구하기
        long halfNum = find(num, idx / 2);

        //지수가 홀수라면 한번 더 곱해줘야함
        if (idx % 2 == 1) {
            return halfNum * halfNum % MOD * num % MOD;
        }
        return halfNum * halfNum % MOD;
    }

    private static long factorial(int n) {
        long result = 1;

        while (n > 1) {
            result = (result * n) % MOD;
            n--;
        }

        return result;
    }
}
