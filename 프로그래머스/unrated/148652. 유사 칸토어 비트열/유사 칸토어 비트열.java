class Solution {
    public int solution(int n, long l, long r) {
        return dfs(n, l, r, 1);
    }
    
     private static int dfs(int n, long l, long r, long idx) {
        //구역을 나누다가 0이라면 1 반환
        if (n == 0) {
            return 1;
        }

        int answer = 0;
        //나눌 구간
        long area = (long) Math.pow(5, n - 1);
        //나눠진 5개의 구간을 돌면서 dfs 실행
        for (int i = 0; i < 5; i++) {
            //가운데 구간은 0이 있는 구간이므로 제외 || 끝 범위가 찾고 있는 범위를 벗어나면 패스 || 처음 범위가 찾고 있는 범위를 벗어나면 패스
            if (i == 2 || r < idx + area * i || l > (idx + (area * (i + 1) - 1))) continue;

            answer += dfs(n - 1, l, r, idx + area * i);
        }
        return answer;
    }
}