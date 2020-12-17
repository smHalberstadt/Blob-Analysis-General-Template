/* @Author: smHalberstadt
 */
package v1_Blob_Detection_G;

import java.util.ArrayList;

public class Blob_Finder {

	// Variables
	private ArrayList<Blob> blobsInPicture;
	private boolean[][] countedBlobs; // starts on top left
	private short[][] imageValues;
	private int imageHeight, imageWidth;
	Image_Processor imPro = new Image_Processor();

	// set methods
	public Blob_Finder(int fold, int imag) {
		this.imageValues = imPro.usableArr(fold, imag);
		this.imageHeight = imPro.getHeight();
		this.imageWidth = imPro.getWidth();
		this.blobsInPicture = new ArrayList<Blob>();
		this.countedBlobs = setCountedBlobs(imageWidth, imageHeight);
	};

	// return methods
	public void findBlobsInPicture(int fold, int imag) { // algorithm part
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				if (imageValues[x][y] == 1 && !countedBlobs[x][y]) { // if we find a uncounted markable pixel
					Blob bl = new Blob();
					dtfb(bl, x, y); // counts all pixels in blob
					this.blobsInPicture.add(bl);
				}
			}
		}
	}

	private void dtfb(Blob currBlob, int i, int j) { // down to find blobs
		if(! countedBlobs[i][j]) {
			countedBlobs[i][j] = true;
			currBlob.add(i, j);
		}
		if(i+1 >= imageWidth || i-1 < 0 || j+1 >= imageWidth) // bounds check
			return;
		 
		if(imageValues[i+1][j] == 1) 
			dtfb(currBlob, i+1, j);
		if(imageValues[i-1][j] == 1)
			dtfb(currBlob, i-1, j);
		if(imageValues[i][j+1] == 1) 
			dtfb(currBlob, i, j+1);
	}
	
	private boolean[][] setCountedBlobs(int x, int y) {
		boolean[][] r = new boolean[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; y++)
				r[i][j] = false;
		return r;
	}
	
	public int numBeads(int threshold) {
		int sum = 0;
		for(Blob b: blobsInPicture)
			if(b.getMass() >= threshold)
				sum++;
		return sum;
	}
	
	public Blob[] getBeads(int threshold) {
	Blob[] retArr = new Blob[numBeads(threshold)];
	int dif = 0;
	for(int n = 0; n < retArr.length; n++) {
		if(this.blobsInPicture.get(n+dif).getMass() >= threshold) {
			retArr[n] = this.blobsInPicture.get(n+dif);
		} else {
			dif++;
		}
	}
	return retArr;
	}
}
