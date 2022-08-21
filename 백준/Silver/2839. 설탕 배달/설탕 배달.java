import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //설탕 무게
		int result = 0; //봉투 개수

		while(N>0) {
			
			//우선 5kg짜리에 한번에 담을 수 있다면 담기
			if(N%5==0) {
				result+=N/5;
				break;
			}else {
				// 5kg에 담을 수 없다면 3kg을 빼줘서 5kg의 배수로 만들어 담음
				N-=3;
				result++;
			}
		}
		
		//N이 음수가 된다면 담을 수 없다는 얘기이므로 -1출력
		if(N<0) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}

	}
}
