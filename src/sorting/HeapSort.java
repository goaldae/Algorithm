package sorting;

public class HeapSort {
    public static void main(String args[]) { 
        int data[] = {12, 11, 13, 5, 6, 7}; 
        int n = data.length; 
        
        HeapSort ob = new HeapSort();
        
        for(int i = (n/2)-1; i>=0; i--){
        	ob.heapify(data, i);
        }
        for(int i = 0; i<n; i++){
        	System.out.println(data[i]);
        }
        
    } 
    
    void heapify(int[] data, int i){
    	System.out.println(data[i]);	
    	int l = i * 2 + 1; //���� �ڽ� ���
    	int r = i * 2 + 2; //������ �ڽ� ���
    	int largest = i;
    	int temp;
    	
    	if(data[l] > data[largest] && data[l] >= data[r]){ //�θ𺸴� ���� �ڽ��� ū ���
    		largest = l;
    	//}else if(data[r] > data[largest] && data[r] >= data[l]){
    		largest = r;
    	}
    	
    	if(largest != i){
    		temp = data[i];
    		data[i] = data[largest];
    		data[largest] = temp;
    		
    		heapify(data, largest);
    	}
    }
}
