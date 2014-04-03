import java.awt.Color;


public class Pixel {

	private int red, green, blue, pos;

	public Pixel(int i, int red, int green, int blue) {
		this.pos = i;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public Color getColor(){
		return new Color(red, green, blue);
	}

	public int getRGB(){
		return new Color(red, green, blue).getRGB();
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int i) {
		pos = i;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public String toString(){
		return pos + "\n";
	}
	
	
	
}
