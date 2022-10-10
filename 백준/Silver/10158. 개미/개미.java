import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int w = Integer.parseInt(st.nextToken()); // x범위
		int h = Integer.parseInt(st.nextToken()); // y범위

		st = new StringTokenizer(br.readLine());
		int stx = Integer.parseInt(st.nextToken()); // 시작 x위치
		int sty = Integer.parseInt(st.nextToken()); // 시작 y위치
		int time = Integer.parseInt(br.readLine());

		int dx = 1;
		int dy = 1;
		int movex = time % (w * 2);
		int movey = time % (h * 2);

		for (int i = 1; i <= movex; i++) {
			int x = stx + dx;
			if (x == w || x == 0) {
				dx *= -1;
			}
			stx = x;
		}

		for (int i = 1; i <= movey; i++) {
			int y = sty + dy;

			if (y == h || y == 0) {
				dy *= -1;
			}
			sty = y;
		}

		System.out.println(stx + " " + sty);
	}
}
