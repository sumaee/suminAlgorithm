import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] strA = br.readLine().toCharArray();
		char[] strB = br.readLine().toCharArray();

		int ALen = strA.length;
		int BLen = strB.length;

		int[][] dp = new int[ALen + 1][BLen + 1];

		for (int i = 1; i <= ALen; i++) {
			for (int j = 1; j <= BLen; j++) {

				if (strA[i - 1] == strB[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}

				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		int i = ALen;
		int j = BLen;
		Stack<Character> stack = new Stack<>();
		while (i > 0 && j > 0) {
			if (strA[i-1] == strB[j-1]) {
				stack.add(strA[i-1]);
				i--;
				j--;
				continue;
			}

			if (dp[i][j] == dp[i][j - 1]) {
				j--;
				continue;
			}

			if (dp[i][j] == dp[i - 1][j]) {
				i--;
				continue;
			}
		}

		sb.append(dp[ALen][BLen]+"\n");
		for (int k = 0; k < dp[ALen][BLen]; k++) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}// main
}
