import java.awt.Image;
import java.awt.Image.*;

public class Face{

	private ArrayList<BufferedImage> hairArray = new ArrayList<>();
	private ArrayList<BufferedImage> faces = new ArrayList<>();

	public Face(BufferedImage pic){

		faceBox(pic); //run openCV shit to get a bounding box

	}


		
	public T removeHair();

	public T returnHair();

	public T removeFace();

	public T returnFace();

	public BufferedImage faceScale(BufferedImage face1, BufferedImage face2){
		double width1, width2, height1, height2;

		width1 = face1.getWidth();
		width2 = face2.getWidth();
		height1 = face1.getHeight();
		height2 = face2.getHeight();

		if(width1 != width2){
			double scaleFactor = width2/width1;
			double scaledWidth = width2/scaleFactor;
			double scaledHeight = height2*scaleFactor;
			BufferedImage newFace2 = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);	
		}
		return newFace2;
	};

	public T faceBox(BufferedImage facePic){

			//openCV gypsy shit
		
	}

	public void saveHair(BufferedImage hairPic){
		hairArray.add(hairPic);
	};

	public BufferedImage loadHair(){
		int hairIndex;
		Random r = new Random();
		hairIndex = r.nextInt(hairArray.size());

		return hairArray.get(hairIndex);
	}
}
