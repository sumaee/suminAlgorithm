import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        boolean[] nums = new boolean[(int) (max - min + 1)];
        long lastIdx = (long) Math.sqrt(max);

        for (long i = 2; i <= lastIdx; i++) {
            long idx = i * i;
            long start = ((min - 1) / idx + 1) * idx;

            for (long j = start; j <= max; j += idx) {
                nums[(int) (j - min)] = true;
            }
        }

        int answer = 0;
        for (long i = 0; i < max - min + 1; i++) {
            if (!nums[(int) i]) {
                answer++;
            }

        }

        System.out.println(answer);
    }
}
