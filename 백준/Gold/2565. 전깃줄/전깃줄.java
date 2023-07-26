import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Wire[] wires = new Wire[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int aLine = Integer.parseInt(st.nextToken());
            int bLine = Integer.parseInt(st.nextToken());

            wires[i] = new Wire(aLine, bLine);
        }

        //두개의 전깃줄 중 하나를 기준으로 오름차순
        Arrays.sort(wires, (o1, o2) -> o1.a - o2.a);

        //다른 전깃줄도 오름차순이 되어야 가장 길게 전깃줄 생성 가능
        int[] dp = new int[n];
        int max = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[i].b > wires[j].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        //없애야 하는 개수는 모든 전깃줄에서 최대 긴 전깃줄을 뺀수
        System.out.println(n - max);
    }

    static class Wire {
        int a, b;

        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
