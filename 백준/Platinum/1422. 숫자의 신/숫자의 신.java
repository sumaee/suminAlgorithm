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
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        List<String> nums = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            nums.add(br.readLine());
            max = Math.max(max, Integer.parseInt(nums.get(i)));
        }

        //앞뒤 숫자 이어 붙혀서 큰 수가 나오게끔 정렬
        nums.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        boolean visited = false;
        for (String num : nums) {
            sb.append(num);
            //붙이는 숫자가 제일 큰 숫자면 남은 자리들을 차지해야함
            if (max == Integer.parseInt(num) && !visited) {
                for (int j = 0; j < n - k; j++) {
                    sb.append(num);
                    visited = true;
                }
            }
        }

        System.out.println(sb);
    }
}
