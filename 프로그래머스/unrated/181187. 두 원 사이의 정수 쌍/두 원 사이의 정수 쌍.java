class Solution {
    public long solution(int r1, int r2) {
         long answer = 0;

        long r1X = (long) Math.pow(r1, 2);
        long r2X = (long) Math.pow(r2, 2);

        long side = 0;
        for (int i = 0; i <= r2; i++) {
            long y1 = (long) Math.sqrt(r2X - (long) Math.pow(i, 2));
            long y2 = (long) Math.sqrt(r1X - (long) Math.pow(i, 2));

            //테두리 추가
            if (Math.sqrt(r1X - Math.pow(i, 2)) % 1 == 0) {
                side++;
            }
            answer += (y1 - y2) * 4;
        }

        return answer + (side * 4 - 4);
    }
}