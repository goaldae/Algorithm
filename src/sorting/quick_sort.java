package sorting;

import java.util.Arrays;

public class quick_sort {

	void quickSort(int[] arr, int L, int R){
		if(L<R){
			int pivot = partition(arr, L, R);
			quickSort(arr, L, pivot-1);
			quickSort(arr, pivot+1, R);
		}
	}

	int partition(int[] arr, int L, int R){
		int p = (L+R)/2;
		int temp;
		while(L<R){
			while(arr[L] < arr[p] && L < R) L++;
			while(arr[R]>=arr[p] && L < R) R--;
			if(L<R){
				temp = arr[L];
				arr[L] = arr[R];
				arr[R] = temp;
				if(L==p) p = R;
			}
		}
		temp = arr[L];
		arr[L] = arr[p];
		arr[p] = temp;

		return L;
	}

	void print(int[] arr){
		for(int i = 0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}

	public static void main(String[] args) {
		int[] arr = {3,2,5,7,10,0,20,23,41,2,5,6,7,1,3,18,94,1};
		quick_sort Q = new quick_sort();
		//System.out.print(arr.length);
		Q.quickSort(arr, 0, arr.length-1);
		Q.print(arr);

	}
}
