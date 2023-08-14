import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());

        long N = 1;
        long S = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            long n = Integer.parseInt(st.nextToken());
            long s = Integer.parseInt(st.nextToken());

            //통분
            S = s * N + n * S;
            N *= n;

            S %= MOD;
            N %= MOD;
        }

        //만약 기약분수라면
        if (S % N != 0) {
            System.out.println(cal(N, MOD - 2) * S % MOD);
        } else {
            System.out.println(S / N);
        }
    }

    private static long cal(long N, int cnt) {
        if (cnt == 1) {
            return N;
        }

        long temp = cal(N, cnt / 2);
        if (cnt % 2 == 1) {
            return temp * temp % MOD * N % MOD;
        } else {
            return temp * temp % MOD;
        }
    }
}
