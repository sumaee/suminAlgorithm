class Solution {
    public long solution(int w, int h) {
       long gcd = w > h ? getGcd(w, h) : getGcd(h, w);

        long real = (long) w * h;
        w /= gcd;
        h /= gcd;

        long white = w + h - 1;
        return real - white * gcd;
    }
     private static int getGcd(int a, int b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }
}