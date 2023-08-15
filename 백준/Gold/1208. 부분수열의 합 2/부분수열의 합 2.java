import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> left = new ArrayList<>();
        getSum(0, 0, n / 2, left);
        Collections.sort(left);

        List<Long> right = new ArrayList<>();
        getSum(0, n / 2, n, right);
        Collections.sort(right);

        answer = 0;
        findS(left, right, s);

        //만약 구하고자 하는 값이 0이라면 공집합끼리의 수를 빼줘야하므로
        if (s == 0) {
            answer--;
        }
        System.out.println(answer);
    }

    private static void getSum(long sum, int start, int end, List<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }

        //해당 수를 안더한것
        getSum(sum, start + 1, end, list);
        //해당 수를 더한것
        getSum(sum + arr[start], start + 1, end, list);
    }

    private static void findS(List<Long> left, List<Long> right, int s) {
        int leftIdx = 0;
        int rightIdx = right.size() - 1;

        while (leftIdx != left.size() && rightIdx >= 0) {
            long sum = left.get(leftIdx) + right.get(rightIdx);

            //합이 같다면
            if (sum == s) {

                //해당 왼쪽 숫자와 같은 숫자개수 세기
                long tempL = left.get(leftIdx);
                long leftCnt = 0;
                while (leftIdx < left.size() && left.get(leftIdx) == tempL) {
                    leftCnt++;
                    leftIdx++;
                }

                //해당 오른쪽 숫자와 같은 숫자 세기
                long tempR = right.get(rightIdx);
                long rightCnt = 0;
                while (rightIdx >= 0 && right.get(rightIdx) == tempR) {
                    rightCnt++;
                    rightIdx--;
                }

                answer += rightCnt * leftCnt;
            }

            //합이 구하고자 하는 값보다 크다면
            else if (sum > s) {
                rightIdx--;
            }

            //합이 구하고자 하는 값보다 작다면
            else {
                leftIdx++;
            }
        }
    }

}
