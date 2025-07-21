import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (k > n / 2) {
            k = n - k;
        }

        int up = 1;
        int down = 1;
        for (int i = 1; i <= k; i++) {
            up *= n;
            n--;

            down *= i;
        }

        System.out.println(up / down);
    }
}
