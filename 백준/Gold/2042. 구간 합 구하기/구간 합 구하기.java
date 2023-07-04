import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[n + 1];
        tree = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            update(i, arr[i]);
        }

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            long num = Long.parseLong(st.nextToken());

            //idx 번째 수를 num으로 바꾸기
            if (check == 1) {
                //원래 값과의 차이
                long diff = num - arr[idx];
                //원래 값 변경
                arr[idx] = num;
                //변경된 값 업데이트
                update(idx, diff);
            }
            //idx 번째 수부터 num번째 수까지의 합을 구해 출력
            else {
                int endIdx = (int) num;
                sb.append(sum(endIdx) - sum(idx - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(int i, long val) {
        while (i < tree.length) {
            tree[i] += val;
            i += (i & -i);
        }
    }

    private static long sum(int i) {
        long total = 0;

        while (i > 0) {
            total += tree[i];
            i -= (i & -i);
        }

        return total;
    }
}
