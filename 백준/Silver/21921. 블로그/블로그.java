import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] visitedCnt = new int[n];
        int xSum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            visitedCnt[i] = Integer.parseInt(st.nextToken());
            if (i < x) {
                xSum += visitedCnt[i];
            }
        }

        int max = xSum;
        int cnt = 1;
        for (int i = 0; i < n - x; i++) {
            xSum -= visitedCnt[i];
            xSum += visitedCnt[i + x];

            if (xSum == max) {
                cnt++;
            } else if (xSum > max) {
                cnt = 1;
                max = xSum;
            }
        }

        System.out.println(max == 0 ? "SAD" : max + "\n" + cnt);
    }
}