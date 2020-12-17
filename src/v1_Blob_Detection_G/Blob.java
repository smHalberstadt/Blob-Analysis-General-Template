package v1_Blob_Detection_G;

import java.util.ArrayList;

public class Blob { // holding data for each blob and all calculations required

	// Variables
	private double xCom, xSum, yCom, ySum;
	private short mass;

	public Blob() { // just initializing everything that needs to be initialized
		this.xSum = 0;
		this.ySum = 0;
		this.mass = 0;
	};

	// Methods for each blob
	// set methods
	public void add(int x, int y) {
		// first modify the center of mass variables
		xSum += x; // the x values for determining xCom
		ySum += y; // the y values for determining yCom
		mass++; // # pixels in each blob
		xCom = xSum / mass;
		yCom = ySum / mass;
	};

	// return methods
	public short getMass() {
		return this.mass;
	}

	public double getXcom() {
		return this.xCom;
	}

	public double getYcom() {
		return this.yCom;
	}

	public double distanceTo(Blob other) { // the r distance
		return Math.sqrt((Math.pow((this.xCom + other.getXcom()), 2) + Math.pow((this.yCom + other.getYcom()), 2)));
	}
	
	public String toString() { // mass, (x,y)
		return String.format("%2d (%8.4f, %8.4f)", this.mass, this.xCom, this.yCom);
	}

}
