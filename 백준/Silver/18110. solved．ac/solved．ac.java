import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int exclude = Math.round((float) n * ((float) 15 / (float) 100));
        Arrays.sort(score);

        int sum = 0;
        for (int i = exclude; i < n - exclude; i++) {
            sum += score[i];
        }

        System.out.println(Math.round((float) sum / (float) (n - exclude * 2)));
    }
}
