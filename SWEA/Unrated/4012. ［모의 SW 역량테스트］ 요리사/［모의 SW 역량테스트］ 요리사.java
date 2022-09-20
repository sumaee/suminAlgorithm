import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N;
    static int[][] food;
    static boolean[] check;
    static int min;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test_case; tc++) {
            sb.append("#" + tc + " ");
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            food = new int[N][N];
            check = new boolean[N];
            // 음식 배열 채우기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    food[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            comb(0, 0);
            sb.append(min).append("\n");
        } // tc
        System.out.println(sb);
    }// main
 
    static void comb(int idx, int sidx) {
        // 개수만큼 묶였을 때
        if (idx == N / 2) {
            int sum1 = 0;
            int sum2 = 0;
            int answer = 0;
 
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (check[i] && check[j]) {
                        sum1 += food[i][j] + food[j][i];
                    }
 
                    else if (!check[i] && !check[j]) {
                        sum2 += food[i][j] + food[j][i];
                    }
                }
            }
            answer = Math.abs(sum1 - sum2);
            min = Math.min(answer, min);
            return;
        }
 
        for (int i = sidx; i < N; i++) {
            check[i] = true;
            comb(idx + 1, i + 1);
            check[i] = false;
        }
 
    }
}