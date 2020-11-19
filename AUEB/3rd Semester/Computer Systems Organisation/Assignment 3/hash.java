 import java.util.Scanner;
 
public class hash{
	//katholikes metavlites
	final static int N=10; 
	static int keys =0;
	public static void main(String args[]){ //run
	    Scanner Cin = new Scanner(System.in); 
		int key,pos,choice,telos=0; //metavlites programmatos
		int[] hash = new int [N]; //pinakas katakermatismoy
		for (int i=0; i<N; i++)hash[i]=0; //arxikopoihsh pinaka
		do{
			System.out.println(" Menu");
			System.out.println("1.Insert Key");
			System.out.println("2.Find Key");
			System.out.println("3.Display Hash Table");
			System.out.println("4.Exit");
			System.out.println("\nChoice?: ");
			choice=Cin.nextInt();
			if (choice==1){
				System.out.println("Give new key (greater than zero): ");
				key=Cin.nextInt();
				if (key>0) insertkey(hash,key);
				else System.out.println ("key must be greater than zero");
			}
			if (choice==2){
				System.out.println("Give key to search for: ");
				key=Cin.nextInt();
				pos = findkey(hash,key);
				if (pos==-1)
					System.out.println("Key not in hash table.");
				else {
					System.out.println("Key value = " + hash[pos]);
					System.out.println("Table position = "+pos);
				}
			}
			if (choice==3) displaytable(hash);
			if (choice==4) telos =1;
		}while(telos==0);
	}
	static void insertkey(int[] hash,int k){ //typikes parametroi (dieuthynsh pinaka kai kleidi)
		/*
		Eisagagei ena kleidi ston pinaka katakermatismoy efoson to kleidi den yparxei hdh ston
		pinaka kai yparxei eleutherh thesh. Kalei th findkey poy elegxei an to kleidi brisetai hdh
		ston pinaka kai th hashfunction poy ypologizei th thesh eisagwghs toy kleidioy ston pinaka
		*/
		int position; //topikh metavlhth
		position=findkey(hash,k);
		if (position!=-1)System.out.println("Key is already in hash table.");
		else
			if (keys<N){
				position=hashfunction(hash,k);
				hash[position]=k;
				keys++;
			}
			else System.out.println("hash table is full");
	}
	
	static int hashfunction(int[] hash,int k){	//typikes parametroi (dieuthynsh pinaka kai kleidi)
		// Ypologizei th thesh eisagwghs toy kleidioy ston pinaka kai thn epistrefei
		int position;	//topikh metablhth
		position=k%N;
		while(hash[position]!=0){
			position++;
			position%=N;
		}
		return position;
	}
	
	static int findkey(int[] hash, int k){	//typikes parametroi (dieythynsh pinaka kai kleidi)
		/* Anazhtei ena kleidi ston pinaka katakermatismoy. An to brei epistrefei th thesh toy 
		diaforetika epistrefei -1 
		*/
		int position, i=0, found=0;  //topikes metablhtes 
		position=k%N;
		while(i<N && found==0){
			i++;
			if(hash[position]==k)
				found=1;
			else{
				position++;
				position%=N;
			}
		}
		if (found==1)
			return position;
		else 
			return -1;
	
	}
	static void displaytable(int[] hash){	//typikh parametros (dieythynsh pinaka)
		//Emfanizei ton pinaka katakermatismoy
		int i;	//topikh metablhth
		System.out.println("\npos key\n");
		for (i=0; i<N;i++)
			System.out.println(" " + i + " " +hash[i]);
	}
}