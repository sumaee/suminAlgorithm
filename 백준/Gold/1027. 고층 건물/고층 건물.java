import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] buildings = new int[n]; // 빌딩 높이 저장
        int[] count = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < n - 1; i++) {
            count[i]++;
            count[i + 1]++;

            double curr = buildings[i + 1] - buildings[i];
            for (int j = i + 2; j < n; j++) {
                double nxt = (double) (buildings[j] - buildings[i]) / (j - i);

                if (curr < nxt) {
                    curr = nxt;
                    count[i]++;
                    count[j]++;
                }
            }
        }

        int answer = 0;
        for (int cnt : count) {
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}