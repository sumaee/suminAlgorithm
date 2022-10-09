import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();

		// R, C먼저 구하기 R<=C, R*C=N
		int R = 1;
		int C = 1;
		for (int i = R; i <= str.length() / 2; i++) {
			for (int j = str.length(); j >= i; j--) {
				if (i * j == str.length() && i <= j) {
					R = i;
					C = j;
				}
			}
		}

		char[][] email = new char[R][C];
		int idx = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				email[j][i] = str.charAt(idx++);
			}
		}

		for (char i[] : email) {
			for (char a : i) {
				sb.append(a);
			}
		}
		System.out.println(sb);
	}
}
