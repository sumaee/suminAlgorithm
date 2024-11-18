import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> squareList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        squareList = new ArrayList<>();

        boolean[] arr = new boolean[100001];
        for (int i = 2; i < 100001; i++) {
            if (arr[i]) continue;
            int square = i * i;

            for (int j = i; j < 100001; j += i) {
                arr[j] = true;
            }
            squareList.add(square);
        }

        long left = 0;
        long right = Integer.MAX_VALUE;
        long answer = 0;
        while (left < right) {
            long mid = (left + right) / 2;

            int tmpK = (int) (mid - getSquareCnt(0, 1, mid));

            if (tmpK < k) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid;
            }
        }

        System.out.println(answer);

    }

    private static long getSquareCnt(int listIdx, long num, long mid) {
        if (listIdx >= squareList.size()) {
            return 0;
        }

        if (num * squareList.get(listIdx) > mid) {
            return 0;
        }

        long cnt = 0;
        cnt += mid / (num * squareList.get(listIdx));
        cnt += getSquareCnt(listIdx + 1, num, mid);
        cnt -= getSquareCnt(listIdx + 1, num * squareList.get(listIdx), mid);
        return cnt;
    }
}