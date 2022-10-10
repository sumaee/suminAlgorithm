import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static List<Integer>[] list;
	static Stack<Integer> stack;
	static boolean[] visited;
	static int[] input;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 학생수
		K = Integer.parseInt(st.nextToken()); // 비교 횟수
		input = new int[N + 1];
		visited = new boolean[N + 1];
		list = new ArrayList[N + 1];
		stack = new Stack<>();
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int sta = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());

			input[ed]++;
			list[sta].add(ed);
		}

		for (int i = 1; i <= N; i++) {
			if (input[i] == 0) {
				topological_sort(i);
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}// main

	private static void topological_sort(int idx) {
		visited[idx] = true;

		for (int nxt : list[idx]) {
			if (!visited[nxt]) {
				topological_sort(nxt);
			}
		}

		stack.push(idx);
	}
}
