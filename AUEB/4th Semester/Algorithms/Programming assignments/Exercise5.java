import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;


class Exercise5{
	LinkedHashMap<String,List<String>> miserMap = new LinkedHashMap<String,List<String>>();
	LinkedHashMap<Integer,List<Integer>> numMap = new LinkedHashMap<Integer,List<Integer>>();
	
	public Exercise5(LinkedHashMap<String,List<String>> map,LinkedHashMap<Integer,List<Integer>> map2){
		miserMap.putAll(map);
		numMap.putAll(map2);
	}
	
	public void solveAndPrintExercise5(LinkedHashMap<String,List<String>> miserMap,LinkedHashMap<Integer,List<Integer>> numMap){
				// exhaustive.
				// Dataset 1 : les miserables
				try{
					long startTime = System.currentTimeMillis(); // me ayto metrame to xrono ths synarthshs.
					int count = 0;
					TimeUnit.SECONDS.sleep(5); // sleep gia 5 deytera.
					List<String> keys = new LinkedList<String>();
					List<List<String>> same = new LinkedList<List<String>>();
					List<List<String>> cliques = new LinkedList<List<String>>();
					for (Map.Entry<String,List<String>> entry : miserMap.entrySet()){
						String key = entry.getKey();
						List<String> value = entry.getValue();
						keys.add(key);
						same.add(value);
					}
					int size = same.size();
					List<List<String>> check = new LinkedList<List<String>>();
					for (int i = 0; i<size; i++){
						check.add(same.get(i));
						for (int k = 1; k<size; k++){
							for (List<String> list : check){
								if (list.containsAll(same.get(k))){
									cliques.add(list); // edw vriskw tis klikes.
								}
							}	
						}
					}
					int max = 0; // edw vriskw th megisth klika.
					List<Integer> counts = new LinkedList<Integer>();
					for (List<String> e : cliques){
						int numOfElements = 0;
						for (String w : e){
							numOfElements += 1;
						}
						counts.add(numOfElements);
					}
					for (int num : counts){
						if (num > max){
							max = num;
						}
					}
					System.out.println("(Exercise 5 Solution , Dataset 1 (les miserables)): ");
					for (Map.Entry<String,List<String>> entry : miserMap.entrySet()){
						String key = entry.getKey();
						List<String> value = entry.getValue();
						int count2 = 0;
						for (int i = 0; i < value.size(); i++){
							count2 +=1;
						}
						if (count2 == max){
							System.out.print("{");
							System.out.print(key + ": "); // ektypwsh tou diktyou me th megisth klika.
							System.out.println(value + "}");
						}
					}
					// Dataset 2 : arithmoi
					List<Integer> numkeys = new LinkedList<Integer>();
					List<List<Integer>> numsame = new LinkedList<List<Integer>>();
					List<List<Integer>> numcliques = new LinkedList<List<Integer>>();
					for (Map.Entry<Integer,List<Integer>> entry : numMap.entrySet()){
						int numkey = entry.getKey();
						List<Integer> numvalue = entry.getValue();
						numkeys.add(numkey);
						numsame.add(numvalue);
					}
					int numsize = numsame.size();
					List<List<Integer>> check2 = new LinkedList<List<Integer>>();
					for (int i = 0; i<numsize; i++){
						check2.add(numsame.get(i)); // elegxw an yparxoun idia stoixeia se 2 h perissoteres listes
						// etsi xeroume an yparxei klika h oxi.
						for (int k = 1; k<numsize; k++){
							for (List<Integer> list : check2){
								if (list.containsAll(numsame.get(k))){
									numcliques.add(list); // edw vriskw tis klikes.
								}
							}	
						}
					}
					int max2 = 0; // edw vriskw th megisth klika.
					List<Integer> counts2 = new LinkedList<Integer>();
					for (List<Integer> e : numcliques){
						int numOfElements2 = 0;
						for (int w : e){
							numOfElements2 += 1;
						}
						counts2.add(numOfElements2);
					}
					for (int num : counts2){
						if (num > max2){
							max2 = num;
						}
					}
					System.out.println("(Exercise 5 Solution, Dataset 2 (zachary karate club)): ");
					for (Map.Entry<Integer,List<Integer>> entry : numMap.entrySet()){
						int key2 = entry.getKey();
						List<Integer> value2 = entry.getValue();
						int count3 = 0;
						for (int i = 0; i < value2.size(); i++){
							count3 +=1;
						}
						if (count3 == max2){
							System.out.print("{");
							System.out.print(key2 + ": "); // ektypwsh tou diktyou me th megisth klika.
							System.out.println(value2 + "}");
						}
					}
					long endTime = System.currentTimeMillis();
					long timeElapsed = endTime - startTime;

					System.out.println("Execution time in milliseconds: " + timeElapsed);
				}catch(InterruptedException e){
					System.out.println("Interrupted exception caught.");
				}
				
				
		}	
	}
