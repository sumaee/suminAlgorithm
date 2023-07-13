import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Road> roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][m + 1];
        roads = new HashSet<>();

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row1 = Integer.parseInt(st.nextToken());
            int col1 = Integer.parseInt(st.nextToken());
            int row2 = Integer.parseInt(st.nextToken());
            int col2 = Integer.parseInt(st.nextToken());

            if (row1 < row2 || col1 < col2) {
                roads.add(new Road(row1, col1, row2, col2));
            } else {
                roads.add(new Road(row2, col2, row1, col1));
            }
        }

        //만약 row가 0일때 공사지점이 있다면 그 이후로는 못가므로 그 지점까지만 1 채우고 그만
        for (int i = 1; i <= m; i++) {
            if (roads.contains(new Road(0, i - 1, 0, i))) break;
            dp[0][i] = 1;
        }

        //만약 col이 0일때 공사지점이 있다면 그 이후로는 못가므로 그 지점까지만 1 채우고 그만
        for (int i = 1; i <= n; i++) {
            if (roads.contains(new Road(i - 1, 0, i, 0))) break;
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //위에서 아래로 내려오는 경우
                if (!roads.contains(new Road(i - 1, j, i, j))) {
                    dp[i][j] += dp[i - 1][j];
                }

                //왼쪽에서 오른쪽으로 가는 경우
                if (!roads.contains(new Road(i, j - 1, i, j))) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        System.out.println(dp[n][m]);
    }

    static class Road {
        int startR, startC, endR, endC;

        public Road(int startR, int startC, int endR, int endC) {
            this.startR = startR;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Road road = (Road) o;
            return startR == road.startR && startC == road.startC && endR == road.endR && endC == road.endC;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startR, startC, endR, endC);
        }
    }
}
