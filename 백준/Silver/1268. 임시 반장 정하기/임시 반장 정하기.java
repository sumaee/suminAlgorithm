import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int studentNum = Integer.parseInt(br.readLine());
        int[][] arr = new int[studentNum][5];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < studentNum; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < studentNum; k++) {
                    if (i != k && arr[i][j] == arr[k][j]) {
                        set.add(k);
                    }
                }
            }

            if (set.size() > max) {
                answer = i;
                max = set.size();
            }
        }

        System.out.println(answer+1);
    }
}