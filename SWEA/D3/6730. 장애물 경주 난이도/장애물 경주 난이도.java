import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, up, down;
	static int[] block;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine()); // 블록 개수
			block = new int[N];
			up = 0; // 올라갈 때 최고 변화
			down = 0; // 내렬갈 때 최고 변화

			// 블록을 먼저 채워주기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				block[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < N - 1; i++) {
				int temp = Math.abs(block[i + 1] - block[i]);
				if (block[i] > block[i + 1]) {
					down = Math.max(down, temp);
				} else { // 올라가는 것
					up = Math.max(up, temp);
				}
			}

			sb.append(up + " " + down).append("\n");
		} // tc
		System.out.println(sb);
	}// main
}
