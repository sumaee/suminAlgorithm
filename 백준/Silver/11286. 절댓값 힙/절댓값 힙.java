import java.lang.*;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Num> que = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n ; i++){
            int now = Integer.parseInt(br.readLine());
            if( now == 0){
                if (!que.isEmpty()) {
                    sb.append(que.poll().num).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
            }else{
                que.offer(new Num(now, Math.abs(now)));
            }

        }

        System.out.println(sb);
    }
}

class Num implements Comparable<Num>{
    int num, abs;
    public Num(int num, int abs){
        this.num = num;
        this.abs = abs;
    }

    @Override
    public int compareTo(Num curr) {
        if (this.abs == curr.abs) return this.num-curr.num;
        return this.abs - curr.abs;
    }
}