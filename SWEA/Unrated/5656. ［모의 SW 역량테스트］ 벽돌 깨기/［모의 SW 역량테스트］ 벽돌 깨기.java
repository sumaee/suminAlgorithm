import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, answer;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			// 공의 개수
			N = Integer.parseInt(st.nextToken());
			// 열
			W = Integer.parseInt(st.nextToken());
			// 행
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			answer = 200;
			// 벽돌의 정보 받기
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dropBall(0, map);
			sb.append(answer + "\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void dropBall(int ballCnt, int[][] map) {

		if (ballCnt == N) {
			// 공을 다 떨어뜨렸으니 남아있는 블록 개수 세기
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0) {
						cnt++;
					}
				}
			}

			answer = Math.min(answer, cnt);
			return;
		}

		// 벽돌을 깨고 임시저장할 배열
		int[][] temp = new int[H][W];

		for (int col = 0; col < W; col++) {
			int row = 0;
			// 깨질 벽돌이 있을 때까지 내리기
			while (row < H && map[row][col] == 0) {
				row++;
			}

			if (row == H) { // 만약 공이 끝까지 떨어졌다는 건 벽돌이 없다는 것
				// 다음 공 떨구기
				dropBall(ballCnt + 1, map);
			} else { // 벽돌이 있을 때

				// 깨뜨릴 것을 임시 배열에 저장
				for (int j = 0; j < H; j++) {
					for (int k = 0; k < W; k++) {
						temp[j][k] = map[j][k];
					}
				}

				// 깨뜨리기
				breakBrick(temp, row, col);

				// 중력 작용
				downBrick(temp);

				// 다음으로 넘어가기
				dropBall(ballCnt + 1, temp);
			}

		}

	}// dropBall

	private static void downBrick(int[][] temp) {
		for (int col = 0; col < W; col++) {
			Queue<Integer> que = new LinkedList<>();
			int row = H - 1;

			// 0이 아닌 것 싹다 큐에 저장하고 0으로 바꾸기
			while (row >= 0) {
				if (temp[row][col] > 0) {
					que.offer(temp[row][col]);
					temp[row][col] = 0;
				}
				row--;
			}

			// 이제 다시 채우기
			row = H - 1;
			while (!que.isEmpty()) {
				temp[row][col] = que.poll();
				row--;
			}
		}
	}

	// 공을 떨어뜨렸을 때 연쇄작용
	private static void breakBrick(int[][] temp, int row, int col) {
		Queue<Node> bricks = new LinkedList<>();

		// 여러개가 같이 터지는 것을 넣기
		if (temp[row][col] > 1) {
			bricks.offer(new Node(row, col, temp[row][col]));
		}

		// 터짐
		temp[row][col] = 0;

		while (!bricks.isEmpty()) {
			Node curr = bricks.poll();

			for (int i = 0; i < 4; i++) {
				int r = curr.row;
				int c = curr.col;

				// 터지는 범위
				for (int j = 1; j < curr.size; j++) {
					r += dr[i];
					c += dc[i];

					if (r >= 0 && c >= 0 && r < H && c < W && temp[r][c] != 0) {
						if (temp[r][c] > 1) {
							bricks.offer(new Node(r, c, temp[r][c]));
						}
						temp[r][c] = 0;
					}
				}
			}
		}
	}// breakBrick

	private static class Node {
		int row, col, size;

		public Node(int row, int col, int size) {
			this.row = row;
			this.col = col;
			this.size = size;
		}
	}// Node
}
