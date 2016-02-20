import java.awt.Image;
import java.awt.Image.*;

public class Pompadour{

	private ArrayList<BufferedImage> hairArray = new ArrayList<>();
	private ArrayList<BufferedImage> faces = new ArrayList<>();
	private BufferedImage targetImg = null;
	private BufferedImage sourceImg = null;
	private ArrayList<Face> faceArray = new ArrayList<>();
	private int index;
	private double scaleFact, deltaX, detlaY, theta;
	private Graphics2D 


	public static void main(String [] args){

		try {
    		targetImg = ImageIO.read(new File("face.jpg"));
    		sourceImg = ImageIO.read(new File("hair.jpg"));
		} catch (IOException e) {}

		Face targetFace = new Face(targetImg); //who we're putting hair on
		Face sourceFace = new Face(sourceImg); //who we're getting hair from

		scaleFact = getScalingFactor(targetFace, sourceFace);
		deltaX = getX(targetFace, sourceFace);
		deltaY = getY(targetFace, sourceFace);
		theta = getTheta(targetFace, sourceFace);
		sourceFace.faceMatch(sourceImg, scaleFact);


		
	}
	public double getX(Face tFace, sFace){
		tBox = tFace.faceBox();
		sBox = sFace.faceBox();
		double sX = sBox.getX();
		double tX = tBox.getX();
		double txMid = tX/2;
		double sxMid = sX/2;
		double deltX = tX - sX;
		return deltX;

	}
	public double getY(Face tFace, sFace){
		tBox = tFace.faceBox();
		sBox = sFace.faceBox();
		double sY = sBox.getY();
		double tY = tBox.getY();
		double tyMid = tY/2;
		double syMid = sY/2;
		double deltY = tY - sY;
		return deltY;

	}
	public double getTheta(Face tFace, sFace){
		return theta;
	}
	public double getScalingFactor(Face tFace, Face sFace){
		targBox = tFace.faceBox();
		sourceBox = sFace.faceBox();
		double tWidth = targBox.getWidth();
		double sWitdth = sourceBox.getWidth();
		double scaleFact = tWidth/sWidth;
		return scalingFact;
	}
}