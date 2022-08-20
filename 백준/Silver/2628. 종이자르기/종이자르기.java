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
		List<Integer> listZero = new ArrayList<>(); // 세로
		List<Integer> listOne = new ArrayList<>(); // 가로

		int width = Integer.parseInt(st.nextToken()); // 가로길이
		int height = Integer.parseInt(st.nextToken()); // 세로길이

		int cut = Integer.parseInt(br.readLine()); // 자르는 횟수

		// 가로세로 나눠서 리스트에 넣기
		for (int i = 0; i < cut; i++) {
			st = new StringTokenizer(br.readLine());

			int check = Integer.parseInt(st.nextToken()); // 가로세로 판단
			int blade = Integer.parseInt(st.nextToken()); // 자르는 위치

			if (check == 0) {
				listZero.add(blade);
			} else {
				listOne.add(blade);
			}
		} // cut for문

		// 가로세로 리스트 들을 정렬해서 0~해당 배열~10 차이 중 큰것 찾기
		// 0과 세로길이 list에 추가
		listZero.add(0);
		listZero.add(height);
		// 0과 가로길이 list에 추가
		listOne.add(0);
		listOne.add(width);

		// 가로 세로 정렬
		Collections.sort(listZero);
		Collections.sort(listOne);

		// 세로 길이 확인
		int heightlen = Integer.MIN_VALUE;
		for (int i = 0; i < listZero.size() - 1; i++) {
			int len = listZero.get(i + 1) - listZero.get(i);
			if (len > heightlen) {
				heightlen = len;
			}
		}
		
		// 가로 길이 확인
		int widthlen = Integer.MIN_VALUE;
		for (int i = 0; i < listOne.size() - 1; i++) {
			int len = listOne.get(i + 1) - listOne.get(i);
			if (len > widthlen) {
				widthlen = len;
			}
		}

		System.out.println(widthlen * heightlen);

	}// main
}
