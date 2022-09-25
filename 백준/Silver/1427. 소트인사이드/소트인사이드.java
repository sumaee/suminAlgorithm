import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] arr;
	static char[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();
		arr = new char[N.length()];
		temp = new char[N.length()];
		
		arr = N.toCharArray();
		mergesort(0, arr.length - 1);
		String result = String.valueOf(arr);

		System.out.println(result);
	}// main

	public static void mergesort(int left, int right) {
		int mid = (left + right) / 2;
		if (left < right) {
			mergesort(left, mid); // 왼쪽을 정렬하기
			mergesort(mid + 1, right); // 오른쪽 정렬하기
			merge(left, right, mid);
		}
	}// mergesort

	public static void merge(int left, int right, int mid) {
		int L = left;
		int R = mid + 1;
		int idx = left;

		while (L <= mid && R <= right) {
			if (arr[L] >= arr[R]) {
				temp[idx++] = arr[L++];
			} else {
				temp[idx++] = arr[R++];
			}
		}

		if (L > mid) {
			for (int i = R; i <= right; i++) {
				temp[idx++] = arr[i];
			}
		} else {
			for (int i = L; i <= mid; i++) {
				temp[idx++] = arr[i];
			}
		}

		for (int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}
	}
}
