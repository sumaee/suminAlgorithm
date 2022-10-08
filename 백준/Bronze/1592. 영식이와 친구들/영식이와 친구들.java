import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 자리 숫자
		int M = Integer.parseInt(st.nextToken()); // 공을 받아야하는 숫자
		int L = Integer.parseInt(st.nextToken()); // 공받은 개수가 홀수면 시계방향 L번째, 짝수면 반시계

		int[] people = new int[N];
		boolean flag = true;
		int result = 0;
		int idx = 0; // 1번사람 부터 시작
		people[idx]++;
		while (flag) {
			
			if (people[idx] == M) {
				flag = false;
				break;
			}

			// 공을 받은 횟수가 홀수이면
			if (people[idx] % 2 != 0) {
				// 다음사람에게 넘기기(시계방향)
				idx = (idx + L ) % N;
				// 다음사람의 받은횟수 하나 추가
				people[idx]++;
			}

			// 공을 받은 횟수가 짝수이면
			else {
				// 다음사람에게 넘기기(반시계방향)
				idx = (N + idx - L) % N;
				// 다음사람 공받은 횟수 증가
				people[idx]++;
			}

			result++;

		}

		System.out.println(result);
	}// main
}
