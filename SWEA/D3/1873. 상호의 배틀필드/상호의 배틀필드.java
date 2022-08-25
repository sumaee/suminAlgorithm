import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
import javax.swing.plaf.synth.SynthSeparatorUI;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test_case; tc++) {
            sb.append("#").append(tc).append(" ");
 
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken()); // 높이, 행
            int W = Integer.parseInt(st.nextToken()); // 너비, 열
 
            String[] str = new String[H];
            char[][] field = new char[H][W];
 
            // 필드 채우기
            for (int i = 0; i < H; i++) {
                str[i] = br.readLine();
                for (int j = 0; j < W; j++) {
                    field[i][j] = str[i].charAt(j);
                }
            }
 
            int N = Integer.parseInt(br.readLine()); // 문자열 길이
            String move = br.readLine();
 
            for (int i = 0; i < N; i++) {
                char data = move.charAt(i);
                next: switch (data) {
 
                // 전차의 방향을 위로 바꾸고 한 칸 위가 평지라면 이동
                case 'U':
                    for (int r = 0; r < H; r++) {
                        for (int c = 0; c < W; c++) {
                            if (field[r][c] == '^' || field[r][c] == '<' || field[r][c] == '>' || field[r][c] == 'v') {
                                field[r][c] = '^';
 
                                if (r - 1 >= 0 && field[r - 1][c] == '.') {
                                    char tmp = field[r][c];
                                    field[r][c] = field[r - 1][c];
                                    field[r - 1][c] = tmp;
                                }
                                break next;
                            }
                        }
                    }
 
                    // 전차의 방향을 아래로 바꾸고 한칸 아래가 평지라면 이동
                case 'D':
                    for (int r = 0; r < H; r++) {
                        for (int c = 0; c < W; c++) {
                            if (field[r][c] == '^' || field[r][c] == '<' || field[r][c] == '>' || field[r][c] == 'v') {
                                field[r][c] = 'v';
 
                                if (r + 1 < H && field[r + 1][c] == '.') {
                                    char tmp = field[r][c];
                                    field[r][c] = field[r + 1][c];
                                    field[r + 1][c] = tmp;
                                }
                                break next;
                            }
                        }
                    }
 
                    // 전차의 방향을 왼쪽으로 바꾸고 한칸 왼쪽이 평지라면 이동
                case 'L':
                    for (int r = 0; r < H; r++) {
                        for (int c = 0; c < W; c++) {
                            if (field[r][c] == '^' || field[r][c] == '<' || field[r][c] == '>' || field[r][c] == 'v') {
                                field[r][c] = '<';
 
                                if (c - 1 >= 0 && field[r][c - 1] == '.') {
                                    char tmp = field[r][c];
                                    field[r][c] = field[r][c - 1];
                                    field[r][c - 1] = tmp;
                                }
                                break next;
                            }
                        }
                    }
 
                    // 전차의 방향을 오른쪽으로 바꾸고 한칸 오른쪽이 평지라면 이동
                case 'R':
                    for (int r = 0; r < H; r++) {
                        for (int c = 0; c < W; c++) {
                            if (field[r][c] == '^' || field[r][c] == '<' || field[r][c] == '>' || field[r][c] == 'v') {
                                field[r][c] = '>';
 
                                if (c + 1 < W && field[r][c + 1] == '.') {
                                    char tmp = field[r][c];
                                    field[r][c] = field[r][c + 1];
                                    field[r][c + 1] = tmp;
                                }
                                break next;
                            }
                        }
                    }
 
                    // 전차가 바라보는 위치로 포탄쏘기
                    // 포탄은 벽돌만 부술수 있음
                case 'S':
                    for (int r = 0; r < H; r++) {
                        for (int c = 0; c < W; c++) {
                            if (field[r][c] == '^') {
                                while (r - 1 >= 0 && field[r - 1][c] != '#') {
                                    if (field[r - 1][c] == '*') {
                                        field[r - 1][c] = '.';
                                        break next;
                                    }
                                    r--;
                                }
                                break next;
                            } else if (field[r][c] == '<') {
                                while (c - 1 >= 0 && field[r][c - 1] != '#') {
                                    if (field[r][c - 1] == '*') {
                                        field[r][c - 1] = '.';
                                        break next;
                                    }
                                    c--;
                                }
                                break next;
                            } else if (field[r][c] == '>') {
                                while (c + 1 < W && field[r][c + 1] != '#') {
                                    if (field[r][c + 1] == '*') {
                                        field[r][c + 1] = '.';
                                        break next;
                                    }
                                    c++;
                                }
                                break next;
                            } else if (field[r][c] == 'v') {
                                while (r + 1 < H && field[r + 1][c] != '#') {
                                    if (field[r + 1][c] == '*') {
                                        field[r + 1][c] = '.';
                                        break next;
                                    }
                                    r++;
                                }
                                break next;
                            }
 
                        }
                    } // case S
 
                }// switch
            }
 
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(field[i][j]);
                }
                sb.append("\n");
            }
        } // tc
        System.out.println(sb);
    }// main
}