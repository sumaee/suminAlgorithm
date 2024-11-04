import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Queue<Node> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = i + 1; j <= n; j++) {
                if (line.charAt(j - 1) == 'Y') {
                    que.offer(new Node(i, j));
                }
            }
        }

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        int[] answer = new int[n + 1];
        int cnt = 0;

        Queue<Node> last = new LinkedList<>();
        while (!que.isEmpty()) {
            Node curr = que.poll();

            if (union(curr.i, curr.j)) {
                last.offer(curr);
            } else {
                answer[curr.i]++;
                answer[curr.j]++;
                cnt++;
            }
        }

        if (cnt != n - 1) {
            System.out.println(-1);
            return;
        }

        while (cnt++ < m) {
            if (last.isEmpty()) {
                System.out.println(-1);
                return;
            }

            answer[last.peek().i]++;
            answer[last.peek().j]++;
            last.poll();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            return true;
        }

        if (a < b) parents[findB] = findA;
        else parents[findA] = findB;

        return false;
    }

    private static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}