import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, root, answer;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        parent = new int[n];

        //부모 기록
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(st.nextToken());
            parent[i] = idx;
            if (idx == -1) {
                root = i;
            }
        }

        //지우려는 노드 자식들 전부 삭제
        int delete = Integer.parseInt(br.readLine());
        removeTree(delete);

        //지운후 리프 노드 개수 확인
        answer = 0;
        visited = new boolean[n];
        dfs(root);
        System.out.println(answer);
    }

    private static void dfs(int start) {
        //해당 노드가 리프노드인지 확인할 boolean
        boolean isLeaf = true;
        visited[start] = true;

        //연결된 노드라면
        if (parent[start] != -2) {
            //parent를 돌면서 start를 부모로 가진 애들을 또 타고 들어가탐색
            for (int i = 0; i < n; i++) {
                if (parent[i] == start && !visited[i]) {
                    dfs(i);
                    isLeaf = false; // 들어갔다 나왔으므로 리프노드가 아님
                }
            }

            //리프 노드라면 정답 추가
            if (isLeaf) {
                answer++;
            }
        }
    }

    private static void removeTree(int start) {
        parent[start] = -2;

        for (int i = 0; i < n; i++) {
            if (parent[i] == start) {
                removeTree(i);
            }
        }
    }

}
