import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] blinker = new int[n + 1];

        Set<Integer> breakDown = new HashSet<>();
        for (int i = 0; i < b; i++) {
            breakDown.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 1; i <= n; i++) {
            if (breakDown.contains(i)) {
                blinker[i] = blinker[i - 1] + 1;
            } else {
                blinker[i] = blinker[i - 1];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            answer = Math.min(answer, blinker[i + k] - blinker[i]);
        }

        System.out.println(answer);

    }
}