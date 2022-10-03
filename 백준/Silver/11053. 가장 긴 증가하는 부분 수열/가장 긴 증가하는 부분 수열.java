import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int A = Integer.parseInt(br.readLine()); // 수열의 크기
		int[] arr = new int[A];
		int[] tmp = new int[A];
		int result = Integer.MIN_VALUE;
		Arrays.fill(tmp, 1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		
		for (int i = 1; i <arr.length; i++) {
			for (int j = 0; j <i; j++) {
				if (arr[i] > arr[j]) {
					tmp[i] = Math.max(tmp[i], tmp[j] + 1);
				}
			}
		}
		
		for(int i=0; i<tmp.length; i++) {
			result=Math.max(result, tmp[i]);
		}
		System.out.println(result);
	}// main
}
