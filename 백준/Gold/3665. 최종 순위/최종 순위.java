import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] input;
    static int[][] arr;
    static int n;
    static StringBuilder sb, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ans = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine()); // 팀의 수

            input = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            arr = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                int team = Integer.parseInt(st.nextToken());
                //등수 대로 진입 차수 만들기
                input[team] = i;

                //간선 정리
                for (int j = 1; j <= n; j++) {
                    if (j != team && arr[j][team] == 0) {
                        arr[team][j] = 1;
                    }
                }
            }
            //순위 변경
            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int team1 = Integer.parseInt(st.nextToken());
                int team2 = Integer.parseInt(st.nextToken());

                change(team1, team2);
            }

            //위상정렬 시작
            ans.append(topologicalSort()).append("\n");
        }
        System.out.println(ans);
    }

    private static String topologicalSort() {
        Queue<Integer> que = new LinkedList<>();
        sb = new StringBuilder();
        //간선이 0인 팀 넣어주기
        for (int i = 1; i <= n; i++) {
            if (input[i] == 0) que.offer(i);
        }

        //팀 수만큼 실행
        for (int i = 1; i <= n; i++) {
            //만약 꺼낼 팀이 없으면 순위 파악이 불가인것
            if (que.isEmpty()) {
                return "IMPOSSIBLE";
            }

            //만약 큐에 두 팀 이상이 들어간다면 그 두 팀을 순위 파악이 정확하지 않은 것
            if (que.size() > 1) {
                return "?";
            }

            int curr = que.poll();
            sb.append(curr).append(" ");

            for (int j = 1; j <= n; j++) {
                if (arr[curr][j] == 1) {
                    arr[curr][j] = 0;
                    input[j]--;

                    if (input[j] == 0) que.offer(j);
                }
            }
        }

        return sb.toString();
    }

    private static void change(int team1, int team2) {
        //arr[team1][team2] 가 0이라면 작년에는 team1이 team2 팀보다 순위가 낮은 것이므로 바꿔줌
        if (arr[team1][team2] == 0) {
            arr[team1][team2] = 1;
            arr[team2][team1] = 0;

            input[team1]--;
            input[team2]++;
        }

        //arr[team1][team2]가 1이라면 작년에는 team1이 team2보다 순위가 높은 것이기 때문에 바꿔줌
        else {
            arr[team1][team2] = 0;
            arr[team2][team1] = 1;

            input[team1]++;
            input[team2]--;
        }
    }
}
