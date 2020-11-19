import java.util.NoSuchElementException;
class List<T> implements ListInterface<T>{
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
	
	private Node<T> head = null;
	private Node<T> tail = null;
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void insertAtFront(T item){
		Node<T> t = new Node<>(item);
		if (isEmpty()){
			head = t;
			tail = t;
		} else{
			t.next = head;
			head = t;
		}
	}
	
	public void insertAtBack(T item){
		Node<T> t = new Node<>(item);
		if (isEmpty()){
			head = t;
			tail = t;
		} else{
			tail.next = t;
			tail = t;
		}
	}
	
	public T removeFromFront() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		
		T data = head.item;
		if (head == tail)
            head = tail = null;
        else
            head = head.next;

        return data;
	}
	
	public T removeFromBack() throws NoSuchElementException{
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		
		T data = tail.item;
		if (head == tail){
			head = tail = null;
		}
		else{
			Node<T> iterator = head;
            while (iterator.next != tail)
                iterator = iterator.next;

            iterator.next = null;
            tail = iterator;
		}
		
		return data;
	}
	
	public void printList(){
		Node<T> current = head; // to current einai to index mas.
		while (current.item != null){
			System.out.println(current.item);
			if (current.next == null){
				break; //to if ayto ginetai gia na mhn vgalei NullPointerException.
			}
			current = current.next;
		}
	}
	
}