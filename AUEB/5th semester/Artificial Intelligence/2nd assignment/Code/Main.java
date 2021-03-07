import java.util.*;
import java.io.*;


public class Main{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		try {
			File test = new File("..\\aclImdb\\test\\labeledBow.feat");
			File train = new File("..\\aclImdb\\train\\labeledBow.feat");
			BufferedReader trainFeat1 = new BufferedReader(new FileReader(train));
			BufferedReader trainFeat2 = new BufferedReader(new FileReader(train));
			BufferedReader testFeat = new BufferedReader(new FileReader(test));
			String trainLine1;
			String trainLine2;
			String testLine;
			trainLine1 = trainFeat1.readLine();
			trainLine2 = trainFeat2.readLine();
			testLine = testFeat.readLine();
			System.out.print("Give m: ");
			int m = scan.nextInt();
			System.out.print("Give n: ");
			int n = scan.nextInt();
			System.out.print("How many reviews would you like to be tested? Give a number: ");
			int revCount = scan.nextInt();
			int a = m-n;
			// Arrays gia eyresh syxnothtas m kai n lexewn.
			// freqPos : periexei lexeis se thetikes kritikes.
			// freqNeg : periexei lexeis se arnhtikes kritikes.
			int[] freqPos = new int[a];
			for (int i = 0; i < a; i++) freqPos[i] = 0;
			int[] freqNeg = new int[a];
			for (int i = 0; i < a; i++) freqNeg[i] = 0;
			ArrayList<Integer> trainReviews = new ArrayList<Integer>();
			ArrayList<Integer> testReviews = new ArrayList<Integer>();
			double rng = 0;
			//for train reviews
			for (int i = 0; i < 2500; i++) {
				do {
					rng = Math.random() * 12499;
				} while (trainReviews.contains((int)rng));
				trainReviews.add((int)rng);
			}
			for (int i = 12500; i < 15000; i++) {
				do {
					rng = Math.random() * 12500 + 12500;
				} while (trainReviews.contains((int)rng));
				trainReviews.add((int)rng);
			}
			//for test reviews
			for (int i = 0; i < revCount; i++) {
				do {
					rng = Math.random() * 25000;
				} while (testReviews.contains((int)rng));
				testReviews.add((int)rng);
			}
			
			Collections.sort(trainReviews);
			Collections.sort(testReviews);
			
			//array pou ftiaxnei ena dianismoa me 0 kai 1 apo ena review gia training
			int[][] trainBinaryRev = new int[5000][a]; // h grammh einai gia kritikes, h sthlh ama einai 0 den uparxei h leksi, ama einai 1 uparxei
			for (int i = 0; i < 5000; i++){
				for (int j = 0; j < a; j++) {
					trainBinaryRev[i][j] = 0;
				}
			}
			
			//array pou ftiaxnei ena dianismoa me 0 kai 1 apo ena review gia testing
			int[][] testBinaryRev = new int[revCount][a]; // h grammh einai gia kritikes, h sthlh ama einai 0 den uparxei h leksi, ama einai 1 uparxei
			for (int i = 0; i < revCount; i++){
				for (int j = 0; j < a; j++) {
					testBinaryRev[i][j] = 0;
				}
			}
			
			// bazoume tis syxnothtes , dhladh poses fores emfanizetai mia lexh
			// se positive h negative kritikh antistoixa.
			// gia ta thetika.
			int value = 0; //value on the left of the :
			int rating = 0; //review's rating, positive if >= 7, negative if < 7
			boolean flag = true; //flag that indicates if we are near the end of a trainLine1
			
			//to get data from train reviews
			int k = 0;
			int j = 0;
			while ((k < 5000) && (j < 25000)){
				if (trainReviews.contains(j)){
					k++;
					rating = Integer.parseInt(trainLine1.substring(0, trainLine1.indexOf(" ")));
					trainLine1 = trainLine1.substring(trainLine1.indexOf(" ") + 1, trainLine1.length());
					while (flag){
						value = Integer.parseInt(trainLine1.substring(0, trainLine1.indexOf(":")));
						if (rating >= 7){
							if ((value >= n) && (value < m)){
								freqPos[value-n] += 1;
							}
						}
						else {
							if ((value >= n) && (value < m)){
								freqNeg[value-n] += 1;
							}
						}
						if (trainLine1.contains(" ")){
							trainLine1 = trainLine1.substring(trainLine1.indexOf(" ") + 1,trainLine1.length());
						}
						else {
							flag = false;
						}
					}
				}
				trainLine1 = trainFeat1.readLine();
				flag = true;
				j++;
			}
			
			//to get 0-1 vector from train reviews
			flag = true;
			k = 0;
			j = 0;
			while ((k < 5000) && (j < 25000)){
				if (trainReviews.contains(j)){
					k++;
					trainLine2 = trainLine2.substring(trainLine2.indexOf(" ") + 1, trainLine2.length());
					while (flag){
						value = Integer.parseInt(trainLine2.substring(0, trainLine2.indexOf(":")));
						if ((value >= n) && (value < m)){
							trainBinaryRev[trainReviews.indexOf(j)][value-n] = 1;
						}
						if (trainLine2.contains(" ")){
							trainLine2 = trainLine2.substring(trainLine2.indexOf(" ") + 1, trainLine2.length());
						}
						else {
							flag = false;
						}
					}
				}
				trainLine2 = trainFeat2.readLine();
				flag = true;
				j++;
			}
			
			//to get 0-1 vector from test reviews
			flag = true;
			k = 0;
			j = 0;
			while ((k < revCount) && (j < 25000)){
				if (testReviews.contains(j)){
					k++;
					testLine = testLine.substring(testLine.indexOf(" ") + 1, testLine.length());
					while (flag){
						value = Integer.parseInt(testLine.substring(0, testLine.indexOf(":")));
						if ((value >= n) && (value < m)){
							testBinaryRev[testReviews.indexOf(j)][value-n] = 1;
						}
						if (testLine.contains(" ")){
							testLine = testLine.substring(testLine.indexOf(" ") + 1,testLine.length());
						}
						else {
							flag = false;
						}
					}
				}
				testLine = testFeat.readLine();
				flag = true;
				j++;
			}

			NaiveBayes nb = new NaiveBayes(revCount, testReviews);
			nb.calculate(m, n, freqPos, freqNeg, testBinaryRev);
			nb.result();
			
			ID3 id3 = new ID3(trainReviews, testReviews);
			id3.calculate(a, trainBinaryRev, testBinaryRev);
			id3.result();
			
			
		}catch(IOException e){
			System.out.println("File name not correct. Try again.");
		}
	}
}