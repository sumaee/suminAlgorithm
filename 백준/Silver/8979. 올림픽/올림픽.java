import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int countryIdx = Integer.parseInt(st.nextToken());
            int goldCnt = Integer.parseInt(st.nextToken());
            int silverCnt = Integer.parseInt(st.nextToken());
            int bronzeCnt = Integer.parseInt(st.nextToken());

            infos.add(new Info(countryIdx, goldCnt, silverCnt, bronzeCnt, 0));
        }

        infos.sort((o1, o2) -> {
            if (o1.goldMedal == o2.goldMedal) {
                if (o1.silverMedal == o2.silverMedal) {
                    return o2.bronzeMedal - o1.bronzeMedal;
                }
                return o2.silverMedal - o1.silverMedal;
            }
            return o2.goldMedal - o1.goldMedal;

        });

        infos.get(0).rate = 1;
        int answerIdx = 0;
        for (int i = 1; i < infos.size(); i++) {
            Info before = infos.get(i - 1);
            Info now = infos.get(i);

            if (now.goldMedal == before.goldMedal && now.silverMedal == before.silverMedal && now.bronzeMedal == before.bronzeMedal) {
                now.rate = before.rate;
            } else {
                now.rate = i + 1;
            }

            if (now.countryIdx == k) {
                answerIdx = i;
            }
        }

        System.out.println(infos.get(answerIdx).rate);

    }

    static class Info {
        int countryIdx;
        int goldMedal;
        int silverMedal;
        int bronzeMedal;
        int rate;

        public Info(int countryIdx, int goldMedal, int silverMedal, int bronzeMedal, int rate) {
            this.countryIdx = countryIdx;
            this.goldMedal = goldMedal;
            this.silverMedal = silverMedal;
            this.bronzeMedal = bronzeMedal;
            this.rate = rate;
        }
    }
}