import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 도시 개수
        int m = Integer.parseInt(br.readLine());// 버스개수

        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) costs[i][j] = 0;
                else costs[i][j] = MAX;
            }
        }


        //입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            costs[start][end] = Math.min(costs[start][end], cost);
        }

        //플로이드 와샬 알고리즘 시작
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        for (int[] cost : costs) {
            for (int result : cost) {
                sb.append(result == MAX ? 0 : result).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
