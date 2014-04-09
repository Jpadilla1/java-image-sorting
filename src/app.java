import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class app {

	static PPM frame;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

		File image = new File("lancia_stratos.ppm");
		Scanner scan = new Scanner(image);

		ArrayList<Pixel> list = new ArrayList<>();

		String type = scan.nextLine();
		int width = scan.nextInt();
		int height = scan.nextInt();
		int nColors = scan.nextInt();

		int i = 0;
		while (scan.hasNext()) {
			list.add( new Pixel( i++, scan.nextInt(), scan.nextInt(), scan.nextInt() ));
		}

		scan.close();

		frame = new PPM(list, width, height, nColors, type);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//		Selection Sort
		
		System.out.println("Selection sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.selectionSort();

		frame.refresh();


		//		Insertion Sort

		System.out.println("Insertion sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.insertionSort();

		frame.refresh();

		//		Quick Sort

		System.out.println("Quick sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.quickSort();

		frame.refresh();

		// 		Merge Sort

		System.out.println("Merge sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.mergeSort();

		frame.refresh();

		// 		Shell Sort

		System.out.println("Shell sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.shellSort();

		frame.refresh();

		// 		Heap Sort

		System.out.println("Heap sort");
		
		frame.shuffle();

		Thread.sleep(2000);

		frame.refresh();

		frame.heapSort();

		frame.refresh();

		Thread.sleep(2000);
		
		System.exit(0);


	}


}
