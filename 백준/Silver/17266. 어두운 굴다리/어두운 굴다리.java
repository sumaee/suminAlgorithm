import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] lights = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            lights[i] = Integer.parseInt(st.nextToken());
        }

        int low = 1;
        int high = n;
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            int before = 0;
            boolean flag = true;
            for (int light : lights) {
                if (light - mid <= before) {
                    before = light + mid;
                } else {
                    flag = false;
                }
            }

            if (n - before > 0) {
                flag = false;
            }

            if (flag) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }
}