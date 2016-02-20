import java.awt.Image;
import java.awt.Image.*;

public class Pompadour{

	BufferedImage img = null;
	ArrayList<Face> faceArray = new ArrayList<>();


	public static void main(String [] args){

		try {
    		img = ImageIO.read(new File("face.jpg"));
		} catch (IOException e) {

		Face newFace = new Face(img);
		faceArray.add(newFace);
		newFace.removeHair();
		
	}
}