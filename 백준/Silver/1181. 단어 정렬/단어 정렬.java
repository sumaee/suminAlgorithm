import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<String> arr = new ArrayList<>();

		//입력을 받을 때부터 중복 없애기
		next: for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (i == 0) {
				arr.add(str);
				continue;
			}
			for (int j = 0; j < arr.size(); j++) {
				if (str.equals(arr.get(j))) {
					continue next;
				}
			}
			arr.add(str);
		}

		Collections.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				} else {
					return o1.length() - o2.length();
				}
			}
		});

		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}

	}
}
