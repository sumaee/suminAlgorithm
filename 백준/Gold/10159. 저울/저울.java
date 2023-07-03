import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] weight = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken()) - 1;
            int light = Integer.parseInt(st.nextToken()) - 1;

            //무거운 것은 1, 가벼운 것은 -1
            weight[heavy][light] = 1;
            weight[light][heavy] = -1;
        }

        //무게를 비교할 수 있는 것들은 하기
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (k == i) continue;
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;

                    if (weight[i][k] == 1 && weight[k][j] == 1) {
                        weight[i][j] = 1;
                        weight[j][i] = -1;
                    }

                    if (weight[i][k] == -1 && weight[k][j] == -1) {
                        weight[i][j] = -1;
                        weight[j][i] = 1;
                    }
                }
            }
        }
       
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (weight[i][j] == 0) cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
