import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int M, A, result;
	static int[] Amove;
	static int[] Bmove;
	static List<BC> battery;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); // 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC개수

			Amove = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Amove[i] = Integer.parseInt(st.nextToken());
			}

			Bmove = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Bmove[i] = Integer.parseInt(st.nextToken());
			}

			battery = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				battery.add(new BC(x, y, size, power));
			}

			Collections.sort(battery, new Comparator<BC>() {
				@Override
				public int compare(BC o1, BC o2) {
					return o2.power - o1.power;
				}
			});
			result=0;
			// 초기값 먼저 하기
			locate perA = new locate(1, 1);
			locate perB = new locate(10, 10);
			charge(perA, perB);

			// 움직이기
			for (int i = 0; i < M; i++) {
				perA = move(Amove[i], perA.x, perA.y);
				perB = move(Bmove[i], perB.x, perB.y);
				charge(perA, perB);
			}
			sb.append(result + "\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void charge(locate perA, locate perB) {
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			if (battery.get(i).size >= Math.abs(battery.get(i).x - perA.x) + Math.abs(battery.get(i).y - perA.y)) {
				listA.add(i);
			}

			if (battery.get(i).size >= Math.abs(battery.get(i).x - perB.x) + Math.abs(battery.get(i).y - perB.y)) {
				listB.add(i);
			}
		}

		int max = 0, tmp = 0;

		if (listA.size() > 0 && listB.size() > 0) {
			for (int a : listA) {
				for (int b : listB) {
					tmp = 0;

					// 같은 충전기 공유
					if (a == b) {
						tmp = battery.get(a).power;
					} else {
						tmp += battery.get(a).power;
						tmp += battery.get(b).power;
					}

					max = Math.max(max, tmp);
				}
			}
		} else if (listA.size() > 0) {
			for (int a : listA) {
				if (max < battery.get(a).power) {
					max = battery.get(a).power;
				}
			}
		} else if (listB.size() > 0) {
			for (int b : listB) {
				if (max < battery.get(b).power) {
					max = battery.get(b).power;
				}
			}
		}

		result += max;

	}// charge

	private static locate move(int move, int x, int y) {

		switch (move) {
		case 1:
			y--;
			break;

		case 2:
			x++;
			break;

		case 3:
			y++;
			break;

		case 4:
			x--;
			break;
		}

		return new locate(x, y);

	}// move

	private static class locate {
		int x, y;

		public locate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}// locate

	private static class BC {
		int power, size, x, y;

		public BC(int x, int y, int size, int power) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.power = power;
		}
	}// BC
}
