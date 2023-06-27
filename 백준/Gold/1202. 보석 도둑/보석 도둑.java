import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 보석의 수
        int k = Integer.parseInt(st.nextToken()); // 가방의 수

        //보석을 담을 리스트
        List<Jewel> jewels = new ArrayList<>();

        //가방을 저장할 리스트
        List<Integer> bags = new ArrayList<>();

        //보석 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(w, v));
        }
        //보석 정렬 -> 무게가 가벼운 거부터 정렬 -> 무게가 같다면 가격이 높은 거 순으로 정렬
        jewels.sort((o1, o2) -> o1.m == o2.m ? o2.v - o1.v : o1.m - o2.m);

        //가방 저장
        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        //가방 정렬 가벼운가방부터확인
        bags.sort((o1, o2) -> o1 - o2);

        PriorityQueue<Integer> putJewel = new PriorityQueue<>((o1, o2) -> o2 - o1);

        //가방을 돌면서 해당 가방에 넣을 수 있는 보석들 다 집어넣기
        int idx = 0;
        long answer = 0;
        for (int bag : bags) {
            //해당 가방의 무게보다 작은 보석들을 전부 넣기
            while (idx < n && jewels.get(idx).m <= bag) {
                putJewel.offer(jewels.get(idx++).v);
            }

            //큐가 비어 있지 않다면 해당 위치의 큐 중 제일 큰 값 뽑아내기
            if (!putJewel.isEmpty()) {
                answer += putJewel.poll();
            }
        }
        System.out.println(answer);

    }
}

class Jewel {
    int m, v; // 무게, 가격

    public Jewel(int m, int v) {
        this.m = m;
        this.v = v;
    }
}