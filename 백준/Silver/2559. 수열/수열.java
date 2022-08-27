import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());//측정일수
		int K=Integer.parseInt(st.nextToken());//연속 날짜 수
		
		int[] temper=new int[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			temper[i]=Integer.parseInt(st.nextToken());
		}
		
		int max=Integer.MIN_VALUE;
		for(int i=0; i<=N-K;i++) {
			int sum=0;
			for(int j=i; j<K+i; j++) {
				sum+=temper[j];
			}
			if(sum>max) {
				max=sum;
			}
		}
		
		System.out.println(max);
	}
}
