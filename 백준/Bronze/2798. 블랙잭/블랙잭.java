import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> cardList = new ArrayList<>();
		List<Integer> sum = new ArrayList<>();
		int cardnum = Integer.parseInt(st.nextToken()); // 카드 개수
		int value = Integer.parseInt(st.nextToken()); // 합의 한계점
		
		// 카드 숫자 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cardnum; i++) {
			cardList.add(Integer.parseInt(st.nextToken()));
		}

		// 카드를 조합하여 더한 값들을 result에 저장
		for (int i = 0; i < cardnum - 2; i++) {
			for (int j = i + 1; j < cardnum - 1; j++) {
				for (int k = j + 1; k < cardnum; k++) {
					sum.add(cardList.get(i) + cardList.get(j) + cardList.get(k));
				}
			}
		}

		// result 정렬하여 value값과 가까운 값 찾기
		Collections.sort(sum);
		for (int i = 0; i < sum.size(); i++) {
			if (sum.get(i) > value) {
				System.out.println(sum.get(i-1));
				return;
			}
		}
		
		//만약 sum값이 value보다 다 작다면 제일 마지막 index를 호출
		System.out.println(sum.get(sum.size()-1));

	}// main
}
