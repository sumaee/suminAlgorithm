import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String str1 = br.readLine();
        String str2 = br.readLine();

        //주변을 0으로 채우기 위해 한칸씩 더 크게 만듬
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                //만약 두 문자열이 같으면 왼쪽 대각선 위에 있는 값보다 1개 더 크게 저장
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                //다르다면 위랑 왼쪽의 값중 큰 값 저장
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int answer = dp[str1.length()][str2.length()];
        sb.append(answer).append("\n");
        if (answer != 0) {
            findLCS(str1, str2, dp);
        }
        System.out.println(sb);
    }

    private static void findLCS(String str1, String str2, int[][] dp) {
        Stack<Character> stack = new Stack<>();

        int str1Len = str1.length();
        int str2Len = str2.length();

        while (str1Len > 0 && str2Len > 0) {
            if (str1.charAt(str1Len - 1) == str2.charAt(str2Len - 1)) {
                stack.push(str1.charAt(str1Len - 1));
                str1Len--;
                str2Len--;
            } else if (dp[str1Len][str2Len] == dp[str1Len][str2Len - 1]) {
                str2Len--;
            } else if (dp[str1Len][str2Len] == dp[str1Len - 1][str2Len]) {
                str1Len--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }
}

