package practice_java;

public class findMax {

	public static void main(String[] args) {
		int [] arr = {100, 200, 1, 3, 4, 5};
		int res = findMax(arr, 0, arr.length-1);
		System.out.printf("%d", res);

	}
	
	public static int findMax(int [] arr, int begin, int end){
		if(begin==end){
			return arr[begin];
		}else{
			return Math.max(arr[begin], findMax(arr, begin+1, end));
		}
	}

}
