import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k, sum, cnt;
	static int[] weight;
	static boolean[] possible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine()); // 추의 개수
		weight = new int[k];
		sum = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			sum += weight[i];
		}

		possible = new boolean[sum + 1];
		bfs(0, 0);
		System.out.println(sum - cnt);
	}// main

	private static void bfs(int idx, int sum) {
		if (idx == k) {
			if (sum > 0 && !possible[sum]) {
				possible[sum] = true;
				cnt++;
			}
			return;
		}

		bfs(idx + 1, sum); // 추 안올리기
		bfs(idx + 1, sum + weight[idx]);// 추올리기
		bfs(idx + 1, sum - weight[idx]); // 추 뺴기
	}

}
