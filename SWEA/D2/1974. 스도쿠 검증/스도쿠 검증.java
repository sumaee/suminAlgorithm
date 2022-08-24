import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");

			int[][] game = new int[9][9];
			Boolean[] check = new Boolean[10];
			// 스도쿠판 채우기
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					game[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			finish: while (true) {
				// 정사각형 확인
				// (0, 0), (0, 3), (0, 6)
				// (3, 0), (3, 3), (3, 6)
				// (6, 0), (6, 3), (6, 6)
				//위의 해당 부분에서 3 by3 크기의 배열을 확인하겠다
				for (int i = 0; i < 9; i += 3) {
					for (int j = 0; j < 9; j += 3) {
						Arrays.fill(check, false);
						//해당 인덱스에서 행 2칸, 열 2칸 이내만 확인
						for (int dr = i; dr <= i + 2; dr++) {
							for (int dc = j; dc <= j + 2; dc++) {
								// 해당 값이 false라면 true로 바꾸기
								if (!(check[game[dr][dc]])) {
									check[game[dr][dc]]=true;
								} else { // 이미 true라면 0출력
									sb.append(0).append("\n");
									break finish;
								}
							}
						}

					}
				}// 정사각형모양 확인 for문
				
				// 가로 확인
				for(int i=0; i<9; i++) {
					Arrays.fill(check, false);
					for(int j=0; j<9; j++) {
						if(!(check[game[i][j]])) {
							check[game[i][j]]=true;
							continue;
						}else {
							sb.append(0).append("\n");
							break finish;
						}
					}
				}
				
				//세로 확인
				for(int i=0; i<9; i++) {
					Arrays.fill(check, false);
					for(int j=0; j<9; j++) {
						if(!(check[game[j][i]])) {
							check[game[j][i]]=true;
							continue;
						}else {
							sb.append(0).append("\n");
							break finish;
						}
					}
				}
				
				//여기까지 왔다는 것은 겹치는것이 없었다는 것이므로 1을 출력
				sb.append(1).append("\n");
				break;
			}//while문

		} // tc
		System.out.println(sb);
	}// main
}
