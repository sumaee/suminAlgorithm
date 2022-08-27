import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine());

			// 첫번째 직사각형 좌표
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());

			// 두번째 직사각형 좌표
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());

			// 점일 경우
			if ((x1 == p2 && y1 == q2) || (x1 == p2 && q1 == y2) || (p1 == x2 && q1 == y2) || (p1 == x2 && y1 == q2)) {
				sb.append("c").append("\n");
			}
			// 공통 부분이 없는 경우
			else if (p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
				sb.append("d").append("\n");
			}
			// 선분으로 겹치는 경우
			else if (p1 == x2 || q1 == y2|| p2 == x1 || y1 == q2) {
				sb.append("b").append("\n");
			}
			// 위의 어떠한 경우도 아니라면 공통부분 없음
			else {
				sb.append("a").append("\n");
			}

		} // tc
		System.out.println(sb);
	
    
	}// main
}
