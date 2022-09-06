import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			sb.append("Case #").append(t+": ");
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			sb.append(a).append(" + ").append(b).append(" = ").append(a+b).append("\n");
		}
		System.out.println(sb);
	}
}
