import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] mod=new boolean[42];
		int cnt=0;
		for(int i=0; i<10; i++) {
			int a=Integer.parseInt(br.readLine());
			
			mod[a%42]=true;
		}
		
		for(int i=0; i<mod.length; i++) {
			if(mod[i]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
