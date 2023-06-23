import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static List<Integer>[] computers;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int v = Integer.parseInt(br.readLine()); // 연결 간선 수

        //초기 설정
        visited = new boolean[n + 1];
        computers = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            computers[i] = new ArrayList<>();
        }

        //연결 정보 받기
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            computers[first].add(second);
            computers[second].add(first);
        }
        answer = 0;
        findVirus(1);
        System.out.println(answer);
    }

    private static void findVirus(int com) {
        visited[com] = true;

        for (int i = 0; i < computers[com].size(); i++) {
            int curr = computers[com].get(i);
            if (!visited[curr]) {
                findVirus(curr);
                answer++;
            }
        }
    }
}
