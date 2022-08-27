import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());

			boolean[] check = new boolean[10];
			int idx = 1;
			int result = 1;
			while (true) {

				result = N * idx;
				int checkCnt = 0;

				for (int i = 0; i < String.valueOf(result).length(); i++) {
					check[String.valueOf(result).charAt(i) - '0'] = true;
				}
				for (int i = 0; i < 10; i++) {
					if (!check[i]) {
						checkCnt++;
					}
				}

				if (checkCnt == 0) {
					sb.append(result).append("\n");
					break;
				}
				idx++;

			} // while

		} // tc
		System.out.println(sb);
	}// main
}
