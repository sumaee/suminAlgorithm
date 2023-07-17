import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String p = br.readLine();

        int idx = 0;
        int answer = 1;

        for (int i = 0; i < p.length(); i++) {
            //해당 문자를 포함하고 있다면 다음 문자까지 확인
            if (s.contains(p.substring(idx, i + 1))) continue;

            //포함하고 있지 않다면 답 하나 늘리고
            answer++;
            //포함하지 않는 곳부터 다시 보기 위한 작업
            idx = i;
        }
        System.out.println(answer);
    }
}
