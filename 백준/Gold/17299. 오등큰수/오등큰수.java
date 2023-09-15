import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] answer = new int[n];
        int[] cnt = new int[1000001];
        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            cnt[nums[i]]++;
        }

        //배열을 돌면서 스택에 있는 인덱스의 숫자의 개수가 더 작으면 현재 인덱스의 수로 채워넣기
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && cnt[nums[i]] > cnt[nums[stack.peek()]]) {
                answer[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        //위의 과정이 끝나고 채워지지 않은 것들은 오등큰수가 없는 것
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
