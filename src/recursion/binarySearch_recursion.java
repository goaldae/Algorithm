package recursion;

public class binarySearch_recursion {
    public static int [][] maze = {
        {0,1,0,1,1},
        {0,1,0,0,0},
        {0,0,0,1,1},
        {1,1,0,0,0},
        {1,0,1,1,0}
    };
    public static int mazeSize = 5;
    
    public static int PHATH = 0;
    public static int BLOCK = 1;
    public static int PASSED = 3;
    public static int WRORRNG = 2;


	public static void main(String[] args) {
        findMaze(0, 0);
        printMaze();
        
    }
	public static void printMaze(){
		for(int i = 0; i<mazeSize; i++){
            for(int j = 0; j<mazeSize; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
	}

    public static boolean findMaze(int x, int y){ //true or false 문제
        if(x>=mazeSize || y >= mazeSize || x<0 || y<0){ //범위 밖일 ?��
            return false;
        }else if(maze[x][y]!=PHATH){ //벽이거나, �??��?�� 길일?�� �?, 길이 ?��?�� ?��
            return false;
        }else if(x==mazeSize-1&&y==mazeSize-1){ //출구?�� ?��?��?��?�� ?��
        	maze[x][y]=PASSED;
        	return true;
        }else{ //?���? �?본적 ?��?�� ?��?��?��?�� 길일 ?��
            maze[x][y] = PASSED; //�?�? 길이?���? ?��?��
            /////////////////?�� ???�� 결과�? 모두 ?��?���? ?��까�??�� ?��기까�? ?��///////////////////
            
            if(findMaze(x-1, y) || findMaze(x, y-1) || findMaze(x+1, y) || findMaze(x, y+1)){ //주�? ???��?�� ?��?��
                return true; //주�? ???��?�� ?��?��?�� 모두 ?��?��?�� ?��기로 ?��
            }
            maze[x][y]=WRORRNG;
            return false;
        }
    }
}

