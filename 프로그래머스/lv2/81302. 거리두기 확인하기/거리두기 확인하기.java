import java.lang.*;
import java.util.*;

class Solution {
    int[] answer;
    int idx;
    public int[] solution(String[][] places) {       
        answer = new int[places.length];
        idx = 0;
        //시험실 마다 확인
        for (String[] place : places) {
            checkPlace(place);
        }

        return answer;
    }

    private void checkPlace(String[] place) {
        char[][] seat = new char[5][5];
        List<Location> locate = new ArrayList<>();

        //seat에 place를 재배치 하면서 사람이 앉아 있는 부분의 위치만 뽑아내기
        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                seat[i][j] = place[i].charAt(j);
                if (seat[i][j] == 'P') {
                    locate.add(new Location(i, j));
                }
            }
        }

        //해당 강의실이 거리두기를 지켰는지 확인
        answer[idx++] = checkDistance(seat, locate);
    }

    private int checkDistance(char[][] seat, List<Location> locate) {

        //사람 위치에 따라 주변 사람 확인
        for (int i = 0; i < locate.size() - 1; i++) {
            Location people1 = locate.get(i);
            for (int j = i + 1; j < locate.size(); j++) {
                Location people2 = locate.get(j);

                //두 사람 사이의 거리 구하기
                int distance = getDistance(people1, people2);
                //만약 두 사람 사이의 거리가 2보다 작다면 거리두기 안지킨 것이므로 0 return
                if (distance < 2) {
                    return 0;
                }

                //만약 두 사람의 거리가 2라면 위치에 따라 확인해보기
                if (distance == 2) {
                    //만약 두 사람이 같은 행에 앉아 있는데 사이에 책상이 있다면 0 return
                    if (people1.row == people2.row && seat[people1.row][(people1.col + people2.col) / 2] == 'O') {
                        return 0;
                    }
                    //두 사람이 같은 열에 앚아 있는데 사이에 책상이 있다면 0 return
                    else if (people1.col == people2.col && seat[(people1.row + people2.row) / 2][people1.col] == 'O') {
                        return 0;
                    }

                    //두 사람이 대각선으로 앉아 있는데 각 사람의 대각선에 파티션이 없다면 0 return
                    else if (Math.abs(people1.row - people2.row) == 1 && Math.abs(people1.col - people2.col) == 1) {
                        if (seat[people1.row][people2.col] != 'X' || seat[people2.row][people1.col] != 'X') {
                            return 0;
                        }
                    }
                }
            }
        }
        //여기까지 왔다는거는 거리두기 지켰다는 것이므로 1 반환
        return 1;
    }

    private int getDistance(Location people1, Location people2) {
        return Math.abs(people1.row - people2.row) + Math.abs(people1.col - people2.col);
    }
}


class Location {
    int row, col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
