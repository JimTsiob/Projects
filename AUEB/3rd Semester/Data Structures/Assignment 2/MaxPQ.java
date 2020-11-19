class MaxPQ{ // ylopoihsh Ouras Proteraiothtas me swro (heap).
	// logikh: einai to megethos tou diskou v mikrotero ap tou diskou w?
	boolean less (int i, int j){ 
		return pq[i].getFreeSpace() < pq[j].getFreeSpace();
	}
	
	void exch(int i, int j){
		Disk t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while (k > 1 && less(k/2,k)){
			exch(k,k/2);
			k = k/2;
		}
	}
	
	private void sink(int k, int N){
		while (2*k <= N){
			int j = 2*k;
			if (j < N && less(j,j+1)) j++;
			if (!less(k,j)) break;
			exch(k,j);
			k = j;
		}
	}
	
	private Disk [] pq;
	private int N;
	MaxPQ(int maxN){
		pq = new Disk[maxN+1];
		N = 0;
	}
	
	boolean empty(){
		return N == 0;
	}
	
	void insert(Disk item){
		pq[++N] = item;
		swim(N);
	}
	
	Disk getmax(){
		exch(1,N);
		sink(1,N-1);
		return pq[N--];
	}
	
	public int printMaxSpace(){ // print to xwro tou diskou me to perissotero space (gia to greedy).
		int max = 0;
		for (int i = 1; i<N+1; i++){
			if (pq[i].getFreeSpace() > max){
				max = pq[i].getFreeSpace();
			}
		}
		return max;
	}
	
	public int printMaxId(){ // print to id tou diskou me to perissotero space (gia to greedy).
		int max = 0;
		int id = 0;
		for (int i = 1; i<N+1; i++){
			if (pq[i].getFreeSpace() > max){
				max = pq[i].getFreeSpace();
				id = pq[i].id;
			}
		}
		return id;
	}
	
	public void printMaxFolder(){ // typwnei tous fakelous tou diskou me to megalytero free space.
		int max = 0;
		for (int i = 1; i<N+1; i++){
			if(pq[i].getFreeSpace() > max){
				max = pq[i].getFreeSpace();
			}
		}
		for (int i = 1; i<N+1; i++){
			if(pq[i].getFreeSpace() == max){
				pq[i].printFolders();
			}
		}
	}
	
	
}