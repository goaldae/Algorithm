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

    public static boolean findMaze(int x, int y){ //true or false ë¬¸ì œ
        if(x>=mazeSize || y >= mazeSize || x<0 || y<0){ //ë²”ìœ„ ë°–ì¼ ?•Œ
            return false;
        }else if(maze[x][y]!=PHATH){ //ë²½ì´ê±°ë‚˜, ì§??‚˜?˜¨ ê¸¸ì¼?•Œ ì¦?, ê¸¸ì´ ?•„?‹ ?•Œ
            return false;
        }else if(x==mazeSize-1&&y==mazeSize-1){ //ì¶œêµ¬?— ?„?‹¬?–ˆ?„ ?•Œ
        	maze[x][y]=PASSED;
        	return true;
        }else{ //?•„ì§? ê°?ë³¸ì  ?—†?Š” ?š«? ¤?žˆ?Š” ê¸¸ì¼ ?–„
            maze[x][y] = PASSED; //ê°?ë³? ê¸¸ì´?¼ê³? ?‘œ?‹œ
            /////////////////?„¤ ???˜ ê²°ê³¼ê°? ëª¨ë‘ ?‚˜?˜¤ê¸? ? „ê¹Œì??Š” ?—¬ê¸°ê¹Œì§? ?•¨///////////////////
            
            if(findMaze(x-1, y) || findMaze(x, y-1) || findMaze(x+1, y) || findMaze(x, y+1)){ //ì£¼ë? ???“¤?„ ?™•?¸
                return true; //ì£¼ë? ???“¤?˜ ?™•?¸?´ ëª¨ë‘ ??‚˜?•¼ ?—¬ê¸°ë¡œ ?˜´
            }
            maze[x][y]=WRORRNG;
            return false;
        }
    }
}

