import java.util.NoSuchElementException;
import java.io.PrintStream;
public class QueueTest{
	public static void main(String[] args){
		StringQueueImpl<String> queue = new StringQueueImpl<>();
		queue.put("Jim");
		queue.put("Francis");
		queue.put("Mike"); //bazoume 3 stoixeia sto queue.
		System.out.println("Is the queue empty?: " + queue.isEmpty()); //elegxoume thn isEmpty.
		System.out.println("Queue size: " + queue.size());//elegxoume oti to size leitourgei opws prepei.
		System.out.println("Printing value of head: " + queue.peek()); //elegxoume thn peek.
		System.out.println("size after peeking: " + queue.size()); //elegxoume oti h peek den vgazei to head apo to queue.
		System.out.println("printing elements before get");
		queue.printQueue(System.out); // print ta stoixeia prin thn get.
		queue.get(); //elegxoume thn get.
		System.out.println("printing elements after get.");
		queue.printQueue(System.out); // print meta thn get.
		System.out.println("size after get: " + queue.size());
		queue.get();
		queue.get();
		queue.get(); // edw tha vgalei sfalma NoSuchElementException gia na to testaroume.
	}
}