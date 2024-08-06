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
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        List<Info> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            list.add(new Info(i, weight, height, 0));
        }

        for (int i = 0; i < list.size(); i++) {
            int rank = 1;
            Info now = list.get(i);

            for (int j = 0; j < list.size(); j++) {
                if (i == j) continue;
                Info compare = list.get(j);

                if (compare.height > now.height && compare.weight > now.weight) {
                    rank++;
                }
            }

            sb.append(rank).append(" ");
        }

        System.out.println(sb);
    }

    static class Info {
        int idx, weight, height, rate;

        public Info(int idx, int weight, int height, int rate) {
            this.idx = idx;
            this.weight = weight;
            this.height = height;
            this.rate = rate;
        }
    }
}