import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// A 배열 담기
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M1 = Integer.parseInt(st.nextToken());
		// N*M 행렬
		int[][] arrA = new int[N][M1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M1; j++) {
				arrA[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// B 배열 담기
		st = new StringTokenizer(br.readLine());
		int M2 = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// M*K 행렬
		int[][] arrB = new int[M2][K];
		for (int i = 0; i < M2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				arrB[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < K; j++) {
				int sum = 0;
				for (int k = 0; k < M1; k++) {
					sum += arrA[i][k] * arrB[k][j];
				}

				sb.append(sum + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}// main
}
