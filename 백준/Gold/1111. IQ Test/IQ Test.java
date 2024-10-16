import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1 || (n == 2 && arr[0] != arr[1])) {
            System.out.println("A");
        } else if (n == 2) {
            System.out.println(arr[0]);
        } else {
            int a;
            int b;

            if (arr[0] == arr[1]) {
                a = 1;
                b = 0;
            } else {
                a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
                b = arr[1] - arr[0] * a;
            }

            boolean flag = true;
            for (int i = 0; i < n-1; i++) {
                if (arr[i] * a + b != arr[i + 1]) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                System.out.println("B");
            } else {
                System.out.println(arr[n - 1] * a + b);
            }
        }
    }
}