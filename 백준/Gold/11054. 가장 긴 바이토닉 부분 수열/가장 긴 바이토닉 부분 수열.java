import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        int[] ascArr = new int[n]; // 오름차순으로 구한 배열 길이 저장
        int[] descArr = new int[n]; // 내림차순으로 구한 배열 길이 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //가장 긴 오름차순 순열 정리
        Arrays.fill(ascArr, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    ascArr[i] = Math.max(ascArr[i], ascArr[j] + 1);
                }
            }
        }

        //가장 긴 내림차순 순열 정리
        Arrays.fill(descArr, 1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    descArr[i] = Math.max(descArr[i], descArr[j] + 1);
                }
            }

        }

        //두 배열을 합쳐서 가장 큰 숫자 -1 이 정답
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, descArr[i] + ascArr[i]);
        }
        System.out.println(answer - 1);
    }
}
