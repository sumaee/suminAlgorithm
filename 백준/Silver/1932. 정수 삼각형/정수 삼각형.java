import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                //누적합 마지막줄 채우기
                if (i == n - 1) {
                    sum[i][j] = arr[i][j];
                }
            }
        }

        //거꾸로 진행
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                sum[row][col] = Math.max(sum[row + 1][col], sum[row + 1][col + 1]) + arr[row][col];
            }
        }

        System.out.println(sum[0][0]);
        
    }
}
