import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int swCnt = Integer.parseInt(br.readLine());// 스위치 개수
		int[] swit = new int[swCnt];

		// 스위치 상태 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < swCnt; i++) {
			swit[i] = Integer.parseInt(st.nextToken());
		}

		// 학생수
		int num = Integer.parseInt(br.readLine());

		// 학생의 정보
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int bg = Integer.parseInt(st.nextToken()); // 1 : 남자, 2: 여자
			int getnum = Integer.parseInt(st.nextToken()); // 받은 번호

			switch (bg) {

			// 남학생의 경우
			case 1:
				// swit[i]가 남학생이 받은 번호의 배수라면 for구문 진행
				for (int j = 0; j < swit.length; j++) {
					// 바꿀 때 1이면 0으로 0이면 1로 변경
					if ((j + 1) % getnum == 0) {
						if (swit[j] == 1) {
							swit[j] = 0;
						} else {
							swit[j] = 1;
						}

					}
				}
				break;
			// 여학생의 경우
			default:
				for (int j = getnum - 1, k = 0; j - k >= 0 && j + k < swit.length; k++) {

					if (swit[j + k] == swit[j - k]) {
						if (swit[j + k] == 1) {
							swit[j + k] = swit[j - k] = 0;
						} else {
							swit[j + k] = swit[j - k] = 1;
						}
					} else {
						break;
					}
				}
			}// 학생에 따른 switch문

		} // 스위치 변경 for문
		for(int i=0; i<swit.length; i++) {
			System.out.print(swit[i]);
			//한줄에 20개가 넘으면 줄바꾸기
			if((i+1)%20==0) {
				System.out.println();
			}else {
				System.out.print(" ");
			}
		}
	}// main
}
