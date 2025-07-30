import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] isVisited;
    static Character[][] puzzle;
    static boolean isFind;
    static String[] words;
    static int maxScore;
    static String maxStringStr;
    static int findWordCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int w = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        words = new String[w];
        for (int i = 0; i < w; i++) {
            String str = br.readLine();
            words[i] = str;
            trie.insert(str);
        }

        Arrays.sort(words);
        br.readLine();
        int b = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b; i++) {
            puzzle = new Character[4][4];

            for (int j = 0; j < 4; j++) {
                String str = br.readLine();
                for (int k = 0; k < 4; k++) {
                    puzzle[j][k] = str.charAt(k);
                }
            }

            isVisited = new boolean[4][4];
            maxScore = 0;
            maxStringStr = "";
            findWordCnt = 0;
            for (String word : words) {
                isFind = false;
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (word.charAt(0) == puzzle[r][c] && trie.root.child.containsKey(puzzle[r][c])) {
                            isVisited[r][c] = true;
                            dfs(trie.root.child.get(puzzle[r][c]), r, c, 1, word);
                            isVisited[r][c] = false;
                        }
                        if (isFind) break;
                    }
                    if (isFind) break;
                }
            }
            sb.append(maxScore).append(" ").append(maxStringStr).append(" ").append(findWordCnt).append("\n");
            if (i == b - 1) {
                break;
            } else {
                br.readLine();
            }
        }

        System.out.println(sb);
    }

    private static void dfs(Node cur, int row, int col, int depth, String word) {
        if (isFind) return;
        if (depth == word.length()) {
            findWordCnt++;
            isFind = true;
            maxScore += checkScore(word);
            if (maxStringStr.length() < word.length()) maxStringStr = word;
            return;
        }

        for (int i = 0; i < 8; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            if (drow < 0 || dcol < 0 || drow >= 4 || dcol >= 4) continue;
            if (isVisited[drow][dcol]) continue;

            if (word.charAt(depth) == puzzle[drow][dcol] && cur.child.containsKey(puzzle[drow][dcol])) {
                isVisited[drow][dcol] = true;
                dfs(cur.child.get(puzzle[drow][dcol]), drow, dcol, depth + 1, word);
                isVisited[drow][dcol] = false;
            }
        }
    }

    private static int checkScore(String word) {
        if (word.length() == 1 || word.length() == 2) return 0;
        if (word.length() == 3 || word.length() == 4) return 1;
        if (word.length() == 5) return 2;
        if (word.length() == 6) return 3;
        if (word.length() == 7) return 5;
        if (word.length() == 8) return 11;

        return -1;
    }

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            child = new HashMap<>();
            isEnd = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.isEnd = true;
        }
    }
}
