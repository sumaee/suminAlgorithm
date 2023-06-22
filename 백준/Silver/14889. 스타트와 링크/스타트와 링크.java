import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static boolean[] visited;
    static int[] num;
    static int n;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        //순열을 파악할 사람 번호 리스트
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = i;
        }

        //표를 선언
        arr = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //조합으로 팀 나누기
        answer = Integer.MAX_VALUE;
        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int idx, int cnt) {
        if (cnt == n / 2) {
            int teamStart = 0;
            int teamLink = 0;
            //수 구하기
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        teamStart += (arr[i][j] + arr[j][i]);
                    } else if (!visited[i] && !visited[j]) {
                        teamLink += (arr[i][j] + arr[j][i]);
                    }
                }
            }
            
            //두팀 사이의 차이
            int diff = Math.abs(teamStart - teamLink); 
            
            //만약 answer이 0인걸 찾았으면 더 볼 필요가 없음
            if (diff == 0) {
                System.out.println(diff);
                System.exit(0);
            }

            //최솟값 구하기
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i + 1, cnt + 1);
                visited[i] = false;
            }
        }

    }
}
