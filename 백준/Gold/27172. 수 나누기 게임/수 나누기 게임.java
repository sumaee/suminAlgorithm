import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxNum = 0;
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, cards[i]);
        }

        int[] arr = new int[maxNum + 1];
        for (int i = 0; i < n; i++) {
            arr[cards[i]] = i + 1;
        }

        int[] answer = new int[n + 1];
        for (int card : cards) {
            for (int i = card * 2; i <= maxNum; i += card) {
                if (arr[i] != 0) {
                    answer[arr[i]]--;
                    answer[arr[card]]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);
    }
}
