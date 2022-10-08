import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] arr = new int[10];
		int[] sum = new int[10];

		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		sum[0] = arr[0];
		int idx = 0;
		for (int i = 1; i < 10; i++) {
			sum[i] = sum[i - 1] + arr[i];

			if (sum[i] >= 100) {
				idx = i;
				break;
			}
            idx=i;
		}
        
        if (idx == 0) {
			System.out.println(sum[idx]);
			return;
		}

		int result = 0;
		if (Math.abs(sum[idx] - 100) == Math.abs(sum[idx - 1] - 100)) {
			result = sum[idx];
		} else {
			result = Math.abs(sum[idx] - 100) < Math.abs(sum[idx - 1] - 100) ? sum[idx] : sum[idx - 1];
		}

		System.out.println(result);
	}
}
