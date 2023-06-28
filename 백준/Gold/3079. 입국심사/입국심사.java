import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 입국 심사대 개수
        int m = Integer.parseInt(st.nextToken()); // 상근이 친구들

        int[] times = new int[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(br.readLine());

            max = Math.max(max, times[i]);
        }

        long min = 0;
        max *= m; // 상근이의 친구들이 모두 오래걸리는 심사대에서 심사를 보는 것이므로 max*=m 진행
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;

            long people = 0;
            //각 심사대마다 mid 시간이 주어졌을 때 몇명을 볼 수 있는지
            for (int time : times) {
                people += mid / time;
                if (people >= m) {
                    break;
                }
            }

            //만약 인원수가 친구들 수 보다 작다면 시간을 더 늘리기
            if (people < m) {
                min = mid + 1;
            }
            //볼 수 있는 인원이 친구들 수보다 크다면 시간 줄이기
            else {
                max = mid - 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}
