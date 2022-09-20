import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 크기
		arr = new int[N][N];

		// 배열 정리
		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			char[] data = a.toCharArray();
			for (int j = 0; j < N; j++) {
				arr[i][j] = data[j] - '0';
			}
		}
		check(0, 0, N);
		System.out.println(sb);
	}// main

	static void check(int row, int col, int size) {
		int cnt=sum(row, col, size);
		if (cnt == (size * size)) {
			sb.append(1);
            return;
		} else if (cnt == 0) {
			sb.append(0);
            return;
		} 
			sb.append("(");
			check(row, col, size / 2);
			check(row, col + size / 2, size / 2);
			check(row + size / 2, col, size / 2);
			check(row + size / 2, col + size / 2, size / 2);
			sb.append(")");

		

	}
    
    static int sum(int row, int col, int size){
        int cnt=0;
        for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				cnt += arr[i][j];
			}
		}
        
        return cnt;
    }
}
