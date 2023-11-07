import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, d, answer;
    static int[][] map;

    static int[] defenses;
    static List<Locate> monsters, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        temp = new ArrayList<>();
        monsters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //몬스터들 위치 기록
                if (map[i][j] == 1) {
                    monsters.add(new Locate(i, j));
                }
            }
        }

        //조합으로 먼저 궁수들 위치 배치
        defenses = new int[3];
        answer = 0;
        perm(0, 0);
        System.out.println(answer);
    }

    private static void perm(int start, int idx) {
        //궁수 3명 배치가 끝났다면
        if (idx == 3) {
            //적들의 초기 위치를 복사해서 공격
            copyMonstersLocate();
            attack();
            return;
        }

        for (int i = start; i < m; i++) {
            defenses[idx] = i;
            perm(i + 1, idx + 1);
        }
    }

    private static void attack() {
        int cnt = 0;

        while (!temp.isEmpty()) {
            //궁수들의 목표 적을 저장
            List<Locate> targets = new ArrayList<>();

            //각 궁수들이 잡을 적들 체크
            for (int i = 0; i < 3; i++) {
                PriorityQueue<Locate> que = new PriorityQueue<>((o1, o2) ->
                        o1.dist == o2.dist ? o1.col - o2.col : o1.dist - o2.dist);

                for (Locate curr : temp) {
                    // 현재 궁수와 거리 계산
                    int dist = Math.abs(curr.row - n) + Math.abs(curr.col - defenses[i]);
                    //잡을 수 있다면 잡을 목표에 추가
                    if (dist <= d) {
                        que.add(new Locate(curr.row, curr.col, dist));
                    }
                }

                //만약 잡을 적이 존재 한다면
                if (!que.isEmpty()) {
                    // 조건에 맞게 몬스터를 잡음 (가장 가깝고, 왼쪽에 있는 적)
                    Locate target = que.poll();
                    //이전의 궁수랑 타겟이 겹치는지 확인
                    boolean check = false;
                    for (int j = 0; j < targets.size(); j++) {
                        if (targets.get(j).row == target.row && targets.get(j).col == target.col) {
                            check = true;
                            break;
                        }
                    }

                    //아무도 잡을 궁수가 없다면 잡기
                    if (!check) {
                        targets.add(new Locate(target.row, target.col));
                    }
                }
            }

            //궁수들이 잡을 적을 찾았으므로 적 죽이기
            for (Locate target : targets) {
                for (int j = temp.size() - 1; j >= 0; j--) {
                    if (target.row == temp.get(j).row && target.col == temp.get(j).col) {
                        cnt++;
                        temp.remove(j);
                        break;
                    }
                }

            }

            //남은 적들 한칸 이동
            for (int i = temp.size() - 1; i >= 0; i--) {
                temp.get(i).row += 1;
                if (temp.get(i).row >= n) {
                    temp.remove(i);
                }
            }
        }
        answer = Math.max(answer, cnt);
    }

    private static void copyMonstersLocate() {
        for (Locate monster : monsters) {
            temp.add(new Locate(monster.row, monster.col));
        }
    }

    static class Locate {
        int row, col, dist;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Locate(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }


    }
}