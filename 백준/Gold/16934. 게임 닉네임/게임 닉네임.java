import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, Integer> nameCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        TrieNode trieNode = new TrieNode();
        nameCnt = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = br.readLine();

            sb.append(trieNode.search(name)).append("\n");
            trieNode.insert(name);
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> child;
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

        public void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }


            node.isEnd = true;
        }

        public String search(String word) {
            Node node = this.root;
            nameCnt.put(word, nameCnt.getOrDefault(word, 0) + 1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                sb.append(c);
                if (!node.child.containsKey(c)) {
                    return sb.toString();
                } else {
                    node = node.child.get(c);
                }
            }

            return nameCnt.get(word) != 1 ? word + nameCnt.get(word) : word;
        }
    }
}
