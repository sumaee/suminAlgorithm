import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int row;
	int col;
	int day;

	public Node(int row, int col, int day) {
		this.row = row;
		this.col = col;
		this.day = day;
	}

}

public class Main {
	static int M;
	static int N;
	static int[][] tomato;
	static boolean[][] visited;
	static Queue<Node> que = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 가로 수
		N = Integer.parseInt(st.nextToken()); // 세로 수

		tomato = new int[N][M];
		visited = new boolean[N][M];
		result = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1) {
					que.add(new Node(i, j, 0));
					visited[i][j] = true;
				}
			}
		}

		bfs();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(result);

	}// main

	public static void bfs() {

		while (!que.isEmpty()) {
			Node curr = que.poll();
			for (int i = 0; i < 4; i++) {
				int row = curr.row + dr[i];
				int col = curr.col + dc[i];
				
				if (row >= 0 && col >= 0 && row < N && col < M && tomato[row][col] == 0) {
					visited[row][col] = true;
					tomato[row][col] = curr.day+1;
					que.offer(new Node(row, col, curr.day + 1));
				}
			}

			result = Math.max(result, curr.day);

		}
	}
}
