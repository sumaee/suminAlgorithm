import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] visited, cycle;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n + 1];
            visited = new boolean[n + 1];
            cycle = new boolean[n + 1];
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (cycle[i]) continue;
                dfs(i);
            }
            sb.append(n - cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int now) {
        if (cycle[now]) return; // 사이클에 포함된 아이이므로 패스
        //방문을 했던 사람이라면 이미 사이클 구성원이므로 사이클 표시
        if (visited[now]) {
            cycle[now] = true;
            cnt++;
        }

        visited[now] = true;
        dfs(arr[now]);

        //여기까지 왔다는것은 돌고 돌아 여기까지 왔다는 것이므로 확인 체크 해주기
        cycle[now] = true;
        visited[now] = false;
    }
}
