import java.util.*;
import java.io.*;
public class Main{
	public static void main(String[] args){
		int telos = 0;
		int choice = 0; // <- h epilogh tou xrhsth.
		int corX = 0; // h syntetagmenh X gia ta shmeia tou arxeiou.
		int corY = 0; // h syntetagmenh Y gia ta shmeia tou arxeiou.
		int N = 0; // to megethos tou Dentrou.
		TwoDTree groot = new TwoDTree(); // to dentro mas.
		File file = null;
		BufferedReader reader = null; // ayta ta 2 gia to file mas.
		try{
			file = new File(args[0]); // ayto einai gia na pairnei to arxeio san orisma. (java Main input.txt px).
		}catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Error.No file given."); // ayto vgainei an grapsoyme java Main.
		}try{
			reader = new BufferedReader(new FileReader(file));
		}catch (FileNotFoundException e){
			System.err.println("Error. The file can't be opened or doesn't exist");
		}
		String line; // gia reading.
		try{
			Scanner fscan = new Scanner(file); // scanner gia to file.
			N = fscan.nextInt(); // pairnoume to plhthos N.
			int pcount = 0; // tha xrhsimopoithei gia error, an exoume parapanw komvous ap to plhthos.
			while(fscan.hasNext()){
				corX = fscan.nextInt();
				corY = fscan.nextInt();
				if (corX > 100 || corX < 0 || corY > 100 || corY < 0){
					System.out.println("numbers must be >= 0 and <= 100");
					return;
				}
				Point ins = new Point(corX,corY);
				groot.insert(ins);
				pcount++;
			}
			if (pcount > N){
				System.out.println("Tree Nodes are more than the actual size.");
				return;
			}
		}catch(FileNotFoundException e){
			System.err.println("Error. The file can't be opened or doesn't exist");
		}
		System.out.println(groot.toString()); // print to dentraki.
		Scanner scan = new Scanner(System.in); // scanner gia to input tou xrhsth.
		do {   // Menu.
			System.out.println("\n1. Compute the size of the tree.");
			System.out.println("2. Insert a new Point: ");
			System.out.println("3. Search if a given point exists in the tree: ");
			System.out.println("4. Provide a query rectangle: ");
			System.out.println("5. Provide a query point: ");
			System.out.print("\nChoice?: ");
			choice = scan.nextInt();
			
			if (choice == 1){
				System.out.println("Size is: " + groot.size());
			}
			
			if (choice == 2){
				System.out.print("\nGive X: ");
				int SynX = scan.nextInt();
				System.out.print("\nGive Y: ");
				int SynY = scan.nextInt();
				if (SynX > 100 || SynX < 0){
					System.out.println("X must be >= 0 and <= 100");
					System.out.print("\nGive X: ");
					SynX = scan.nextInt();
				}
				if (SynY > 100 || SynY < 0){
					System.out.println("Y must be >= 0 and <= 100");
					System.out.print("\nGive Y: ");
					SynY = scan.nextInt();
				}
				Point insertion = new Point(SynX,SynY);
				groot.insert(insertion);
				System.out.println("Point inserted.");
			}
			
			if (choice == 3){
				System.out.print("\nGive X: ");
				int SynX = scan.nextInt();
				System.out.print("\nGive Y: ");
				int SynY = scan.nextInt();
				if (SynX > 100 || SynX < 0){
					System.out.println("X must be >= 0 and <= 100");
					System.out.print("\nGive X: ");
					SynX = scan.nextInt();
				}
				if (SynY > 100 || SynY < 0){
					System.out.println("Y must be >= 0 and <= 100");
					System.out.print("\nGive Y: ");
					SynY = scan.nextInt();
				}
				Point src = new Point(SynX,SynY);
				System.out.println("Is (" + src.x + "," + src.y + ") in the tree?: " + groot.search(src));
			}
			
			if (choice == 4){
				System.out.print("Give xmin: ");
				int xMin = scan.nextInt();
				System.out.print("Give xmax: ");
				int xMax = scan.nextInt();
				System.out.print("Give ymin: ");
				int yMin = scan.nextInt();
				System.out.print("Give ymax: ");
				int yMax = scan.nextInt();
				if (xMin > 100 || xMin < 0){
					System.out.println("xmin must be >= 0 and <= 100.");
					System.out.print("Give xmin: ");
					xMin = scan.nextInt();
				}
				if (xMax > 100 || xMax < 0){
					System.out.println("xmax must be >= 0 and <= 100.");
					System.out.print("Give xmax: ");
					xMax = scan.nextInt();
				}
				if (yMin > 100 || yMin < 0){
					System.out.println("ymin must be >= 0 and <= 100.");
					System.out.print("Give ymin: ");
					yMin = scan.nextInt();
				}
				if (yMax > 100 || yMax < 0){
					System.out.println("ymax must be >= 0 and <= 100.");
					System.out.print("Give ymax: ");
					yMax = scan.nextInt();
				}
				if (xMin > xMax){
					System.out.println("xmax must be greater than xmin.");
					System.out.print("Give xmin: ");
					xMin = scan.nextInt();
					System.out.print("Give xmax: ");
					xMax = scan.nextInt();
				}
				if (yMin > yMax){
					System.out.println("ymax must be greater than ymin.");
					System.out.print("Give ymin: ");
					yMin = scan.nextInt();
					System.out.print("Give ymax: ");
					yMax = scan.nextInt();
				}
				Rectangle r1 = new Rectangle(xMin,yMin,xMax,yMax);
				System.out.println("The Rectangle is: " + r1.toString());
				List<Point> pointList = new List<Point>();
				pointList = groot.rangeSearch(r1);
				System.out.println("Points in the Rectangle are: ");
				pointList.printList();
				
			}
			
			if (choice == 5){
				System.out.print("\nGive X: ");
				int SynX = scan.nextInt();
				System.out.print("\nGive Y: ");
				int SynY = scan.nextInt();
				if (SynX > 100 || SynX < 0){
					System.out.println("X must be >= 0 and <= 100");
					System.out.print("\nGive X: ");
					SynX = scan.nextInt();
				}
				if (SynY > 100 || SynY < 0){
					System.out.println("Y must be >= 0 and <= 100");
					System.out.print("\nGive Y: ");
					SynY = scan.nextInt();
				}
				Point neighbor = new Point(SynX,SynY);
				System.out.println("Point (" + SynX + "," + SynY + ") is closer to : " + groot.nearestNeighbor(neighbor));
			}
		} while(telos==0); // atermwn broxos.
	}
}