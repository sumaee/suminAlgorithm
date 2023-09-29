import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            //행 확인
            if (checkRow(i)) answer++;
            //열 확인
            if (checkCol(i)) answer++;
        }
        System.out.println(answer);
    }

    private static boolean checkRow(int row) {
        //경사로가 설치되어있는지 확인하는 배열
        int[] slope = new int[n];

        for (int i = 0; i < n - 1; i++) {
            int diff = map[row][i] - map[row][i + 1];

            //높이차가 1 이상이면 안됨
            if (diff < -1 || diff > 1) return false;

            //내려가는 경사로 설치 확인
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {
                    //범위를 벗어나거나 이미 경사로가 설치되어있다면 안됨
                    if (i + j >= n || slope[i + j] == 1) return false;
                    //다음 칸이 경사로를 놓는 칸과 높이가 다르다면 안됨
                    if (map[row][i] - 1 != map[row][i + j]) return false;

                    slope[i + j] = 1;
                }
            }
            //올라가는 경사로 설치 확인
            else if (diff == -1) {
                for (int j = 0; j < l; j++) {
                    //범위를 벗어나거나 이미 경사로가 설치되어있다면 안됨
                    if (i - j < 0 || slope[i - j] == 1) return false;
                    //다음 칸이 경사로를 놓는 칸과 높이가 다르다면 안됨
                    if (map[row][i] != map[row][i - j]) return false;

                    slope[i - j] = 1;
                }
            }
        }
        return true;
    }

    private static boolean checkCol(int col) {
        //경사로가 설치되어있는지 확인하는 배열
        int[] slope = new int[n];

        for (int i = 0; i < n - 1; i++) {
            int diff = map[i][col] - map[i + 1][col];

            //높이차가 1 이상이면 안됨
            if (diff < -1 || diff > 1) return false;

            //내려가는 경사로 설치 확인
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {
                    //범위를 벗어나거나 이미 경사로가 설치되어있다면 안됨
                    if (i + j >= n || slope[i + j] == 1) return false;
                    //다음 칸이 경사로를 놓는 칸과 높이가 다르다면 안됨
                    if (map[i][col] - 1 != map[i + j][col]) return false;

                    slope[i + j] = 1;
                }
            }
            //올라가는 경사로 설치 확인
            else if (diff == -1) {
                for (int j = 0; j < l; j++) {
                    //범위를 벗어나거나 이미 경사로가 설치되어있다면 안됨
                    if (i - j < 0 || slope[i - j] == 1) return false;
                    //다음 칸이 경사로를 놓는 칸과 높이가 다르다면 안됨
                    if (map[i][col] != map[i - j][col]) return false;

                    slope[i - j] = 1;
                }
            }
        }
        return true;
    }
}