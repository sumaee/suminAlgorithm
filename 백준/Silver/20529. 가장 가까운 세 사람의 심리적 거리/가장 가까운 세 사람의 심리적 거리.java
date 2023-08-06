import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            
            //33명이 되면 무조건 3명은 똑같은 MBTI를 가지기 때문에 0이 최솟값이 될 수밖에 없음
            if (n >= 33) {
                sb.append(0).append("\n");
                continue;
            }

            String[] mbti = new String[n];

            for (int i = 0; i < n; i++) {
                mbti[i] = st.nextToken();
            }

            int answer = Integer.MAX_VALUE;
            out:
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    for (int k = j + 1; k < n; k++) {
                        int result = getDist(mbti, i, j, k);
                        answer = Math.min(answer, result);
                    }

                    if (answer == 0) break out;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int getDist(String[] mbti, int first, int second, int third) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            cnt += mbti[first].charAt(i) == mbti[second].charAt(i) ? 0 : 1;
            cnt += mbti[second].charAt(i) == mbti[third].charAt(i) ? 0 : 1;
            cnt += mbti[third].charAt(i) == mbti[first].charAt(i) ? 0 : 1;
        }

        return cnt;
    }
}
