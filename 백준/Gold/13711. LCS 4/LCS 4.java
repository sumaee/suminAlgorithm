import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] LIS = new int[N];
		int[] arr = new int[N];
		int[] tmp = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			LIS[Integer.parseInt(st.nextToken()) - 1] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = LIS[Integer.parseInt(st.nextToken()) - 1];
		}

		tmp[0] = arr[0];
		int tmpLen = 1;
		for (int i = 1; i < N; i++) {

			int value = arr[i];

			// 만약 넣으려는 수가 LIS에 들어가있는 마지막 수보다 크면 그냥 뒤에 추가
			if (LIS[tmpLen - 1] < value) {
				LIS[tmpLen] = value;
				tmpLen++;
			}

			// 아니라면 들어갈곳 찾아서 대치
			else {
				int start = 0;
				int end = tmpLen;

				while (start < end) {
					int mid = (start + end) / 2;

					if (LIS[mid] < value) {
						start = mid + 1;
					} else {
						end = mid;
					}

				}

				LIS[start] = value;
			}
		}
		System.out.println(tmpLen);
	}// main
}
