import java.util.*;
import java.io.*;
public class Thiseas{
	// to stack mas gia to Traverse, typou Position gia na dexetai tis theseis twn keliwn kai na ginetai anazhthsh.
	static StringStackImpl<Position> stack = new StringStackImpl<>(); 
	
	
	/* methodos pou pairnei ws orismata ton pinaka , th thesh eisodou , arithmo grammwn kai sthlwn.
	xrhsimopoieitai gia thn eyresh tou monopatiou kai ths exodou. */
	public static void Traverse (int [][] maze,Position Enter,int rows,int cols){
		stack.push(Enter); // bazoume th thesh eisodou ws prwto stoixeio sto stack.
		int i = Enter.getX(); // pairnoume th thesh keliou grammhs.
		int j = Enter.getY(); // pairnoume th thesh keliou sthlhs.
		// ta topothetoume sto properCell gia na vlepoume apo pou xekiname kai arxizei h anazhthsh.
		Position properCell = new Position(i,j);
				while(!stack.isEmpty()){
						System.out.println("Current position is: (" + properCell.getX() + "," + properCell.getY() + ")"); 
						if(i>0 && maze[i-1][j] == 0 && maze[i-1][j] != 3){ // xekiname psaxnwntas panw.
						maze[i-1][j] = 3; // antikathistoume ta kelia pou exoume episkeptei me 3.
						i = i - 1;
						properCell = new Position(i,j); // bazoume th thesh pou eimaste sto properCell,
						stack.push(properCell); // kai meta to topothetoume sth stoiva mas.
						if (i == 0){ // ta emfwleymena if psaxnoun gia th lysh kai otan th vroun stamataei h epanalhpsh.
							break;
						} // antistoixa kai gia ta ypoloipa.
					}
					else if (j>0 && maze[i][j-1] == 0 && maze[i][j-1] != 3){ // meta aristera.
						maze[i][j-1] = 3;
						j = j - 1;
						properCell = new Position(i,j); 
						stack.push(properCell);
						if (j == 0 || i == 0){
							break;
						}
						
					
					
					}
					else if (i < rows-1 && maze[i+1][j] == 0 && maze[i+1][j] != 3){// meta katw.
						maze[i+1][j] = 3;
						i = i + 1;
						properCell = new Position(i,j); 
						stack.push(properCell);
						if (i == rows - 1){
							break;
						}
					}
					else if (j < cols -1 && maze[i][j+1] == 0 && maze[i][j+1] != 3 ){// meta dexia.
						maze[i][j+1] = 3;
						j = j + 1;
						properCell = new Position(i,j); 
						stack.push(properCell);
						if (j == cols - 1 || i == 0){
							break;
						}
						
					
					
					}
					else{//backtracking an den vrei kapoio 0.
						stack.pop();
						if (!stack.isEmpty()){
							Position fail = stack.peek();
							i = fail.getX();
							j = fail.getY();
							properCell.setX(i);
							properCell.setY(j);
						}
						
					 
					
					}
				
					 
			}
			
		
	  if(stack.isEmpty()){ // an to stack einai adeio den yparxei exodos.
		  System.out.println("No path found.");
	  } else { /* an to stack den einai adeio to mono stoixeio pou apomenei einai h exodos
				 kai to typwnoume.*/
		  System.out.println("Path found.");
		  System.out.println("The exit is: (" + properCell.getX() + "," + properCell.getY()+")");
	  }
		
	
	} 
	
	public static void main(String[] args){
		int numRows,numColumns; // sthles , grammes
		int [][] grid; // o pinakas mas
		Position Entrance; // to E.
		int EntryX,EntryY; // ayta tha xrhsimopoihthoun gia th thesh ths eisodou.
		File file = null;
		BufferedReader reader = null; // ayta ta 2 gia to file mas.
		try{
			file = new File(args[0]); // ayto einai gia na pairnei to arxeio san orisma. (java Thiseas filename.txt px).
		}catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Error.No file given."); // ayto vgainei an grapsoyme java Thiseas.
		}try{
			reader = new BufferedReader(new FileReader(file));
		}catch (FileNotFoundException e){
			System.err.println("Error. The file can't be opened or doesn't exist");
		}      // ayto vgainei an grapsoume java Thiseas <onona arxeiou pou den yparxei>.txt
		try{
			Scanner scan =  new Scanner (file);
				numRows = scan.nextInt(); // scannaroume ton arithmo twn grammwn apo to arxeio me ton scanner.
				System.out.println("rows num is: " + numRows); // ayta einai mono gia na elegxoume oti ta diavase swsta.
				numColumns = scan.nextInt(); // omoiws gia arithmo twn sthlwn.
				System.out.println("columns num is: " + numColumns);
				EntryX = scan.nextInt(); // keli grammhs ths eisodou (ston dosmeno labyrintho to 0).
				EntryY = scan.nextInt(); // keli sthlhs ths eisodou (ston dosmeno labyrintho to 3).
				Entrance = new Position (EntryX,EntryY); // edw kanoume initialize thn eisodo.
				System.out.println("Entrance is: (" + Entrance.getX() + "," + Entrance.getY()+ ")");
				// initialize ton pinaka me tis diastaseis pou phrame ap to arxeio.
				grid = new int [numRows][numColumns]; 
				// edw scannaroume ton pinaka kai ton topothetoume sth metavlhth grid.
				for (int i = 0; i < numRows; i++){
					for (int j = 0; j < numColumns; j++){
						grid [i][j] = scan.nextInt();
					}
				}
				/*ta loops apo katw ousiastika dhmiourgoun th swsth morfh tou pinaka se String
				kai ton kanoun print sthn othonh gia na sigoureytoume oti exei scannaristei swsta. */
				String result = "\n";
				for (int i = 0; i<numRows; i++){
					for (int j = 0; j < numColumns; j++){
						result += grid[i][j] + " ";
					}
					result += "\n";
				}
				System.out.println(result);
				// telos, edw  xrhsimopoieitai h methodos Traverse gia na vrethei h lysh tou dosmenou ap to arxeio labyrinthou.
				Traverse(grid,Entrance,numRows,numColumns);
		}catch (IOException e){ // auto elegxei ton scanner.
			System.out.println("IO exception , try again.");
		}
	}				
}