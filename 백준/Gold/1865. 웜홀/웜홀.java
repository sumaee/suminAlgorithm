import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] node;
    static int n, m, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 지점의 수
            m = Integer.parseInt(st.nextToken()); // 도로의 수
            w = Integer.parseInt(st.nextToken()); // 웜홀의 수

            node = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                node[i] = new ArrayList<>();
            }

            //도로 정보 입력 => 도로는 방향이 없음
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                node[start].add(new Node(end, time));
                node[end].add(new Node(start, time));
            }

            //웜홀 정보 입력 => 웜홀은 방향이 있음
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken()) * -1;

                node[start].add(new Node(end, time));
            }

            //벨만 포드 시작


            if (isPossible()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean isPossible() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 987654321);

        dist[1] = 0;
        //정점 개수 -1 만큼 진행하며 최단거리 갱신
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Node nxt : node[j]) {
                    if (dist[j] + nxt.time < dist[nxt.idx]) {
                        dist[nxt.idx] = dist[j] + nxt.time;
                    }
                }
            }
        }

        //한번더 거리 갱신을 시도했을 때 바뀐다면 음의 사이클이 존재
        for (int i = 1; i <= n; i++) {
            for (Node nxt : node[i]) {
                if (dist[i] + nxt.time < dist[nxt.idx]) {
                    return true;
                }
            }
        }

        return false;

    }

    static class Node {
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
