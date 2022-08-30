import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합으로 구해보기
// 벌이 오른쪽으로 이동할 경우의 누적합 1
// 벌이 왼쪽으로 이동할 경우의 누적합 2

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long[] bee = new long[N];

		// 누적합을 구할 배열
		long[] honeyRight = new long[N]; // 왼쪽 끝에 벌, 오른쪽에 꿀통이 있을 때
		long[] honeyLeft = new long[N]; // 오른쪽 끝에 벌, 왼쪽에 꿀통이 있을 때
		int right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bee[i] = Integer.parseInt(st.nextToken());

			right += bee[i];
			honeyRight[i] = right;
		}
		
		int left=0;
		for (int i = N-1; i >=0; i--) {
			left+=bee[i];
			honeyLeft[i] +=left;
		}
		
		// 1. 벌은 왼쪽, 벌통은 오른쪽으로 양끝에 고정시킨 후 벌 하나를 움직이는 경우
		// 2. 벌은 오른쪽, 벌통은 왼쪽으로 양끝에 고정시킨 후 벌 하나를 움직이는 경우
		// 3. 벌이 양끝에 고정되어있고 벌통이 움직이는 경우
		
		// 1번 케이스
		long result=Integer.MIN_VALUE;
		for(int i=1; i<N-1; i++) {
			
			long bee1=honeyRight[N-1]-bee[0]-bee[i];
			
			long bee2=honeyRight[N-1]-honeyRight[i];
			
			result=Math.max(bee1+bee2, result);
		}
		
		// 2번 케이스
		for(int i=N-2; i>=1; i--) {
			
			long bee1=honeyLeft[0]-bee[N-1]-bee[i];
			
			long bee2=honeyLeft[0]-honeyLeft[i];
			
			result=Math.max(bee1+bee2, result);
		}
		
		//3번 케이스
		for(int i=1; i<N-1; i++) {
			
			long bee1=honeyRight[i]-bee[0];
			
			long bee2=honeyLeft[i]-bee[N-1];
			
			result=Math.max(bee1+bee2, result);
		}
		
		System.out.println(result);
	}
}
