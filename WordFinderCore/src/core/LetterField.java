package core;

public class LetterField implements ILetterField {

	private final Letter letter;
	private final int x;
	private final int y;
	private boolean selected;
	
	public LetterField (Letter letter, int x, int y) {
		this.letter = letter;
		this.x = x;
		this.y = y;
		this.selected = false;
	}
	
	@Override
	public Letter getLetter() {
		return this.letter;
	}

	@Override
	public void select() {
		this.selected = true;
	}

	@Override
	public void unselect() {
		this.selected = false;
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public boolean isAdjacent(ILetterField other) {
		return !(this.getX() == other.getX() && this.getY() == other.getY())
				&& this.getX() >= other.getX() - 1
				&& this.getX() <= other.getX() + 1
				&& this.getY() >= other.getY() - 1
				&& this.getY() >= other.getY() + 1;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

}
