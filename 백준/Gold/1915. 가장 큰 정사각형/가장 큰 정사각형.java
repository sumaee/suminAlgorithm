import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(input[j - 1]);
			}
		}

		int[][] sum = new int[N + 1][M + 1];
		int max=0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] == 0) {
					sum[i][j] = 0;
				} else {
					sum[i][j] = Math.min(Math.min(sum[i - 1][j], sum[i][j - 1]), sum[i - 1][j - 1]) + 1;
				}
				
				max=Math.max(max, sum[i][j]);
			}
		}
		
		System.out.println(max*max);

	}
}
