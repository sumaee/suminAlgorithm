import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test_case; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
 
            int N = Integer.parseInt(st.nextToken()); // 행의 수
            int M = Integer.parseInt(st.nextToken()); // 열의 수
            String[] arr = new String[N];
            // flag[][0] : 흰 개수, flag[][1]: 파랑 개수, flag[][2]: 빨강 개수
            char[][] flag = new char[N][3];
 
            // 깃발 채우기
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
                for (int j = 0; j < M; j++) {
                    if (arr[i].charAt(j) == 'W') {
                        flag[i][0]++;
                    } else if (arr[i].charAt(j) == 'B') {
                        flag[i][1]++;
                    } else {
                        flag[i][2]++;
                    }
                }
 
            }
            //바뀌는 최대의 값이 N*M이므로 이와 밑의 for문에서의 cnt와 비교하여 result 설정
            int result=N*M;
 
            // 흰색이 들어갈 수 있는 최대 줄은 N-2만큼
            for (int w = 0; w < N - 2; w++) {
                // 파랑색이 들어갈 수 있는 개수는 흰색 다음줄부터 N-1까지
                // 빨간색은 자동으로 N까지 채워짐
                for (int b = w + 1; b < N - 1; b++) {
                    //바꿔야 하는 칸의 개수 세기
                    int cnt=0;
                     
                    //흰색이 들어가야 하는 줄에 빨간색과 파란색의 개수를 세기
                    for(int white=0; white<=w; white++) {
                        cnt+=flag[white][1];
                        cnt+=flag[white][2];
                    }
                     
                    //파랑색이 들어가야 하는 줄에 흰색과 빨간색 개수 세기
                    for(int blue=w+1; blue<=b; blue++) {
                        cnt+=flag[blue][0];
                        cnt+=flag[blue][2];
                    }
                     
                    //빨간색은 파랑색 다음으로 쭉
                    for(int red=b+1; red<=N-1; red++) {
                        cnt+=flag[red][0];
                        cnt+=flag[red][1];
                    }
                     
                    result=Math.min(cnt,  result);
                     
                     
                }
            }
            sb.append(result).append("\n");
        } // tc
        System.out.println(sb);
    }// main
}