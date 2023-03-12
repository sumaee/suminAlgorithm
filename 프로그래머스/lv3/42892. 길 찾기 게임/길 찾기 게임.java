import java.lang.*;
import java.util.*;

class Solution {
    
    int[][] answer;
    int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        Node[] node = new Node[nodeinfo.length];
        //일단 노드에 담기
         for(int i=0; i<nodeinfo.length; i++)
             node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1, null, null);
        
        //y축 기준으로 내림차순으로 먼저 정렬하고 y가 같다면 x 축 오름차순 기준으로 정렬
        Arrays.sort(node, (o1, o2) -> o1.y==o2.y ? o1.x-o2.x : o2.y-o1.y);
        
        //왼쪽 자식와 오른쪽 자식 집어넣기
        Node root = node[0];
        for(int i=1; i<node.length; i++)
            findChild(root, node[i]);
        
        answer = new int[2][node.length];
        //전위 순회
        idx = 0;
        preorder(root);
        
        //후위 순회
        idx = 0;
        postorder(root);
        
        return answer;
    }
    
    //왼쪽 오른쪽 자식 집어넣기
    public void findChild(Node root, Node now){
        
        if(now.x < root.x){
            if(root.left == null) root.left = now;
            else findChild(root.left, now);
        }else{
            if(root.right == null) root.right = now;
            else findChild(root.right, now);
        }
    }
    
    //전위 순회
    public void preorder(Node now){
        if(now != null){
            answer[0][idx++] = now.value;
            preorder(now.left);
            preorder(now.right);
        }
        
    }
    
    //후위 순회
    public void postorder(Node now){
        if(now != null){
            postorder(now.left);
            postorder(now.right);
            answer[1][idx++] = now.value;
        }
    }
}

class Node{
    int x, y, value;
    Node left, right;
        
    public Node(int x, int y, int value, Node left, Node right){
        this.x=x;
        this.y=y;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}