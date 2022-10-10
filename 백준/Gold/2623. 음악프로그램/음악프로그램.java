import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static boolean[] check;
	static int[] input;
	static List<Integer>[] list;
	static Stack<Integer> stack;
	static int N, M;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 가수의 수
		M = Integer.parseInt(st.nextToken()); // 보조 PD의 수

		list = new ArrayList[N + 1];
		input = new int[N + 1];
		visited = new boolean[N + 1];
		check = new boolean[N + 1];
		flag = true;

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 가수 명수

			int start = Integer.parseInt(st.nextToken()); // 시작
			for (int j = 0; j < num - 1; j++) {
				int end = Integer.parseInt(st.nextToken());

				list[start].add(end);
				input[end]++;
				start = end;
			}
		}

		// 위상정렬 시작
		stack = new Stack<>();
		for (int i = 1; i <= N; i++) {

			if (!flag) {
				System.out.println(0);
				return;
			}

			if (input[i] == 0) {
				topological_sort(i);
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop() + "\n");
		}
		System.out.println(sb);
	}// main

	private static void topological_sort(int idx) {

		visited[idx] = true;

		for (int nxt : list[idx]) {
			if (!visited[nxt]) {
				topological_sort(nxt);
			}

			// 이미 방문했는데 스택에 안들어간거면 사이클 존재
			else if (!check[nxt]) {
				flag = false;
				return;
			}
		}
		check[idx] = true;
		stack.add(idx);
	}
}
