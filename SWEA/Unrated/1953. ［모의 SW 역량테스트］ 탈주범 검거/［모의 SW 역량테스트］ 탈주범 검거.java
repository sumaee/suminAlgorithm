import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, R, C, L, result;
	static int[][] tunnel;
	static boolean[][] visited;
	static Queue<Node> que;
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

			N = Integer.parseInt(st.nextToken()); // 세로 크기
			M = Integer.parseInt(st.nextToken()); // 가로 크기
			R = Integer.parseInt(st.nextToken()); // 뚜껑 세로 위치
			C = Integer.parseInt(st.nextToken()); // 뚜껑 가로 위치
			L = Integer.parseInt(st.nextToken()); // 소요시간

			tunnel = new int[N][M];
			visited = new boolean[N][M];
			result = 0;
			que = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tunnel[i][j] = Integer.parseInt(st.nextToken());

					// 만약 멘홀뚜껑 위치라면 큐에 넣기
					if (i == R && j == C) {
						que.offer(new Node(i, j, tunnel[i][j], 1));
					}
				}
			}
			bfs();
			sb.append(result + "\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void bfs() {

		while (!que.isEmpty()) {
			Node curr = que.poll();

			// 해당시간이 되거나 이미 방문했던 곳이라면 더 진행할 필요 없으므로 다음으로 진행
			if (curr.time > L || visited[curr.row][curr.col]) {
				continue;
			}

			result++;

			// 가지 않았던 곳이라면 방문 처리
			visited[curr.row][curr.col] = true;

			// 홀 방향에 따라서 갈 수 있는 곳이 바뀌므로 큐에 넣어줘야할 값들이 바뀜
			switch (curr.hole) {
			// 상우좌하 다갈 수 있으므로 상우좌하 다 넣어주기
			case 1:
				for (int i = 0; i < 4; i++) {
					int row = curr.row + dr[i];
					int col = curr.col + dc[i];

					if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
						if (i == 0 && tunnel[row][col] != 3 && tunnel[row][col] != 4 && tunnel[row][col] != 7) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 1 && tunnel[row][col] != 2 && tunnel[row][col] != 4 && tunnel[row][col] != 5) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 2 && tunnel[row][col] != 3 && tunnel[row][col] != 5 && tunnel[row][col] != 6) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 3 && tunnel[row][col] != 2 && tunnel[row][col] != 6 && tunnel[row][col] != 7) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						}
					}
				}
				break;

			// 상하만 갈 수 있으므로 상하만 넣어주기 -> dr, dc 인덱스 0, 2
			case 2:
				for (int i = 0; i < 4; i++) {
					if (i % 2 == 0) {
						int row = curr.row + dr[i];
						int col = curr.col + dc[i];

						if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
							if (i == 0 && tunnel[row][col] != 3 && tunnel[row][col] != 4 && tunnel[row][col] != 7) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							} else if (i == 2 && tunnel[row][col] != 3 && tunnel[row][col] != 5
									&& tunnel[row][col] != 6) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							}
						}
					}
				}
				break;

			// 좌우만 갈수 있으므로 좌우만 넣기 -> dr, dc 인덱스 1, 3
			case 3:
				for (int i = 0; i < 4; i++) {
					if (i % 2 != 0) {
						int row = curr.row + dr[i];
						int col = curr.col + dc[i];

						if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
							if (i == 1 && tunnel[row][col] != 2 && tunnel[row][col] != 4 && tunnel[row][col] != 5) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							} else if (i == 3 && tunnel[row][col] != 2 && tunnel[row][col] != 6
									&& tunnel[row][col] != 7) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							}
						}
					}
				}
				break;

			// 상우만 갈수 있으므로 상우만 넣기 -> dr, dc 인덱스 0, 1
			case 4:
				for (int i = 0; i < 2; i++) {
					int row = curr.row + dr[i];
					int col = curr.col + dc[i];

					if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
						if (i == 0 && tunnel[row][col] != 3 && tunnel[row][col] != 4 && tunnel[row][col] != 7) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 1 && tunnel[row][col] != 2 && tunnel[row][col] != 4
								&& tunnel[row][col] != 5) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						}
					}
				}
				break;

			// 우하만 갈 수 있으므로 우하만 넣기 -> dr, dc 인덱스 1, 2
			case 5:
				for (int i = 1; i < 3; i++) {
					int row = curr.row + dr[i];
					int col = curr.col + dc[i];

					if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
						if (i == 1 && tunnel[row][col] != 2 && tunnel[row][col] != 4 && tunnel[row][col] != 5) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 2 && tunnel[row][col] != 3 && tunnel[row][col] != 5
								&& tunnel[row][col] != 6) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						}
					}
				}
				break;

			// 좌하만 갈수 있으므로 좌하만 넣기 -> dr, dc 인덱스 2, 3
			case 6:
				for (int i = 2; i < 4; i++) {
					int row = curr.row + dr[i];
					int col = curr.col + dc[i];

					if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
						if (i == 3 && tunnel[row][col] != 2 && tunnel[row][col] != 6 && tunnel[row][col] != 7) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						} else if (i == 2 && tunnel[row][col] != 3 && tunnel[row][col] != 5
								&& tunnel[row][col] != 6) {
							que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
						}
					}
				}
				break;

			// 상좌만 갈수 있으므로 상좌만 넣기 -> dr, dc 인덱스 0, 3
			case 7:
				for (int i = 0; i < 4; i++) {
					if (i % 3 == 0) {
						int row = curr.row + dr[i];
						int col = curr.col + dc[i];

						if (row >= 0 && col >= 0 && row < N && col < M && tunnel[row][col] != 0 && !visited[row][col]) {
							if (i == 0 && tunnel[row][col] != 3 && tunnel[row][col] != 4 && tunnel[row][col] != 7) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							} else if (i == 3 && tunnel[row][col] != 2 && tunnel[row][col] != 6
									&& tunnel[row][col] != 7) {
								que.offer(new Node(row, col, tunnel[row][col], curr.time + 1));
							}
						}
					}
				}
				break;
			}
		}
	}// bfs

	private static class Node {
		int row, col, hole, time;

		public Node(int row, int col, int hole, int time) {
			this.time = time;
			this.hole = hole;
			this.row = row;
			this.col = col;
		}
	}// Node
}// 끝
