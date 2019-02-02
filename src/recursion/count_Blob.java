package recursion;

public class count_Blob {
	public static int [][] image = {
			{1,0,0,0,0,0,0,1},
			{0,1,1,0,0,1,0,0},
			{1,1,0,0,1,0,1,0},
			{0,0,0,0,0,1,0,0},
			{0,1,0,1,0,1,0,0},
			{0,1,0,1,0,1,0,0},
			{1,0,0,0,1,0,0,1},
			{0,1,1,0,0,1,1,1},
	};
	
	public static int imageSize = 8;
	
	public static int count = 0;

	public static int BGD_PIXEL = 0;
	public static int IMAGE_PIXEL = 1;
	public static int CNT_PIXEL = 2;
	
	public static void main(String[] args) {
		countPixel(7,7);
		System.out.println(count);
		printImage();
		
	}
	
	public static void printImage(){
		for(int i = 0; i<imageSize; i++){
            for(int j = 0; j<imageSize; j++){
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
        System.out.println();
	}
	
	public static boolean countPixel(int x, int y){
		if(x<0 || y<0 || x>=imageSize || y>=imageSize){ //¹üÀ§¸¦ ¹þ¾î³µÀ» ¶§
			return false;
		}else if(image[x][y] != IMAGE_PIXEL){ //1ÀÌ ¾Æ´Ò ¶§
			return false;
		}else{
			count++;
			image[x][y] = CNT_PIXEL; //Áö³ª°¬À½À» Ç¥½Ã
			if(countPixel(x-1,y+1)||countPixel(x,y+1)||countPixel(x+1,y+1)||
					countPixel(x-1,y)||countPixel(x+1,y)||countPixel(x-1,y-1)||
					countPixel(x,y-1)||countPixel(x+1,y-1)){
				return true;
			}
			return false;
		}
	}

}
