class Disk implements Comparable<Disk>{
	public int id; // to id. 
	private int space = 1000000;	// xwros diskou (1000000 MB = 1 TB).
	List<Integer> folders = new List<>(); // lista fakelwn.
	//constructor tou Disk.
	Disk(int i){
		id = i;
	}
	
	void insertFolder(int item){
		folders.insertAtFront(item);
	// kathe fora pou mpainei neo folder meiwnoume to megethos tou ap to space gia na vroume to free space me thn getFreeSpace.
		space = space - item; 
	}					

	
	
	int getFreeSpace(){
		return space;
	}
	
	@Override
	public int compareTo(Disk B){ // h compareTo.
		if(this.getFreeSpace() > B.getFreeSpace()){ // A > B
			return 1;
		}
		else if(this.getFreeSpace() < B.getFreeSpace()){ // A < B
			return -1;
		} 
		else{  // A == B
			return 0;
		}
	}
	
	public void printFolders(){
		folders.printList();
	}
}
	