import java.util.*;
import java.io.*;

class Exercise2{
	// o C periexei ta grammata kai to plhthos emfanisewn tous.
	HashMap<Character,Integer> C = new HashMap<Character,Integer>();
	
	public Exercise2(HashMap<Character,Integer> map){
		C.putAll(map);
	}
	
	// dhmiourgoume thn klash HuffmanNode wste 
	// na mporoume na ftiaxoume to dentro kai na ta valoume sthn Priority Queue.
	class HuffmanNode{
		int data;
		char c;
		
		HuffmanNode left;
		HuffmanNode middle; // ayto dioti exoume 0,1,2.
		HuffmanNode right;
	}
	
	// dhmiourgoume kai to MyComparator gia na mporoume
	// na sygkrinoume ta data sta nodes kai na topothetountai swsta sthn Priority Queue.
	class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y){ 
			return x.data - y.data; 
		} 
	} 
	
	
	public void printSolution(){
		Exercise2 ex2 = new Exercise2(C);
		HuffmanNode root = solveExercise2(C);
		System.out.println("(Exercise 2 solution) Huffman code for each character:");
		printCode(root,"");
		System.out.println("\n");
	}
	
	// anadromikh synarthsh gia ektypwsh
	// ths kwdikopoihshs huffman tou kathe xarakthra.
	// to s paristanei to 0,1 h 2 antistoixa.
    public void printCode(HuffmanNode root, String s) 
    { 
  
        // an o komvos exei aristero,mesaio kai dexi ypodentro isa me null
		// tote einai fyllo tou dentrou kai ektypwnw ton antistoixo xarakthra.
        if (root.left 
                == null
			&& root.middle
			    == null
            && root.right 
                   == null
            && Character.isLetter(root.c)) { 
  
            // c einai o xarakthras tou komvou.
            System.out.println(root.c + ":" + s); 
  
            return; 
        } 
  
        // an pame sta aristera vazoume 0. 
        // an pame kentro vazoume 1.
	    // kai an pame dexia vazoume 2.
  
        // anadromikh klhsh ths synarthshs gia aristero,
		// mesaio kai dexi ypodentro antistoixa.
        printCode(root.left, s + "0"); 
        printCode(root.middle, s + "1");
		printCode(root.right,s + "2");
    } 
	
	public HuffmanNode solveExercise2(HashMap<Character,Integer> C){
		int n = C.size();
		char[] letters = new char[n];
		int[] counts = new int[n];
		int index = 0;
		// edw vazw ta keys kai ta values se pinakes wste 
		// na mporw na ta valw pio eykola sthn PQ kai sto dentro.
		for (Map.Entry<Character, Integer> mapEntry : C.entrySet()) {
			letters[index] = mapEntry.getKey();
			counts[index] = mapEntry.getValue();
			index++;
		}
		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator()); 
		// edw ftiaxnw ta huffman nodes kai ta topothetw sto queue.
		for (int i = 0; i<n; i++){
			HuffmanNode hn = new HuffmanNode();
			hn.c = letters[i];
			hn.data = counts[i];
			hn.left = null;
			hn.middle = null;
			hn.right = null;
			q.add(hn);
		}
		HuffmanNode root = null; // h riza tou dentrou.
		// edw tha vgazw ta 3 minimum values (giati exoume 0,1,2)
		// apo ton swro mexri to megethos tou PQ na ftasei 1.
		// synexizw mexri na ginoun extracted ola ta values.
		while (q.size() > 1){
			// 1o min extract. 
            HuffmanNode x = q.peek(); 
            q.poll(); 
  
            // 2o min extract. 
            HuffmanNode y = q.peek(); 
            q.poll(); 
			
			// 3o min extract.
			HuffmanNode z = q.peek();
			q.poll();
			
            // ftiaxnw ena neo node f pou isoutai me to
			// athroisma twn syxnothtwn twn 3 nodes kai
			// vazw thn timh tous sto f.
            HuffmanNode f = new HuffmanNode(); 
            f.data = x.data + y.data + z.data; 
            f.c = '-'; 
  
            // to prwto extracted node to orizw ws aristero paidi. 
            f.left = x; 
			// to deytero extracted node to orizw ws mesaio paidi.
			f.middle = y;
		
            // kai to trito ws dexi paidi. 
            f.right = z; 
  
            //kanoume to f riza. 
            root = f; 
  
            // kai to vazoume sto PQ. 
            q.add(f); 
		}
		return root;
	}
}