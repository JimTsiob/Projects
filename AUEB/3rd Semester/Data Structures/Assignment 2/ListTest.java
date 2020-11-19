class ListTest{
	public static void main(String args[]){
		List<Integer> nums = new List<>();
		nums.insertAtFront(5);
		nums.insertAtBack(6);
		nums.printList();
		nums.removeFromFront();
		nums.removeFromFront();
		nums.removeFromFront(); // throws NoSuchElementException.
	}
}