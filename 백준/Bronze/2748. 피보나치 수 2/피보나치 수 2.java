import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		long n1=0;
		long n2=1;
		long[]num=new long[n+1];
		num[0]=0;
		num[1]=1;
		for(int i=2; i<=n; i++) {
			num[i]=n1+n2;
			n1=n2;
			n2=num[i];
			
		}
		
		
		System.out.println(num[n]);
		
	}
}
