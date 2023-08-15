import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /* 신발끈 공식 참고*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] x = new long[n + 1];
        long[] y = new long[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        x[n] = x[0];
        y[n] = y[0];

        long sumA = 0, sumB = 0;
        for (int i = 0; i < n; i++) {
            sumA += x[i] * y[i + 1];
            sumB += y[i] * x[i + 1];
        }

        String answer = String.format("%.1f", Math.abs(sumA - sumB) / 2.0);
        System.out.println(answer);
    }
}
