/*Author: smHalberstadt
 *
 */
package v1_Blob_Detection_G;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image_Processor {

	// Variables
	private String[][] file_locations;
	private int imageHeight = 480, imageWidth = 640, tau = 180;
	File input_file = null;
	// set methods
	public Image_Processor() {
		file_locations = new String[10][200]; // C:\Users\*\eclipse-workspace\Blob_Detection\images (for those who need the formula of the path)
		String file_string = "C:\\Users\\*\\eclipse-workspace\\Blob_Detection\\images";
		for (int file_number = 0; file_number < 10; file_number++) { // sorts by file
			file_string = file_string + "\\run_" + (file_number + 1);
			for (int file = 0; file < 200; file++) { // gets each image location
				file_string = file_string + "\\frame" + String.format("%05d", file) + ".jpg";
				file_locations[file_number][file] = file_string;
				file_string = "C:\\Users\\*\\eclipse-workspace\\Blob_Detection\\images\\run_"
						+ (file_number + 1);
			}
			file_string = "C:\\Users\\*\\eclipse-workspace\\Blob_Detection\\images";
		} // array created with all correct locations
	};
	// return methods
	public BufferedImage getImage(String imageLoc) {
		BufferedImage image = null;
		try {
			input_file = new File(imageLoc); // image file path
			image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(input_file); // Reading input file

			//System.out.println("Reading complete.");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return image;
	}
	
	private BufferedImage alterImage(BufferedImage picture) { // this is here in case there is a need to get the raw altered image.
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				// Here (x,y)denotes the coordinate of image
				// for modifying the pixel value.
				int toGetRGB = picture.getRGB(x, y);
				Color color = new Color(toGetRGB, true);
				// getting RGB Value
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				// calculate average
				int avg = (r + g + b) / 3;
				if (avg >= tau) {// set pixels to adjusted color
					picture.setRGB(x, y, 255);
				} else {
					picture.setRGB(x, y, 0);
				}
			}
		} // this formula is used for a grey scale image.
		return picture;
	} // alterImage end
	
	private short[][] blobPicture(BufferedImage picture){// for this specific project to get accurate blobs for algorithm in blob finder
		
		short[][] returnArr = new short[this.imageWidth][this.imageHeight];
		for(int x = 0; x < imageWidth; x++) {
			for(int y = 0; y < imageHeight; y++) {
				int toGetRGB = picture.getRGB(x, y);
				Color color = new Color(toGetRGB, true);
				if(color.getRed() == 225) {
					returnArr[x][y] = 1;
				} else {
					returnArr[x][y] = 0;
				}
			}
		}
		return returnArr;
	}
	public BufferedImage usableImage(int fol, int pic){
		return alterImage(getImage(file_locations[fol][pic]));
	}
	
	public short[][] usableArr(int fol, int pic){ // specific for this project
		return blobPicture(alterImage(getImage(file_locations[fol][pic])));
	}
	
	public int getWidth() {
		return this.imageWidth;
	}
	
	public int getHeight() {
		return this.imageHeight;
	}
}
