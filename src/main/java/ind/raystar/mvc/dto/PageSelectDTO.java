package ind.raystar.mvc.dto;

public class PageSelectDTO {
	private int last;
	private int start;
	private int end;
	private int current;
	
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getStart() {
		return start;
	}
	public int getCurrent() {
		return current;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
}
