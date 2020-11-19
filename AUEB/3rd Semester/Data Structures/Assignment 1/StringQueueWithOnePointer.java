import java.util.NoSuchElementException;
import java.io.PrintStream;
public class StringQueueWithOnePointer<T> {
	protected class Node<T> {
		T item;
		Node<T> next;
		Node(T item){
			this.item = item; 
			next = null;
		}
	}
	protected Node<T> tail = null;
	public int count = 0; // ayto ginetai gia na exoume O(1) sto size.
	
	public boolean isEmpty(){
		return tail == null;
	}
	
	public void put(T item) {	
		Node<T> t = new Node<T>(item);
		count++; // kathe fora pou mpainei ena stoixeio ayxanetai to count kata 1.
		if (isEmpty()){
			tail = t;
			tail.next = tail;
		}else{
			t.next = tail.next;
			tail.next = t;
			tail = t;
		}
		
	}
	
	public T get() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			count --; // kathe fora pou afaireitai ena stoixeio meiwnetai to count kata 1.
			if (tail.next == tail){
				T g = tail.item;
				tail = null;
				return g; // to if ayto ginetai gia na mhn exoume NullPointerException.
			}
			T v = tail.next.item;
			Node<T> t = tail.next;
			tail.next = t.next;
			t.next = null;
			return v;
		}
	}
	
	public T peek() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			T v = tail.next.item;
			return v;
		}
	}
	
	public void printQueue(PrintStream stream){
		Node<T> current = tail.next; // to current einai to index mas.
		while (current.next != tail){
			System.out.println(current.item);
			if (current.next == null){
				break;
			}
			if (current.next == tail){
				System.out.println(tail.item);
				break;
			}
			current = current.next;
		}
		System.out.println(current.item);
		System.out.println(tail.item); //ayto gia na vgainoun ola ta stoixeia mas.
	}
	
	public int size(){
		return count;
	}
}	