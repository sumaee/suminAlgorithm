import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static long[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        line = new long[k];
        long max = 0;

        for (int i = 0; i < k; i++) {
            line[i] = Long.parseLong(br.readLine());
            max = Math.max(max, line[i]);
        }
        long min = 1;
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;

            long sum = cutLine(mid);
            if (sum < n) {
                max = mid - 1;
            } else {
                min = mid + 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }

    private static long cutLine(long mid) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += line[i] / mid;
        }
        return sum;
    }
}
