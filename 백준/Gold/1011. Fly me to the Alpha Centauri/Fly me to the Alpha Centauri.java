import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            long len = y - x;
            long sqrt = (long) Math.sqrt(len);

            if (sqrt * sqrt == len) {
                sb.append(2 * sqrt - 1);
            } else if (sqrt * sqrt < len && sqrt * (sqrt + 1) >= len) {
                sb.append(2 * sqrt);
            } else if (sqrt * (sqrt + 1) < len && (sqrt + 1) * (sqrt + 1) > len) {
                sb.append(2 * sqrt + 1);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
