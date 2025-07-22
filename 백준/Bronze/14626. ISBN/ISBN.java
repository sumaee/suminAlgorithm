import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        char[] numArr = new char[s.length() - 1];
        for (int i = 0; i < s.length() - 1; i++) {
            numArr[i] = s.charAt(i);
        }

        int value = 10 - (s.charAt(s.length() - 1) - '0');
        if (value == 10) {
            value = 0;
        }
        int sum = 0;

        int checkIdx = -1;
        for (int i = 0; i < numArr.length; i++) {
            if (numArr[i] == '*') {
                checkIdx = i;
                continue;
            }

            if (i % 2 == 0) {
                sum += (numArr[i] - '0');
            } else {
                sum += (numArr[i] - '0') * 3;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            int temp = sum;

            if (checkIdx % 2 == 0) {
                temp += i;

            } else {
                temp += i * 3;
            }

            if (temp % 10 == value) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
