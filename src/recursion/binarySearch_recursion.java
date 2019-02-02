package practice_java;

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
        if(x>=mazeSize || y >= mazeSize || x<0 || y<0){ //범위 밖일 때
            return false;
        }else if(maze[x][y]!=PHATH){ //벽이거나, 지나온 길일때 즉, 길이 아닐 때
            return false;
        }else if(x==mazeSize-1&&y==mazeSize-1){ //출구에 도달했을 때
        	maze[x][y]=PASSED;
        	return true;
        }else{ //아직 가본적 없는 뚫려있는 길일 떄
            maze[x][y] = PASSED; //가본 길이라고 표시
            /////////////////네 셀의 결과가 모두 나오기 전까지는 여기까지 함///////////////////
            
            if(findMaze(x-1, y) || findMaze(x, y-1) || findMaze(x+1, y) || findMaze(x, y+1)){ //주변 셀들도 확인
                return true; //주변 셀들의 확인이 모두 끝나야 여기로 옴
            }
            maze[x][y]=WRORRNG;
            return false;
        }
    }
}

