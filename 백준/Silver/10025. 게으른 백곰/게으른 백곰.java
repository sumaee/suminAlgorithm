import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) * 2 + 1;

        int[] ice = new int[1000001];
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ice[x] = g;
            maxIdx = Math.max(maxIdx, x);
        }

        int iceCnt = 0;
        int answer = 0;

        for (int i = 0; i < 1000001; i++) {
            if (i - k >= 0) iceCnt -= ice[i - k];

            iceCnt += ice[i];
            answer = Math.max(answer, iceCnt);
        }

        System.out.println(answer);
    }
}
