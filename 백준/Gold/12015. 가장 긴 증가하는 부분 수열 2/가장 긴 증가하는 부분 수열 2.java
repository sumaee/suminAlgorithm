import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		int[] LIS = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 우선 첫번째 값을 그냥 넣기
		LIS[0] = arr[0];
		int LISLen = 1;

		// 이 다음부터 나오는 수가 집어넣은 마지막 수보다 크다면 추가
		// 작다면 집어넣을 곳을 탐색하여 대치
		for (int i = 1; i < N; i++) {

			int value = arr[i];

			// 만약 넣으려는 수가 LIS에 들어가있는 마지막 수보다 크면 그냥 뒤에 추가
			if (LIS[LISLen - 1] < value) {
				LIS[LISLen] = value;
				LISLen++;
			}

			// 아니라면 들어갈곳 찾아서 대치
			else {
				int start = 0;
				int end = LISLen;

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

		System.out.println(LISLen);
	}// main
}
