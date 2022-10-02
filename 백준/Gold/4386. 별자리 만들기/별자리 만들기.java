import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int n;
	static double[][] star;
	static boolean[] visited;
	static double[] dist;
	static double result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 별의 개수
		visited = new boolean[n];// 연결 유무를 확인할 방문처리용도
		star = new double[n][2];
		dist = new double[n];
		result = 0;

		Arrays.fill(dist, INF);

		// 별의 위치 저장
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			star[i][0] = x;
			star[i][1] = y;
		}
		prim(0);
		System.out.printf("%.2f", result);
	}// main

	private static void prim(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.len, o2.len);
			}

		});
		// 첫번째 별자리 넣어주기
		que.offer(new Node(start, 0));

		while (!que.isEmpty()) {
			Node curStar = que.poll();

			if (visited[curStar.idx]) {
				continue;
			}

			visited[curStar.idx] = true;
			dist[curStar.idx] = curStar.len;
			result += Math.sqrt(dist[curStar.idx]);

			for (int i = 0; i < star.length; i++) {
				if (!visited[i]) {
					double len = Math.pow(star[curStar.idx][0] - star[i][0], 2)
							+ Math.pow(star[curStar.idx][1] - star[i][1], 2);
					if (dist[i] > len) {
						dist[i] = len;
						que.offer(new Node(i, len));
					}
				}
			}
		}
	}// prim

	private static class Node {
		int idx;
		double len;

		public Node(int idx, double len) {
			this.idx = idx;
			this.len = len;
		}

	}
}
