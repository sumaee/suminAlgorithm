import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[][] cookieInfos = new char[n][n];

        boolean findFlag = false;
        Location heartLocate = null;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                cookieInfos[i][j] = line.charAt(j);

                if (!findFlag && line.charAt(j) == '*') {
                    heartLocate = new Location(i + 1, j);
                    findFlag = true;
                }
            }
        }

        sb.append(heartLocate.row + 1).append(" ").append(heartLocate.col + 1).append("\n");

        //왼팔길이
        int cnt = 0;
        for (int i = heartLocate.col - 1; i >= 0; i--) {
            if (cookieInfos[heartLocate.row][i] == '*') {
                cnt++;
            } else {
                break;
            }
        }

        sb.append(cnt).append(" ");
        cnt = 0;

        //오른팔길이
        for (int i = heartLocate.col + 1; i < n; i++) {
            if (cookieInfos[heartLocate.row][i] == '*') {
                cnt++;
            } else {
                break;
            }
        }

        sb.append(cnt).append(" ");
        cnt = 0;

        //허리길이 & 마지막 위치 기록
        Location waist = null;
        for (int i = heartLocate.row + 1; i < n; i++) {
            if (cookieInfos[i][heartLocate.col] == '*') {
                cnt++;
            } else {
                waist = new Location(i - 1, heartLocate.col);
                break;
            }
        }
        sb.append(cnt).append(" ");
        cnt = 0;

        //왼 다리 길이
        for (int i = waist.row + 1; i < n; i++) {
            if (cookieInfos[i][waist.col - 1] == '*') {
                cnt++;
            } else {
                break;
            }
        }

        sb.append(cnt).append(" ");
        cnt = 0;

        //오른 다리 길이
        for (int i = waist.row + 1; i < n; i++) {
            if (cookieInfos[i][waist.col + 1] == '*') {
                cnt++;
            } else {
                break;
            }
        }

        sb.append(cnt).append(" ");
        System.out.println(sb);
    }

    static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}