import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Integer> sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //두수의 합 구하기
        sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum.add(arr[i] + arr[j]);
            }
        }

        //정렬
        Arrays.sort(arr);
        sum.sort((o1, o2) -> o1 - o2);
        int answer = -1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                //두수의 차이가 sum에 있는지 확인 -> 있고 현재 답보다 크다면 갱신
                int minus = arr[i] - arr[j];
                if (binarySearch(minus) && arr[i] > answer) {
                    answer = arr[i];
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(int gap) {
        int start = 0;
        int end = sum.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            //중간값이 차이보다 작다면 시작점 이동
            if (sum.get(mid) < gap) {
                start = mid + 1;
            }

            //중간값이 해당 차이라면 찾음 리턴
            else if (sum.get(mid) == gap) {
                return true;
            }
            //중간값이차이보다 크다면 끝점 이동
            else {
                end = mid - 1;

            }
        }

        //여기까지 왔다는 것은 값이 없다는 뜻
        return false;
    }
}
