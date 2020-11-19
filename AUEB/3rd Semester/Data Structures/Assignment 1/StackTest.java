import java.util.NoSuchElementException;
import java.io.PrintStream;
public class StackTest{
	public static void main(String[] args){
		StringStackImpl<String> stack = new StringStackImpl<>(); // sth thesh tou String vazoume oti data type theloume (Integer,Float klp).
		stack.push("Jim");
		stack.push("Mike"); // vazoume 4 stoixeia sth stoiva.
		stack.push("Francis");
		stack.push("Johnathan");
		stack.push("George");
		stack.push("John");
		stack.push("Nick");
		stack.push("Maddox");
		stack.push("lel");
		stack.push("LUL");
		
		StringStackImpl<Integer> intStack = new StringStackImpl<>();
		
		intStack.push(1);
		intStack.push(2);
		intStack.push(3);
		intStack.push(4);
		intStack.push(5);
		intStack.push(6);
		intStack.push(7);
		intStack.push(8);
		intStack.push(9);
		intStack.push(10);
		System.out.println("Middle of int stack is: " + intStack.findMiddle(intStack.head));
		
		System.out.println("Size of stack is: "+stack.size()); // edw elegxetai to size.
		System.out.println("Stack empty or not?: "+stack.isEmpty()); // edw elegxetai h isEmpty.
		stack.printStack(System.out); // ektypwnei ta stoixeia sthn othonh.
		System.out.println("Printing value of head: "+stack.peek()); // to peek elegxetai ws exhs: ektypwnei to head sthn othonh.
		System.out.println("Size after peek: "+stack.size()); // kai me to size mporoume na doume an ontws afhnei ton komvo sth stoiva h oxi.
		stack.pop();
		System.out.println("printing after removal of one element");
		stack.printStack(System.out);
		System.out.println("Size after popping one element: " + stack.size());
		stack.pop();
		stack.pop(); // edw elegxetai h NoSuchElementException , tha vgalei sfalma edw.
	}
}
	