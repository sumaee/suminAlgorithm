import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int studentNum = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        int[][] check = new int[studentNum][studentNum];
        //주어진 것에 대해 알수있는 것 먼저 채우기
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());

            check[small - 1][big - 1] = -1;
            check[big - 1][small - 1] = 1;
        }

        // 경유지
        for (int i = 0; i < studentNum; i++) {
            //출발지
            for (int j = 0; j < studentNum; j++) {
                //도착지
                for (int k = 0; k < studentNum; k++) {
                    if (check[j][i] == -1 && check[i][k] == -1) {
                        check[j][k] = -1;
                        check[k][j] = 1;
                    }

                    if (check[j][i] == 1 && check[i][k] == 1) {
                        check[j][k] = 1;
                        check[k][j] = -1;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < studentNum; i++) {
            int cnt = 0;
            for (int j = 0; j < studentNum; j++) {
                if (check[i][j] != 0)
                    cnt++;
            }
            if (cnt == studentNum - 1) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}