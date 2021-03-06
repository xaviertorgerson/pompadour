package classes;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
  
    beach.explore();
  }
    public static void testTr()
  {
    Picture beach = new Picture("face2.jpg");
    beach.explore();
    beach.transparent();
    beach.explore();
  }
  public static void testCropHair(){
      Picture face = new Picture("manFace.jpg");
      Picture face2 = new Picture("face2.jpg");
      Picture face3 = new Picture("face3.jpg");
     // face.explore();
      face2.explore();
      //face.cropHair();
      face2.cropHair();
      //face3.cropHair();
     
      //face.explore();
      face2.explore();
      //face3.explore();
      
    }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
  
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
   
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  public static void testMask(){
       Picture face = new Picture("manFace.jpg");
      Picture face2 = new Picture("face2.jpg");
      Picture face3 = new Picture("face3.jpg");
     // face.explore();
      face2.explore();
      face.mask();
      face2.mask();
      //face3.cropHair();
     
      face.explore();
      face2.explore();
      //face3.explore();
      
    }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("manFace.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
   public static void testEdgeDetection2()
  {
    Picture swan = new Picture("manFace.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    testZeroBlue();
    testCropHair();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    testMask();
    testTr();
    testEdgeDetection();
    testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}