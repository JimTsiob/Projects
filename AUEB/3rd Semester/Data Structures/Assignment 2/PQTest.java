class PQTest{
	public static void main(String args[]){
		MaxPQ queue = new MaxPQ(3);
		Disk LG = new Disk(1);
		Disk Toshiba = new Disk(2);
		queue.insert(LG);
		queue.insert(Toshiba);
		Toshiba.insertFolder(1200);
		Toshiba.insertFolder(2000);
		LG.insertFolder(1500);
		System.out.println("Is the queue empty?: " + queue.empty());
		System.out.println("\t");
		queue.getmax();
		System.out.println("Is the queue empty?: " + queue.empty());
		queue.getmax(); // testing getmax.
		System.out.println("Is the queue empty?: " + queue.empty());
		
	}
	
}
