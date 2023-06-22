import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n + 1];

        //일단 소수 배열 채우기
        for (int i = 2; i <= n; i++) {
            nums[i] = i;
        }

        //2부터 시작해서 소수 아닌 애들지우기
        for (int i = 2; i <= n; i++) {
            if (nums[i] == 0) continue;

            for (int j = 2 * i; j <= n; j += i) {
                nums[j] = 0;
            }
        }

        //해당 소수들을 데큐에 넣어가면서 합이 n이 되는지 확인
        Deque<Integer> que = new ArrayDeque<>();
        int answer = 0;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            if (nums[i] == 0) continue;
            que.offer(nums[i]);
            sum += nums[i];

            if (sum > n) {
                while (sum > n && !que.isEmpty()) {
                    sum -= que.pollFirst();
                }
            }
            if (sum == n) answer++;
        }
        System.out.println(answer);
    }
}
