import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] sum = new int[301];
        int[] score = new int[301];

        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        sum[1] = score[1];
        sum[2] = score[1] + score[2];

        for (int i = 3; i <= n; i++) {
            sum[i] = Math.max(sum[i - 2], sum[i - 3] + score[i - 1]) + score[i];
        }

        System.out.println(sum[n]);
    }
}