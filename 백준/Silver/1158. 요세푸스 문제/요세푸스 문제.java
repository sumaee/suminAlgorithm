import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 사람수
		int K = Integer.parseInt(st.nextToken()); // 뛰어셀 수

		Queue<Integer> que = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		sb.append("<");
		while (que.size() != 1) {
			for (int i = 1; i < K; i++) {
				que.offer(que.poll());
			}
			sb.append(que.poll() + ", ");
		}

		sb.append(que.poll() + ">");

		System.out.println(sb);
	}

}
