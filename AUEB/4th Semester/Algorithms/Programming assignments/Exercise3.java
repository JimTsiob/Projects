class Exercise3{
	private int[] W;
	private int C = 0;
	
	public Exercise3(int arr[],int sum){
		W = new int [arr.length];
		for (int i = 0; i<W.length; i++){
			W[i] = arr[i];
		}
		C = sum;
	}
	
	boolean subsetSumBox(int[] W, int C){
		// h timh tou subset[i][j] tha 
        // einai true an yparxei yposynolo tou 
        // W[0..j-1] me athroisma pou isoutai me C.
		// arxika dhmiourgoume ton pinaka subset pou periexei times
		// true kai false analoga me to ean to athroisma stis theseis
		// autes isoutai me C.
		boolean subset[][] = new boolean[C + 1][W.length + 1]; 
		
		// an to athroisma einai 0 tote h apanthsh einai true.
		for (int i = 0; i <= W.length; i++){
			subset[0][i] = true;
		} 
        //an to athroisma den einai 0 kai o W einai adeios
        //tote epistrefei false.
        for (int i = 1; i <= C; i++){
			subset[i][0] = false; 
		} 
            
        // Gemizoume ton pinaka yposynolwn me bottom up tropo.
        for (int i = 1; i <= C; i++) { 
            for (int j = 1; j <= W.length; j++) { 
                subset[i][j] = subset[i][j - 1]; 
                if (i >= W[j - 1]) 
                    subset[i][j] = subset[i][j] 
                                   || subset[i - W[j - 1]][j - 1]; 
            } 
        } 
  
        return subset[C][W.length]; 
	}
	
	public void solveAndPrintExercise3(){
		System.out.print("(Exercise 3 solution) W is {");
		for (int i = 0; i<W.length; i++){
			if (i == W.length-1){
				System.out.print(W[i]);
				break;
			}
			System.out.print(W[i] + ",");
		}
		System.out.println("}");
		System.out.println("C = " + C);
		System.out.println("is there a subset in this W that has a sum that is equal to " + C + "?: " + subsetSumBox(W,C));
		System.out.println("\n");
	}
}