import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int c;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        weights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        //절반으로 분할해서 값 찾기
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        comb(firstList, 0, 0, n / 2);
        comb(secondList, 0, n / 2, n);

        //첫번째 리스트를 기준으로 조합 찾기
        //이분탐색을 하기 위해 두번째 리스트 오름차순 정렬
        secondList.sort((o1, o2) -> o1 - o2);
        int answer = 0;
        for (int i = 0; i < firstList.size(); i++) {
            int idx = binarySearch(0, secondList.size() - 1, firstList.get(i), secondList);
            answer += idx + 1;
        }

        System.out.println(answer);
    }

    private static int binarySearch(int start, int end, int key, List<Integer> secondList) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (secondList.get(mid) + key <= c) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    private static void comb(List<Integer> sumList, int sum, int start, int end) {
        //가방에 넣을 수 있는 무게보다 커지면 return
        if (sum > c) return;

        //index가 끝까지 도달했다면 list에 넣고 return
        if (start == end) {
            sumList.add(sum);
            return;
        }

        //아무것도 안더하고 그대로 넘기기
        comb(sumList, sum, start + 1, end);
        //해당 숫자를 더하고 넘기기
        comb(sumList, sum + weights[start], start + 1, end);
    }
}
