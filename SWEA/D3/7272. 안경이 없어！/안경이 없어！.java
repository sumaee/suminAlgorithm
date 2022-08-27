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
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());

			char[] eng = { '0', '2', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0',
					'1', '1', '1', '1', '1', '1', '1', '1' };
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			if (str1.length() != str2.length()) {
				sb.append("DIFF").append("\n");
			} else {
				for (int i = 0; i < str1.length(); i++) {

					if ((eng[str1.charAt(i) - 'A'] == '0' && eng[str2.charAt(i) - 'A'] == '0')
							|| (eng[str1.charAt(i) - 'A'] == '1' && eng[str2.charAt(i) - 'A'] == '1'
									|| (eng[str1.charAt(i) - 'A'] == '2' && eng[str2.charAt(i) - 'A'] == '2'))) {
						if(i==str1.length()-1) {
							sb.append("SAME").append("\n");
							break;
						}
						continue;
					} else {
						
						sb.append("DIFF").append("\n");
						break;
					}

				}

			}

		} // tc
		System.out.println(sb);
	}// main
}
