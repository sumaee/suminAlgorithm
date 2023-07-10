import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] dp;
    static int[] peopleNum;
    static List<Integer>[] town;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        peopleNum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            peopleNum[i] = Integer.parseInt(st.nextToken());
        }

        town = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            town[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int town1 = Integer.parseInt(st.nextToken());
            int town2 = Integer.parseInt(st.nextToken());

            town[town1].add(town2);
            town[town2].add(town1);
        }

        dp = new int[n + 1][2];
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int townNum, int parent) {
        for (int nxtNum : town[townNum]) {
            if (nxtNum == parent) continue;
            //자식 노드 먼저 탐색
            dfs(nxtNum, townNum);
            //탐색하고 왔을 때 만약 현재 노드가 우수 마을이 아닐때 최댓값 넣기
            //현재 마을이 우수 마을이 아니라면 자식 노드가 우수마을일 수도 아닐 수도 있음
            //3번의 조건을 만족 시키지 못한다고 생각했으나, 주민의 숫자가 최대가 되게 하는 한, 우수마을이 아닌 마을은 최소 하나 이상의 우수 마을과 인접하게 됨
            dp[townNum][0] += Math.max(dp[nxtNum][0], dp[nxtNum][1]);

            //현재 마을이 우수마을이라면 자식 노드는 우수마을이 아니여야함
            dp[townNum][1] += dp[nxtNum][0];
        }

        dp[townNum][1] += peopleNum[townNum];
    }
}
