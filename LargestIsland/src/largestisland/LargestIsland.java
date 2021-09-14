/*
 * Hannah Edwards
 * Find the largest island in a cluster
 */
package largestisland;


public class LargestIsland {

    public static void main(String[] args) {
        
        //test case 1
        int[][] testOne = {
            {1,1,1,0,0,1},
            {0,1,1,0,1,0},
            {1,0,1,0,1,0},
            {0,0,0,0,1,1},
            {1,0,0,0,0,0},
            {0,1,0,0,0,0}};
        
        //test case 2
        int[][] testTwo = {
            {1,0,1,1},
            {1,0,0,1},
            {0,1,0,1},
            {0,0,0,1}};
        
        //test case 1
        int[] answer = markLargest(testOne);
        System.out.println("Largest Island Size: " + answer[0] + " Represented as: " + answer[1]);
        print(testOne);
        System.out.println("______________________________________________");
        //test case 2
        answer = markLargest(testTwo);
        System.out.println("Largest Island Size: " + answer[0] + " Represented as: " + answer[1]);
        print(testTwo);
    }
    
    //print array
    static void print(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    static public int[] markLargest(int[][] map){
        //keep track of where you have been
        boolean[][] visited = new boolean[map.length][map[0].length];        
        
        //traverse through all of the cells of the map
        int label = 1;   //each island gets a label 
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {                
                //check to see if this location is an unvisited island                
                if (map[i][j] == 1 && !visited[i][j]){
                    //visit all of the cells in this island                    
                    map = measureSize(map, i, j, visited, label);
                    //move on to next island
                    label++;
                }
            }
        }        
        int[] answer = getStats(map, label);
        return answer;        
    }
    
    static public int[][] measureSize(int map[][], int row, int col, boolean visited[][], int label){        
        
        //these check the 8 possible surrounding spaces for the row/col combo        
        int rowNum[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int colNum[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        
        // Mark this cell as visited
        visited[row][col] = true; 
        
        //mark it as part of the island
        map[row][col] = label;        
        
        for (int i = 0; i < rowNum.length; i++) {
            //if we can go to this space
            if(promising(map, row + rowNum[i], col + colNum[i], visited)){   
                //check the space next to it
                measureSize(map, row + rowNum[i], col + colNum[i], visited, label);                
            }            
        }
        return map;        
    }
    
    static public boolean promising(int[][] map, int row, int col, boolean[][] v){
        //check bounds
        return (row < map.length) && (row >= 0) 
                && (col < map[0].length) && (col >= 0)
                && (!v[row][col]) && (map[row][col] == 1);
    }
    
    static int[] getStats(int[][] m, int maxL){
        
        //tally how many there are of each label
        int[] sizes = new int[maxL];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j] != 0){
                    sizes[m[i][j]-1] += 1;
                }
            }
        }
        
        //find largest island size from list
        int maxCount = 0;
        int keyLabel = 0;
        for (int i = 0; i < sizes.length; i++) {
            if(sizes[i] > maxCount){
                maxCount = sizes[i];
                keyLabel = i+1;
            }
        }
        
        int[] answer = {maxCount, keyLabel};
        return answer;
    }
    
 
    
}
