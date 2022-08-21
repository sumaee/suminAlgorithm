import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 우선순위 : 별(4)<동그라미(3)<네모(2)<세모(1)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int round = Integer.parseInt(br.readLine());

		// 게임
		for (int i = 0; i < round; i++) {

			// a의 카드개수 & 카드 종류
			st = new StringTokenizer(br.readLine());
			int aNum = Integer.parseInt(st.nextToken());
			int[] aCard = new int[5];
			for (int j = 0; j < aNum; j++) {
				aCard[Integer.parseInt(st.nextToken())]++;
			}

			// b의 카드개수 & 카드 종류
			st = new StringTokenizer(br.readLine());
			int bNum = Integer.parseInt(st.nextToken());
			int[] bCard = new int[5];
			for (int j = 0; j < bNum; j++) {
				bCard[Integer.parseInt(st.nextToken())]++;
			}

			for (int j = 4; j >= 1; j--) {
				if (bCard[j] != aCard[j]) {
					sb.append(aCard[j] > bCard[j] ? "A" : "B").append("\n");
					break;
				}

				if (j == 1 && aCard[j] == bCard[j]) {
					sb.append("D").append("\n");
				}
			}
		} // round

		System.out.println(sb);
	}// main
}
