import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int[][] arr = new int[26][str.length() + 1]; // 각 알파벳의 누적합 구하기

        for (int i = 0; i < 26; i++) {

            for (int j = 1; j <= str.length(); j++) {
                int idx = str.charAt(j - 1) - 'a';

                if (idx == i) {
                    arr[i][j] = arr[i][j - 1] + 1;
                } else {
                    arr[i][j] = arr[i][j - 1];
                }
            }
        }

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            char ch = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken()) + 1;
            int end = Integer.parseInt(st.nextToken()) + 1;

            sb.append(arr[ch - 'a'][end] - arr[ch - 'a'][start - 1]).append("\n");
        }
        System.out.println(sb);
    }
}
