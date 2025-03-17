import java.lang.*;
import java.util.*;

class Solution {
    static Map<Integer, Locate> pointMap;
    static List<List<Locate>> robotRoutes;
    public int solution(int[][] points, int[][] routes) {
        pointMap = new HashMap<>();
        robotRoutes = new ArrayList<>();
        for(int i=0; i<points.length ;i++){
            pointMap.put(i+1, new Locate(points[i][0], points[i][1]));
        }
        
        int longRoute = 0;
        for(int i=0; i<routes.length ;i++){
            List<Locate> route = findRoute(routes[i]);
            robotRoutes.add(route);
            
            longRoute = Math.max(longRoute, route.size());
        }        
        
        return findStrikeCnt(longRoute);
    }
    
    private static int findStrikeCnt(int longRoute){
        int cnt = 0;
        
        for(int i=0; i<longRoute ; i++){
            Map<Locate, Integer> strikeCnt = new HashMap<>();
            for(List<Locate> route : robotRoutes){
                if(route.size() <= i) continue;
                
                Locate roboteLocate = route.get(i);
                strikeCnt.put(roboteLocate, strikeCnt.getOrDefault(roboteLocate, 0)+1);
            }
            
            for(int count : strikeCnt.values()){
                if(count>1) cnt++;
            }
        }
        
        return cnt;
    }
    
    private static List<Locate> findRoute(int[] route){
        List<Locate> routeList = new ArrayList<>();
        
        for(int i=1 ; i<route.length ; i++){
            Locate start = pointMap.get(route[i-1]);
            Locate end = pointMap.get(route[i]);
            
            int r = start.r;
            int c = start.c;
            
            if(routeList.isEmpty()){
                routeList.add(new Locate(r, c));
            }
            
            while(r != end.r){
                r += (r > end.r ? -1 : 1);
                routeList.add(new Locate(r, c));
            }
            
            while(c != end.c){
                c += (c > end.c ? -1 : 1);
                routeList.add(new Locate(r, c));
            }
        }
        
        return routeList;
    }
    
    static class Locate{
        int r, c;
        
        public Locate(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(r, c);
        }
        
        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Locate locate = (Locate) obj;
            return r == locate.r && c == locate.c;
        }
    }
}