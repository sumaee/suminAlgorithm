import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//계단오르기 문제(Q.2579)랑 매우 비슷해서 어렵진 않았음
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		
		int[] beer=new int[N+1];
		int[] sum=new int[N+1];
		
		for(int i=1; i<=N; i++) {
			beer[i]=Integer.parseInt(br.readLine());
		}
		
		sum[1]=beer[1];
		if(N>1) {
			sum[2]=beer[1]+beer[2];
		}
				
		for(int i=3; i<=N; i++) {
			sum[i]=Math.max(Math.max(sum[i-2], sum[i-3]+beer[i-1])+beer[i], sum[i-1]);
		}
		
		
		System.out.println(sum[N]);
	}
}
