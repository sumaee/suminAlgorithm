import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] cnt = new int[1000001];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            cnt[nums[i]]++;
        }

        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            int answer = 0;
            for (int j = 1; j * j <= curr; j++) {
                if (curr % j == 0) {
                    answer += cnt[j];

                    if (j * j < curr) {
                        answer += cnt[curr / j];
                    }
                }
            }
            answer--;
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
