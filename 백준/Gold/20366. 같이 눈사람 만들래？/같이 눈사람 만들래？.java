import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, answer;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        size = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(size);

        answer = Integer.MAX_VALUE;
        //일단 엘사의 눈사람 크기에 맞춰서 안나 눈사람 만들기
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j >= i + 1; j--) {
                makeSnowman(i, j);
            }
        }
        System.out.println(answer);
    }

    private static void makeSnowman(int i, int j) {

        int stIdx = i + 1;
        int enIdx = j - 1;

        while (stIdx < enIdx) {
            if (stIdx == i || stIdx == j) {
                stIdx++;
                continue;
            }

            if (enIdx == i || enIdx == j) {
                enIdx--;
                continue;
            }

            int elza = size[i] + size[j];
            int anna = size[stIdx] + size[enIdx];

            answer = Math.min(answer, Math.abs(elza - anna));

            //만약 엘사가 더 크다면 안나의 눈 크기 늘리기
            if (elza > anna) {
                stIdx++;
            } else if (elza < anna) {
                enIdx--;
            } else {
                return;
            }
        }

    }
}
