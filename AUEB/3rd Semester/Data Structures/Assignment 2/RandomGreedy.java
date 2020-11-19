import java.util.*;
import java.io.*;
import java.lang.Math;
class RandomGreedy{
	public static void main (String args[]){
		try{
			BufferedWriter out = null;
			String[] filenames1 = { "data/100N/file1.txt","data/100N/file2.txt","data/100N/file3.txt","data/100N/file4.txt",
									"data/100N/file5.txt","data/100N/file6.txt","data/100N/file7.txt","data/100N/file8.txt",
									"data/100N/file9.txt","data/100N/file10.txt"};
			for (int i = 0; i < filenames1.length; i++){ // write files.
				out = new BufferedWriter(new FileWriter(filenames1[i]));
				for (int j = 0; j < 100; j++){
					int rand = (int)(Math.random() * ((1000000 - 0) + 1)) + 0; // to diasthma twn fakelwn.
					out.write(rand + "\n");
				}
				out.close();
			}
			String[] filenames2 = { "data/500N/file1.txt","data/500N/file2.txt","data/500N/file3.txt","data/500N/file4.txt",
								  "data/500N/file5.txt","data/500N/file6.txt","data/500N/file7.txt","data/500N/file8.txt",
								  "data/500N/file9.txt","data/500N/file10.txt"};
			for (int i = 0; i<filenames2.length; i++){
				out = new BufferedWriter(new FileWriter(filenames2[i]));
				for (int j = 0; j < 500; j++){
					int rand = (int)(Math.random() * ((1000000 - 0) + 1)) + 0; // to diasthma twn fakelwn.
					out.write(rand + "\n");
				}
				out.close();
			}
			String [] filenames3 = {"data/1000N/file1.txt","data/1000N/file2.txt","data/1000N/file3.txt","data/1000N/file4.txt",
									"data/1000N/file5.txt","data/1000N/file6.txt","data/1000N/file7.txt","data/1000N/file8.txt",
									"data/1000N/file9.txt","data/1000N/file10.txt"};
			for (int i = 0; i<filenames3.length; i++){
				out = new BufferedWriter(new FileWriter(filenames3[i]));
				for (int j = 0; j < 1000; j++){
					int rand = (int)(Math.random() * ((1000000 - 0) + 1)) + 0; // to diasthma twn fakelwn.
					out.write(rand + "\n");
				}
				out.close();
			}
			File file = null;
			BufferedReader reader = null;
			double avg = 0; // o mesos oros diskwn gia N = 100.
			double avg2 = 0; // o mesos oros diskwn gia N = 500.
			double avg3 = 0; // o mesos oros diskwn gia N = 1000.
			double avg4 = 0; // o mesos oros diskwn gia N = 100 me quickSort.
			double avg5 = 0; // o mesos oros diskwn gia N = 500 me quickSort.
			double avg6 = 0; // o mesos oros diskwn gia N = 1000 me quickSort.
			for (int i = 0; i < filenames1.length; i++){
				file = new File(filenames1[i]);
				reader = new BufferedReader(new FileReader(filenames1[i]));
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
			for (int p = 0; p < folderCount; p++){
				Folder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
			}
			Disk [] diskArray = new Disk [roundized+1];
			for (int p = 0; p < roundized+1; p++){
				diskArray[p] = new Disk(idcount);
				idcount++;
			}
			diskCount = roundized+1;	
			for (int p = 0; p < folderCount; p++){
					sum = sum + Folder[p];
					if (Folder[p] < 0 || Folder[p] > 1000000){
							// mhnyma lathous.
							System.out.println("Your folder size must be > 0 and < 1000000");
							sum = 0.0;
							diskCount = 1; // ayta ginontai reset giati ousiastika exei termatisei to programma.
							break;
					}
					for (int j = 0; j < roundized+1; j++){
						if (Folder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(Folder[p]);
							break; // 
						}
					}	
			}
			MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
			for (int p = 0; p < roundized+1; p++){
				queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
			}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg = avg + diskCount;// gia na vroume ton meso oro diskwn.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used for file = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
				// xrhsimopoioume kai thn arraylist gia thn printFolders (gia na vlepoume to megethos twn fakelwn).
						queue.getmax();
					} 
				}catch (IOException e) {
				System.err.println ("Error , could not write file.");
				}
			}
			
			avg = avg / 10;
			System.out.println("the average count of disks for 10 Files of 100 Integers using Greedy is: " + avg);
			for (int i = 0; i < filenames2.length; i++){
				file = new File(filenames2[i]);
				reader = new BufferedReader(new FileReader(filenames2[i]));
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
			for (int p = 0; p < folderCount; p++){
				Folder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
			}
			Disk [] diskArray = new Disk [roundized+1];
			for (int p = 0; p < roundized+1; p++){
				diskArray[p] = new Disk(idcount);
				idcount++;
			}
			diskCount = roundized+1;	
			for (int p = 0; p < folderCount; p++){
					sum = sum + Folder[p];
					if (Folder[p] < 0 || Folder[p] > 1000000){
							// mhnyma lathous.
							System.out.println("Your folder size must be > 0 and < 1000000");
							sum = 0.0;
							diskCount = 1; // ayta ginontai reset giati ousiastika exei termatisei to programma.
							break;
					}
					for (int j = 0; j < roundized+1; j++){
						if (Folder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(Folder[p]);
							break; // 
						}
					}	
			}
			MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
			for (int p = 0; p < roundized+1; p++){
				queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
			}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg2 = avg2 + diskCount;// gia na vroume ton meso oro diskwn.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used for file = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
				// xrhsimopoioume kai thn arraylist gia thn printFolders (gia na vlepoume to megethos twn fakelwn).
						queue.getmax();
					} 
				}catch (IOException e) {
				System.err.println ("Error , could not write file.");
				}
			}
			avg2 = avg2 / 10;
			System.out.println("The average count of disks for 10 files of 500 integers using Greedy is: " + avg2);
			for (int i = 0; i < filenames1.length; i++){
				file = new File(filenames3[i]);
				reader = new BufferedReader(new FileReader(filenames3[i]));
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
			for (int p = 0; p < folderCount; p++){
				Folder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
			}
			Disk [] diskArray = new Disk [roundized+1];
			for (int p = 0; p < roundized+1; p++){
				diskArray[p] = new Disk(idcount);
				idcount++;
			}
			diskCount = roundized+1;	
			for (int p = 0; p < folderCount; p++){
					sum = sum + Folder[p];
					if (Folder[p] < 0 || Folder[p] > 1000000){
							// mhnyma lathous.
							System.out.println("Your folder size must be > 0 and < 1000000");
							sum = 0.0;
							diskCount = 1; // ayta ginontai reset giati ousiastika exei termatisei to programma.
							break;
					}
					for (int j = 0; j < roundized+1; j++){
						if (Folder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(Folder[p]);
							break; // 
						}
					}	
			}
			MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
			for (int p = 0; p < roundized+1; p++){
				queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
			}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg3 = avg3 + diskCount;// gia na vroume ton meso oro diskwn.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used for file = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
				// xrhsimopoioume kai thn arraylist gia thn printFolders (gia na vlepoume to megethos twn fakelwn).
						queue.getmax();
					} 
				}catch (IOException e) {
				System.err.println ("Error , could not write file.");
				}
			}
			avg3 = avg3 / 10;
			System.out.println("The average count of disks for 10 files of 1000 integers using Greedy is: " + avg3);
			
			
			for (int i=0; i<filenames1.length; i++){
				file = new File(filenames1[i]); 
				reader = new BufferedReader(new FileReader(file));
				Disk first = new Disk(1); // prwtos diskos.
				int idcount = 0; // ayto gia ta id twn diskwn (an xreiastei na dhmiourgithoun alloi) .
				String line;
				double sum = 0; // double gia na exoume akribeia sta Terabytes.
				int diskCount = 1;
				int folderCount = 0; // gia thn taxinomhsh tou pinaka.
				// ayto to ArrayList tha xrishmopoithei gia thn eisagwgh twn diskwn sto Priority Queue.
				ArrayList<Disk> array = new ArrayList<>(); 
				array.add(first);
				ArrayList<Integer> folders = new ArrayList<>(); // ayto tha xrhsimopoieithei gia to quickSort. 
				float sum2 = 0.0f;
				try{
					while ((line = reader.readLine()) != null){
					int folderSize = Integer.parseInt(line);
					sum2 = sum2 + folderSize;
					folderCount++; 
					folders.add(folderSize); // diavazoume tous fakelous ap to arxeio kai tous vazoume sto folders.	
				}	
				sum2 = sum2 / 1000000;
				int roundized = Math.round(sum2); // stroggylopoihsh gia na ftiaxoume tous diskous.
				int [] sortFolder = new int [folderCount];// array , tha xrhsimopoihthei gia to quickSort.
				for (int p = 0; p < folderCount; p++){
					sortFolder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
				}
				Disk [] diskArray = new Disk [roundized];
				for (int p = 0; p < roundized; p++){
					diskArray[p] = new Disk(idcount);
					idcount++;
				}
				diskCount = roundized;
				Sort.quickSort(sortFolder,0,folderCount-1); // edw ginetai h taxinomhsh.
				for (int p = 0; p < folderCount; p++){
					sum = sum + sortFolder[p];
					for (int j = 0; j < roundized; j++){
						if (sortFolder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(sortFolder[p]);
							break; // 
						}
					}
					
				}
				MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
					for (int p = 0; p < roundized; p++){
					queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
				}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg4 = avg4 + diskCount; // gia na vroume to meso oro.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
						queue.getmax();
					}	 
				}
				catch (IOException e){
				System.err.println("could not read or write file.");
				}
			} 
			
			
			avg4 = avg4 / 10; // mesos oros.
			System.out.println("the average count of disks for 10 Files of 100 Integers using Sorted Greedy is: " + avg4);
			
			
			for (int i=0; i<filenames2.length; i++){
				file = new File(filenames2[i]); 
				reader = new BufferedReader(new FileReader(file));
				Disk first = new Disk(1); // prwtos diskos.
				int idcount = 0; // ayto gia ta id twn diskwn (an xreiastei na dhmiourgithoun alloi) .
				String line;
				double sum = 0; // double gia na exoume akribeia sta Terabytes.
				int diskCount = 1;
				int folderCount = 0; // gia thn taxinomhsh tou pinaka.
				// ayto to ArrayList tha xrishmopoithei gia thn eisagwgh twn diskwn sto Priority Queue.
				ArrayList<Disk> array = new ArrayList<>(); 
				array.add(first);
				ArrayList<Integer> folders = new ArrayList<>(); // ayto tha xrhsimopoieithei gia to quickSort. 
				float sum2 = 0.0f;
				try{
					while ((line = reader.readLine()) != null){
					int folderSize = Integer.parseInt(line);
					sum2 = sum2 + folderSize;
					folderCount++; 
					folders.add(folderSize); // diavazoume tous fakelous ap to arxeio kai tous vazoume sto folders.	
				}	
				sum2 = sum2 / 1000000;
				int roundized = Math.round(sum2); // stroggylopoihsh gia na ftiaxoume tous diskous.
				int [] sortFolder = new int [folderCount];// array , tha xrhsimopoihthei gia to quickSort.
				for (int p = 0; p < folderCount; p++){
					sortFolder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
				}
				Disk [] diskArray = new Disk [roundized];
				for (int p = 0; p < roundized; p++){
					diskArray[p] = new Disk(idcount);
					idcount++;
				}
				diskCount = roundized;
				Sort.quickSort(sortFolder,0,folderCount-1); // edw ginetai h taxinomhsh.
				for (int p = 0; p < folderCount; p++){
					sum = sum + sortFolder[p];
					for (int j = 0; j < roundized; j++){
						if (sortFolder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(sortFolder[p]);
							break; // 
						}
					}
					
				}
				MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
					for (int p = 0; p < roundized; p++){
					queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
				}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg5 = avg5 + diskCount; // gia na vroume to meso oro.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
						queue.getmax();
					}	 
				}
				catch (IOException e){
				System.err.println("could not read or write file.");
				}
			} 
			
			
			avg5 = avg5 / 10; // mesos oros.
			System.out.println("the average count of disks for 10 Files of 100 Integers using Sorted Greedy is: " + avg5);
			
		
		
		
		for (int i=0; i<filenames3.length; i++){
				file = new File(filenames3[i]); 
				reader = new BufferedReader(new FileReader(file));
				Disk first = new Disk(1); // prwtos diskos.
				int idcount = 0; // ayto gia ta id twn diskwn (an xreiastei na dhmiourgithoun alloi) .
				String line;
				double sum = 0; // double gia na exoume akribeia sta Terabytes.
				int diskCount = 1;
				int folderCount = 0; // gia thn taxinomhsh tou pinaka.
				// ayto to ArrayList tha xrishmopoithei gia thn eisagwgh twn diskwn sto Priority Queue.
				ArrayList<Disk> array = new ArrayList<>(); 
				array.add(first);
				ArrayList<Integer> folders = new ArrayList<>(); // ayto tha xrhsimopoieithei gia to quickSort. 
				float sum2 = 0.0f;
				try{
					while ((line = reader.readLine()) != null){
					int folderSize = Integer.parseInt(line);
					sum2 = sum2 + folderSize;
					folderCount++; 
					folders.add(folderSize); // diavazoume tous fakelous ap to arxeio kai tous vazoume sto folders.	
				}	
				sum2 = sum2 / 1000000;
				int roundized = Math.round(sum2); // stroggylopoihsh gia na ftiaxoume tous diskous.
				int [] sortFolder = new int [folderCount];// array , tha xrhsimopoihthei gia to quickSort.
				for (int p = 0; p < folderCount; p++){
					sortFolder[p] = folders.get(p); // gia na ginei swsta to sort ta vazoume se array.
				}
				Disk [] diskArray = new Disk [roundized];
				for (int p = 0; p < roundized; p++){
					diskArray[p] = new Disk(idcount);
					idcount++;
				}
				diskCount = roundized;
				Sort.quickSort(sortFolder,0,folderCount-1); // edw ginetai h taxinomhsh.
				for (int p = 0; p < folderCount; p++){
					sum = sum + sortFolder[p];
					for (int j = 0; j < roundized; j++){
						if (sortFolder[p] <= diskArray[j].getFreeSpace()){
							diskArray[j].insertFolder(sortFolder[p]);
							break; // 
						}
					}
					
				}
				MaxPQ queue = new MaxPQ(diskCount); // to Priority Queue pou tha exei tous diskous.
					for (int p = 0; p < roundized; p++){
					queue.insert(diskArray[p]); // eisagoume tous diskous sto PQ.
				}
				sum = sum / 1000000; // ayth h diairesh ginetai gia na mas vgalei ta MB se TB.
				avg6 = avg6 + diskCount; // gia na vroume to meso oro.
				System.out.println("Sum of all folders is: " + sum + " TB");
				System.out.println("Total number of disks used = " + diskCount);
				while (!queue.empty()){ // oso h PQ den einai adeia typwnoume ta stoixeia ths sthn othonh.
						queue.getmax();
					}	 
				}
				catch (IOException e){
				System.err.println("could not read or write file.");
				}
			} 
			
			
			avg6 = avg6 / 10; // mesos oros.
			System.out.println("the average count of disks for 10 Files of 100 Integers using Sorted Greedy is: " + avg6);
		
		}catch (IOException e) {
			System.err.println ("Error , could not write or read file.");
		}
		
		
	}
	
}
