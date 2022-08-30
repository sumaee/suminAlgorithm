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
		int[] check = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			check[i] = 1;
		}

		// 앞에서부터 비교해가며 check에 경우의 수를 쌓아가기
		// 그 경우의 수 중 큰 것을 check에 쌓기
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j]) {
					check[i]=Math.max(check[i], check[j]+1);
				}
			}
		}
		
		int maxLen=Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			if(check[i]>maxLen) {
				maxLen=check[i];
			}
		}
		System.out.println(maxLen);

	}
}
