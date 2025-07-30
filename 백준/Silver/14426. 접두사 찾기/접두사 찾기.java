import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine());
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            String str = br.readLine();

            if (trie.search(str)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new HashMap<Character, Node>();
            this.isEnd = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.isEnd = true;
        }

        public boolean search(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!node.child.containsKey(c)) {
                    return false;
                }
                node = node.child.get(c);
            }

            return true;
        }
    }
}
