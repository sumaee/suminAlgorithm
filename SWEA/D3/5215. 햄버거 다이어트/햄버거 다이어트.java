import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 재료 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			int[] score = new int[N];
			int[] cal=new int[N];
			int result=Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				score[i]=Integer.parseInt(st.nextToken());
				cal[i]=Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<(1<<N); i++) {
				int scoreSum=0;
				int calSum=0;
				for(int j=0; j<N; j++) {
					if((i & (1<<j))>0) {
						calSum+=cal[j];
						scoreSum+=score[j];
					}
				}
				if(calSum<L) {
					result=Math.max(scoreSum, result);
				}
			}
			
			sb.append(result).append("\n");
			
		}//tc
		System.out.println(sb);
	}//main
}
