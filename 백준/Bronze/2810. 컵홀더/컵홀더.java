import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 좌석 수
		char[] seat = new char[N];

		String str = br.readLine();
		seat = str.toCharArray();

		// 컵홀더수 구하기
		int cupHolder = N + 1;
		for (int i = 0; i < N;) {
			if (seat[i] == 'L') {
				cupHolder--;
				i += 2;
			} else {
				i++;
			}
		}

		if (cupHolder > N) {
			System.out.println(N);
		} else {
			System.out.println(cupHolder);
		}
	}
}
