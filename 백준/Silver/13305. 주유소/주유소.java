import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        //간격 저장
        int[] dis = new int[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }

        //가격 저장
        st = new StringTokenizer(br.readLine());
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        //다음 가격과 비교해서 내림차순 일때까지만 현재 가격 저장하기
        int currCost = cost[0];
        for (int i = 1; i < n - 1; i++) {
            if (cost[i] > currCost) {
                cost[i] = currCost;
            } else {
                currCost = cost[i];
            }
        }

        //간격과 곱하기
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += cost[i] * dis[i];
        }
        System.out.println(sum);
    }
}
