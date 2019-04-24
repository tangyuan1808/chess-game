package lz.model;

public class Player {

	int color;
	int score;
	
	public Player(int color) {
		this.color = color;
		this.score = 0;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [color=" + color + ", score=" + score + "]";
	}
	
	
	
	
}
