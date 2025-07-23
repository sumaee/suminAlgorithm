import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        TrieNode trie = new TrieNode();
        while (n-- > 0) {
            String[] dir = br.readLine().split("\\\\");
            trie.insert(dir);
        }
        trie.search(trie.root, 0);
    }

    static class Node {
        Map<String, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class TrieNode {
        Node root;

        public TrieNode() {
            this.root = new Node();
        }

        public void insert(String[] words) {
            Node node = root;

            for (String word : words) {
                node.child.putIfAbsent(word, new Node());
                node = node.child.get(word);
            }

            node.isEnd = true;
        }

        public void search(Node cur, int depth) {
            if (cur.child != null) {
                List<String> child = new ArrayList<>(cur.child.keySet());
                Collections.sort(child);

                for (String word : child) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print(" ");
                    }
                    System.out.println(word);
                    search(cur.child.get(word), depth + 1);
                }
            }
        }
    }
}
