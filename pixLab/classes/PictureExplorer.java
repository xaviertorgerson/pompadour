package classes;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;


/**
 * Displays a picture and lets you explore the picture by displaying the row, column, red,
 * green, and blue values of the pixel at the cursor when you click a mouse button or
 * press and hold a mouse button while moving the cursor.  It also lets you zoom in or
 * out.  You can also type in a row and column value to see the color at that location.
 * 
 * Originally created for the Jython Environment for Students (JES). 
 * Modified to work with DrJava by Barbara Ericson
 * Also modified to show row and columns by Barbara Ericson
 * 
 * @author Keith McDermottt, gte047w@cc.gatech.edu
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class PictureExplorer implements MouseMotionListener, MouseListener
{
  
  // main GUI
  /** window to hold GUI */
  private JFrame pictureFrame;
  /** window that allows the user to scroll to see a large picture */
  private JScrollPane scrollPane;
  
  private Point firstEye;
  private Point secondEye;
  private Rectangle hairSample;
  
  private int clickCount = 0;
  private boolean dragging = false;
  
  // GUI components
  /** next button */
  private JButton nextButton;
  /** clear button */
  private JButton clearButton;
  
  
  /** The picture being explored */
  private DigitalPicture picture;
  
  /** The image icon used to display the picture */
  private ImageIcon scrollImageIcon;
  
  /** The image display */
  private ImageDisplay imageDisplay;
  
  /** the zoom factor (amount to zoom) */
  private double zoomFactor;
  
  /** the number system to use, 0 means starting at 0, 1 means starting at 1 */
  private int numberBase=0;
  
  /**
   * Public constructor 
   * @param picture the picture to explore
   */
  public PictureExplorer(DigitalPicture picture)
  {
    // set the fields
    this.picture=picture;
    zoomFactor=1;
    
    // create the window and set things up
    createWindow();
  }
  
  /**
   * Changes the number system to start at one
   */
  public void changeToBaseOne()
  {
    numberBase=1;
  }
  
  /**
   * Set the title of the frame
   *@param title the title to use in the JFrame
   */
  public void setTitle(String title)
  {
    pictureFrame.setTitle(title);
  }
  
  /**
   * Method to create and initialize the picture frame
   */
  private void createAndInitPictureFrame()
  {
    pictureFrame = new JFrame(); // create the JFrame
    pictureFrame.setResizable(true);  // allow the user to resize it
    pictureFrame.getContentPane().setLayout(new BorderLayout()); // use border layout
    pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when close stop
    pictureFrame.setTitle(picture.getTitle());

    
  }
  
  /**
   * Create and initialize the scrolling image
   */
  private void createAndInitScrollingImage()
  {
    scrollPane = new JScrollPane();
    
    BufferedImage bimg = picture.getBufferedImage();
    imageDisplay = new ImageDisplay(bimg);
    imageDisplay.addMouseMotionListener(this);
    imageDisplay.addMouseListener(this);
    imageDisplay.setToolTipText("Click a mouse button on a pixel to see the pixel information");
    scrollPane.setViewportView(imageDisplay);
    pictureFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
  }
  
  /**
   * Creates the JFrame and sets everything up
   */
  private void createWindow()
  {
    // create the picture frame and initialize it
    createAndInitPictureFrame();
    
    
    //create the information panel
    createInfoPanel();
    
    //creates the scrollpane for the picture
    createAndInitScrollingImage();
    
    // show the picture in the frame at the size it needs to be
    pictureFrame.pack();
    pictureFrame.setVisible(true);
  }
  
  /**
   * Method to set up the next and previous buttons for the
   * pixel location information
   */
  private void setUpNextAndPreviousButtons()
  {
    // create the image icons for the buttons
    Icon prevIcon = new ImageIcon(DigitalPicture.class.getResource("leftArrow.gif"), 
                                  "previous index");
    Icon nextIcon = new ImageIcon(DigitalPicture.class.getResource("rightArrow.gif"), 
                                  "next index");
    // create the arrow buttons
    clearButton = new JButton(prevIcon);
    nextButton = new JButton(nextIcon);
    
    // set the tool tip text
    clearButton.setToolTipText("Click to go to try hairstyles");
    nextButton.setToolTipText("Click to clear facial features");


    // set the sizes of the buttons
    int clearWidth = prevIcon.getIconWidth() + 2;
    int nextWidth = nextIcon.getIconWidth() + 2;
    int clearHeight = prevIcon.getIconHeight() + 2;
    int nextHeight = nextIcon.getIconHeight() + 2;
    
    Dimension clearDimension = new Dimension(clearWidth,clearHeight);
    Dimension nextDimension = new Dimension(nextWidth, nextHeight);
    
    clearButton.setPreferredSize(clearDimension);
    nextButton.setPreferredSize(nextDimension);

    clearButton.setEnabled(false);
    nextButton.setEnabled(false);
    
    // handle previous column button press
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          clickCount--;
          nextButton.setEnabled(false);
          if (clickCount == 0) {
             clearButton.setEnabled(false);

          }
          
                if(clickCount == 0) {
          System.out.println("Remove First Eye");
      }
      else if(clickCount == 1) {
          System.out.println("Remove Second Eye");
      }
      else if(clickCount == 2) {
          System.out.println("Remove Hair Sample");
      }
      
      
          //Remove element on screen corrosponding to click count
     
      }
    });
    
    // handle previous row button press
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          /*
        rowIndex--;
        if (rowIndex < 0)
          rowIndex = 0;
        displayPixelInformation(colIndex,rowIndex);
        */
      }
    });
    
  }
  
  /**
   * Create the pixel location panel
   * @param labelFont the font for the labels
   * @return the location panel
   */
  public JPanel createLocationPanel(Font labelFont) {
    
    // create a location panel
    JPanel locationPanel = new JPanel();
    locationPanel.setLayout(new FlowLayout());
    Box hBox = Box.createHorizontalBox();
    
    // set up the next and previous buttons
    setUpNextAndPreviousButtons();

    
    // add the items to the vertical box and the box to the panel
    hBox.add(Box.createHorizontalGlue());

    hBox.add(clearButton);
    hBox.add(Box.createHorizontalStrut(10));
    hBox.add(nextButton);

    locationPanel.add(hBox);
    hBox.add(Box.createHorizontalGlue());
    
    return locationPanel;
  }

  
  /**
   * Creates the North JPanel with all the pixel location
   * and color information
   */
  private void createInfoPanel()
  {
    // create the info panel and set the layout
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BorderLayout());
    
    // create the font
    Font largerFont = new Font(infoPanel.getFont().getName(),
                               infoPanel.getFont().getStyle(),14);
    
    // create the pixel location panel
    JPanel locationPanel = createLocationPanel(largerFont);

    // add the panels to the info panel
    infoPanel.add(BorderLayout.NORTH,locationPanel);
    
    // add the info panel
    pictureFrame.getContentPane().add(BorderLayout.NORTH,infoPanel);
  } 
  
  /**
   * Method to check that the current position is in the viewing area and if
   * not scroll to center the current position if possible
   */
  public void checkScroll()
  {
    // get the x and y position in pixels
    int xPos = 0; 
    int yPos = 0;
    
    // only do this if the image is larger than normal
    if (zoomFactor > 1) {
      
      // get the rectangle that defines the current view
      JViewport viewport = scrollPane.getViewport();
      Rectangle rect = viewport.getViewRect();
      int rectMinX = (int) rect.getX();
      int rectWidth = (int) rect.getWidth();
      int rectMaxX = rectMinX + rectWidth - 1;
      int rectMinY = (int) rect.getY();
      int rectHeight = (int) rect.getHeight();
      int rectMaxY = rectMinY + rectHeight - 1;
      
      // get the maximum possible x and y index
      int macolIndexX = (int) (picture.getWidth() * zoomFactor) - rectWidth - 1;
      int macolIndexY = (int) (picture.getHeight() * zoomFactor) - rectHeight - 1;
      
      // calculate how to position the current position in the middle of the viewing
      // area
      int viewX = xPos - (int) (rectWidth / 2);
      int viewY = yPos - (int) (rectHeight / 2);
      
      // reposition the viewX and viewY if outside allowed values
      if (viewX < 0)
        viewX = 0;
      else if (viewX > macolIndexX)
        viewX = macolIndexX;
      if (viewY < 0)
        viewY = 0;
      else if (viewY > macolIndexY)
        viewY = macolIndexY;
      
      // move the viewport upper left point
      viewport.scrollRectToVisible(new Rectangle(viewX,viewY,rectWidth,rectHeight));
    }
  }
  
  /**
   * Zooms in the on picture by scaling the image.  
   * It is extremely memory intensive.
   * @param factor the amount to zoom by
   */
  public void zoom(double factor)
  {
    // save the current zoom factor
    zoomFactor = factor;
    
    // calculate the new width and height and get an image that size
    int width = (int) (picture.getWidth()*zoomFactor);
    int height = (int) (picture.getHeight()*zoomFactor);
    BufferedImage bimg = picture.getBufferedImage();
    
    // set the scroll image icon to the new image
    imageDisplay.setImage(bimg.getScaledInstance(width, height, Image.SCALE_DEFAULT));
    imageDisplay.revalidate();
    checkScroll();  // check if need to reposition scroll
  }
  
  /**
   * Repaints the image on the scrollpane.  
   */
  public void repaint()
  {
    pictureFrame.repaint();
  }
  
  //****************************************//
  //               Event Listeners          //
  //****************************************//
  
  /**
   * Called when the mouse is dragged (button held down and moved)
   * @param e the mouse event
   */
  public void mouseDragged(MouseEvent e)
  {
    dragging = true;
    System.out.println("Mouse");
  }
  
  /**
   * Method to check if the given x and y are in the picture
   * @param column the horizontal value
   * @param row the vertical value
   * @return true if the row and column are in the picture 
   * and false otherwise
   */
  private boolean isLocationInPicture(int column, int row)
  {
    boolean result = false; // the default is false
    if (column >= 0 && column < picture.getWidth() &&
        row >= 0 && row < picture.getHeight())
      result = true;
    
    return result;
  }
  
  
  /**
   * Method called when the mouse is moved with no buttons down
   * @param e the mouse event
   */
  public void mouseMoved(MouseEvent e)
  {}
  
  /**
   * Method called when the mouse is clicked
   * @param e the mouse event
   */
  public void mouseClicked(MouseEvent e)
  {

  }
  
  /**
   * Method called when the mouse button is pushed down
   * @param e the mouse event
   */ 
  public void mousePressed(MouseEvent e)
  {
      if(clickCount == 2) {
          hairSample = new Rectangle(e.getX(), e.getY(), 0, 0);
          System.out.println("Hair Sample Started");
        }

  }
  
  /**
   * Method called when the mouse button is released
   * @param e the mouse event
   */
  public void mouseReleased(MouseEvent e)
  {
      clickCount++;
      if(clickCount >= 3) {
          clickCount = 3;
      }
      else if(clickCount <= 1) {
          clickCount = 1;
      }
        
      if(clickCount == 1) {
          firstEye = new Point(e.getX(), e.getY()); 
          System.out.println("First Eye " + firstEye);
      }
      else if(clickCount == 2) {
          secondEye = new Point(e.getX(), e.getY()); 
          System.out.println("Second Eye " + secondEye);
      }
      else if(clickCount == 3 && dragging) {
          hairSample.setSize((int)Math.abs(e.getX()-hairSample.getX()),(int)Math.abs(e.getY()-hairSample.getY()));
          System.out.println("Hair Sample " + hairSample);
      }
      else {
          System.out.println("Other");
          clickCount--;
      }
      
      
                   clearButton.setEnabled(false);
      if(clickCount == 3) {
             nextButton.setEnabled(true);
        }
        else {
             nextButton.setEnabled(false);
      }
      
      if(clickCount > 0) {
             clearButton.setEnabled(true);
      }
               
          
      dragging = false;
  }
  
  /**
   * Method called when the component is entered (mouse moves over it)
   * @param e the mouse event
   */
  public void mouseEntered(MouseEvent e)
  {
      dragging = false;
  }
  
  /**
   * Method called when the mouse moves over the component
   * @param e the mouse event
   */
  public void mouseExited(MouseEvent e)
  {
      dragging = false;
  }
 
  
  
  /**
   * Test Main.  It will explore the beach 
   */
  public static void main( String args[])
  {
    Picture pix = new Picture("face2.jpg");
    pix.explore();
  }
  
}
