import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		List<Integer> result = new ArrayList<>();

		int max = 0;
		for (int i = N ; i >= 1; i--) {
			list.clear();
			list.add(N); // 0
			list.add(i); // 1
			int idx = 0;

			while (list.get(list.size() - 1) >= 0) {
				list.add(list.get(idx) - list.get(idx + 1));
				idx++;
			}

			if (list.size() > max) {
				result.clear();
				max = list.size()-1;
				for(int k=0; k<max; k++) {
					result.add(list.get(k));
				}
			}

		}
		sb.append(max).append("\n");
		for (int j = 0; j < max; j++) {
			sb.append(result.get(j)).append(" ");
		}
		System.out.println(sb);
	}
}
