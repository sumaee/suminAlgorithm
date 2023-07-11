import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] stack; // 지능과 힘 스택을 저장할 배열
    static boolean[] visited;
    static Game[] mission;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        mission = new Game[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int str = Integer.parseInt(st.nextToken());
            int intel = Integer.parseInt(st.nextToken());
            int pnt = Integer.parseInt(st.nextToken());

            mission[i] = new Game(str, intel, pnt);
        }

        stack = new int[1001][1001];
        for (int i = 0; i < 1001; i++) {
            Arrays.fill(stack[i], -1);
        }
        visited = new boolean[n];
        System.out.println(dfs(1, 1));
    }

    private static int dfs(int str, int intel) {

        if (stack[str][intel] != -1) return stack[str][intel];
        //얻을 수있는 포인트
        int pnt = 0;

        stack[str][intel] = 0;
        //미션을 돌면서 수행할 수 있는 미션 저장
        List<Integer> nxt = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mission[i].str <= str || mission[i].intel <= intel) {
                //수행하지 않은 미션들 수행
                if (!visited[i]) {
                    visited[i] = true;
                    nxt.add(i);
                    pnt += mission[i].pnt;
                }
                stack[str][intel] += 1;
            }
        }

        //힘과 지능 스탯 포인트 분배한후 다음 으로 넘어가서 확인
        for (int i = 0; i <= pnt; i++) {
            int nxtStr = Math.min(1000, str + i);
            int nxtInt = Math.min(1000, intel + pnt - i);
            stack[str][intel] = Math.max(stack[str][intel], dfs(nxtStr, nxtInt));
        }

        //미션 초기화
        for (Integer integer : nxt) {
            visited[integer] = false;
        }

        return stack[str][intel];
    }

    static class Game {
        int str, intel, pnt;

        public Game(int str, int intel, int pnt) {
            this.str = str;
            this.intel = intel;
            this.pnt = pnt;
        }
    }
}
