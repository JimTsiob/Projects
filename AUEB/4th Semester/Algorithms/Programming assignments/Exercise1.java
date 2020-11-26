class Exercise1{
	public int count = 0; // counter for swaps.
	private int inputArray[];
	
	public Exercise1(int[] arr){
		inputArray = new int [arr.length];
		for (int i = 0; i<inputArray.length; i++){
			inputArray[i] = arr[i];
		}
	}
	
	public void solveExercise1(){
		mergeSort(inputArray,0,inputArray.length-1);
	}
	
	void mergeSort(int A[], int left, int right) {
		if (left >= right) return;
		int mid = (left + right)/2;
		mergeSort(A, left, mid);
		mergeSort(A, mid+1, right);
		countSwapMerge(A, left, mid, right);
	}
	
	void countSwapMerge(int[] A,int left,int mid,int right){
		// Find sizes of two subarrays to be merged 
        int n1 = mid - left + 1; 
        int n2 = right - mid; 
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = A[left + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = A[mid + 1 + j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
        // Initial index of merged subarray array 
        int k = left; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                A[k] = L[i]; 
                i++;
				
            } 
            else
            { 
                A[k] = R[j]; 
                j++; 
				count += (mid+1)-(left+i); 
            } 
            k++; 
        } 
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            A[k] = L[i]; 
            i++; 
            k++; 
			
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
			{ 
				A[k] = R[j]; 
				j++; 
				k++;
				
			}
		}
		
	void printSolution(){
		System.out.println("(Exercise 1 solution) Count of swaps is : " + count); 
		System.out.println("\n");
	}
}