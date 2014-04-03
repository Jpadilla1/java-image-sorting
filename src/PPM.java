import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class PPM extends JFrame{

	private ArrayList<Pixel> pixels = new ArrayList<>();
	private int width, height, nColors;
	private String type;
	private JLabel label;

	public PPM(ArrayList<Pixel> pixels, int width, int height, int nColors, String type) throws FileNotFoundException {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.nColors = nColors;
		this.type = type;

		label = new JLabel(this.getImage());
		add(label);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public ArrayList<Pixel> getPixels() {
		return pixels;
	}

	public void setPixels(ArrayList<Pixel> pixels) {
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getnColors() {
		return nColors;
	}

	public void setnColors(int nColors) {
		this.nColors = nColors;
	}

	public String getImageType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void shuffle(){
		Collections.shuffle(pixels);
	}

	public void refresh() {
		label.setIcon(this.getImage());
		this.repaint();
	}

	public ImageIcon getImage() {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < pixels.size(); i++){
			bi.setRGB( i % width, i / width, pixels.get(i).getRGB());
		}
		return new ImageIcon(bi);
	}

	public void selectionSort() {
		int refreshRate = 0;
		for (int i = 0; i < pixels.size(); i++) {
			Pixel minIndexVal = pixels.get(i);
			int pos = 0;
			for (int j = i+1; j < pixels.size(); j++) {
				if ( pixels.get(j).getPos() < minIndexVal.getPos() ) { 
					minIndexVal = pixels.get(j);
					pos = j;
				}
			}

			Pixel temp = pixels.get(i);
			pixels.set(i, minIndexVal);
			pixels.set(pos, temp);
			refreshRate++;
			if (refreshRate >= 500){
				refreshRate = 0;
				refresh();
			}
		}
	}

	public void insertionSort(){
		Pixel temp;
		int refreshRate = 0;
		for (int i = 1; i < pixels.size(); i++) {
			for(int j = i ; j > 0 ; j--){
				if(pixels.get(j).getPos() < pixels.get(j-1).getPos()){
					temp = pixels.get(j);
					pixels.set(j, pixels.get(j-1));
					pixels.set(j-1, temp);
				}
			}
			refreshRate++;
			if (refreshRate >= 1000){
				refreshRate = 0;
				refresh();
			}
		}
	}

	public ArrayList<Pixel> mergeSort(ArrayList<Pixel> pixels, int refreshRate) {
		ArrayList<Pixel> firstHalf = new ArrayList<>();
		ArrayList<Pixel> secondHalf = new ArrayList<>();
		for (int i = 0; i < pixels.size() / 2; i++) {
			firstHalf.add(pixels.get(i));
		}
		for (int i = pixels.size() / 2; i < pixels.size(); i++) {
			secondHalf.add(pixels.get(i));
		}
		
		return merge(mergeSort(firstHalf, refreshRate), mergeSort(secondHalf, refreshRate));
	}

	public ArrayList<Pixel> merge(ArrayList<Pixel> l1, ArrayList<Pixel> l2) {
		if (l1.size() == 0) {
			return l2;
		}
		if (l2.size() == 0) {
			return l1;
		}
		ArrayList<Pixel> result = new ArrayList<>();
		Pixel nextElement;
		if (l1.get(0).getPos() > l2.get(0).getPos()) {
			nextElement = l2.get(0);
			l2.remove(0);
		} else {
			nextElement = l1.get(0);
			l1.remove(0);
		}
		result.add(nextElement);
		result.addAll(merge(l1,l2));
		return result;
	}

}
