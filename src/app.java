import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class app {

	static PPMFrame frame;

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

		frame = new PPMFrame(list, width, height, nColors, type);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Scanner kb = new Scanner(System.in);
		int selec = 0;
		do {

			menu();
			selec = Integer.parseInt(kb.next());

			frame.shuffle();
			frame.refresh();
			Thread.sleep(2000);

			sort(selec);
			frame.refresh();
			
		} while(selec != -1);

		kb.close();

	}

	public static void sort(int selec) {
		switch(selec){
		case 1:
			System.out.println("Selection Sort");
			frame.selectionSort();
			break;
		case 2:
			System.out.println("Insertion Sort");
			frame.insertionSort();
			break;
		case 3:
			System.out.println("Merge Sort");
			frame.mergeSort();
			break;
		case 4:
			System.out.println("Quick Sort");
			frame.quickSort();
			break;
		case 5:
			System.out.println("Shell Sort");
			frame.shellSort();
			break;
		case 6:
			System.out.println("Heap Sort");
			frame.heapSort();
			break;
		default:
			System.err.println("not a valid choice! Select between 1 and 6.");
		}
		System.out.println();
	}

	public static void menu(){
		String message = "1. Selection\n"
				+ "2. Insertion\n"
				+ "3. Merge\n"
				+ "4. Quick\n"
				+ "5. Shell\n"
				+ "6. Heap\n:";
		System.out.print(message);
	}

}
