package sorting;

public class HeapSort {
    public static void main(String args[]) { 
        int data[] = {7, 13, 6, 5, 11, 12}; 
        int n = data.length; 
        
        HeapSort ob = new HeapSort();
        
        ob.buildHeap(data, n); //heap tree 만들기
        
        printData(data, n);
       
        ob.heapSort(data, n-1); //완성된 heap tree를 heap sort하기
      
        printData(data, n);        
    } 
    
    public static void printData(int[] data, int n){
    	 for(int i = 0; i<n; i++){
         	System.out.printf("%d ", data[i]);
         }
         System.out.println();
    }
    
    void buildHeap(int[] data, int n){
    	for(int i = (n-1)/2; i>=0; i--){
        	heapify(data, i, n);
        }
    }
    
    void heapSort(int[] data, int i){
    	if(i<=0){
    		return;
    	}
    	
    	int temp;
    	temp = data[0];
    	data[0] = data[i];
    	data[i] = temp;
    	
    	heapify(data, 0, i);
    	heapSort(data, i-1);
    }
    
    void heapify(int[] data, int i, int n){
    	int l = i * 2 + 1; //왼쪽 자식 노드
    	int r = i * 2 + 2; //오른쪽 자식 노드
    	int largest = i;
    	int temp;
    	
    	if(l < n && data[l] > data[largest]){ //부모보다 왼쪽 자식이 큰 경우
    		largest = l;
    	}
    	if(r < n && data[r] > data[largest]){
    		largest = r;
    	}
    	
    	if(largest != i){
    		temp = data[i];
    		data[i] = data[largest];
    		data[largest] = temp;
    		
    		heapify(data, largest, n);
    	}
    }
}
