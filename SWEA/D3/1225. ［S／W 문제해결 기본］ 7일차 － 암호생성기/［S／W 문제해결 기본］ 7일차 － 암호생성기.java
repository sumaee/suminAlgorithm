import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = 10;
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			int T = Integer.parseInt(br.readLine());

			Queue<Integer> num = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				num.add(Integer.parseInt(st.nextToken()));
			}
			
		good:	while(true) {
			
				for(int i=1; i<=5;i++) {
					if(num.peek()-i<=0) {
						num.poll();
						num.add(0);
						break good;
					}
					num.add(num.poll()-i);
					
				}
			}
			
			for(int i=0; i<8; i++) {
				sb.append(num.poll()+" ");
				
			}
			sb.append("\n");
		}//tc
		System.out.println(sb);
	}//main
}