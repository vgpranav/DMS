package uk.co.mmscomputing.util;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import uk.co.mmscomputing.util.log.LogBook;

abstract public class UtilMainApp extends JApplet{

  private Properties properties=new Properties();
  private File       propertiesFile;
  private JFrame     frame = null;

  public UtilMainApp(){
    super();
    frame = null;
  }

  public UtilMainApp(String title, String[] argv){    
    JFrame.setDefaultLookAndFeelDecorated(true);
    
    frame=new JFrame(title);
//    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.addWindowListener(new WindowAdapter() {
      @Override
	public void windowClosing(WindowEvent ev) {
        stop();System.exit(0);
      }
    });
    init();
    start();
  }

  protected void setFrameSize(JFrame frame, Rectangle bounds){
    frame.setSize(bounds.width*4/5,bounds.height*4/5);
/*
    frame.pack();

    int w = frame.getBounds().width;
    if(bounds.width<w){w=bounds.width*95/100;}
    int h = frame.getBounds().height;
    if(bounds.height<h){h=bounds.height*95/100;}

    frame.setSize(w,h);
*/
  }

  abstract protected JPanel getCenterPanel(Properties properties)throws Exception;

  public void createGUI(){
    try{
      LogBook log=new LogBook(false);

      Runtime rt=Runtime.getRuntime();
      System.out.println("Runtime Total Memory: "+(rt.totalMemory()/(1024*1024))+" MB");
      System.out.println("Runtime Max   Memory: "+(rt.maxMemory()/(1024*1024))+" MB");

      String s=System.getProperty("java.home");
      System.out.println("java directory: "+s);

      String classname=getClass().getName();
      String filename =classname.substring(0,classname.lastIndexOf('.'))+".properties.txt";

      String userdir=System.getProperty("user.dir");
      System.out.println("current directory: "+userdir);

      String userhome=System.getProperty("user.home");
      System.out.println("user directory: "+userhome);

      File   parent   =new File(userhome,"mmsc");
      try{   
        parent.mkdirs();
        propertiesFile=new File(parent.getAbsolutePath(),filename);
      }catch(Exception e){
        System.out.println("9\bCould not create directory:\n\t"+parent.getAbsolutePath()+"\n\t"+e);
        propertiesFile=new File(filename);
      }

      System.out.println("properties file: "+propertiesFile.getAbsolutePath());

      if(propertiesFile.exists()){properties.load(new FileInputStream(propertiesFile));}


      JTabbedPane tp=new JTabbedPane();

      String mainapptitle = properties.getProperty(getClass().getName()+".mainapp.title");
      if(mainapptitle==null){mainapptitle="MainApp";}
      JPanel centerPanel=getCenterPanel(properties);
      tp.addTab(mainapptitle,centerPanel);

      String logtitle = properties.getProperty(getClass().getName()+".log.title");
      if(logtitle==null){logtitle="Log";}
      tp.addTab("Log",log);

      Container cp=this.getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(tp,BorderLayout.CENTER);

      if(frame!=null){
        frame.getContentPane().add(this);

        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        setFrameSize(frame,ge.getMaximumWindowBounds());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

      }
    }catch(Exception e){
      System.out.println("9\b"+getClass().getName()+".createGUI:\n\tCould not create GUI\n\t"+e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
public void init(){
    try{
    	//properties.clear();
    	String DepartmentId = "0904000001";
		String UserId = "91009291";
		String dbaseip = "172.18.16.108";
		String dbuid = "dms";
		String dbpwd = "intranet";
		String dbase = "dms";
		String dtype = ".";
		String DocId = ".";
		String section = ".";
		/*String DepartmentId = "";
		String UserId = "";
		String dbaseip = "";
		String dbuid = "";
		String dbpwd = "";
		String dbase = "";*/
    	try{
    		
    		DepartmentId = getParameter("DepartmentId") == null ? DepartmentId : getParameter("DepartmentId");
    		UserId = getParameter("UserId") == null ? DepartmentId : getParameter("UserId");
    		dbaseip = getParameter("dbaseip") == null ? DepartmentId : getParameter("dbaseip");
    		dbuid = getParameter("dbuid") == null ? DepartmentId : getParameter("dbuid");
    		dbpwd = getParameter("dbpwd") == null ? DepartmentId : getParameter("dbpwd");
    		dbase = getParameter("dbase") == null ? dbase : getParameter("dbase");
    		dtype = getParameter("dtype") == null ? dtype : getParameter("dtype");
    		DocId = getParameter("DocId") == null ? DocId : getParameter("DocId");
    		section = getParameter("section") == null ? section : getParameter("section");
    	
    	}
    	catch(Exception e)
    	{
    		
    	}
    	properties.setProperty("DepartmentId", DepartmentId);
		properties.setProperty("UserId", UserId);
		properties.setProperty("dbaseip", dbaseip);
		properties.setProperty("dbuid", dbuid);
		properties.setProperty("dbpwd", dbpwd);
		properties.setProperty("dbase", dbase);
		properties.setProperty("dtype", dtype);
		properties.setProperty("DocId", DocId);
		properties.setProperty("section", section);
//      javax.swing.SwingUtilities.invokeAndWait(
      javax.swing.SwingUtilities.invokeLater(
        new Runnable(){
          public void run(){
            createGUI();        
          }
        }
      );
    }catch(Exception e){
      System.out.println("9\b"+getClass().getName()+".init:\n\tCould not create GUI\n\t"+e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
public void stop(){
    try{
      //properties.store(new FileOutputStream(propertiesFile),propertiesFile.getAbsolutePath());
    }catch(Exception e){
      System.out.println("9\b"+getClass().getName()+".stop:\n\tCould not save properties\n\t"+e.getMessage());
      e.printStackTrace();
    }
    super.stop();
  }
}