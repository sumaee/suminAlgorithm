import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;

    static List<Airfield> airfields;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 비행장 수
        k = Integer.parseInt(st.nextToken()); // 최대 혀용 중간 급유 횟수

        airfields = new ArrayList<>();

        //급유기 좌표 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            airfields.add(new Airfield(x, y, i));
        }

        int min = 0;
        int max = 1500;
        int answer = 0;

        while (min <= max) {
            int mid = (min + max) / 2;

            //해당 연료통으로 목적지까지 갈 수 있다면 연료통 줄이기
            if (bfs(mid)) {
                max = mid - 1;
                answer = mid;
            }

            //갈 수 없다면 연료통 늘리기
            else {
                min = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs(int mid) {
        Queue<Airfield> que = new LinkedList<>();
        visited = new boolean[n + 1];
        int cnt = 0;
        int size = 0;
        que.offer(new Airfield(0, 0, 0));
        while (!que.isEmpty()) {
            size = que.size();

            //경유할 수 있지 않다면 return false
            if (cnt == k + 1) {
                return false;
            }

            //큐안에 저장된 위치에서 갈 수 있는 모든 곳을 확인하기 위한 for 문
            for (int i = 0; i < size; i++) {
                Airfield curr = que.poll();

                //방문했던 곳이라면 패스
                if (visited[curr.idx]) continue;

                visited[curr.idx] = true;
                for (int j = 1; j < n; j++) {
                    //현재 거리와 다음 거리 계산
                    int fuel = (int) Math.ceil(Math.sqrt(Math.pow(curr.x - airfields.get(j).x, 2) + Math.pow(curr.y - airfields.get(j).y, 2)) / 10);

                    //만약 해당 거리를 mid만큼의 연료로 갈 수 있다면
                    if (fuel <= mid) {
                        //경유한 지점에서 도착지까지 바로 갈 수 있는지 확인
                        int fuelToFinish = (int) Math.ceil(Math.sqrt(Math.pow(10000 - airfields.get(j).x, 2) + Math.pow(10000 - airfields.get(j).y, 2)) / 10);

                        //갈수 있다면 바로 return true
                        if (fuelToFinish <= mid) {
                            return true;
                        }

                        //갈 수 없다면 한번 더 경유해야하므로 큐에 넣기
                        que.offer(new Airfield(airfields.get(j).x, airfields.get(j).y, airfields.get(j).idx));
                    }
                }
            }
            //경유했으므로 경유횟수 증가
            cnt++;
        }

        //while문에 안걸렸다면 갈 수 없는 것이므로 return false;
        return false;
    }
}

class Airfield {
    int x, y, idx;

    public Airfield(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}
