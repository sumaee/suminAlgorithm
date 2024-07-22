import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String str = br.readLine();
        String answer = "z";
        answer = answer.repeat(50);
        for (int i = 2; i < str.length(); i++) {
            for (int j = 1; j < i; j++) {
                StringBuilder last = new StringBuilder();
                StringBuilder second = new StringBuilder();
                StringBuilder first = new StringBuilder();

                last.append(str.substring(i));
                second.append(str, j, i);
                first.append(str, 0, j);

                last.reverse();
                second.reverse();
                first.reverse();

                String temp = String.valueOf(first) + second + last;
                
                if (temp.compareTo(answer) < 0) {
                    answer = temp;
                }
            }
        }

        System.out.println(answer);
    }

}