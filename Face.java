import java.awt.Image;
import java.awt.Image.*;

public class Face{

	public Face(BufferedImage pic){
		box = faceBox(); //openCV shit

	}

	public BufferedImage getPicture(BufferedImage pic){
		return pic;
	}
	public T removeHair();

	public T returnHair();
	
	public T removeFace();

	public BufferedImage returnFace(int index){
	
	}
	public void faceScale(BufferedImage face, double scaleFactor){
		double scaledWidth =  box.getWidth() / scaleFactor;
		double scaledHeight = box.getHeight / scaleFactor;
		face = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
	}

	public T faceBox(){

			//openCV gypsy shit

	}

}
