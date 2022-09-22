import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] arr;
	static int[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());// 숫자 개수
			arr = new int[N];
			temp = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 병합정렬 시작
			mergeSort(0, N - 1);
			for(int a: arr) {
				sb.append(a+" ");
			}
			sb.append("\n");
		} // tc
		System.out.println(sb);

	}// main

	public static void mergeSort(int left, int right) {
		int mid = (left + right) / 2;
		if (left < right) {
			mergeSort(left, mid); // 왼쪽 정렬
			mergeSort(mid + 1, right); // 오른족 정렬
			merge(left, right, mid); // 합치자
		}
	}// mergeSort

	public static void merge(int left, int right, int mid) {
		int L = left;
		int R = mid + 1;
		int idx = left;

		//
		while (L <= mid && R <= right) {
			if (arr[L] <= arr[R]) {
				temp[idx++] = arr[L++];
			} else {
				temp[idx++] = arr[R++];
			}
		}

		if (L > mid) {
			for (int i = R; i <= right; i++) {
				temp[idx++] = arr[i];
			}
		}

		else {
			for (int i = L; i <= mid; i++) {
				temp[idx++] = arr[i];
			}
		}

		for (int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}

	}// merge
}
