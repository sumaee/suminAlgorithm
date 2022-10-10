import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(time);

		int result = 0;
		int minute = 0;
		for (int i = 0; i < N; i++) {
			minute += time[i];
			result += minute;
		}
		System.out.println(result);
	}
}
