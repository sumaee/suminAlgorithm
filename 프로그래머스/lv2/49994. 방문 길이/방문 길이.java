import java.util.*;

class Solution {
    public int solution(String dirs) {
        //위치를 담을 set 선언
        Set<Locate> set = new HashSet<>();
        //초반 위치 설정
        int startX = 0, startY = 0;
        int endX = 0, endY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            switch (dir) {
                case 'U':
                    if (startY != 5) {
                        endY += 1;
                        set.add(new Locate(startX, startY, endX, endY));
                        startY = endY;
                    }
                    break;
                case 'D':
                    if (startY != -5) {
                        endY -= 1;
                        set.add(new Locate(startX, startY, endX, endY));
                        startY = endY;
                    }
                    break;
                case 'L':
                    if (startX != -5) {
                        endX -= 1;
                        set.add(new Locate(startX, startY, endX, endY));
                        startX = endX;
                    }
                    break;
                case 'R':
                    if (startX != 5) {
                        endX += 1;
                        set.add(new Locate(startX, startY, endX, endY));
                        startX = endX;
                    }
                    break;
            }
        }
        return set.size();

    }
}

class Locate {
    int inX, inY, outX, outY;

    public Locate(int inX, int inY, int outX, int outY) {
        this.inX = inX;
        this.inY = inY;
        this.outX = outX;
        this.outY = outY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locate)) return false;
        Locate locate = (Locate) o;
        return (inX == locate.inX && inY == locate.inY && outX == locate.outX && outY == locate.outY)
                || (inX == locate.outX && inY == locate.outY && outX == locate.inX && outY == locate.inY);
    }

        @Override
    public int hashCode() {
        return Objects.hash(inX, inY, outX, outY) + Objects.hash(outX, outY, inX, inY);
    }
} 