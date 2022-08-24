import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 1: 흑돌, 2: 백돌
	// 내돌과 내가 놓을 자리 사이에 상대 돌이 있어야 놓을 수 있음
	// 주어지는 인덱스가 배열 인덱스보다 한개 더 크다는 것 주의
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());

			// 바둑판 크기
			int N = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			// 돌을 놓는 횟수
			int M = Integer.parseInt(st.nextToken());

			// 바둑돌 시작 세팅
			arr[N / 2 - 1][N / 2 - 1] = 2;
			arr[N / 2][N / 2] = 2;
			arr[N / 2 - 1][N / 2] = 1;
			arr[N / 2][N / 2 - 1] = 1;

			// 8방 탐색을 위한 dr, dc 배열
			// 상부터 시계방향
			int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
			int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken()) - 1;
				int row = Integer.parseInt(st.nextToken()) - 1;

				int BW = Integer.parseInt(st.nextToken()); // 1: 흑돌, 2: 백돌

				// 돌을 먼저 놓기
				arr[row][col] = BW;

				// 8방 탐색하여 돌 색 바꾸기
				for (int k = 0; k < 8; k++) {
					int dRow = row + dr[k];
					int dCol = col + dc[k];

					while (true) {
						// 경게를 벗어나거나 해당 탐색자리가 0이면 그만~
						if ((dRow < 0 || dRow > arr.length - 1 || dCol < 0 || dCol > arr.length - 1)
								|| arr[dRow][dCol] == 0) {
							break;
						}

						// 만약 내가 놓은 자리와 탐색하는 자리의 돌 색이 같지 않다면
						// 해당 방향으로 같은 돌이 나오는지 탐색
						if (arr[row][col] != arr[dRow][dCol]) {
							// 해당 방향으로 쭉 가서 마지막 같은 색이나오는 부분을 확인하겠다
							dRow += dr[k];
							dCol += dc[k];
						} else {
							break;
						}
					} // 해당 줄 탐색

					// 위에 while문으로 탐색한 방향대로 쭉 색깔 바꾸기
					if (!(dRow < 0 || dRow > arr.length - 1 || dCol < 0 || dCol > arr.length - 1)
							&& arr[dRow][dCol] == arr[row][col]) {
						// 다시 원점으로 돌아올 때까지 진행
						while (dRow != row || dCol != col) {
							arr[dRow][dCol] = arr[row][col];
							dRow -= dr[k];
							dCol -= dc[k];
						}
					}
				} // 8방탐색 돌바꾸기

			}

			// 완성 되었으니 흑돌, 백돌 색 세기
			int bCnt = 0, wCnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1) {
						bCnt++;
					} else if(arr[i][j]==2){
						wCnt++;
					}
				}
			}

			sb.append(bCnt).append(" ").append(wCnt).append("\n");
		} // tc
		System.out.println(sb);
	}// main
}
