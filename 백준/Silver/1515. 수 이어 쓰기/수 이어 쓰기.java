import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();
        char[] numArr = num.toCharArray();

        int idx = 0;
        int answer = 1;
        while (idx < numArr.length) {
            String temp = String.valueOf(answer);

            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == numArr[idx]) {
                    idx++;
                }

                if (idx >= numArr.length) break;
            }

            answer += 1;
        }

        System.out.println(answer - 1);
    }
}