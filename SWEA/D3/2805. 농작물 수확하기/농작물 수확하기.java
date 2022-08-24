import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");

			int N = Integer.parseInt(br.readLine());
			int[][] ground = new int[N][N];
			String[] arr = new String[N];

			// 우선 땅을 1차원 배열로 한줄로 받아 찢어 넣어 2차원으로 넣기
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine();
				for (int j = 0; j < N; j++) {
					ground[i][j] = arr[i].charAt(j) - '0';
				}
			}
            
			// 합구하기
			int sum = 0;
			int idx = 0;
			for (int i = 0; i <= N / 2; i++) {

				for (int j = (N / 2 - idx); j <= (N / 2 + idx) && j < N && j >= 0; j++) {
					sum += ground[i][j];
				}
				idx++;
			}
			
			idx-=2;
			
			for (int i = N / 2 + 1; i < N; i++) {

				for (int j = N / 2 - idx; j <= N / 2 + idx && j < N && j >= 0; j++) {
					sum += ground[i][j];
				}
				idx--;
			}

			sb.append(sum).append("\n");

		} // tc
		System.out.println(sb);

	}// main
}
