import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] history = new int[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;

            history[first][second] = -1;
            history[second][first] = 1;
        }

        //유추할 수 있는 모든 사건 유추
        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n; i++) {
                if (i == l) continue;
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;

                    if (history[i][l] == 1 && history[l][j] == 1) {
                        history[i][j] = 1;
                        history[j][i] = -1;
                    }

                    if (history[i][l] == -1 && history[l][j] == -1) {
                        history[i][j] = -1;
                        history[j][i] = 1;
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int event1 = Integer.parseInt(st.nextToken()) - 1;
            int event2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(history[event1][event2]).append("\n");
        }

        System.out.println(sb);
    }
}
