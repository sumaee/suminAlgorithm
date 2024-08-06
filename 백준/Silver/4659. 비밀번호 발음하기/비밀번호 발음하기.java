import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
        while (true) {
            String password = br.readLine();

            if (password.equals("end")) {
                break;
            }
            boolean flag = true;

            int vowelTotalCnt = 0;
            int vowelCnt = 0;
            int consonantCnt = 0;

            for (int i = 0; i < password.length(); i++) {
                if (vowels.contains(password.charAt(i))) {
                    vowelTotalCnt++;
                    vowelCnt++;
                    consonantCnt = 0;

                    if (vowelCnt == 3) {
                        flag = false;
                        break;
                    }
                } else {
                    consonantCnt++;
                    vowelCnt = 0;

                    if (consonantCnt == 3) {
                        flag = false;
                        break;
                    }
                }

                if (i != 0 && password.charAt(i - 1) == password.charAt(i)) {
                    if (password.charAt(i) != 'e' && password.charAt(i) != 'o') {
                        flag = false;
                        break;
                    }
                }
            }

            if (vowelTotalCnt == 0) {
                flag = false;
            }

            sb.append("<").append(password).append("> ");
            sb.append(flag ? "is acceptable." : "is not acceptable.").append("\n");
        }

        System.out.println(sb);
    }
}