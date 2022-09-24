import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		average();
		mid();
		many();
		range();
	}// main

	public static void average() {
		double sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i];
		}
		int result = (int)Math.round(sum / N);
		System.out.println(result);
	}

	public static void mid() {
		int result = arr[N / 2];
		System.out.println(result);
	}

	public static void many() {
		List<Integer> list = new ArrayList<>();
		int cnt;
		int max = 1;
		for (int i = 0; i < N; i += cnt) {
			cnt = 1;
			int j = i + 1;
			while (j < N && arr[j] == arr[i]) {
				cnt++;
				j++;
			}

			if (cnt > max) {
				max = cnt;
				list.clear();
				list.add(arr[i]);
			} else if (cnt == max) {
				list.add(arr[i]);
			}

		}

		if (list.size() == 1) {
			System.out.println(list.get(0));
		} else {
			System.out.println(list.get(1));
		}

	}

	public static void range() {
		int result = arr[N - 1] - arr[0];
		System.out.println(result);
	}
}
