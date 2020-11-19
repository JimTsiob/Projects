import java.util.NoSuchElementException;
public interface ListInterface<T>{
	public void insertAtFront(T data);
	public void insertAtBack(T data);
	public T removeFromFront() throws NoSuchElementException;
	public T removeFromBack() throws NoSuchElementException;
	public void printList();
}