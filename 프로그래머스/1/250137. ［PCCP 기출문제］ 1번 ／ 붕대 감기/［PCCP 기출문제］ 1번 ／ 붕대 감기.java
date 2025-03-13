import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int currHealth = health;
        int lastAttackTime = 0;
        for(int[] attack : attacks){
            if(currHealth <= 0){
                return -1;
            }
            
            int time = attack[0] - lastAttackTime - 1;
            int recovery = time / bandage[0];
            currHealth = Math.min(health, currHealth + time * bandage[1]);
            currHealth = Math.min(health, currHealth + recovery * bandage[2]);
            
            currHealth -= attack[1];
            lastAttackTime = attack[0];
            
            if(currHealth <= 0 ) return -1;
        }
        
        return currHealth;
    }
}