import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];
        int len = Math.min(m, n);

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        while (len > 1) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= m - len; j++) {
                    char num = arr[i][j];

                    if (arr[i][j + len - 1] == num && arr[i + len - 1][j] == num && arr[i + len - 1][j + len - 1] == num) {
                        System.out.println(len * len);
                        return;
                    }
                }
            }

            len--;
        }
        System.out.println(1);
    }
}