import java.util.*;
import java.io.*;

class NaiveBayes{
	
	//grammes einai to review number, sthles pairnoun ties 0 kai 1, 0 gia to ti einai pragmatika, 1 gia to ti ebgale to algorithmos mas
	private String[][] testAlg;
	private ArrayList<Integer> reviews;
	private int revCount;
		
	NaiveBayes(int r, ArrayList<Integer> rev){
		revCount = r;
		reviews = (ArrayList<Integer>) rev.clone();
		testAlg = new String[revCount][2];
		for (int i = 0; i < revCount; i++){
			if (reviews.get(i) < 12500) testAlg[i][0] = "pos";
			else testAlg[i][0] = "neg";
		}
		System.out.println();
	}
	
	void calculate(int m, int n, int[] fp, int[] fn, int[][] bn){
		int a = m-n;
		double revprobpos = 0.5; //probability to be a positive review
		double revprobneg = 0.5; //probability to be a negative review
		double[] probPos = new double[a]; //probability of the word a appearing in positive reviews
		double[] probNeg = new double[a]; //probability of the word a appearing in negative reviews
		for (int i = 0; i < a; i++){
			probPos[i] = fp[i]/(double)2500;
			probNeg[i] = fn[i]/(double)2500;
		}

		for (int i = 0; i < revCount; i++){
			for (int j = 0; j < a; j++){
				if (bn[i][j] == 1){
					revprobpos *= probPos[j];
					revprobneg *= probNeg[j];
				}
				else {
					revprobpos *= (1 - probPos[j]);
					revprobneg *= (1 - probNeg[j]);
				}
			}
			if (revprobpos > revprobneg) testAlg[i][1] = "pos";
			else testAlg[i][1] = "neg";
			revprobpos = 0.5;
			revprobneg = 0.5;
		}
	}
	
	void result(){
		double k = 0.0;
		System.out.println("---------------------------------------");
		for (int i = 0; i < revCount; i++){
			if (testAlg[i][0].equals(testAlg[i][1])) k++;
		}
		System.out.println("Bernoulli Naive Bayes accuracy is " + k/(revCount)*100 + " %.");
		// precision Positive.
		double posCount = 0.0; // gia ta positives.
		double truePos = 0.0; // gia ta true positives.
		// eyresh TRUE POSITIVE.
		for (int i = 0; i < revCount; i++){
			if (testAlg[i][0].equals("pos") && testAlg[i][0].equals(testAlg[i][1])){
				truePos++;
			}
			if (testAlg[i][1].equals("pos")){
				posCount++;
			}
		}
		System.out.println("Bernoulli Naive Bayes positive precision is " + truePos/(posCount)*100 + " %."); 
		// precision Negative.
		double negCount = 0.0; // gia ta negatives.
		double trueNeg = 0.0; // gia ta true negatives.
		// eyresh TRUE NEGATIVE.
		for (int i = 0; i < revCount; i++){
			if (testAlg[i][0].equals("neg") && testAlg[i][0].equals(testAlg[i][1])){
				trueNeg++;
			}
			if (testAlg[i][1].equals("neg")){
				negCount++;
			}
		}
		System.out.println("Bernoulli Naive Bayes negative precision is " + trueNeg/(negCount)*100 + " %.");
		// recall positive.
		// eyresh FALSE POSITIVE kai FALSE NEGATIVE.
		double falsePos = 0.0;
		double falseNeg = 0.0;
		for (int i = 0; i < revCount; i++){
			if (testAlg[i][0].equals("neg") && testAlg[i][1].equals("pos")){
				falsePos++;
			}
			if (testAlg[i][0].equals("pos") && testAlg[i][1].equals("neg")){
				falseNeg++;
			}
		}
		System.out.println("Bernoulli Naive Bayes positive recall is " + truePos/(truePos + falseNeg)*100 + " %.");
		System.out.println("Bernoulli Naive Bayes negative recall is " + trueNeg/(trueNeg + falsePos)*100 + " %.");
		// eyresh F1. xrhsimopoioyme to positive precision kai recall gia ton typo.
		double posRecall = truePos/(truePos + falseNeg);
		double posPrecision = truePos/(posCount);
		System.out.println("Bernoulli Naive Bayes F1 score is " + (2 * (posPrecision * posRecall))/(posPrecision + posRecall)*100 + " %.");
		System.out.println("---------------------------------------");
	}
}