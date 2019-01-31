package practice_java;

public class p0131 {
	public static void main(String[] args) {
		int [] arr = {1,2,3,4,5};
		int res = search(arr, 0, 4, 5);
		
		System.out.printf("%d", res);	
	}
	
	public static int search(int [] arr, int begin, int end, int target){
		if(begin>end){
			return -1;
		}else if(arr[begin] == target){
			return begin;
		}else{
			return search(arr, begin+1, end, target);
		}
	}
	
}
