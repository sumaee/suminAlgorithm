import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a, c, g, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String dna = br.readLine();
        Dna dnaInfo = new Dna();

        for (int i = 0; i < p; i++) {
            dnaInfo.add(dna.charAt(i));
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int answer = 0;
        if (dnaInfo.isPossible()) {
            answer++;
        }

        int idx = -1;
        for (int i = p; i < s; i++) {
            idx = i - p;

            dnaInfo.minus(dna.charAt(idx));
            dnaInfo.add(dna.charAt(i));

            if (dnaInfo.isPossible()) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static class Dna {
        int aCnt, cCnt, gCnt, tCnt;

        public Dna() {
            this.aCnt = 0;
            this.cCnt = 0;
            this.gCnt = 0;
            this.tCnt = 0;
        }

        public void add(char ch) {
            if (ch == 'A') {
                this.aCnt++;
            } else if (ch == 'C') {
                this.cCnt++;
            } else if (ch == 'G') {
                this.gCnt++;
            } else if (ch == 'T') {
                this.tCnt++;
            }
        }

        public void minus(char ch) {
            if (ch == 'A') {
                this.aCnt--;
            } else if (ch == 'C') {
                this.cCnt--;
            } else if (ch == 'G') {
                this.gCnt--;
            } else if (ch == 'T') {
                this.tCnt--;
            }
        }

        public boolean isPossible() {
            return this.aCnt >= a && this.cCnt >= c && this.gCnt >= g && this.tCnt >= t;
        }
    }
}
