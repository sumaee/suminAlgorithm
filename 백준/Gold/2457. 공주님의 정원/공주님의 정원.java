import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers.add(new Flower(startMonth, startDay, endMonth, endDay));
        }

        //피는 시기가 빠른 순, 같다면 지는 시기가 늦은 순으로 정렬
        flowers.sort((o1, o2) -> o1.start == o2.start ? o2.end - o1.end : o1.start - o2.start);

        //조건 정리
        int start = 301;
        int endLimit = 1201;
        int end = 0;
        //꽃 피는 시기가 12월 1일인 경우까지만 가능 그 이후는 볼 필요가 없음
        int answer = 0;
        int idx = 0;
        while (start < endLimit) {
            boolean isPossible = false;

            //꽃들을 돌며 시기 확인
            for (int i = idx; i < n; i++) {
                //현재 start보다 개화시기가 작으면 볼 필요가 없음
                if (start < flowers.get(i).start) {
                    break;
                }

                //꽃이 지는 시기가 현재 설정한 것보다 작다면 갱신 -> 최대한 오래 피어있어야하므로 제일 늦은 시간을 정해줌
                if (end < flowers.get(i).end) {
                    isPossible = true;
                    end = flowers.get(i).end;
                    idx = i + 1; // 다음에 확인할 시작점도 갱신
                }
            }

            //만약 꽃을 찾았다면
            if (isPossible) {
                //시작점 갱신해서 다시 while 돌기
                start = end;
                answer++;
            }
            //꽃이 없다면 종료
            else {
                break;
            }
        }

        //만약 마지막 날이 1201보다 작다면 조건을 충족시키지 못하므로 0 출력
        if (end < endLimit) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }

    }

    static class Flower {
        int start, end;

        public Flower(int startMonth, int startDay, int endMonth, int endDay) {
            this.start = startMonth * 100 + startDay;
            this.end = endMonth * 100 + endDay;
        }
    }
}
