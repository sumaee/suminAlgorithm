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
			sb.append("#").append(tc).append("\n");
			
			int N=Integer.parseInt(br.readLine());
			int[][] arr=new int[N][N];
			
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				
				for(int dr=0; dr<N; dr++) {
					sb.append(arr[N-1-dr][i]);
				}
				sb.append(" ");
				for(int dr=N-1; dr>=0; dr--) {
					sb.append(arr[N-1-i][dr]);
				}
				sb.append(" ");
				for(int dr=0; dr<N; dr++) {
					sb.append(arr[dr][N-1-i]);
				}
				sb.append("\n");
			}
			
		} // tc
		System.out.println(sb);
	}// main
}
