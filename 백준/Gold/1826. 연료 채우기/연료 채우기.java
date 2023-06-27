import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        //거리가 가까운 주유소부터 출력
        PriorityQueue<Gas> gases = new PriorityQueue<>((o1, o2) -> o1.dis - o2.dis);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dis = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());

            gases.offer(new Gas(dis, fuel));
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        //연료가 많은 순으로 뽑히게끔 출력
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //현재 연료로 도착지점이 될때까지 반복
        int answer = 0;
        while (p < l) {
            //현재 거리에서 갈 수 있는 범위에 있는 주유소들을 que에 넣음
            while (!gases.isEmpty() && gases.peek().dis <= p) {
                que.offer(gases.poll().fuel);
            }

            //갈수 있는 주유소가 없다면 -1 출력
            if (que.isEmpty()) {
                System.out.println(-1);
                return;
            }

            //갈수 있는 주유소 중에서 연료가 제일 많은 주유소를 가서 연료 넣기
            answer++;
            p += que.poll();
        }

        System.out.println(answer);
    }
}

class Gas {
    int dis, fuel;

    public Gas(int dis, int fuel) {
        this.dis = dis;
        this.fuel = fuel;
    }
}