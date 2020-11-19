import java.io.PrintStream;
import java.util.NoSuchElementException;
/* sth thesh tou T mporoume na valoume
   oti typo dedomenwn theloume (int,double,string klp). */
public class StringStackImpl<T> implements StringStack<T>{
	protected class Node<T>{
		T item; 
		Node<T> next;
		/* ftiaxnoume ayto to class gia na 
		mporoume na ylopoihsoume th syndedemenh lista */
		Node(T item, Node<T> next){
			this.item = item; 
			this.next = next;
		}
	}
	protected Node<T> head = null;
	public int count = 0; // ayto ginetai gia na exoume O(1) sto size. 
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void push(T item){
		head = new Node<T>(item,head);
		count++; // kathe fora pou mpainei ena stoixeio ayxanetai to count kata 1.
	}
	
	public T pop() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}else{
		count--; // kathe fora pou afaireitai ena stoixeio meiwnetai to count kata 1.
		T v = head.item;
		Node<T> t = head.next;
		head = t;
		return v;
		}
	}
	
	public T peek() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}else{
			T v = head.item;
			return v;
		}
	}
	
	public void printStack(PrintStream stream){
		Node<T> current = head; // to current einai to index mas.
		while (current.item != null){
			System.out.println(current.item);
			if (current.next == null){
				break;
			}
			current = current.next;
		}
	}
	
	public int size(){
		return count;
	}
	
	public T findMiddle (Node<T> h){
		if (h == null){
			return null;
		}
		int N = 0;
		Node<T> current = h;
		while (current != null){
			N++;
			current = current.next;
		}
		int counter = 0;
		current = h;
		if (N % 2 == 0){
			while (current != null){
				if (counter == N/2+1){
					h = current;
				}
				counter++;
				current = current.next;
			}
		}
		else{
			while (current != null){
				if (counter == N/2){
					h = current;
				}
				counter++;
				current = current.next;
			}
		}
		//h.item = h.item*2;
		return h.item;
	}
}