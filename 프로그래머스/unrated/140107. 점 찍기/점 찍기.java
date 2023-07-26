class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (int i = 0; i <= d; i += k) {
            long y = (long) Math.sqrt((long) Math.pow(d, 2) - (long) Math.pow(i, 2)) / k;
            answer += y + 1;
        }
        return answer;
    }
}