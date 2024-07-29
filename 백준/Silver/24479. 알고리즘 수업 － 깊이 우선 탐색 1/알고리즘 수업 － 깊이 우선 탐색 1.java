import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r, idx;
    static List<Integer>[] node;
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        node = new List[n + 1];
        order = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            node[u].add(v);
            node[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(node[i]);
        }

        idx = 1;
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(order[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int start) {
        order[start] = idx;

        for (int nxt : node[start]) {
            if (order[nxt] == 0) {
                idx++;
                dfs(nxt);
            }
        }
    }

}