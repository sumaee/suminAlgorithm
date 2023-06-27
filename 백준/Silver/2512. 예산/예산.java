import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] money;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        money = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        int total = 0;
        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, money[i]);
            total += money[i];
        }

        int limit = Integer.parseInt(br.readLine());

        //만약 필요 예산 값이 한계치 보다 작다면 모두 줄 수 있으므로 최댓값으로 주기
        if (total <= limit) {
            System.out.println(max);
            return;
        }

        int min = 0;
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            int sum = checkSum(mid);

            //합이 한계치보다 크다면 중간값을 낮추기
            if (sum > limit) {
                max = mid - 1;
            }
            //합이 한계치보다 작다면 더 줄 수 있으므로 중간값 높히기
            else {
                min = mid + 1;
                answer = mid;
            }

        }

        System.out.println(answer);

    }

    private static int checkSum(int mid) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (money[i] <= mid) {
                sum += money[i];
            } else {
                sum += mid;
            }
        }

        return sum;
    }
}
