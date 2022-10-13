import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		char[] arr = str.toCharArray();

		Stack<Character> stack = new Stack<>();
		boolean flag = false;

		for (int i = 0; i < str.length(); i++) {
			if (arr[i] == '<') {
				flag = true;
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}

				sb.append(arr[i]);
			}

			else if (arr[i] == '>') {
				flag = false;
				sb.append(arr[i]);
			}

			else if (flag) {
				sb.append(arr[i]);
			}

			else if (!flag) {
				if (arr[i] == ' ') {
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(arr[i]);
				}

				else {
					stack.add(arr[i]);
				}
			}

		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
}
