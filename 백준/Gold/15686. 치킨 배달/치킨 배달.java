import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class locate {
	int row;
	int col;

	public locate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	// 0 : 빈칸, 1 : 집, 2 : 치킨집
	static int N;
	static int M;
	static int[][] city;
	static List<locate> home;
	static List<locate> chicken;
	static Stack<locate> list;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());// 도시 크기
		M = Integer.parseInt(st.nextToken()); // 고를 치킨집 개수

		city = new int[N][N];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		list = new Stack<locate>();
		answer = Integer.MAX_VALUE;

		// 도시 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());

				// 치킨집의 위치 저장하면서 집, 치킨집 위치 받기
				if (city[i][j] == 1) {
					home.add(new locate(i, j));
				} else if (city[i][j] == 2) {
					chicken.add(new locate(i, j));
				}
			}
		}

		findMin(0, 0);
		System.out.println(answer);

	}// main

	public static void findMin(int idx, int sidx) {

		// 해당 치킨집수가 되면 가장가까운 거리에 있는 집과 치킨집 사이의 거리를 구함
		if (list.size() == M) {
			int min = 0;
			for (int i = 0; i < home.size(); i++) {
				int hr = home.get(i).row;
				int hc = home.get(i).col;
				int dis = Integer.MAX_VALUE;

				for (int j = 0; j < list.size(); j++) {
					int cr = list.get(j).row;
					int cc = list.get(j).col;

					// 가장 가까운 치킨집의 거리를 dis에 저장
					dis = Math.min(dis, Math.abs(cr - hr) + Math.abs(cc - hc));
				}
				// 한집에 대해 가장 가까운 치킨집을 골랐으므로 min에 더해줌
				min += dis;
			}

			// 각 집을 돌며 M개의 치킨집 중 가장 가까운 거리를
			// min에 더해줬으므로 answer에 비교하며 저장
			answer = Math.min(answer, min);
			return;
		}

		for (int i = idx; i <= chicken.size() - M + sidx; i++) {
			list.push(chicken.get(i));
			findMin(i + 1, sidx + 1);
			list.pop();
		}

	}// findMIn
}
