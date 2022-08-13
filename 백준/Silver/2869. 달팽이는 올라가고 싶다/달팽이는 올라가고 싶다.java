import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine()," ");
		
		int A=Integer.parseInt(st.nextToken());
		int B=Integer.parseInt(st.nextToken());
		int V=Integer.parseInt(st.nextToken());
		
		int day = (V - B) / (A - B);
		int m=(V - B) % (A - B);
		
		if(m!=0) {
			day++;
		}
		
		System.out.println(day);

	}

}
