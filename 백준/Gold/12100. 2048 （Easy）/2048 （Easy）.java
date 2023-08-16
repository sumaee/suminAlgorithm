import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        play(0);
        System.out.println(answer);
    }

    private static void play(int cnt) {
        //5번 이동이 끝났으면 다음 확인을 위해 끝
        if (cnt == 5) {
            //최댓값 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
            return;
        }

        //전으로 돌아오기 위한 copymap 선언
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        //이동
        for (int i = 0; i < 4; i++) {
            //위로 몰기
            if (i == 0) {
                for (int c = 0; c < n; c++) {
                    int idx = 0;
                    int block = 0;
                    for (int r = 0; r < n; r++) {
                        if (map[r][c] != 0) {
                            if (block == map[r][c]) {
                                map[idx - 1][c] = block * 2;
                                block = 0;
                                map[r][c] = 0;
                            } else {
                                block = map[r][c];
                                map[r][c] = 0;
                                map[idx][c] = block;
                                idx++;
                            }
                        }
                    }
                }
            }
            //아래쪽으로 몰기
            else if (i == 1) {
                for (int c = 0; c < n; c++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int r = n - 1; r >= 0; r--) {
                        if (map[r][c] != 0) {
                            if (block == map[r][c]) {
                                map[idx + 1][c] = block * 2;
                                block = 0;
                                map[r][c] = 0;
                            } else {
                                block = map[r][c];
                                map[r][c] = 0;
                                map[idx][c] = block;
                                idx--;
                            }
                        }
                    }
                }
            }
            //왼쪽으로 몰기
            else if (i == 2) {
                for (int r = 0; r < n; r++) {
                    int idx = 0;
                    int block = 0;
                    for (int c = 0; c < n; c++) {
                        if (map[r][c] != 0) {
                            if (block == map[r][c]) {
                                map[r][idx - 1] = block * 2;
                                block = 0;
                                map[r][c] = 0;
                            } else {
                                block = map[r][c];
                                map[r][c] = 0;
                                map[r][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
            }
            //오른쪽으로 몰기
            else {
                for (int r = 0; r < n; r++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int c = n - 1; c >= 0; c--) {
                        if (map[r][c] != 0) {
                            if (block == map[r][c]) {
                                map[r][idx + 1] = block * 2;
                                block = 0;
                                map[r][c] = 0;
                            } else {
                                block = map[r][c];
                                map[r][c] = 0;
                                map[r][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
            }

            //다음 횟수로 이동
            play(cnt + 1);
            //원상복구
            for (int j = 0; j < n; j++) {
                map[j] = copy[j].clone();
            }
        }

    }
}
