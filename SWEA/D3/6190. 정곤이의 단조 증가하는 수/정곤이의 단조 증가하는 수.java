import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int max = Integer.MIN_VALUE;

			st = new StringTokenizer(br.readLine());
			// 숫자 받기
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 일단 곱해서
			// 값이 단조인지 확인
			// 단조라면 max값과 비교&저장
			 for (int i = 0; i < arr.length - 1; i++) {
				next:for (int j = i + 1; j < arr.length; j++) {
					int ans = arr[i] * arr[j];

					// 값의 자리를 비교하기 위해서 String사용
					String check = String.valueOf(ans);
					// 단조 확인
					for (int k = 0; k < check.length() - 1; k++) {
						char data = check.charAt(k);
						if (data > check.charAt(k + 1)) {
							// 앞자리가 다음자리보다 크다면 단조가 아니니까
							continue next;
						}
					}
					// 여기까지 왔다는건 단조임을 통과하고 온것이므로 값 비교
					if (ans > max) {
						max = ans;
					}

				}

			}
			 if(max==Integer.MIN_VALUE) {
				 sb.append(-1).append("\n");
			 }else {
				 sb.append(max).append("\n");
			 }
		} // tc
		System.out.println(sb);
	}// main
}
