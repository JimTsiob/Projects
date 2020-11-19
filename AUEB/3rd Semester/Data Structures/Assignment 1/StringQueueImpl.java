import java.io.PrintStream;
import java.util.NoSuchElementException; 
/* sth thesh tou T mporoume na valoume
   oti typo dedomenwn theloume (int,double,string klp). */
public class StringQueueImpl<T> implements StringQueue<T>{
	protected class Node<T>{
		T item; 
		Node<T> next;
		/* ftiaxnoume ayto to class gia na 
		mporoume na ylopoihsoume th syndedemenh lista */
		Node(T item){
			this.item = item; 
			next = null;
		}
	}
	protected Node<T> head = null;
	protected Node<T> tail = null;
	public int count = 0; // ayto ginetai gia na exoume O(1) sto size. 
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void put(T item){
		Node<T> t = tail;
		count ++; // kathe fora pou mpainei ena stoixeio ayxanetai to count kata 1. 
		tail = new Node<T>(item);
		if (isEmpty()){
			head = tail;
		}else{
			t.next = tail;
		}
	}	
	public T get() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}else{
		count --; // kathe fora pou afaireitai ena stoixeio meiwnetai to count kata 1.
		T v = head.item;
		Node<T> t = head.next;
		head = t;
		return v;
		}
	}
	
	public T peek() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			T v = head.item;
			return v;
		}
	}
	
	public void printQueue(PrintStream stream){
		Node<T> current = head; // to current einai to index mas.
		while (current.item != null){
			System.out.println(current.item);
			if (current.next == null){
				break; //to if ayto ginetai gia na mhn vgalei NullPointerException.
			}
			current = current.next;
		}
	}
	
	public int size(){
		return count;
	}
	
}	
	
		