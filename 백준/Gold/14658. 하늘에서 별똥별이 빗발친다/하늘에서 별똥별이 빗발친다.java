import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Star[] stars = new Star[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            stars[i] = new Star(col, row);
        }

        int answer = k;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                Star star1 = stars[i];
                Star star2 = stars[j];

                int cnt = 0;
                for (int o = 0; o < k; o++) {
                    if (stars[o].col >= star1.col && stars[o].row >= star2.row
                            && stars[o].col <= star1.col + l && stars[o].row <= star2.row + l) {
                        cnt++;
                    }
                }
                answer = Math.min(answer, k - cnt);
            }
        }

        System.out.println(answer);
    }

    static class Star{
        int col, row;

        public Star(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}