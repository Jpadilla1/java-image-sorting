import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class PPMFrame extends JFrame{

	private ArrayList<Pixel> pixels = new ArrayList<>();
	private int width, height, nColors;
	private String type;
	private JLabel label;

	public PPMFrame(ArrayList<Pixel> pixels, int width, int height, int nColors, String type) throws FileNotFoundException {
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
			if (refreshRate >= 1000){
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

	// Quick Sort

	public void quickSort() {
		qsort(pixels, 0, pixels.size() - 1, 0);
	}

	// quicksort the subarray from a[lo] to a[hi]
	private void qsort(ArrayList<Pixel> a, int lo, int hi, int refreshRate) { 
		if (hi <= lo) return;

		if (refreshRate >= 10) {
			refreshRate = 0;
			refresh();
		}
		int j = partition(a, lo, hi);
		qsort(a, lo, j-1, refreshRate++);
		qsort(a, j+1, hi, refreshRate++);
	}

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	// and return the index j.
	private int partition(ArrayList<Pixel> a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Pixel v = a.get(lo);
		while (true) {
			// find item on lo to swap
			while (less(a.get(++i), v))
				if (i == hi) break;

			// find item on hi to swap
			while (less(v, a.get(--j)))
				if (j == lo) break;      // redundant since a[lo] acts as sentinel

			// check if pointers cross
			if (i >= j) break;

			exch(a, i, j);
		}

		// put partitioning item v at a[j]
		exch(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	/***********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private boolean less(Pixel v, Pixel w) {
		return v.getPos() < w.getPos();
	}

	// exchange a[i] and a[j]
	private void exch(ArrayList<Pixel> a, int i, int j) {
		Pixel swap = a.get(i);
		a.set(i, a.get(j));
		a.set(j, swap);
	}


	// Merge
	// stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
	private void merge(ArrayList<Pixel> aux, int lo, int mid, int hi) {

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			if (aux.size() != pixels.size())
				aux.add(pixels.get(k));
			else
				aux.set(k, pixels.get(k));
		}

		// merge back to a[]
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              pixels.set(k, aux.get(j++));  // this copying is unneccessary
			else if (j > hi)               pixels.set(k, aux.get(i++));
			else if (less(aux.get(j), aux.get(i))) pixels.set(k, aux.get(j++));
			else                           pixels.set(k, aux.get(i++));
		}

	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * @param a the array to be sorted
	 */
	public void mergeSort() {
		int N = pixels.size();
		ArrayList<Pixel> aux = new ArrayList<>(N);
		int refreshRate = 0;
		for (int n = 1; n < N; n = n+n) {
			for (int i = 0; i < N-n; i += n+n) {
				if (refreshRate >= 500){
					refreshRate = 0;
					refresh();
				}
				refreshRate++;
				int lo = i;
				int m  = i+n-1;
				int hi = Math.min(i+n+n-1, N-1);
				merge(aux, lo, m, hi);
			}
		}
	}

	// Shell
	public void shellSort() {
		int N = pixels.size();

		// 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
		int h = 1;
		int refreshRate = 0;
		while (h < N/3) h = 3*h + 1; 

		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(pixels.get(j), pixels.get(j-h)); j -= h) {
					exch(pixels, j, j-h);
					if ( refreshRate >= 5000) {
						refreshRate = 0;
						refresh();
					}
					refreshRate++;
				}
			}
			h /= 3;
		}
	}

	// Heap Sort
	public void heapSort() {
		int N = pixels.size()-1;
		int refreshRate = 0;
		for (int k = N/2; k >= 1; k--)
			sink(k, N);
		while (N > 1) {
			if ( refreshRate >= 100) {
				refreshRate = 0;
				refresh();
			}
			refreshRate++;
			exch(pixels, 1, N--);
			sink(1, N);
		}
	}

	/***********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private void sink( int k, int N) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(pixels.get(j), pixels.get(j+1))) j++;
			if (!less(pixels.get(k), pixels.get(j))) break;
			exch(pixels, k, j);
			k = j;
		}
	}

}
