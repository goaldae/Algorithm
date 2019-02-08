package sorting;

public class quick_sort {
	static int[] data = {3, 4, 1, 2, 10, -9, 8, 2, 5};
	static int n = data.length;
	
	public static void main(String[] args) {		
		quickSort(0, n-1);
		
		System.out.println();
		for(int i = 0; i<n; i++){
			System.out.printf("%d ", data[i]);
		}
		System.out.println();
	}

	public static void quickSort(int p, int r){
		for(int i = 0; i<n; i++){
			System.out.printf("%d ", data[i]);
		}
		System.out.println();
		int temp;
		int i = p-1;
		int j = p;
		int x = r;
		
		if(p<r){
			while(j<r){
				if(data[j]<data[x]){ //피벗보다 작은 경우
					i++;
					temp = data[i];
					data[i] = data[j];
					data[j] = temp;
					j++;
				}else{ //피벗보다 크거나 같아도 해야하나?
					j++;
				}
			}
			temp = data[i+1];
			data[i+1] = data[x];
			data[x] = temp;
			
			quickSort(p, i);
			quickSort(i+2, r);
		}
	}
}
