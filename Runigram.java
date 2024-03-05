// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
			
		// // Tests the reading and printing of an image:	
		// Color[][] tinypic = read("tinypic.ppm");
		// print(tinypic);

		// // Creates an image which will be the result of various 
		// // image processing operations:
		// Color[][] imageOut;

		// // Tests the horizontal flipping of an image:
		// imageOut = flippedHorizontally(tinypic);
		// System.out.println();
		// print(imageOut);
		
		// // Tests the Verical flipping of an image:
		// imageOut = flippedVertically(tinypic);
		// System.out.println();
		// print(imageOut);

		// // Tests the greyscaling of an image
		// imageOut = grayScaled(tinypic);
		// System.out.println();
		// print(imageOut);

		// // Tests the scaling of an image:
		// imageOut = scaled(tinypic, 5, 3);
		// System.out.println();
		// print(imageOut);
		//chdcddhcdh


		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) 
	{
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		for (int i=0; i<numRows; i++)
		{
			for (int j=0; j<numCols; j++)
			{
				int redColor = in.readInt(); // For each pixel (i,j), reads 3 values from the file,
				int greenColor = in.readInt();
				int blueColor = in.readInt();
				image[i][j]= new Color(redColor, greenColor, blueColor); // creates from the 3 colors a new Color object, and makes pixel (i,j) refer to that object.
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) 
		{
			 // Prints the pixels of the given image.
			 for (int i=0; i<image.length; i++)
			 {
				 for (int j=0; j<image[0].length; j++)
				 {
					print(image[i][j]);
				 }
					System.out.println();
			 }
		}


	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] imageFliipedHorizontally = new Color[image.length][image[0].length];
		for (int i=0; i<image.length; i++)
		{
			for (int j=0; j<image[0].length; j++)
			{ //calculates its corresponding position in the flipped image by reversing the order of columns
				imageFliipedHorizontally[i][j]=image[i][image[0].length-1-j];
			}
		}
 
		return imageFliipedHorizontally;
	}
	

	public static Color[][] flippedVertically(Color[][] image){
		Color[][] imageFliipedHVertically = new Color[image.length][image[0].length];
		for (int i=0; i<image.length; i++)
		{
			for (int j=0; j<image[0].length; j++)
			{
			imageFliipedHVertically[i][j]=image[image.length-1-i][j];
			}
			}
 
		return imageFliipedHVertically;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel)
	{
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		int luminance = (int)(0.299 * r + 0.587 * g + 0.114 * b);
	
		return new Color(luminance, luminance, luminance);
	}
			
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
	Color[][] greyScaled = new Color[image.length][image[0].length];
	for (int i=0; i<image.length; i++)
		{
			for (int j=0; j<image[0].length; j++)
			{
				greyScaled[i][j]= luminance(image[i][j]);
			}
		}
		return greyScaled;
	}
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] imageScaled = new Color[height][width];
		int originalWidth=image[0].length;
		int orginialHeight = image.length;
		double scalerFactori=(double)orginialHeight/height;
		double scalerFactorj=(double)originalWidth/width;
		for (int i=0; i<imageScaled.length; i++)
			{
				for (int j=0; j<imageScaled[0].length; j++)
				{
					imageScaled[i][j]= image[(int)(i*(scalerFactori))][(int)(j*(scalerFactorj))];
				}
			}
			return imageScaled;
		}		
	

	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		// double blend = new Color( alpha*c1 + (1-alpha)*c2 );	
		int secoundColorR = c2.getRed();
		int secoundColorG = c2.getGreen();
		int secoundColorB = c2.getBlue();
		
		int firstColorR = c1.getRed();
		int firstColorG = c1.getGreen();
		int firstColorB = c1.getBlue();

		int blendedColourR= (int)((double)(alpha*firstColorR) + ((double)((1-alpha)*secoundColorR)));
		int blendedColourG= (int)((double)(alpha*firstColorG) + ((double)((1-alpha)*secoundColorG)));
		int blendedColourB= (int)((double)(alpha*firstColorB) + ((double)((1-alpha)*secoundColorB)));

		return new Color(blendedColourR, blendedColourG, blendedColourB);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] imageBlended = new Color[image1.length][image1[0].length]; //The two images must have the same dimensions.
		for (int i=0; i<imageBlended.length; i++)
		{
			for (int j=0; j<imageBlended[0].length; j++)
			{
				imageBlended[i][j]= blend(image1[i][j], image2[i][j], alpha);
			}
		}

		return imageBlended;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//if source matrix is not the same as target matrix
		//create a new array thats called updated source and run for loop that puts the source in size of the target - but how?
		//once dimentions are the same: we can start morphing

		
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length; //cfjefejjf
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

