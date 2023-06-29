import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] connect, plan;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        connect = new int[n + 1];
        //초기 설정
        for (int i = 1; i <= n; i++) {
            connect[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int check = Integer.parseInt(st.nextToken());
                //연결 되어있으면 union
                if (check == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        plan = new int[m];
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        if (isPossible(plan[0])) {
            sb.append("YES");
        } else {
            sb.append("NO");
        }

        System.out.println(sb);


    }

    private static boolean isPossible(int start) {
        for (int i = 1; i < m; i++) {
            if (find(start) != find(plan[i])) {
                return false;
            }
        }
        return true;
    }

    private static void union(int x, int y) {
        connect[find(y)] = find(x);
    }

    private static int find(int x) {
        if (x == connect[x]) {
            return x;
        }

        return connect[x] = find(connect[x]);
    }


}