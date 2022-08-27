import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(1);
			return;
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			
			for(int n1=1; n1<=N/2; n1++) {
				for(int n2=n1; n1*n2<=N; n2++) {
					if(n1*n2==(long)i) {
						cnt++;
					}
				}
			}

		}
		System.out.println(cnt);
	}
}
