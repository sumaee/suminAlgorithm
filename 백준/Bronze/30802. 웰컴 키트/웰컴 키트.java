import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] tSize = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            tSize[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for (int size : tSize) {
            if (size == 0) {
                continue;
            }
            if (size / t == 0) {
                cnt++;
            } else if (size % t == 0) {
                cnt += size / t;
            } else {
                cnt += (size / t + 1);
            }
        }

        System.out.println(cnt);
        System.out.println((n / p) + " " + (n % p));
    }
}
