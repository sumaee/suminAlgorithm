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

        List<Integer> money = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= k) {
                money.add(coin);
            }
        }
        money.sort((o1, o2) -> o2 - o1);
        int cnt = 0;
        for (int i = 0; i < money.size(); i++) {
            if (k != 0) {
                cnt += (k / money.get(i));
                k %= money.get(i);
            }
        }


        System.out.println(cnt);
    }
}
