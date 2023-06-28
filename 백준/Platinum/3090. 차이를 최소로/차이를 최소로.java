import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, t;
    static int[] arr;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[n];
        answer = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //배열 숫자 사이의 차이 최대값 구하기
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, Math.abs(arr[i] - arr[i + 1]));
        }

        int min = 0;
        max += 1;
        while (min <= max) {
            int mid = (min + max) / 2;

            //해당 차이만큼 가능하므로 차이 줄이기
            if (checkArr(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        for (int tmp : answer) {
            sb.append(tmp).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean checkArr(int mid) {
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            answer[i] = arr[i];
        }
        //왼쪽값을 기준으로 오른쪽 값이 더 큰지 확인
        for (int i = 0; i < n - 1; i++) {
            //만약 mid 보다 크다면 mid 만큼 줄이기 위한 작업 실행
            if (answer[i + 1] - answer[i] > mid) {
                cnt += answer[i + 1] - (answer[i] + mid);
                answer[i + 1] = answer[i] + mid;
            }
        }

        //맨 뒤에서 돌면서 오른쪽 값을 기준으로 왼쪽 값이 더 큰지 확인
        for (int i = n - 1; i > 0; i--) {
            //만약 mid 보다 크다면 mid 만큼 줄이기
            if (answer[i - 1] - answer[i] > mid) {
                cnt += answer[i - 1] - (answer[i] + mid);
                answer[i - 1] = answer[i] + mid;
            }
        }


        return cnt <= t;
    }
}
