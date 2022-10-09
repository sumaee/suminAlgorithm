import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int L = Integer.parseInt(br.readLine()); // 롤케이크 길이
		int N = Integer.parseInt(br.readLine()); // 방청객의 수

		int maxCakeP = 0;
		int realP = 0; // 실제로 많이 받은 사람
		int[] cake = new int[L + 1];
		int[] people = new int[N + 1]; // 실제로 많이 받은 사람
		int[] expectP = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cnt = 0;

			expectP[i] = end - start + 1;

			for (int j = start; j <= end; j++) {
				if (cake[j] == 0) {
					cake[j]++;
					cnt++;
					people[i]++;
				}
			}

		}

		for (int i = N; i >= 1; i--) {
			maxCakeP = expectP[maxCakeP] <= expectP[i] ? i : maxCakeP;
			realP = people[realP] <= people[i] ? i : realP;
		}

		System.out.println(maxCakeP);
		System.out.println(realP);
	}// main
}
