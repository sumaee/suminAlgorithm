import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
        } else {
            st = new StringTokenizer(br.readLine());
            int[] scores = new int[p];
            Arrays.fill(scores, -1);

            for (int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            int rank = 1;
            int cnt = 1;
            for (int i = 0; i < p; i++) {
                if (scores[i] > score) {
                    rank++;
                    cnt++;
                } else if (scores[i] == score) {
                    cnt++;
                }
            }

            System.out.println(cnt <= p ? rank : -1);
        }
    }
}