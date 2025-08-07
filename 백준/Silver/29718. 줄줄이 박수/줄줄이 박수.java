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

        int[][] infos = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                infos[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[] sum = new int[m + 1];
        for (int i = 0; i < m; i++) {
            sum[i + 1] += sum[i];
            for (int j = 0; j < n; j++) {
                sum[i + 1] += infos[j][i];
            }
        }

        int answer = 0;
        int a = Integer.parseInt(br.readLine());
        for (int i = 0; i <= m - a; i++) {
            answer = Math.max(answer, sum[i + a] - sum[i]);
        }

        System.out.println(answer);
    }
}
