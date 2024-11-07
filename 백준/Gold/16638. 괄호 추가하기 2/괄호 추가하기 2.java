import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int n, maxGalHoCnt;
    static int answer;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        answer = Integer.MIN_VALUE;
        String str = br.readLine();
        arr = str.toCharArray();
        if (n == 1) {
            System.out.println(arr[0]);
        } else {
            maxGalHoCnt = n / 2;
            set(0, "");
            System.out.println(answer);
        }
    }

    static void set(int cnt, String str) {
        //만약 괄호를 채웠다면
        if (cnt >= maxGalHoCnt) {
            char temp = str.charAt(str.length() - 1);
            if (temp != ')') {
                str += arr[n - 1];
            }

            answer = Math.max(answer, cal(str.toCharArray()));
            return;
        }

        StringBuilder notChoose = new StringBuilder(str);
        notChoose.append(arr[cnt * 2]).append(arr[cnt * 2 + 1]);
        set(cnt + 1, notChoose.toString());

        StringBuilder choose = new StringBuilder(str);
        choose.append("(").append(arr[cnt * 2]).append(arr[cnt * 2 + 1]).append(arr[cnt * 2 + 2]).append(")");
        if (cnt + 1 < maxGalHoCnt) choose.append(arr[cnt * 2 + 3]);
        set(cnt + 2, choose.toString());
    }

    private static int cal(char[] str) {
        Stack<Character> op = new Stack<>();
        Stack<Integer> num = new Stack<>();

        next:
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                num.push(str[i] - '0');
            } else {
                if (str[i] == '(') {
                    op.push(str[i]);
                } else if (str[i] == ')') {
                    while (op.peek() != '(') {
                        int num1 = num.pop();
                        int num2 = num.pop();
                        num.push(calOp(num2, num1, op.pop()));
                    }
                    op.pop();
                } else {
                    while (true) {
                        if (op.isEmpty()) {
                            op.push(str[i]);
                            continue next;
                        }
                        char opTemp = op.peek();
                        if (opTemp == '(' || (opTemp != '*' && str[i] == '*')) {
                            op.push(str[i]);
                            break;
                        } else {
                            int num1 = num.pop();
                            int num2 = num.pop();
                            num.push(calOp(num2, num1, op.pop()));
                        }
                    }
                }
            }
        }

        while (!op.isEmpty()) {
            int num1 = num.pop();
            int num2 = num.pop();
            num.push(calOp(num2, num1, op.pop()));
        }

        return num.pop();
    }

    private static int calOp(int num1, int num2, char op) {
        if (op == '+') {
            return num1 + num2;
        } else if (op == '-') {
            return num1 - num2;
        } else if (op == '*') {
            return num1 * num2;
        }

        return 0;
    }
}