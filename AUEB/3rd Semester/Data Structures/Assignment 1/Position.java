public class Position{
		/* dikh mas klash , deixnei to shmeio ston pinaka
		xrhsimopoieitai gia to Exit kai to path (an yparxoun).*/
		protected int x;
		protected int y;
		
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public int setX(int x){
			this.x = x;
			return x;
		}
		
		public int setY(int y){
			this.y = y;
			return y;
		}
}