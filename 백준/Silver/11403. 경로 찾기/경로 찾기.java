import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //플로이드 와샬 시작
        for (int k = 0; k < n; k++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (arr[start][k] == 1 && arr[k][end] == 1) {
                        arr[start][end] = 1;
                    }
                }
            }
        }

        for (int[] row : arr) {
            for (int col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
