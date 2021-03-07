import java.util.*;
import java.io.*;


class ID3{
	
	//grammes einai to review number, sthles pairnoun ties 0 kai 1, 0 gia to ti einai pragmatika, 1 gia to ti ebgale to algorithmos mas
	private String[][] testAlg;
	private ArrayList<Integer> trainMainReviews;
	private ArrayList<Integer> testMainReviews;
	
	ID3(ArrayList<Integer> trainRev, ArrayList<Integer> testRev){
		trainMainReviews = (ArrayList<Integer>) trainRev.clone();
		testMainReviews = (ArrayList<Integer>) testRev.clone();
		testAlg = new String[testMainReviews.size()][2];
		for (int i = 0; i < testMainReviews.size(); i++){
			if (testRev.get(i) < 12500) testAlg[i][0] = "pos";
			else testAlg[i][0] = "neg";
		}
		System.out.println();
	}
	
	// Mini class TreeNode gia ton algorithmo ID3.
	
	public class TreeNode{
		int a;
		TreeNode left = null;
		TreeNode right = null;
		ArrayList<Integer> reviews;
		int[][] binaryRev;
		String result;
		int word;
	
		TreeNode(){}
	}
	
	
	int[][][] makeTimesCount(int a, ArrayList<Integer> reviews, int[][] bn){
		//to proto keli einai h leksi pou uparxei sto dianisma (opou einai oi sthles tou bineryRev)
		//0 sto 2o keli einai posa mhdenika exei i leksi apo ola ta reviews
		//1 sto 2o keli einai poses monades exei  i leksi apo ola ta reviews
		//0 sto 3o keli einai ama einai apo positive
		//1 sto 3o keli einai ama einai apo negative
		int[][][] timesCount = new int[a][2][2];
		for (int j = 0; j < a; j++){
			timesCount[j][0][0] = 0;
			timesCount[j][1][0] = 0;
			//
			timesCount[j][0][1] = 0;
			timesCount[j][1][1] = 0;
		}
		for (int i = 0; i < reviews.size(); i++){
			for (int j = 0; j < a; j++){
				if (reviews.get(i) < 12500){
					if (bn[i][j] == 0) timesCount[j][0][0]++;
					else timesCount[j][1][0]++;
				}
				else {
					if (bn[i][j] == 0) timesCount[j][0][1]++;
					else timesCount[j][1][1]++;
				}
			}
		}
		return timesCount;
	}
	
	double log2(double n){
		double res = (double)(Math.log(n) / Math.log(2));
		return res;
	}
	
	double calculateEntropy(ArrayList<Integer> reviews, int posRev, int negRev){
		double pyes;
		double pno;
		pyes = -1*((posRev/(double)reviews.size()) * log2(posRev/(double)reviews.size()));
		pno = -1*((negRev/(double)reviews.size()) * log2(negRev/(double)reviews.size()));
		return (pyes + pno);
	}
	
	int calculateInformationGain(int a, int[][][] timesCount, ArrayList<Integer> reviews, double e){
		double[][] wordEntropy = new double[a][2]; //grammes einai h leksi pou uparxei sto dianisma 
		//0 sthlh einai to entropy tis leksis i gia ta mhdenika
		//1 sthlh einai to entropy tis leksis i gia tis monades
		for (int j = 0; j < a; j++){
			//brace yourself
			wordEntropy[j][0] = -1*(timesCount[j][0][0]/(double)(timesCount[j][0][0] + timesCount[j][0][1])) * log2(timesCount[j][0][0]/(double)(timesCount[j][0][0] + timesCount[j][0][1]))
			-1*(timesCount[j][0][1]/(double)(timesCount[j][0][0] + timesCount[j][0][1])) * log2(timesCount[j][0][1]/(double)(timesCount[j][0][0] + timesCount[j][0][1]));
			//ever more OwO
			wordEntropy[j][1] = -1*(timesCount[j][1][0]/(double)(timesCount[j][1][0] + timesCount[j][1][1])) * log2(timesCount[j][1][0]/(double)(timesCount[j][1][0] + timesCount[j][1][1]))
			-1*(timesCount[j][1][1]/(double)(timesCount[j][1][0] + timesCount[j][1][1])) * log2(timesCount[j][1][1]/(double)(timesCount[j][1][0] + timesCount[j][1][1]));
			//almost done
			if (!(wordEntropy[j][0] <= 1)) wordEntropy[j][0] = 0;
			if (!(wordEntropy[j][1] <= 1)) wordEntropy[j][1] = 0;
			////System.out.println(wordEntropy[j][0] + " " + wordEntropy[j][1]);
		}
		double[] information = new double[a];
		for (int j = 0; j < a; j++){
			information[j] = ((timesCount[j][0][0] + timesCount[j][0][1]) / (double)reviews.size()) * wordEntropy[j][0] + ((timesCount[j][1][0] + timesCount[j][1][1]) / (double)reviews.size()) * wordEntropy[j][1];
		}
		int word = -1;
		double maxGain = -1;
		double[] gain = new double[a];
		for (int j = 0; j < a; j++){
			gain[j] = e - information[j];
			if (gain[j] > maxGain){
				word = j;
				maxGain = gain[j];
			}
		}
		return word;
	}
	 
	public TreeNode makeTree(int a, ArrayList<Integer> rev, int[][] bn){
		TreeNode root = new TreeNode();
		root.a = a;
		root.reviews = (ArrayList<Integer>) rev.clone();
		root.binaryRev = new int[root.reviews.size()][a];
		for (int i = 0; i < root.reviews.size(); i++){
			for (int j = 0; j < root.a; j++){
				root.binaryRev[i][j] = bn[i][j];
			}
		}
		int posRev = 0; // number of positive reviews
		int negRev = 0; // number of negative reviews
		for (int i : root.reviews){
			if (i < 12500) posRev++;
			else negRev++;
		}
		double posPer = posRev / (double)root.reviews.size();
		double negPer = negRev / (double)root.reviews.size();
		//checks if we have to make root as a leaf
		if (posPer >= 0.9) root.result = "pos";
		else if (negPer >= 0.9) root.result = "neg";
		else if (root.reviews.size() == 0) root.result = "pos"; //proepilegmeni epilogh
		else if (root.a == 0) root.result = "pos"; //proepilegmeni epilogh
		else {
			root.result = null;
			int[][][] timesCount = makeTimesCount(root.a, root.reviews, root.binaryRev);
			double e = calculateEntropy(root.reviews, posRev, negRev); // e = entropy
			root.word = calculateInformationGain(root.a, timesCount, root.reviews, e); //root.word = the word with the most information game in this node
			ArrayList<Integer> leftReviews = new ArrayList<Integer>(); //reviews pou exoun thn leksi
			ArrayList<Integer> rightReviews = new ArrayList<Integer>(); //reviews pou den exoun thn leksi
			for (int i = 0; i < root.reviews.size(); i++){
				if (root.binaryRev[i][root.word] == 1){
					leftReviews.add(root.reviews.get(i)); //if the word exists, add it to the left list
				}
				else {
					rightReviews.add(root.reviews.get(i)); //if the word doesn't exist, add it to the right list
				}
			}
			// k kai l einai counters pou xreiazomaste gia na ekxorisoume tis times stis sostes dieuthinseis tou leftbn kai rightbn
			int k = 0; 
			int l = 0;
			int[][] leftbn = new int[leftReviews.size()][root.a-1]; //binaryRev gia left node reviews
			int[][] rightbn = new int[rightReviews.size()][root.a-1]; //binaryRev gia right node reviews
			for (int i = 0; k < leftReviews.size(); i++){
				if (root.reviews.get(i) == leftReviews.get(k)) { //h root.reviews kai h leftReviews exoun diaforetikes diastaseis ara xrhsimopoioun diaforetikous indexes
					for (int j = 0; j < root.a; j++){
						if (j != root.word) {
							leftbn[k][l] = root.binaryRev[i][j];
							l++;
						}
					}
				k++;
				l = 0;
				}
			}
			k = 0;
			l = 0;
			for (int i = 0; k < rightReviews.size(); i++){
				if (root.reviews.get(i) == rightReviews.get(k)) {
					for (int j = 0; j < root.a; j++){
						if (j != root.word) {
							rightbn[k][l] = root.binaryRev[i][j];
							l++;
						}
					}
				k++;
				l = 0;
				}
			}
			root.left = makeTree(root.a-1, leftReviews, leftbn);
			root.right = makeTree(root.a-1, rightReviews, rightbn);
		}
		return root;
	}
	
	public String traverseTree(int a, int[] bnr, TreeNode node){
		if (node.result != null) return node.result;
		else {
			int j = 0;
			int givebnr[] = new int[a-1]; //new bnr we will give to the child
			for (int i = 0; i < a-1; i++){
				if (i != node.word) {
					givebnr[j] = bnr[i];
					j++;
				}
			}
			if (bnr[node.word] == 1) return traverseTree(a-1, givebnr, node.left);
			else return traverseTree(a-1, givebnr, node.right);
		}
	}
	
	void calculate(int a, int[][] trainbn, int[][] testbn){
		int[] bnr = new int[a]; //binaryRevRow dhladh to dianisma enos review
		TreeNode tree = makeTree(a, trainMainReviews, trainbn);
		for (int i = 0; i < testMainReviews.size(); i++){
			for (int j = 0; j < a; j++){
				bnr[j] = testbn[i][j];
			}
			testAlg[i][1] = traverseTree(a, bnr, tree);
		}
	}
	
	void result(){
		double k = 0.0;
		System.out.println("---------------------------------------");
		for (int i = 0; i < testMainReviews.size(); i++){
			if (testAlg[i][0].equals(testAlg[i][1])) k++;
		}
		System.out.println("ID3 accuracy is " + k/(testMainReviews.size())*100 + " %.");
		// precision Positive.
		double posCount = 0.0; // gia ta positives.
		double truePos = 0.0; // gia ta true positives.
		// eyresh TRUE POSITIVE.
		for (int i = 0; i < testMainReviews.size(); i++){
			if (testAlg[i][0].equals("pos") && testAlg[i][0].equals(testAlg[i][1])){
				truePos++;
			}
			if (testAlg[i][1].equals("pos")){
				posCount++;
			}
		}
		System.out.println("ID3 positive precision is " + truePos/(posCount)*100 + " %."); 
		// precision Negative.
		double negCount = 0.0; // gia ta negatives.
		double trueNeg = 0.0; // gia ta true negatives.
		// eyresh TRUE NEGATIVE.
		for (int i = 0; i < testMainReviews.size(); i++){
			if (testAlg[i][0].equals("neg") && testAlg[i][0].equals(testAlg[i][1])){
				trueNeg++;
			}
			if (testAlg[i][1].equals("neg")){
				negCount++;
			}
		}
		System.out.println("ID3 negative precision is " + trueNeg/(negCount)*100 + " %.");
		// recall positive.
		// eyresh FALSE POSITIVE kai FALSE NEGATIVE.
		double falsePos = 0.0;
		double falseNeg = 0.0;
		for (int i = 0; i < testMainReviews.size(); i++){
			if (testAlg[i][0].equals("neg") && testAlg[i][1].equals("pos")){
				falsePos++;
			}
			if (testAlg[i][0].equals("pos") && testAlg[i][1].equals("neg")){
				falseNeg++;
			}
		}
		System.out.println("ID3 positive recall is " + truePos/(truePos + falseNeg)*100 + " %.");
		System.out.println("ID3 negative recall is " + trueNeg/(trueNeg + falsePos)*100 + " %.");
		// eyresh F1. xrhsimopoioyme to positive precision kai recall gia ton typo.
		double posRecall = truePos/(truePos + falseNeg);
		double posPrecision = truePos/(posCount);
		System.out.println("ID3 F1 score is " + (2 * (posPrecision * posRecall))/(posPrecision + posRecall)*100 + " %.");
		System.out.println("---------------------------------------");
	}
}	