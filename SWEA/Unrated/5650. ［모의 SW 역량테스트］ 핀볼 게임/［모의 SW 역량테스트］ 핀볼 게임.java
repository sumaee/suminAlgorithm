import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, answer;
	static int[][] board;
	static Map<Integer, Node[]> wormhole;
	static Queue<Node> start;
	static int direct;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine()); // 게임판 크기
			board = new int[N][N];
			start = new LinkedList<>();
			wormhole = new HashMap<>();
			answer = 0;
			// 게임 판 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					// 출발지 리스트에 넣어주기
					if (board[i][j] == 0) {
						start.offer(new Node(i, j));
					} else if (board[i][j] >= 6) {
						if (!wormhole.containsKey(board[i][j])) {
							wormhole.put(board[i][j], new Node[2]);
							wormhole.get(board[i][j])[0] = new Node(i, j);
						} else {
							wormhole.get(board[i][j])[1] = new Node(i, j);
						}
					}
				}
			}

			check();
			sb.append(answer).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void check() {
		// 출발지 리스트를 돌며 확인하기
		while (!start.isEmpty()) {
			// 출발지 뽑기
			Node curr = start.poll();

			// 방향 정해서 출발
			// 방향 순서 : 위 오 아 왼
			for (int i = 0; i < 4; i++) {
				int r = curr.row + dr[i];
				int c = curr.col + dc[i];
				direct = i;
				move(curr.row, curr.col, r, c);
			}
		}
	}// check

	private static void move(int sr, int sc, int nr, int nc) {
		// 점수 기록할 것
		int cnt = 0;
		// 원래 자리로 돌아오거나 블랙홀을 만난다면 끝
		outer : while (!(nr == sr && nc == sc)) {

			// 다음 칸이 벽일 경우 진행방향의 반대로 진행하고 점수 카운트
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				change(5);
				nr += dr[direct];
				nc += dc[direct];
				cnt++;
				continue;
			}

			switch (board[nr][nc]) {

			// 블록이 없으므로 그방향 그대로 진행
			case 0:
				nr += dr[direct];
				nc += dc[direct];
				break;

			// 블랙홀일 경우 그만
			case -1:
				break outer;

			// 웜홀일 경우 위치를 바꿔 진행
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				int tmpr = nr;
				int tmpc = nc;
				if (nr == wormhole.get(board[tmpr][tmpc])[0].row && nc == wormhole.get(board[tmpr][tmpc])[0].col) {
					nr = wormhole.get(board[tmpr][tmpc])[1].row;
					nc = wormhole.get(board[tmpr][tmpc])[1].col;
				} else {
					nr = wormhole.get(board[tmpr][tmpc])[0].row;
					nc = wormhole.get(board[tmpr][tmpc])[0].col;
				}
				nr += dr[direct];
				nc += dc[direct];
				break;

			// 블록들을 만날 경우 점수 획득
			default:
				change(board[nr][nc]);
				nr += dr[direct];
				nc += dc[direct];
				cnt++;
				break;
			}

		}

		// while문이 끝났다는 것은 블랙홀은 만나거나 제자리로 왔다는 것
		// 점수 갱신
		answer = Math.max(answer, cnt);
	}// move

	private static void change(int block) {
		switch (block) {
		// 1번블록을 어떠한 방향으로 만났는지에 대한 이동 방향 변경
		case 1:

			if (direct == 0) { // 이동 방향이 위로 진행하고 있었을 경우
				direct = 2;
			} else if (direct == 1) { // 이동방향이 오른쪽이었을 경우
				direct = 3;
			} else if (direct == 2) { // 이동방향이 아래였을 경우
				direct = 1;
			} else if (direct == 3) { // 이동방향이 왼쪽이었을 경우
				direct = 0;
			}

			break;

		case 2:
			if (direct == 0) { // 이동 방향이 위로 진행하고 있었을 경우
				direct = 1;
			} else if (direct == 1) { // 이동방향이 오른쪽이었을 경우
				direct = 3;
			} else if (direct == 2) { // 이동방향이 아래였을 경우
				direct = 0;
			} else if (direct == 3) { // 이동방향이 왼쪽이었을 경우
				direct = 2;
			}

			break;

		case 3:
			if (direct == 0) { // 이동 방향이 위로 진행하고 있었을 경우
				direct = 3;
			} else if (direct == 1) { // 이동방향이 오른쪽이었을 경우
				direct = 2;
			} else if (direct == 2) { // 이동방향이 아래였을 경우
				direct = 0;
			} else if (direct == 3) { // 이동방향이 왼쪽이었을 경우
				direct = 1;
			}

			break;

		case 4:
			if (direct == 0) { // 이동 방향이 위로 진행하고 있었을 경우
				direct = 2;
			} else if (direct == 1) { // 이동방향이 오른쪽이었을 경우
				direct = 0;
			} else if (direct == 2) { // 이동방향이 아래였을 경우
				direct = 3;
			} else if (direct == 3) { // 이동방향이 왼쪽이었을 경우
				direct = 1;
			}

			break;

		case 5:
			direct = (direct + 2) % 4;
			break;
		}// switch
	}// change

	private static class Node {
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}// Node
}
