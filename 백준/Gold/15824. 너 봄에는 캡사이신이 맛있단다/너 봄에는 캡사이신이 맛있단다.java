import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += arr[i] * pow(i);
            answer -= arr[i] * pow(n - i - 1);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static long pow(int num) {
        if (num == 0) return 1;

        long half = pow(num / 2);
        if (num % 2 == 1) {
            return half * half * 2 % MOD;
        }
        return half * half % MOD;
    }
}
