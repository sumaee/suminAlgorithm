import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        List<Fish> fishInfos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishInfos.add(new Fish(i, j, dir, idx, true));
                map[i][j] = idx;
            }
        }

        fishInfos.sort((o1, o2) -> o1.idx - o2.idx);

        Fish fish = fishInfos.get(map[0][0] - 1);
        Shark shark = new Shark(0, 0, fish.dir, fish.idx);
        fish.isAlive = false;
        map[0][0] = -1;

        answer = 0;
        dfs(map, shark, fishInfos);
        System.out.println(answer);
    }

    private static void dfs(int[][] map, Shark shark, List<Fish> fishInfos) {
        answer = Math.max(answer, shark.eatFishCnt);

        //물고기 이동
        for (Fish currFish : fishInfos) {
            if (!currFish.isAlive) continue;

            for (int j = 0; j < 8; j++) {
                int nextDir = (currFish.dir + j) % 8;
                int drow = currFish.row + dr[nextDir];
                int dcol = currFish.col + dc[nextDir];

                //움직일 수 있을 때 움직이기
                if (drow >= 0 && dcol >= 0 && drow < 4 && dcol < 4 && map[drow][dcol] != -1) {
                    map[currFish.row][currFish.col] = 0;
                    //물고기가 있다면 위치 바꾸기
                    if (map[drow][dcol] != 0) {
                        Fish changeFish = fishInfos.get(map[drow][dcol] - 1);
                        changeFish.row = currFish.row;
                        changeFish.col = currFish.col;
                        map[currFish.row][currFish.col] = changeFish.idx;
                    }

                    currFish.row = drow;
                    currFish.col = dcol;
                    map[drow][dcol] = currFish.idx;
                    currFish.dir = nextDir;
                    break;
                }
            }
        }

        //상어 이동
        for (int i = 1; i < 4; i++) {
            int drow = shark.row + dr[shark.dir] * i;
            int dcol = shark.col + dc[shark.dir] * i;

            //범위 안이고 먹을 물고기가 있다면 움직이기
            if (drow >= 0 && dcol >= 0 && drow < 4 && dcol < 4 && map[drow][dcol] > 0) {
                int[][] tempMap = copyMap(map);
                List<Fish> tempFishInfos = copyFishInfos(fishInfos);

                tempMap[shark.row][shark.col] = 0;
                Fish eatFish = tempFishInfos.get(map[drow][dcol] - 1);
                Shark nextShark = new Shark(drow, dcol, eatFish.dir, shark.eatFishCnt + eatFish.idx);
                eatFish.isAlive = false;
                tempMap[drow][dcol] = -1;

                dfs(tempMap, nextShark, tempFishInfos);
            }
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static List<Fish> copyFishInfos(List<Fish> fishInfos) {
        List<Fish> newFishInfos = new ArrayList<>();
        fishInfos.forEach(fish -> newFishInfos.add(new Fish(fish.row, fish.col, fish.dir, fish.idx, fish.isAlive)));
        return newFishInfos;
    }

    static class Info {
        int row, col, dir;

        public Info(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    static class Shark extends Info {
        int eatFishCnt;

        public Shark(int row, int col, int dir, int eatFishCnt) {
            super(row, col, dir);
            this.eatFishCnt = eatFishCnt;
        }
    }

    static class Fish extends Info {
        int idx;
        boolean isAlive;

        public Fish(int row, int col, int dir, int idx, boolean isAlive) {
            super(row, col, dir);
            this.idx = idx;
            this.isAlive = isAlive;
        }
    }
}