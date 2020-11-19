class Sort{
	public static void quickSort(int[] a, int p, int r){
		if (r <= p) {
			return;
		}
		int i = partition(a,p,r);
		quickSort(a,p,i-1);
		quickSort(a,i+1,r);
	}
	
	public static int partition(int [] a, int l, int r){
		int i = l-1;
		int j = r; 
		int v = a[r]; // pivot
		for(;;){
			while(less(v,a[++i])); // allazoume th seira sto less (a[++i,v]).
			while(less(a[--j],v)) if (j == l) break; // kai sto less(v,a[--j]) kai etsi exoume taxinomhsh fthinousas seiras.
			if (i>=j) break;
			exch(a,i,j);
		}
		exch(a,i,r);
		return i;
	}
	
	static boolean less (int v , int w){ 
		return v < w;
	}
	
	static void exch(int[] a, int i, int j){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}