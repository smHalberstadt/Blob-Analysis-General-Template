package v1_Blob_Detection_G;

import java.util.ArrayList;

public class Blob_Tracker {

	// Variables
	private int folder, first, last, thresh = 25;
	
	
	// set methods
	public Blob_Tracker(int fol, int firstImage, int lastImage) {
		this.folder = fol;
		this.first = firstImage;
		this.last = lastImage;
	}
	
	// return methods
	public ArrayList<Double> findMovement() { // intent is to track through each frame until you get to the end of the frames 
		ArrayList<Double> changeInDistance = new ArrayList<Double>();
		for(int pic = first; pic < last-1; pic++) {
			Blob_Finder a = new Blob_Finder(folder, pic);
			Blob_Finder b = new Blob_Finder(folder, pic+1);
			
			Blob[] aBlobs = a.getBeads(thresh);
			Blob[] bBlobs = b.getBeads(thresh);
			for(int blobPos = 0; blobPos < aBlobs.length; blobPos++) {
				changeInDistance.add(blobPos, ( changeInDistance.get(blobPos) + aBlobs[blobPos].distanceTo(bBlobs[blobPos]) ) );
			}
		}
		return changeInDistance;
	}
}
