import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, answer;
    static EggInfo[] eggInfos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 계란의 개수

        eggInfos = new EggInfo[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            eggInfos[i] = new EggInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //맨 왼쪽의 계란 부터 시작
        findAnswer(0, 0);
        System.out.println(answer);
    }

    private static void findAnswer(int idx, int cnt) {
        //맨 마지막 계란이라면 갱신
        if (idx == n) {
            answer = Math.max(cnt, answer);
            return;
        }

        //손에 든 계란의 내구도가 0이하거나 깨지지 않은 다른 계란이 없으면
        if (eggInfos[idx].s <= 0 || cnt == n - 1) {
            findAnswer(idx + 1, cnt);
            return;
        }

        //깨지지 않은 다른 계란 중 하나를 친다
        int temp = cnt;
        for (int i = 0; i < n; i++) {
            //만약 들고 있는 계란과 같은 위치거나 치려는 계란이 이미 깨져있다면
            if (i == idx || eggInfos[i].s <= 0) continue;

            //계란 깨기
            eggInfos[idx].s -= eggInfos[i].w; // 현재 들고 있는 계란의 내구도 낮추기
            eggInfos[i].s -= eggInfos[idx].w; // 바닥에 있는 계란의 내구도 낮추기

            //만약 손에 들고 있던게 깨졌으면 cnt 증가
            if (eggInfos[idx].s <= 0) cnt++;
            //만약 바닥에 있던게 깨졌으면 cnt 증가
            if (eggInfos[i].s <= 0) cnt++;
            
            //다음 단계 진행
            findAnswer(idx + 1, cnt);

            //새로운 것을 확인하기 위해 깨뜨린 계란 원상복구
            eggInfos[idx].s += eggInfos[i].w; // 현재 들고 있는 계란의 내구도 복구
            eggInfos[i].s += eggInfos[idx].w; // 바닥에 있는 계란의 내구도 복구
            cnt = temp;
        }

    }

    static class EggInfo {
        int s, w;

        public EggInfo(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
}