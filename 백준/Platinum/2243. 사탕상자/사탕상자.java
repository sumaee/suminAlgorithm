import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] candyBox;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        candyBox = new int[1000001];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());

            //빼는 상황
            if (check == 1) {
                int rank = Integer.parseInt(st.nextToken());
                //이분 탐색을 통해 꺼낼 수 있는 사탕의 위치 찾기
                int left = 1;
                int right = 1000001;
                int answer = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    int sum = sum(mid);
                    if (sum >= rank) {
                        right = mid - 1;
                        answer = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                sb.append(answer).append("\n");

                //사탕을 빼줬으므로 업데이드
                update(answer, -1);
            }

            //사탕을 넣거나 빼는 상황
            else if (check == 2) {
                int rank = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());

                update(rank, num);
            }

        }
        System.out.println(sb);
    }

    private static int sum(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += candyBox[idx];
            idx -= idx & -idx;
        }

        return sum;
    }

    private static void update(int idx, int num) {
        while (idx < candyBox.length) {
            candyBox[idx] += num;
            idx += idx & -idx;
        }
    }
}
