package yilanOyunu;

import javax.swing.JTextField;

public class Part extends JTextField{

	
	private static final long serialVersionUID = 1L;
	private int partX;
	private int partY;
	private int partHeight=30;
	private int partWidth=30;
	private String partYon;
	public int getPartX() {
		return partX;
	}
	public void setPartX(int partX) {
		this.partX = partX;
	}
	public int getPartY() {
		return partY;
	}
	public void setPartY(int partY) {
		this.partY = partY;
	}
	public int getPartHeight() {
		return partHeight;
	}
	public void setPartHeight(int partHeight) {
		this.partHeight = partHeight;
	}
	public int getPartWidth() {
		return partWidth;
	}
	public void setPartWidth(int partWidth) {
		this.partWidth = partWidth;
	}
	public String getPartYon() {
		return partYon;
	}
	public void setPartYon(String partYon) {
		this.partYon = partYon;
	}
	
	
	
	
	
}
