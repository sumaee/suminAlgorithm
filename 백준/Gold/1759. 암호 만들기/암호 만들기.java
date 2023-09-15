import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int l, c;
    static String[] alps;
    static StringBuilder sb = new StringBuilder();
    static String[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        alps = new String[c];
        answer = new String[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            alps[i] = st.nextToken();
        }

        Arrays.sort(alps);

        findPwd(0, 0);
        System.out.println(sb);
    }

    private static void findPwd(int idx, int cnt) {
        //l만큼 길이의 개수를 찾았다면 끝
        if (cnt == l) {
            StringBuilder temp = new StringBuilder();
            int aeiou = 0;
            int other = 0;
            for (String alp : answer) {
                if (alp.equals("a") || alp.equals("e") || alp.equals("i") || alp.equals("o") || alp.equals("u")) {
                    aeiou++;
                } else {
                    other++;
                }
                temp.append(alp);
            }

            if (aeiou >= 1 && other >= 2)
                sb.append(temp).append("\n");
            return;
        }

        for (int i = idx; i < c; i++) {
            answer[cnt] = alps[i];
            findPwd(i + 1, cnt + 1);
        }
    }
}
