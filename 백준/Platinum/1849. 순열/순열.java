import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        //해당 인덱스보다 앞에 있는 빈칸의 개수를 누적합으로 구해놓음
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            update(i, 1);
        }

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int cnt = Integer.parseInt(br.readLine()) + 1;

            //이분 탐색을 통해 넣을 수 있는 위치 찾기
            int left = 1;
            int right = n + 1;
            int idx = 0;
            while (left <= right) {
                int mid = (left + right) / 2;

                int sum = sum(mid);
                if (sum >= cnt) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            arr[idx] = i;

            //넣어주었으므로 빈칸 갱신
            update(idx, -1);
        }

        for (int i = 1; i <= n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void update(int idx, int val) {
        while (idx < tree.length) {
            tree[idx] += val;
            idx += idx & -idx;
        }
    }

    private static int sum(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}
