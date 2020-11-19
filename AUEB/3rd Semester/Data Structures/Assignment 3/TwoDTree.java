public class TwoDTree{
	private class TreeNode{
		private Point item; // an object of the class Point
		public TreeNode l; // left subtree pointer.
		public TreeNode r; // right subtree pointer.
		public boolean vert; // gia allagh epipedou sto search kai to insert (true = x, false = y).
		
		public TreeNode(Point item,boolean vertical){
			this.item = item;
			l = r = null;
			vert = vertical;
		}
		
	}
	private TreeNode head; // riza.
	public int treeSize = 0; // gia O(1).
	public Rectangle shape = new Rectangle(0,0,100,100); // gia to nearestNeighbor kai to rangeSearch (test Rectangle se times [0,100]).
	
	public TwoDTree(){} // default constructor.
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		return treeSize;
	}
	
	
	private TreeNode insertR(TreeNode h, Point p, boolean vert) {
		if (h == null){ 
		treeSize++;
		return new TreeNode (p,vert); // an to dentro einai adeio dhmiourgeitai h riza.
		}
		if (p.x == h.item.x && p.y == h.item.y) {
			System.out.println("Point is already in the tree or equals to the root.");
			return h;
		}
		if (p.x < h.item.x && vert || !vert && p.y < h.item.y) h.l = insertR(h.l,p,!vert); 
		else h.r = insertR(h.r,p,!vert);
		return h; 
	}
		
	public void insert(Point p) {
		head = insertR(head,p,true);
	}	
	
	private boolean searchR(TreeNode h, Point p, boolean vert) { 
		if (h == null) {
			return false;
		} 
		if (p.x == h.item.x && p.y == h.item.y) return true; 
		if (p.x < h.item.x && vert || !vert && p.y < h.item.y) return searchR(h.l,p,!vert);
		else return searchR(h.r,p,!vert);
	}
	
	public boolean search(Point p) {
		return searchR(head,p,true); 
	}
	
	
	private Point nearRecursive(TreeNode h,Rectangle rect,int x,int y,Point c){
		if (h == null){
			return c;
		}
		
		int pointDistance = 0;
		int rectDistance = 0;
		Rectangle left = null;
		Rectangle right = null;
		Point query = new Point(x,y);
		Point near = c;
		
		if (near != null){
			pointDistance = query.squareDistanceTo(near);
			rectDistance = rect.squareDistanceTo(query);
		}	
		
		
		if (near == null || pointDistance > rectDistance){
			Point p = new Point(h.item.x,h.item.y);
			if (near == null || pointDistance > query.squareDistanceTo(p))
				near = p;
			
			
			if (h.vert){ // ayto shmainei oti eimaste sto epipedo X (katheta). 
				left = new Rectangle(rect.xmin(), rect.ymin(), h.item.x, rect.ymax()); // aristerh pleyra tou orthogwniou.
				right = new Rectangle(h.item.x,rect.ymin(), rect.xmax(), rect.ymax()); // dexia pleyra tou orthogwniou.
				
				if (x < h.item.x){
					near = nearRecursive(h.l,left,x,y,near);
					near = nearRecursive(h.r,right,x,y,near);
				}else{
					near = nearRecursive(h.r,right,x,y,near);
					near = nearRecursive(h.l,left,x,y,near);
				}
			
			}else{
				left = new Rectangle(rect.xmin(), rect.ymin(), rect.xmax(), h.item.y);
				right = new Rectangle(rect.xmin(), h.item.y, rect.xmax(), rect.ymax());
				
				if (y < h.item.y){ // eimaste sto epipedo y (orizontia) ara recursive anazhthsh gia to pio kontino shmeio edw.
					near = nearRecursive(h.l,left,x,y,near);
					near = nearRecursive(h.r,right,x,y,near);
				}else{
					near = nearRecursive(h.r,right,x,y,near);
					near = nearRecursive(h.l,left,x,y,near);
				}
			}
		
		}
		return near; // epistrofh kontinoterou shmeiou.
	}
	
	
	public Point nearestNeighbor(Point p){
		if (isEmpty()){
			return null;
		}
		// xrhsimopoioume anadromikh methodo wste na vroume to kontinotero shmeio sto p.
		return nearRecursive(head,shape,p.x,p.y,null);
		
	}
	
	private void rangeRecursive(TreeNode h,Rectangle rect1,Rectangle rect2,List<Point> list){
		if (h == null){ //ftiaxnoume anadromikh methodo gia na anazhtoume swsta to dentro kai to orthogwnio.
			return; 
		}
		
		if (rect2.intersects(rect1)){
			Point p = new Point(h.item.x,h.item.y);
			if (rect2.contains(p)){
				list.insertAtFront(p); // an vrisketai mesa sto orthogwnio thn vazoume sth lista.
			}
			if (h.vert){ // an h.vert exetazoume to x epipedo alliws to y.
				rect1 = new Rectangle(rect1.xmin(), rect1.ymin(), h.item.x, rect1.ymax());
			}
			else {  // aristerh pleyra tou orthogwniou.
				rect1 = new Rectangle(rect1.xmin(), rect1.ymin(), rect1.xmax(), h.item.y);
			}
			rangeRecursive(h.l,rect1,rect2,list); // exetazoume prwta to aristero ypodentro.
			if (h.vert){
				rect1 = new Rectangle(h.item.x, rect1.ymin(), rect1.xmax(), rect1.ymax());
			}else{ 		// dexia pleyra toy orthogwniou.
				rect1 = new Rectangle(rect1.xmin(), h.item.y, rect1.xmax(), rect1.ymax());
			}// exetazoume meta to dexi ypodentro.
			rangeRecursive(h.r,rect1, rect2, list);
			
		}
		
	}
	
	public List<Point> rangeSearch(Rectangle rect){
		List<Point> pointList = new List<Point>();
		rangeRecursive(head,shape,rect,pointList);
		return pointList;
	}

	
	private String toStringR(TreeNode h) { 
		if (h == null) return ""; 
		String s = toStringR(h.l); 
		s += h.item.toString() + "\n"; // toString gia test tou dentrou.
		s += toStringR(h.r);
		return s; 
	} 
	
	public String toString() { 
		return toStringR(head); 
	} 
}