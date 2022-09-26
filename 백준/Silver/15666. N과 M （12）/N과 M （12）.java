import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] temp;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		temp = new int[M];
		boolean[] check = new boolean[10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			if (!check[a]) {
				check[a] = true;
				list.add(a);
			}
		}

		Collections.sort(list);
		perm(0, 0);
	}// main

	public static void perm(int idx, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(temp[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = idx; i < list.size(); i++) {
				temp[cnt] = list.get(i);
				perm(i, cnt+1);
		}
	}
}
