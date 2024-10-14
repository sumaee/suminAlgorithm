import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        Delivery[] deliveries = new Delivery[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int boxCnt = Integer.parseInt(st.nextToken());

            deliveries[i] = new Delivery(start, end, boxCnt);
        }

        int[] deliveryLimit = new int[n];
        for (int i = 1; i < n; i++) {
            deliveryLimit[i] = c;
        }

        Arrays.sort(deliveries, (o1, o2) -> o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end);

        int answer = 0;
        for (int i = 0; i < m; i++) {
            Delivery delivery = deliveries[i];

            int boxLimit = c;

            for (int j = delivery.start; j < delivery.end; j++) {
                boxLimit = Math.min(boxLimit, deliveryLimit[j]);
            }

            boxLimit = Math.min(boxLimit, delivery.boxCnt);
            for (int j = delivery.start; j < delivery.end; j++) {
                deliveryLimit[j] -= boxLimit;
            }

            answer += boxLimit;
        }

        System.out.println(answer);
    }

    static class Delivery {
        int start, end, boxCnt;

        public Delivery(int start, int end, int boxCnt) {
            this.start = start;
            this.end = end;
            this.boxCnt = boxCnt;
        }
    }

}