import java.util.*;
import java.io.*;
import java.lang.Math;
class Greedy{
	public static void main(String args[]){
		File file = null;
		BufferedReader reader = null; // ayta ta 2 gia to file mas.
		try{
			file = new File(args[0]); // ayto einai gia na pairnei to arxeio san orisma. (java Greedy folders.txt px).
		}catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Error.No file given."); // ayto vgainei an grapsoyme java Greedy.
		}try{
			reader = new BufferedReader(new FileReader(file));
		}catch (FileNotFoundException e){
			System.err.println("Error. The file can't be opened or doesn't exist");
		}
		int idcount = 0; // ayto gia ta id twn diskwn (an xreiastei na dhmiourgithoun alloi) .
		String line;
		double sum = 0; // double gia na exoume akribeia sta Terabytes.
		int diskCount = 1;
		int folderCount = 0;
		// ayto to ArrayList tha xrishmopoithei gia thn eisagwgh twn diskwn sto Priority Queue.
		ArrayList<Disk> array = new ArrayList<>(); 
		float sum2 = 0.0f;
		ArrayList<Integer> folders = new ArrayList<>();
		try{
		while ((line = reader.readLine()) != null){
			int folderSize = Integer.parseInt(line);
			sum2 = sum2 + folderSize;
			folderCount++; 
			folders.add(folderSize); // diavazoume tous fakelous ap to arxeio kai tous vazoume sto folders.	
		}
		sum2 = sum2 / 1000000;
		int roundized = Math.round(sum2);	
		int [] Folder = new int [folderCount];// array , tha xrhsimopoihthei gia to quickSort.
		for (int i = 0; i < folderCount; i++){
			Folder[i] = folders.get(i); // gia na ginei swsta to sort ta vazoume se array.
		}
		Disk [] diskArray = new Disk [roundized+1];
		for (int i = 0; i < roundized+1; i++){
			diskArray[i] = new Disk(idcount);
			idcount++;
		}
		diskCount = roundized+1;
		for (int i = 0; i < folderCount; i++){
				sum = sum + Folder[i];
				if (Folder[i] < 0 || Folder[i] > 1000000){
						// mhnyma lathous.
						System.out.println("Your folder size must be > 0 and < 1000000");
						sum = 0.0;
						diskCount = 1; // ayta ginontai reset giati ousiastika exei termatisei to programma.
						break;
				}
				for (int j = 0; j < roundized+1; j++){
					if (Folder[i] <= diskArray[j].getFreeSpace()){
						diskArray[j].insertFolder(Folder[i]);
						break; // 
					}
				}	
		}
		MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
		for (int i = 0; i < roundized+1; i++){
			queue.insert(diskArray[i]); // eisagoume tous diskous sto PQ.
		}
		sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
		System.out.println("Sum of all folders is: " + sum + " TB");
		System.out.println("Total number of disks used = " + diskCount);
		while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
		// xrhsimopoioume kai thn arraylist gia thn printFolders (gia na vlepoume to megethos twn fakelwn).
			for (int i = 0; i < roundized+1; i++){ 
				/* pairnoume to id kai to free space tou diskou me th megalyterh xwrhtikothta , ta typwnoume 
				   kai typwnoume kai to megethos twn arxeiwn pou exoun apothikeytei. Meta ton vgazoume me to getmax.*/
				System.out.print("id: " + queue.printMaxId() + " " + queue.printMaxSpace() + ": ");
				queue.printMaxFolder();
				System.out.print("\n");
				queue.getmax();
			}	 
		}
		
		}catch (IOException e){
			System.err.println("IO Exception. Error processing the file.");
		}
	}
}