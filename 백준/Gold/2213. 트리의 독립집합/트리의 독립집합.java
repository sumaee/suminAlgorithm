import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Integer>[] set;
    static int[][] dp;
    static int[] weight;
    static PriorityQueue<Integer> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        set = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            set[i] = new ArrayList<>();
        }
        weight = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            set[a].add(b);
            set[b].add(a);
        }

        dp = new int[n + 1][2];
        dfs(1, 0);

        que = new PriorityQueue<>();
        if (dp[1][1] > dp[1][0]) {
            sb.append(dp[1][1]).append("\n");
            findGroup(1, 0, true);
        } else {
            sb.append(dp[1][0]).append("\n");
            findGroup(1, 0, false);
        }

        while (!que.isEmpty()) sb.append(que.poll()).append(" ");

        System.out.println(sb);

    }

    private static void findGroup(int idx, int parent, boolean flag) {
        //현재 노드가 독립 집합에 포함될 때
        if (flag) {
            que.offer(idx);

            for (int nxt : set[idx]) {
                //부모노드라면 패스해서 자식노드부터 확인
                if (nxt == parent) continue;
                //다음 노드는 포함이 되면 안됨
                findGroup(nxt, idx, false);
            }
        }
        //현재 노드가 독립집합이 아닐때
        else {
            for (int nxt : set[idx]) {
                //부모노드라면 패스해서 자식노드부터 확인
                if (nxt == parent) continue;
                //다음 노드가 독립집합에 포함된 값이 더 크다면 true, 아니라면 false로 보내기
                findGroup(nxt, idx, dp[nxt][1] > dp[nxt][0]);
            }
        }
    }


    private static void dfs(int idx, int parent) {

        for (int nxtIdx : set[idx]) {
            //부모노드라면 패스해서 자식노드부터 확인
            if (nxtIdx == parent) continue;

            dfs(nxtIdx, idx);

            //현재 노드(idx)가 독립집합에 포함된다면 자식노드는 포함되면 안됨
            dp[idx][1] += dp[nxtIdx][0];

            //현재 노드(idx)가 독립집합이 아니라면 자식노드는 독립집합일수도 아닐수도 있음
            dp[idx][0] += Math.max(dp[nxtIdx][0], dp[nxtIdx][1]);
        }

        dp[idx][1] += weight[idx];
    }
}
