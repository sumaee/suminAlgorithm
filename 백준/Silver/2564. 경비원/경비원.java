import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken()); // 가로 길이
		int y = Integer.parseInt(st.nextToken()); // 세로 길이

		int n = Integer.parseInt(br.readLine()); // 상점의 수
		int[][] store = new int[n][2]; // store[][0] : 방향
										// store[][1] : 위치

		// 상점 방향과 위치 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken()); // 방향
			store[i][1] = Integer.parseInt(st.nextToken()); // 위치(좌표)

		} // for

		// 내 위치 받기
		st = new StringTokenizer(br.readLine());
		int myWay = Integer.parseInt(st.nextToken());
		int mylocate = Integer.parseInt(st.nextToken());

		// 내 방향을 고려해서 내 방향 기준으로 양쪽을 펼쳐 직선 만들기

		// 거리 계산
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (store[i][0] == 1) {

				if (myWay == 1) {
					result += Math.abs(mylocate - store[i][1]);
				} else if (myWay == 2) {
					result += Math.min(mylocate + y + store[i][1], (x - mylocate) + y + (x - store[i][1]));
				} else if (myWay == 3) {
					result += (mylocate + store[i][1]);
				} else if (myWay == 4) {
					result += (mylocate + (y - store[i][1]));
				}

			} else if (store[i][0] == 2) {

				if (myWay == 2) {
					result += Math.abs(mylocate - store[i][1]);
				} else if (myWay == 1) {
					result += Math.min(mylocate + y + store[i][1], (x - mylocate) + y + (x - store[i][1]));
				} else if (myWay == 3) {
					result += ((y - mylocate) + store[i][1]);
				} else if (myWay == 4) {
					result += ((y - mylocate) + (x - store[i][1]));
				}

			} else if (store[i][0] == 3) {

				if (myWay == 3) {
					result += Math.abs(mylocate - store[i][1]);
				} else if (myWay == 1) {
					result += (mylocate + store[i][1]);
				} else if (myWay == 2) {
					result += (mylocate + (y - store[i][1]));
				} else if (myWay == 4) {
					result += Math.min(mylocate + x + store[i][1], (y - mylocate) + x + (y - store[i][1]));
				}

			} else if (store[i][0] == 4) {

				if (myWay == 4) {
					result += Math.abs(mylocate - store[i][1]);
				} else if (myWay == 1) {
					result += ((x - mylocate) + store[i][1]);
				} else if (myWay == 2) {
					result += ((x - mylocate) + (y - store[i][1]));
				} else if (myWay == 3) {
					result += Math.min(mylocate + x + store[i][1], (y - mylocate) + x + (y - store[i][1]));
				}

			}
		} // for

		System.out.println(result);
	}// main

}
