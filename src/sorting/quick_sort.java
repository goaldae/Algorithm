package sorting;

public class quick_sort {
		
	public static void main(String[] args) {
		int[] data = {3, 4, 1, 2, 10, -9, 8, 2, 5};
		int n = data.length;
		
		quick_sort ob = new quick_sort();
		
		ob.quickSort(data, 0, n-1);
		ob.printData(data, n);
		
	}
	
	public void printData(int[] data, int n){
		for(int i = 0; i<n; i++){
			System.out.printf("%d ", data[i]);
		}
	}
	public void quickSort(int[] data, int p, int r){
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
			
			quickSort(data, p, i);
			quickSort(data, i+2, r);
		}
	}
}
