package classes;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  public void cropHair(){
      Pixel [][] pixels = this.getPixels2D();
      int height = pixels.length;
      int width = pixels[0].length;
    
      //Skin: R:  226   G: 174   B: 152  (225,590)
      //Hair  R:  108   G: 88    B: 79   (212,585)
      
      //backgroung (green) rgb (94,104,69)
      //lips (red) rgb (210 94 97)
      for (int row = pixels.length - 1; row > 1; row--)
    {
      for (int col = 0; col < width; col++)
      {
          if(pixels[row][col].getRed() + 10 >= 114 && pixels[row][col].getGreen() + 10 >= 87  && pixels[row][col].getRed() + 10 >= 76){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() + 5 >= 94 && pixels[row][col].getGreen() + 5 >= 104  && pixels[row][col].getRed() + 5 >= 69){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() + 15 >= 210 && pixels[row][col].getGreen() + 15 >= 94  && pixels[row][col].getRed() + 15 >= 97){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() <= 240 && pixels[row][col].getRed() <= 240 && pixels[row][col].getRed() <= 240){
              if((row - 1 > 0) && (pixels[row][col].getRed() + 10 <= pixels[row - 1][col].getRed() || 
                  pixels[row][col].getGreen() + 10 <= pixels[row - 1][col].getGreen()
                  || pixels[row][col].getBlue() + 10 <= pixels[row - 1][col].getBlue())){
                 
                      //pixels[row][col].setColor(Color.GREEN);
                
            }
              
            }
      }
    } 
    
    double avg = pixels[0][0].getAverage();
    double tempA = pixels[0][0].getAverage();
    int counter = 0;
    int maxCounter = -1;
    double sum = 0;
    double totalA = 0;
    double avgR = 0;
    double avgG = 0;
    double avgB = 0;
    double tR = 0;
    double tG = 0;
    double tB = 0;
    double aR = 0;
    double aG = 0;
    double aB = 0;
      for (int row = 75; row < 93; row++){
         for (int col = 154; col < 193 ; col++){
                   avg = pixels[row][col].getAverage();
                   tR = tR + pixels[row][col].getRed();
                   tG = tG + pixels[row][col].getGreen();
                   tB = tB + pixels[row][col].getBlue();
                   
                   sum = sum + avg;
                   counter++;              
      
    } 
  }
    aR = tR/(counter);
    aG = tG/(counter);
    aB = tB/(counter); 
    totalA = sum /(counter);
    
    System.out.println("avg is: " + totalA);
     for (int row = pixels.length - 1; row > (pixels.length/2); row--){
          for (int col = 0; col < width; col++){
              pixels[row][col].setColor(Color.WHITE);
            }
        }
     for (int row = pixels.length/2; row >= 0; row--){
          for (int col = 0; col < width; col++){
             
                  if((pixels[row][col].getRed() - tR <= 50) && (pixels[row][col].getGreen() - tG <= 50) 
                  && (pixels[row][col].getBlue() - tB <= 50)){
                       // pixels[row][col].setColor(Color.BLACK);
                    }else{
                       pixels[row][col].setColor(Color.WHITE);
                    }
                }
            }
           // int color = this.getRGB(0,0);
           //Color c = new Color(140,40,75);
          
}
    public void transparent(){
          //Image im = makeColorTransparent(this, c);
          int avg = 4;
          int x = 5;
            Pixel[][] pixels = this.getPixels2D();
            for(Pixel[] rowArray : pixels){
                for(Pixel pixelObj : rowArray){
                    avg =(pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/ 3;
                    pixelObj.setRed(avg);
                    pixelObj.setGreen(avg);
                    pixelObj.setBlue(avg);
                }
            }
    }
    public void mask(){
         Pixel [][] pixels = this.getPixels2D();
      int height = pixels.length;
      int width = pixels[0].length;
    
      //Skin: R:  226   G: 174   B: 152  (225,590)
      //Hair  R:  108   G: 88    B: 79   (212,585)
      
      //backgroung (green) rgb (94,104,69)
      //lips (red) rgb (210 94 97)
      for (int row = pixels.length - 1; row > 1; row--)
    {
      for (int col = 0; col < width; col++)
      {
          if(pixels[row][col].getRed() + 10 >= 114 && pixels[row][col].getGreen() + 10 >= 87  && pixels[row][col].getRed() + 10 >= 76){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() + 5 >= 94 && pixels[row][col].getGreen() + 5 >= 104  && pixels[row][col].getRed() + 5 >= 69){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() + 15 >= 210 && pixels[row][col].getGreen() + 15 >= 94  && pixels[row][col].getRed() + 15 >= 97){
            pixels[row][col].setColor(Color.WHITE);
            }
          if(pixels[row][col].getRed() <= 240 && pixels[row][col].getRed() <= 240 && pixels[row][col].getRed() <= 240){
              if((row - 1 > 0) && (pixels[row][col].getRed() + 10 <= pixels[row - 1][col].getRed() || 
                  pixels[row][col].getGreen() + 10 <= pixels[row - 1][col].getGreen()
                  || pixels[row][col].getBlue() + 10 <= pixels[row - 1][col].getBlue())){
                 pixels[row][col].setColor(Color.BLACK);
                
            }
            
          }
      }
    } 
    
    double avg = pixels[0][0].getAverage();
    double tempA = pixels[0][0].getAverage();
    int counter = 0;
    int maxCounter = -1;
    double sum = 0;
    double totalA = 0;
      for (int row = 75; row < 93; row++){
          for (int col = 154; col < 193 ; col++){
                   avg = pixels[row][col].getAverage();
                   sum = sum + avg;
                   counter++;              
      
    } 
  }
  
    totalA = sum /(counter);
    
    System.out.println("avg is: " + totalA);
     for (int row = pixels.length - 1; row > (pixels.length/2); row--){
          for (int col = 0; col < width; col++){
              pixels[row][col].setColor(Color.WHITE);
            }
        }
     for (int row = pixels.length/2; row >= 0; row--){
          for (int col = 0; col < width; col++){
             
                  if((pixels[row][col].getAverage() - totalA <= 60)){
                       pixels[row][col].setColor(Color.BLACK);
                    }else{
                       pixels[row][col].setColor(Color.WHITE);
                    }
                }
           }
          
          for (int col = width - 1; col > 1; col--)
           {
              for (int row = 0; row < height; row++)
                  {
                     if(col <= 0.18 * width || (col >= width - (0.18 * width))){
                                pixels[row][col].setColor(Color.WHITE);
                            }
                        }
                    }
                    
}
   

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
   
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  public void edgeDetection2(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color botColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        
        topPixel = pixels[row][col];
        bottomPixel = pixels[row + 1][col];
        botColor = bottomPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist && topPixel.colorDistance(botColor) > 
            edgeDist){
          leftPixel.setColor(Color.BLACK);
          topPixel.setColor(Color.BLACK);
        }
        else{
          leftPixel.setColor(Color.WHITE);
          topPixel.setColor(Color.WHITE);
        }
      }
      
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
   
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
