import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] infos = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            infos[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        for (int i = 0; i < n - m + 1; i++) {
            long sum = 0;
            for (int j = i; j < i + m; j++) {
                sum += infos[j];
            }

            answer = Math.max(answer, sum);

        }
        System.out.println(answer);
    }
}
