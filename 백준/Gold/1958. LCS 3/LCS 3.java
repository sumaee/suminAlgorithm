import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] strA = br.readLine().toCharArray();
		char[] strB = br.readLine().toCharArray();
		char[] strC = br.readLine().toCharArray();

		int ALen = strA.length;
		int BLen = strB.length;
		int CLen = strC.length;

		int[][][] dp = new int[ALen + 1][BLen + 1][CLen + 1];

		for (int i = 1; i <= ALen; i++) {
			for (int j = 1; j <= BLen; j++) {
				for (int k = 1; k <= CLen; k++) {
					if (strA[i - 1] == strB[j - 1] && strB[j - 1] == strC[k - 1]) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					}

					else {
						dp[i][j][k] = Math.max(dp[i][j][k - 1], Math.max(dp[i - 1][j][k], dp[i][j - 1][k]));
					}
				}
			}
		}
        
		sb.append(dp[ALen][BLen][CLen] + "\n");
		System.out.println(sb);
	}// main
}
