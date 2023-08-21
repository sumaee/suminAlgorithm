import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        int[] candy = new int[n + 1];
        int[] person = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            person[i] = 1;
        }

        //찬구관계 union
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //친구 관계끼리 묶였을 때 한번에 뺏기는 사탕 개수 & 연결된 친구 수 구하기
        for (int i = 1; i <= n; i++) {
            if (parents[i] != i) {
                int parent = find(i);
                candy[parent] += candy[i];
                person[parent] += person[i];
            }
        }

        long[] dp = new long[k];
        for (int i = 1; i <= n; i++) {
            if (parents[i] != i) continue;
            for (int j = k - 1; j >= person[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - person[i]] + candy[i]);
            }
        }

        System.out.println(dp[k - 1]);
    }

    private static void union(int a, int b) {
        parents[find(b)] = find(a);
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
