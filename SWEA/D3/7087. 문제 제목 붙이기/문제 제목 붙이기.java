import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			List<String> list = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(br.readLine());
			}
			
			boolean[] check=new boolean[26];
			int idx = 65;
			int result = 0;
			for (int i = 0; i < N; i++) {
				check[(int) list.get(i).charAt(0)-65]=true;
			
			}
			
			for(int i=0; i<26; i++) {
				if(!check[i]) {
					break;
				}else {
					result++;
				}
			}
			sb.append(result + "\n");

		}
		System.out.println(sb);
	}
}
