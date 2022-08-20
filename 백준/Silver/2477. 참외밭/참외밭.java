import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cnt = Integer.parseInt(br.readLine()); // 1m^2당 참외개수
		int d;
		int[] len = new int[6]; // 길이

		int maxVertical = Integer.MIN_VALUE; // 가장 긴 세로 길이
		int maxVerticalIdx = 0;

		int maxWidth = Integer.MIN_VALUE; // 가장 긴 가로 길이
		int maxWidthIdx = 0;

		int V;

		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			d = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());

			// 가장 긴 가로 찾기
			if ((d == 1 || d == 2) && len[i] > maxWidth) {
				maxWidth = len[i];
				maxWidthIdx = i;
			}

			// 가장 긴 세로 찾기
			else if ((d == 3 || d == 4) && len[i] > maxVertical) {
				maxVertical = len[i];
				maxVerticalIdx = i;
			}

		} // 입력받고 가로 세로 최대 길이, 위치 찾기 for문

		// 작은 직사각형 가로 세로 구하기
		int width = 0;
		int vertical = 0;

		if (maxWidthIdx == 0) {
			vertical = Math.abs(len[5] - len[maxWidthIdx + 1]);
		} else if (maxWidthIdx == 5) {
			vertical = Math.abs(len[0] - len[maxWidthIdx - 1]);
		} else {
			vertical = Math.abs(len[maxWidthIdx + 1] - len[maxWidthIdx - 1]);
		}

		if (maxVerticalIdx == 0) {
			width = Math.abs(len[5] - len[maxVerticalIdx + 1]);
		} else if (maxVerticalIdx == 5) {
			width = Math.abs(len[0] - len[maxVerticalIdx - 1]);
		} else {
			width = Math.abs(len[maxVerticalIdx + 1] - len[maxVerticalIdx - 1]);
		}

		V = maxWidth * maxVertical - vertical * width;

		System.out.println(V * cnt);
	}// main
}
