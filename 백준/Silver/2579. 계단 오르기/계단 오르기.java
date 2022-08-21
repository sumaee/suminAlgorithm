import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int stairNum = Integer.parseInt(br.readLine());

		int[] sum = new int[301]; // 합의 최댓값을 적을 배열
		int[] stair = new int[301]; // 계단에 적힌 점수를 적을 배열

		// 계단 채우기
		for (int i = 1; i <= stairNum; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
        //계단 첫칸과 두번째 칸은 채워놓기
		sum[1] = stair[1];
		sum[2] = stair[1] + stair[2];
        //도착지점&전칸을 밟아 이동하는 것과 한칸 건너뛰어 이동하는 것중
        // 누가 누적합이 크냐의 싸움
		for (int i = 3; i <= stairNum; i++) {
			sum[i] = Math.max(sum[i - 2], sum[i - 3] + stair[i - 1]) + stair[i];
		}
		
		System.out.println(sum[stairNum]);
	}
}
