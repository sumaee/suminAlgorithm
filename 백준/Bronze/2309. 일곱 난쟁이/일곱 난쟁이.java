import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int arr[];
	static int N;
	static boolean[] check;
	static int sum;
	static StringBuilder sb;
	static List<Integer> list = new ArrayList<>();
	static int[] result = new int[7];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = 9;
		arr = new int[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		powerSet(0);
		Arrays.sort(result);
		for (int i = 0; i < 7; i++) {
			sb.append(result[i]).append("\n");
		}

		sb.append("\n");
		System.out.println(sb);

		sum = 0;

	}

	static void powerSet(int idx) {
		if (idx == N) {
			int kSum = 0;
			list.clear();
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					kSum += arr[i];
					list.add(arr[i]);
				}
			}
			if (kSum == 100 && list.size() == 7) {
				for (int i = 0; i < 7; i++) {
					result[i] = list.get(i);
				}
			}
			return;
		}

		check[idx] = true;
		powerSet(idx + 1);
		check[idx] = false;
		powerSet(idx + 1);
	}
}
