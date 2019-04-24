package lz.action;

public class  Movement {

	int startX;
	int startY;
	int endX;
	int endY;
	
	
	
	public Movement(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	
	
	public boolean doIMove(int startX, int startY, int endX, int endY)
	{
		if( startX == endX && startY == endY )
			return false;
		else
			return true;
	}
	
	
	
	public int getStartX() {
		return startX;
	}





	public void setStartX(int startX) {
		this.startX = startX;
	}





	public int getStartY() {
		return startY;
	}





	public void setStartY(int startY) {
		this.startY = startY;
	}





	public int getEndX() {
		return endX;
	}





	public void setEndX(int endX) {
		this.endX = endX;
	}





	public int getEndY() {
		return endY;
	}





	public void setEndY(int endY) {
		this.endY = endY;
	}



	@Override
	public String toString() {
		return "movement [startX=" + startX + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY + "]";
	}
	
	
	
	
}
