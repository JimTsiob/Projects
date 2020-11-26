import java.util.*;
import java.io.*;

class Exercise4{
	HashMap<String,List<String>> philMap = new HashMap<String,List<String>>();
	
	public Exercise4(HashMap<String,List<String>> map){
		philMap.putAll(map);
	}
	
	
	public int solveExercise4(HashMap<String,List<String>> philMap){
		List<Integer> counter = new ArrayList<Integer>();
			for (Map.Entry<String,List<String>> entry : philMap.entrySet()){
				String key = entry.getKey();
				List<String> values = entry.getValue();
				int count = 0;
				for (int i = 0; i < values.size(); i++){
					count +=1;
				}
				count = count/2; // dioti psaxnoume gia zeygaria.
				if (count == 0){
					count = 1;
				}
				counter.add(count); // vriskw ta plithi twn monopatiwn zeygariwn kai ta vazw se ayto to ArrayList.
			}
			int max = 0;
			for (Integer i : counter){
				if (i > max){
					max = i;
				}
			}
			for (Map.Entry<String,List<String>> entry : philMap.entrySet()){
				String key = entry.getKey();
				List<String> values = entry.getValue();
				int count = 0;
				for (int i = 0; i < values.size(); i++){
					count +=1;
				}
				count = count/2;
				if (count == max){
					System.out.println("(Exercise 4 Solution): ");
					System.out.print("{");
					System.out.print(key + ": "); // ektypwsh tou diktyou me to longest shortest path gia to output opws zitithike.
					System.out.println(values + "}");
				}
			}
			// to max aytwn twn monopatiwn einai to longest shortest path ara kai h diametros 
			// opote thn epistrefw.
			return max;
	}
	
	public void printSolution(){
		Exercise4 ex4 = new Exercise4(philMap);
		int max = ex4.solveExercise4(philMap);
		System.out.println("diameter is : " + max);
		System.out.println("\n");
	}
}