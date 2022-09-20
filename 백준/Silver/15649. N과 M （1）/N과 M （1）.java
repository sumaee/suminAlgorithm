import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int arr[];
	static int result[];
	static boolean checkArr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		result = new int[M];
		checkArr = new boolean[N];
		// arr배열에 숫자를 채우기

		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		check(0);
	}// main

	static void check(int idx) {
		// M개까지 채웠으면 끝이므로 뽑아내기
		if (idx == M) {
			for (int i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
//			Arrays.fill(checkArr, false);
			return;
		}

		// result에 arr i번째 수를 넣고 확인
		for (int i = 0; i < N; i++) {
			if (!checkArr[i]) {
				checkArr[i] = true;
				result[idx] = arr[i];
				check(idx+1);
				checkArr[i]=false;
			}

		}

	}

}