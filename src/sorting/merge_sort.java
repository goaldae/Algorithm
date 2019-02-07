package sorting;

public class merge_sort {
	static int[] data = {3, 4, 1, 2, 10, -9, 8, 2, 5,1,1,1,1,1};
	static int n = data.length;
	
	public static void main(String[] args) {		
		mergeSort(0, n-1);
		
		for(int i = 0; i<n; i++){
			System.out.printf("%d ", data[i]);
		}
	}
	
	public static void mergeSort(int p, int r){
		if(p < r){
			int q = (p+r)/2;
			mergeSort(p, q);
			mergeSort(q+1, r);
			merge(p, q, r);
		}
	}
	
	public static void merge(int p, int q, int r){
		int i = p;
		int j = q+1;
		int c = p;
		int []tmp = new int[n+1];
		
		while(i<=q && j<=r){
			if(data[i]<data[j]){
				tmp[c++]=data[i++];
				
			}else{
				tmp[c++]=data[j++];
				
			}
		}
		while(i<=q){
			tmp[c++]=data[i++];
		}
		while(j<=r){
			tmp[c++]=data[j++];
		}
		
		for(int k = p; k<r+1; k++){
			data[k]=tmp[k];
		}
	}
}
