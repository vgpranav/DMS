package uk.co.mmscomputing.application.imageviewer;

import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import uk.co.mmscomputing.util.*;

public class MainApp extends UtilMainApp{

  public MainApp(){                     // need this in every applet application class
    super();
    ImageIO.scanForPlugins();
  }

  public MainApp(String title, String[] argv){
    super(title,argv);
    ImageIO.scanForPlugins();
  }

  @Override
protected JPanel getCenterPanel(Properties properties)throws Exception{
    return new ScannerTab(properties);
  }

//  protected void setFrameSize(JFrame frame, Rectangle bounds){
//    frame.setSize(bounds.width*95/100,bounds.height*95/100);
//  }

  public static void main(String[] argv){
    try{
    	MainApp app=new MainApp("Multi Page Image Viewer [2005-09-22]", argv);
    	System.gc();//////////////////////////this statement is added by shree on 15/08/2013 to test garbage collection\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}

