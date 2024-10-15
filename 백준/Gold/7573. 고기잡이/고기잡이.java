import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Fish[] fishInfo = new Fish[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            fishInfo[i] = new Fish(row, col);
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {

                Fish fish1 = fishInfo[i];
                Fish fish2 = fishInfo[j];

                int h = 1;
                int v = (l - h * 2) / 2;

                while (v > 0) {
                    int cnt = 0;
                    for (int k = 0; k < m; k++) {
                        if (fishInfo[k].row >= fish1.row && fishInfo[k].col >= fish2.col
                                && fishInfo[k].row <= fish1.row + h && fishInfo[k].col <= fish2.col + v) {
                            cnt++;
                        }
                    }

                    answer = Math.max(answer, cnt);

                    h++;
                    v = (l - h * 2) / 2;
                }
            }
        }

        System.out.println(answer);

    }

    static class Fish{
        int row, col;

        public Fish(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}