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


        char[][] aArr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                aArr[i][j] = line.charAt(j);
            }
        }

        char[][] bArr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                bArr[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {

                if (aArr[i][j] != bArr[i][j]) {
                    for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            aArr[k][l] ^= 1;
                        }
                    }

                    answer++;
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (aArr[i][j] != bArr[i][j]) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }
}