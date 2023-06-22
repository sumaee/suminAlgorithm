import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];

        //2~n까지의 배열 먼저 채우기
        for (int i = 2; i < arr.length; i++) {
            arr[i] = i;
        }

        //2부터 시작해 특정 수의 배수 지우기
        for (int i = 2; i <= n; i++) {
            //이미 지워진 수라면 패스
            if (arr[i] == 0) continue;

            //지워진 수가 아니라면 그 수의 배수들 지우기(단 자기 자신을 뺴고)
            for (int j = 2 * i; j <= n; j += i) {
                arr[j] = 0;
            }
        }

        //m부터 지워지지 않은 수 출력
        for (int i = m; i <= n; i++) {
            if (arr[i] != 0) sb.append(i).append("\n");
        }

        System.out.println(sb);

    }
}
