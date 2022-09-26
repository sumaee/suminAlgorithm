import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class a {
	int x;
	int y;

	public a(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<a> arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr.add(new a(x, y));
		}

		Collections.sort(arr, new Comparator<a>() {
			@Override
			public int compare(a o1, a o2) {
				if (o1.y == o2.y) {
					return o1.x - o2.x;
				} else {
					return o1.y - o2.y;
				}
			}
		});

		for (int i = 0; i < N; i++) {
			System.out.println(arr.get(i).x + " " + arr.get(i).y);
		}
	}
}
