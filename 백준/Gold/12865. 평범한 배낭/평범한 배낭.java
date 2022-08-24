import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken()); // 물품의 수
		int K=Integer.parseInt(st.nextToken()); // 버틸수 있는 무게
		
		int[][] dp=new int[N+1][K+1];
		int[] W=new int[N+1]; // 물건의 무게
		int[] V=new int[N+1]; // 물건의 가치
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			W[i]=Integer.parseInt(st.nextToken());
			V[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1 ;j<=K; j++) {
				
				if(j>=W[i]) {
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-W[i]]+V[i]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
		br.close();
		
		
	}//main
}
