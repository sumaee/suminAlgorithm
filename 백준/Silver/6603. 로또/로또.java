import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] lotto;
    static boolean[] visited;
    static int k;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            //0이 처음으로 들어오면 끝
            if (k == 0) break;
            //로또 수 담기
            lotto = new int[k];
            for (int i = 0; i < k; i++) {
                lotto[i] = Integer.parseInt(st.nextToken());
            }

            //조합
            visited = new boolean[k];
            combination(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void combination(int idx, int cnt) {
        if (cnt == 6) {
            for (int i = 0; i < k; i++) {
                if (visited[i]) sb.append(lotto[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < k; i++) {
            visited[i] = true;
            combination(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
}
