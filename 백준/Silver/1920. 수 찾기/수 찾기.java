import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        //n개의 숫자를 담을 배열 선언
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //이분탐색을 위한 정렬
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            //이분 탐색 시작
            if (binaryCheck(arr, num)) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean binaryCheck(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            //찾고자 하는 값이 중간값보다 작다면 right을 mid로 바꾸기
            if (num < arr[mid]) {
                right = mid - 1;
            }

            //찾고자 하는 값이 중간값보다 크다면 left를 mid로 바꾸기
            else if (num > arr[mid]) {
                left = mid + 1;
            }

            //찾고자하는 값이 중간값이라면 true return
            else {
                return true;
            }

        }

        //while문에서 return이 안되었다면 없다는 뜻
        return false;
    }
}
