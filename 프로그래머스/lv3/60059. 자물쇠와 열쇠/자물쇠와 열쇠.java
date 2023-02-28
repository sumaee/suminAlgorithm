import java.lang.*;
import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int lockLen = lock.length;
        int keyLen = key.length;
        int start = keyLen - 1;
        
        int[][] newLock = new int[lockLen+keyLen*2-2][lockLen+keyLen*2-2];
        
        //새로운 자물쇠에 원래 자물쇠 박기
        for(int i=keyLen-1 ; i<keyLen-1+lockLen; i++){
            for(int j=keyLen-1 ; j<keyLen-1+lockLen; j++){
                newLock[i][j] = lock[i-(keyLen-1)][j-(keyLen-1)];
            }
        }
        
        //4번확인
        for(int i=0; i<4; i++){
            if(check(newLock, key, lockLen)){
                //열쇠가 맞다는 뜻이므로 반환
                return true;
            }
            //아니라면 90도 회전시켜서 다시 확인
            key = rotation(key);
        }
        return false;
    }
    
    //회전
    public int[][] rotation(int[][] key){
        int[][] newKey = new int[key.length][key.length];
        
        for(int i=0; i<key.length; i++){
            for(int j=0; j<key.length; j++){
                newKey[i][j] = key[key.length - j - 1][i];
            }
        }
        
        return newKey;        
    }
    
    //열쇠가 자물쇠에 맞는지 확인
    public boolean check(int[][] newLock, int[][] key, int lockLen){
        
        int newLockLen = newLock.length;
        int keyLen = key.length;
        
        //자물쇠와 열쇠 합치기
        for(int i = 0; i< newLockLen - keyLen + 1 ; i++){
            for(int j=0; j<newLockLen - keyLen +1 ; j++){
                
                // 더하기
                for(int k=0 ; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        newLock[i+k][j+l]+=key[k][l];
                    }
                }
                
                //자물쇠가 맞는지 확인하기
                //원래 자물쇠 위치만 파악해서 확인 하면 됨
                boolean result = true;
               out:  for(int k=keyLen-1 ; k<keyLen-1+lockLen; k++){
                    for(int l=keyLen-1; l<keyLen-1+lockLen; l++){
                        if(newLock[k][l] != 1){
                            result = false;
                            break out;
                        }
                    }                
                }
                
                if(result)
                    return true;
                
                  // 더했던 거 원상 복구
                 for(int k=0 ; k<keyLen; k++){
                    for(int l=0; l<keyLen; l++){
                        newLock[i+k][j+l]-=key[k][l];
                    }
                }
            }
        } 
        return false;
        
    } 
}
