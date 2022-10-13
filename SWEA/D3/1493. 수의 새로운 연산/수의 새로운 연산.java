import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Node num1 = findXY(a);
			Node num2 = findXY(b);

			Node result = new Node((num1.x + num2.x), (num1.y + num2.y));

			sb.append(cal(result) + "\n");
		}
		System.out.println(sb);
	}// main

	private static int cal(Node node) {
		int count = 1;
		for (int i = 1;; i++) {
			for (int x = 1, y = i; x <= i; x++, y--) {
				if (x == node.x && y == node.y) {
					return count;
				}
				count++;
			}
		}
	}// cal

	private static Node findXY(int num) {
		int count = 1;
		for (int i = 1;; i++) {
			for (int x = 1, y = i; x <= i; x++, y--) {
				if (count == num) {
					return new Node(x, y);
				}
				count++;
			}
		}
	}// findXY

	private static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
