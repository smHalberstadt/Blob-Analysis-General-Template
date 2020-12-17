package v1_Blob_Detection_G;

import java.util.ArrayList;
import java.util.Scanner;

public class blob_main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int folderNum, first, last;
		// No Illegal Inputs
		do {
			System.out.println("Input folder: (1-10)");
			folderNum = input.nextInt();
			if(folderNum > 10 || folderNum < 1)
				System.out.println("Input not in range");
		} while (folderNum > 10 || folderNum < 1);

		do {
			System.out.println("Input initial file: (0-198)");
			first = input.nextInt();
			if(first > 198 || first < 0)
				System.out.println("Input not in range");
		} while (first > 198 || first < 0);

		do {
			System.out.println("Input final file: (1-199)");
			last = input.nextInt();
			if(last > 199 || last < 1)
				System.out.println("Input not in range");
			if(first >= last)
				System.out.println("Input needs to be bigger than the initial");
		} while (last > 199 || last < 1 || first >= last);

		Blob_Tracker bT = new Blob_Tracker(folderNum-1, first, last);
		bT.findMovement();
		
	}
	
	public void printArrayList(ArrayList<Double> data) {
		for(int i = 0; i < data.size(); i++)
			System.out.println(data.get(i));
	}

}
