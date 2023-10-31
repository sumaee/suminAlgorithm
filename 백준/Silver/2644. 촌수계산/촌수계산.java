import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, answer;
    static boolean flag;

    static List<Integer>[] relations;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int last = Integer.parseInt(st.nextToken());

        relations = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            relations[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            relations[parent].add(child);
            relations[child].add(parent);
        }

        //깊이 우선 탐색
        visited = new boolean[n + 1];
        dfs(first, last, 0);

        //dfs 에서 못끝내고 온것 못찾았다는 뜻
        System.out.println(-1);
    }

    private static void dfs(int curr, int last, int cnt) {
        //사람을 찾았다면
        if (curr == last) {
            answer = cnt;
            System.out.println(answer);
            System.exit(0);
        }

        for (int relation : relations[curr]) {
            if (!visited[relation]) {
                visited[relation] = true;
                dfs(relation, last, cnt + 1);
            }
        }
    }
}