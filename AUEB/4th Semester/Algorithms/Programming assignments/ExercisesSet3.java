import java.util.*;
import java.io.*;


class ExercisesSet3{
	public static void main(String[] args){
		String ex1filename = args[0];
		String ex2filename = args[1];
		String ex3filename = args[2];
		String ex4filename = args[3];
		String ex5filename = args[4];
		String ex5filename2 = args[5];
		exercise1(ex1filename);
		exercise2(ex2filename);
		exercise3(ex3filename);
		exercise4(ex4filename);
		exercise5(ex5filename,ex5filename2);
	}
	
	public static void exercise1(String filename){
		File file = new File(filename);
		List<Integer> input = new ArrayList<Integer>();
		try{
			Scanner scan = new Scanner(file);
			int val = 0;
			while(scan.hasNext()){
				val = scan.nextInt();
				input.add(val);
			}
			int [] arr = new int [input.size()];
			for (int i = 0; i<arr.length; i++){
				arr[i] = input.get(i);
			}
			Exercise1 ex1 = new Exercise1(arr);
			ex1.solveExercise1();
			ex1.printSolution();
		}catch(FileNotFoundException e){
			System.out.println("The file was not found.Try another name or add txt to the end.");
		}
	}
	
	public static void exercise2(String filename){
		File txtfile = new File(filename);
		int nextChar;
		try {
		BufferedReader in = new BufferedReader(new FileReader(txtfile));
		// dhmiourghsa ena hashMap wste na exw ta grammata kai tis syxnothtes emfanishs tous.
		HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
		char ch;
		try{
			while ((nextChar = in.read()) != -1){
				ch = ((char) nextChar);
				if (ch >= 'a' && ch <= 'z'){
					int value = freq.getOrDefault(ch,0);
					freq.put(ch,value + 1);
				}
			}
			in.close();
		}catch(IOException e){
			System.out.println("IO exception.");
		}
		System.out.println("(Exercise 2 letters with frequencies)");
		System.out.println(freq);
		Exercise2 ex2 = new Exercise2(freq);
		//System.out.println(solveExercise2(freq));
		ex2.solveExercise2(freq);
		ex2.printSolution();
		}catch(FileNotFoundException e){
			System.out.println("The file was not found.Try another name or add txt to the end.");
		}
	}
	
	public static void exercise3(String filename){
		File file = new File(filename);
		List<Integer> input = new ArrayList<Integer>();
		try{
			Scanner scan = new Scanner(file);
			int val = 0;
			int desiredSum = 0; // edw tha mpei to C.
			int count = 0; // gia dieykolynsh anagnwshs twn dedomenwn apo ta arxeia.
			while(scan.hasNext()){
				count += 1;
				val = scan.nextInt();
				input.add(val);
			}
			int [] arr = new int [input.size()];
			int [] sec = new int [arr.length-1];
			for (int i = 0; i<arr.length; i++){
				arr[i] = input.get(i);
			}
			for (int i = 0; i<arr.length-1; i++){
				sec[i] = arr[i]; // ayto to kanw gia na parw ola ta stoixeia tou arxeiou ektos apo to teleytaio
								// pou einai to C, gia na ftiaxw ton W.
			}
			desiredSum = arr[arr.length-1];
			Exercise3 ex3 = new Exercise3(sec,desiredSum);
			ex3.solveAndPrintExercise3();
		}catch(FileNotFoundException e){
			System.out.println("The file was not found.Try another name or add txt to the end.");
		}
	}
	
	public static void exercise4(String filename){
		try{
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line;
			// antigrafw olo to arxeio se ena allo text file giati den 
			// vrhka allo tropo gia na ta valw sto hashmap.
			// yparxoun diafora spaces (1,2,6 se kapoies lexeis) opote
			// anagkastika eprepe na ftiaxw arxeio pou kathe lexh apexei 1 space apo thn allh.
			try{
				FileWriter fw = new FileWriter("outfileex4.txt");
				while((line = br.readLine()) != null){
				line = line.trim(); // vgazw ta whitespaces gia na exoun apostash enos kenou mono.
				line=line.replaceAll("\\s+", " ");
				fw.write(line + "\n");
				}
			}
			catch(IOException e){
				System.out.println("IO exception.");
			}
			String str;
			LinkedHashMap<String,List<String>> philMap = new LinkedHashMap<String,List<String>>();
			BufferedReader br2 = new BufferedReader(new FileReader("outfileex4.txt"));
			List<String> values = new ArrayList<String>();
			try{ // eisagwgh twn komvwn sto hashmap.
				while ((str = br2.readLine()) != null) {
					String[] s = str.split(" ");
					values = philMap.get(s[0]);
					if (values == null){
						values = new ArrayList<String>();
						philMap.put(s[0],values);
					}
					values.add(s[1]);
				}
				Exercise4 ex4 = new Exercise4(philMap);
				ex4.printSolution();
			
			}catch(IOException e){
				System.out.println("IO exception.");
			}
		}catch(FileNotFoundException e){
				System.out.println("File not found. Try another name or add .txt extension to the end.");
			}
				
		}

	public static void exercise5(String filename,String filename2){
		try{
			File file = new File(filename);
			File file2 = new File(filename2);
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			String str;
			String str2;
			LinkedHashMap<String,List<String>> miserMap = new LinkedHashMap<String,List<String>>();
			LinkedHashMap<Integer,List<Integer>> numMap = new LinkedHashMap<Integer,List<Integer>>();
			List<String> values = new ArrayList<String>();
			List<Integer> numValues = new ArrayList<Integer>();
			try{ // eisagwgh twn komvwn sto hashmap.
				while ((str = br.readLine()) != null) {
					String[] s = str.split(" ");
					values = miserMap.get(s[0]);
					if (values == null){
						values = new ArrayList<String>();
						miserMap.put(s[0],values);
					}
					values.add(s[1]);
				}
				while((str2 = br2.readLine()) != null){
					String[] s = str2.split("\t");
					numValues = numMap.get(Integer.parseInt(s[0]));
					if (numValues == null){
						numValues = new ArrayList<Integer>();
						numMap.put(Integer.parseInt(s[0]),numValues);
					}
					numValues.add(Integer.parseInt(s[1]));
				}
				Exercise5 ex5 = new Exercise5(miserMap,numMap);
				ex5.solveAndPrintExercise5(miserMap,numMap);
			}catch(IOException e){
				System.out.println("IO exception.");
			}
		}catch(FileNotFoundException e){
				System.out.println("File not found. Try another name or add .txt extension to the end.");
			}
		}			
	}
