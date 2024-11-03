import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Students[] students = new Students[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            List<Integer> algos = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                algos.add(Integer.parseInt(st.nextToken()));
            }

            students[i] = new Students(m, level, algos);
        }

        Arrays.sort(students, (o1, o2) -> o1.d - o2.d);

        int left = -1;
        int right = -1;
        int answer = Integer.MIN_VALUE;
        int[] algoCnt = new int[k + 1];

        while (right != n - 1) {
            if (left != -1) {
                for (int algo : students[left].algos) {
                    algoCnt[algo]--;
                }
            }
            left++;

            while (right != n - 1 && students[right + 1].d - students[left].d <= d) {
                right++;
                for (int algo : students[right].algos) {
                    algoCnt[algo]++;
                }
            }

            //효율값 비교
            int e = 0;
            for (int i = 1; i <= k; i++) {
                if (algoCnt[i] != 0 && algoCnt[i] != right - left + 1) {
                    e++;
                }
            }
            answer = Math.max(answer, e * (right - left + 1));

        }
        System.out.println(answer);
    }

    static class Students {
        int m, d;
        List<Integer> algos;

        public Students(int m, int d, List<Integer> algos) {
            this.m = m;
            this.d = d;
            this.algos = algos;
        }
    }
}