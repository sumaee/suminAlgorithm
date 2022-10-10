import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] balloon;
	static int N, cnt, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 풍선의개수
		balloon = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			balloon[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		int idx = findHigh();
		while (balloon[idx] != 0) {
			int start = balloon[idx];
			for (int i = idx; i < N; i++) {
				if (balloon[i] == start) {
					balloon[i] = 0;
					start--;
				}
			}
			cnt++;
			idx = findHigh();
		}

		System.out.println(cnt);
	}// main

	private static int findHigh() {
		int high = 0;
		for (int i = 0; i < N; i++) {
			high = balloon[high] < balloon[i] ? i : high;
		}
		return high;
	}
}