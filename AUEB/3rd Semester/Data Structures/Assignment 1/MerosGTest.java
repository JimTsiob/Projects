import java.util.NoSuchElementException;
import java.io.PrintStream;
/* idia test me to StringQueue, sto <String>
bazoume oti typo dedomenwn theloume (Integer,Float klp).
kanw ta idia test me to Queue*/
public class MerosGTest{ 
	public static void main(String[] args){
		StringQueueWithOnePointer<String> circular = new StringQueueWithOnePointer<>();
		System.out.println(circular.isEmpty());
		circular.put("Jim");
		circular.put("Mike");
		circular.put("Francis");
		System.out.println ("peeking value is: " + circular.peek());
		System.out.println("size of queue is: " + circular.size());
		System.out.println("Queue before get: ");
		circular.printQueue(System.out);
		System.out.println("Queue after get: ");
		circular.get();
		circular.printQueue(System.out);
		System.out.println ("peeking value is: " + circular.peek());
		System.out.println("size after get and peek: "+circular.size());
		circular.get();
		System.out.println("size: "+circular.size());
		circular.get();
		System.out.println(circular.isEmpty());
		circular.get();		// edw bgazei NoSuchElementException.
	}
}