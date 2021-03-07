import java.util.*;

class RandomForest{
	ArrayList<Integer> testReviews;
	ArrayList<Integer> trainReviews;
	String[][] testAlg;
	
	RandomForest(int rt,ArrayList<Integer> trainRev,ArrayList<Integer> testRev){
		trainReviews = (ArrayList<Integer>) trainRev.clone();
		testReviews = (ArrayList<Integer>) testRev.clone();
		if (rt > testRev.size()){
			testAlg = new String [rt][2];
			for (int i = 0; i < testReviews.size(); i++){
				if (testReviews.get(i) < 12500) testAlg[i][0] = "pos";
				else testAlg[i][0] = "neg";
			}
		}else{
			testAlg = new String [testReviews.size()][2];
			for (int i = 0; i < testReviews.size(); i++){
				if (testReviews.get(i) < 12500) testAlg[i][0] = "pos";
				else testAlg[i][0] = "neg";
			}
		}
		
	}
	
	void calculate (int m,int n,int runTimes, int revCount, int [][] trainbn, int[][] testbn){
		int a = m-n;
		int max = m-1;
		int min = n;
		int posCount = 0; // plhthos thetikwn (me basei ton algorithmo) kritikwn.
		int negCount = 0; // plhthos arnhtikwn (me basei ton algorithmo) kritikwn.
		String[] sampleResults = new String[runTimes];
		for (int i = 0; i<runTimes; i++){
			int newSize = (int) (Math.random() * ( revCount - 1 )) + 1;
			ArrayList<Integer> sampleRev = new ArrayList<Integer>();
			int number = 0;
			number = (int) (Math.random() * (max - min)); // tyxaio deigma lexewn.
			//System.out.println(number);
			for (int j = 0; j<newSize; j++){
				sampleRev.add(testReviews.get(j)); // edw pairnei to tyxaio deigma twn kritikwn.
			}
			ID3 id3 = new ID3(trainReviews,sampleRev);
			TreeNode tree = id3.makeTree(number,sampleRev,trainbn);
			int[] bnr = new int[number]; //binaryRevRow dhladh to dianisma enos review
			for (int k = 0; k < sampleRev.size(); k++){
				for (int l = 0; l < number; l++){
					bnr[l] = testbn[k][l];
				}
				
			}
			String result = id3.traverseTree(number, bnr, tree);
			sampleResults[i] = result;
			if (sampleResults[i].equals("pos")){
				posCount++;
			}else{
				negCount++;
			}
			if (sampleResults[i].equals("pos")){
				testAlg[i][1] = "pos";
			}else{
				testAlg[i][1] = "neg";
			}
		}
	}
	
	void result(int rt){
		double k = 0.0;
		for (int i = 0; i < rt; i++){
			if (testAlg[i][0].equals(testAlg[i][1])) k++;
		}
		System.out.println("Random Forest accuracy is " + k/(rt)*100 + "%.");
	}
		
}
