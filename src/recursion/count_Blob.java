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
		countPixel(0,0);
		System.out.println(count);
	}
	public static boolean countPixel(int x, int y){
		if(image[x][y] == BGD_PIXEL){
			return false;
		}else if(x<0 || y<0 || x>=imageSize || y>=imageSize){
			return false;
		}else if(image[x][y] == CNT_PIXEL){
			return false;
		}else{
			count++;
			image[x][y]=CNT_PIXEL;
			if(countPixel(x-1,y+1)||countPixel(x,y+1)||countPixel(x+1,y+1)||
					countPixel(x-1,y)||countPixel(x+1,y)||countPixel(x-1,y-1)||
					countPixel(x,y-1)||countPixel(x+1,y-1)){
				return true;
			}
			return false;
		}
	}

}
