import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 거꾸로 올라오면서 최댓값과 해당 자리의 합 구해서 check 배열에 채우기
	static int N;
	static int[][] arr;
	static int[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		check = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// check 배열 마지막 줄 채우기
		for (int i = 0; i < N; i++) {
			check[N - 1][i] = arr[N - 1][i];
		}

		// 맨 위부터 찾기 시작이지만 사실상 아래서부터 채우기가 됨
		int result = findMax(0, 0);
		
		System.out.println(result);

	}// main

	static int findMax(int row, int col) {
        //만약 마지막 행이라면 해당 행을 보냄
		if (row == N - 1) {
			return check[row][col];
		}
		
		//값이 채워져 있지 않은 경우라면 다음 행의 두개를 비교하여 큰 값과 더한값을 채우기
		if(check[row][col]==0) {
			check[row][col]=Math.max(findMax(row+1, col), findMax(row+1, col+1))+arr[row][col];
		}
		return check[row][col];
	}
}
