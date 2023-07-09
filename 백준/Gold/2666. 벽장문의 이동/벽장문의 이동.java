import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] closet;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int open1 = Integer.parseInt(st.nextToken());
        int open2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        closet = new int[m];
        for (int i = 0; i < m; i++) {
            closet[i] = Integer.parseInt(br.readLine());
        }
        ans = Integer.MAX_VALUE;
        dfs(open1, open2, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int open1, int open2, int cnt, int idx) {
        if (idx == m) {
            ans = Math.min(cnt, ans);
            return;
        }

        //더 가까운 벽장 찾기
        int closet1 = Math.abs(open1 - closet[idx]);
        int closet2 = Math.abs(open2 - closet[idx]);

        //closet1이 더 가깝다면 열려있는 벽장 변경
        dfs(closet[idx], open2, cnt + closet1, idx + 1);

        //closet2가 더 가깝다면 열려있는 벽장 변경, 이동 횟수는 열려있는 것과의 거리만큼 이동시켜야함
        dfs(open1, closet[idx], cnt + closet2, idx + 1);
    }
}
