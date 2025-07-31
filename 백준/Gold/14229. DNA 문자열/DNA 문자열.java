import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static boolean isFind;
    static Trie trie;
    static String answer;
    static Character[] dna = {'A', 'C', 'G', 'T'};
    static Character[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        trie = new Trie();
        for (int i = 0; i < str.length(); i++) {
            String subStr = str.substring(i);
            trie.insert(subStr);
        }

        answer = "";
        for (int i = 1; i <= str.length(); i++) {
            temp = new Character[i];
            find(0, i);
            if (isFind) {
                break;
            }
        }

        System.out.println(answer);

    }

    private static void find(int idx, int limit) {
        if (isFind) {
            return;
        }

        if (idx == limit) {
            StringBuilder sb = new StringBuilder();
            for (Character c : temp) {
                sb.append(c);
            }

            if (trie.search(sb.toString())) {
                answer = sb.toString();
                isFind = true;
            }
            return;
        }

        for (Character character : dna) {
            temp[idx] = character;
            find(idx + 1, limit);
        }
    }

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
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

        public boolean search(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!node.child.containsKey(c)) {
                    return true;
                }
                node = node.child.get(c);
            }

            return false;
        }
    }
}
