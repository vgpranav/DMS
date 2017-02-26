package uk.co.mmscomputing.application.imageviewer;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.print.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;                // as of 1.5.0 java.util has class Scanner
import java.util.Date;

import javax.imageio.*;
import javax.imageio.stream.*;

import java.beans.*;

import javax.imageio.metadata.*;

import uk.co.mmscomputing.util.JarImageIcon;
import uk.co.mmscomputing.application.imageviewer.FtpWrapper;

//import uk.co.mmscomputing.imageio.*;
import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.image.operators.*;

import java.sql.*;
import java.sql.Statement;

public class ImageTab extends JPanel implements PropertyChangeListener{

  static public final String fileOpenID="uk.co.mmscomputing.file.open.dir";
  static public final String fileSaveID="uk.co.mmscomputing.file.save.dir";
  private java.awt.List optionsList;
  String depid="", userid="", dbaseip="", dbuid="", dbpwd="", dbase="",dtype="",docid="",section = "",Year = "",equipname="";
  protected Properties   properties;
  protected JTabbedPane  images;
  protected JFileChooser openfc;
  protected JFileChooser savefc;
  String mssg="";
  Scanner scanner;
  Uploader uploader;
  private Connection con;
  JComboBox cbox;
  JComboBox cbox1;
  JPanel p=new JPanel();
  JPanel q=new JPanel();
  public ImageTab(Properties properties){
	
    this.properties=properties;
    depid = properties.getProperty("DepartmentId").toString();
    section = properties.getProperty("section").toString();
    userid = properties.getProperty("UserId").toString();
    docid = properties.getProperty("DocId")==null?".":properties.getProperty("DocId").toString();
    dbaseip = properties.getProperty("dbaseip").toString();
    dbuid = properties.getProperty("dbuid").toString();
    dbpwd = properties.getProperty("dbpwd").toString();
    dbase = properties.getProperty("dbase").toString();
    dtype= properties.getProperty("dtype").toString();
    
    equipname = docid;
   
    System.out.println("deptid-"+depid);
    System.out.println("section-"+section);
    System.out.println("Equipment id-"+equipname);
    setLayout(new BorderLayout());

    
    p.setLayout(new BorderLayout());
    
    q.setLayout(new BoxLayout(q,BoxLayout.PAGE_AXIS));
    setButtonPanel(q);
    p.add(q,BorderLayout.NORTH);
    add(p,BorderLayout.WEST);
   
    images=new JTabbedPane();
    add(images,BorderLayout.CENTER);

    String userdir=System.getProperty("user.home");
    setOpenDir(properties.getProperty(fileOpenID,userdir));
    setSaveDir(properties.getProperty(fileSaveID,userdir));
  }
  
  private  void connectToDB() {
	    try {
	      
	      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	      String database = "jdbc:oracle:thin:@"+dbaseip+":1521:"+dbase;
	      con = DriverManager.getConnection(database,dbuid,dbpwd);
	      //empValues.setText("Connected to the Database. Fetching Values from DEPT Tables.\n");
	    } catch (SQLException ex)     {
	      System.out.println("Connection Error =  "  + ex.toString());
	    }
  }
  
  
  
  /*public  void fetchValues() {
	   try {
		connectToDB();
	    Statement stmt = con.createStatement();
	    //StringBuffer allRowValues = new StringBuffer();
	    ResultSet rset=null;
	    int counter = 1;
	    if(depid.equals("0805000001")||depid.equals("0705000001"))
	    {
	     //rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE where s_department_id = '"+depid+"' and S_DOCUMENT_TYPE='"+dtype+"' ");
	      rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE where s_department_id = '"+depid+"' order by S_DOCUMENT_TYPE_ID");
		   	 
	    
	    }
	    else if(depid.equals("1107000001"))
	    {
		    //  rset = stmt.executeQuery("SELECT b.s_sub_document_type||'('||A.S_DOCUMENT_TYPE||')' as S_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE A,TBL_SUB_DOCUMENT_TYPE B ,TBL_HRD_DOC_MAPPING C WHERE A.S_DOCUMENT_TYPE_ID=B.S_DOCUMENT_TYPE_ID AND "+ 
	    	//" B.S_SUB_DOCUMENT_TYPE_ID=C.S_SUB_DOCUMENT_TYPE_ID AND C.S_ESS_USER_ID='"+userid+"' AND A.S_DEPARTMENT_ID='1107000001'");
		      
		      rset = stmt.executeQuery("SELECT b.n_sub_document_code||'-'||b.s_sub_document_type as S_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE A,TBL_SUB_DOCUMENT_TYPE B ,TBL_HRD_DOC_MAPPING C WHERE A.S_DOCUMENT_TYPE_ID=B.S_DOCUMENT_TYPE_ID AND A.S_DOCUMENT_TYPE_ID='"+dtype+"' and"+ 
		  	    	" B.S_SUB_DOCUMENT_TYPE_ID=C.S_SUB_DOCUMENT_TYPE_ID AND C.S_ESS_USER_ID='"+userid+"' AND A.S_DEPARTMENT_ID='1107000001' order by b.n_sub_document_code||'-'||b.s_sub_document_type");
	    }else if(depid.equals("1208000002")){
	    	 rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE FROM TBL_CV_DOC_TYPE ORDER BY S_DOCUMENT_TYPE");
			   
	    }else if(depid.equals("1200000001")){
	    	 rset = stmt.executeQuery("SELECT B.S_SUB_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE A,TBL_SUB_DOCUMENT_TYPE B WHERE A.S_DOCUMENT_TYPE_ID = B.S_DOCUMENT_TYPE_ID  AND A.S_DOCUMENT_TYPE_ID = '"+section+"'  ORDER BY b.n_sub_document_code");
			   
	    } else
	    {
		     rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE FROM TBL_DOCUMENT_TYPE where s_department_id = '"+depid+"' order by S_DOCUMENT_TYPE_ID");
		   	  	
		    }
	   
	     
	     while (rset.next())
	    {
	      cbox.addItem(rset.getString(1));
	      //allRowValues.append("ROW " + counter +  ":  ENAME = " + rset.getString(1) + " &  ENO = " + rset.getString(2) + "\n");
	      counter++;
	    }
	    //empValues.setText(allRowValues.toString());
	    rset.close();
	    stmt.close();
	    con.close();
	  } catch (SQLException ex) 
	  {
	    System.out.println("Error While Fetching Values =  "  +  ex.toString());
	  }
  }*/
  
  public void setOpenDir(String path){
    //new File(path).mkdirs();
    openfc=new JFileChooser(path);
  }

  public void setSaveDir(String path){
    //new File(path).mkdirs();
    savefc=new JFileChooser(path);
  }

  protected void setButtonPanel(JPanel gui){
    JPanel buttonPanel=new JPanel();
    cbox = new JComboBox();
    cbox1 = new JComboBox();
    //fetchValues();
    fillComboYearnew();
    buttonPanel.setLayout(new GridLayout(0,1));
    JLabel jl = new JLabel();
    JLabel j2 = new JLabel();
    jl.setText("Select Document Type:");
    buttonPanel.add(jl);
    buttonPanel.add(cbox);
    if(depid.equals("1200000001")){
    	  j2.setText("Select Year:");
    	    buttonPanel.add(j2);
    	    buttonPanel.add(cbox1);
    }
  
     buttonPanel.add(new JButton(getNewAction()));   
    buttonPanel.add(new JButton(getOpenAction()));
    buttonPanel.add(new JButton(getSaveAction()));
    //buttonPanel.add(new JButton(getPrintAction()));
    //buttonPanel.add(new JButton(getConvertAction()));
    buttonPanel.add(new JButton(getRotateAction()));
    
    /*cbox.addActionListener(setScanner());
    try{
        scanner=Scanner.getDevice();
        if(scanner!=null){
          //gui.add(scanner.getScanGUI());
          if(scanner instanceof TwainScanner){
        	String[] list=((TwainScanner)scanner).getDeviceNames();
            for(int i=0;i<list.length;i++){
            	cbox.addItem(list[i].toString());
            }
          }
        }
    }catch(Exception e){
    	  System.out.println("9\b"+getClass().getName()+".setButtonPanel:\n\t"+e);
    }*/
    
    //buttonPanel.add(new JButton(getScanner()));
    gui.add(buttonPanel);
   
  }
  public  void fillComboYearnew()
  {
  		int i = 0;
  	int nYear = 1994;
  	for(i = 1; i <= 25; i++ )
  	{
  		 cbox1.addItem(nYear);			
  		nYear = nYear + 1;
  	}
  	
  }
  public Action getNewAction(){
    return new AbstractAction("Clear",new JarImageIcon(getClass(),"32x32/new.png")){
      public void actionPerformed(ActionEvent ev){
        images.removeAll();
      }
    };
  }

  public Action getOpenAction(){
    return new AbstractAction("Attach",new JarImageIcon(getClass(),"32x32/open.png")){
      public void actionPerformed(ActionEvent ev){
        int res=openfc.showOpenDialog(null);
        properties.setProperty(fileOpenID,openfc.getCurrentDirectory().toString());
        if(res==JFileChooser.APPROVE_OPTION){
          try{
            open(openfc.getSelectedFile().getPath());
          }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Image Open Error : "+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE); 
          }
        }
      }
    };
  }

  public  Action getSaveAction(){
    return new AbstractAction("Save",new JarImageIcon(getClass(),"32x32/save.png")){
      public void actionPerformed(ActionEvent ev){
        //int res=savefc.showSaveDialog(null);
    	  int res = 0;
        //properties.setProperty(fileSaveID,savefc.getCurrentDirectory().toString());
        properties.setProperty(fileSaveID,"C:\\DMS\\" + cbox.getItemAt(cbox.getSelectedIndex()));
       // p.disable();
        if(res==JFileChooser.APPROVE_OPTION){
          new Thread(){
            public void run(){
              try{
            	  
            	  String filename = "";
            	     //save(savefc.getSelectedFile().getPath());
            	  try {
            		  connectToDB();
        		    Statement stmt = con.createStatement();
        		    ResultSet rset = null;
        		     if(depid.equals("1107000001"))
        		     {
        		    	 rset = stmt.executeQuery("SELECT b.S_SUB_DOCUMENT_TYPE_ID  as S_DOCUMENT_TYPE_ID FROM TBL_DOCUMENT_TYPE A,TBL_SUB_DOCUMENT_TYPE B ,TBL_HRD_DOC_MAPPING C WHERE A.S_DOCUMENT_TYPE_ID=B.S_DOCUMENT_TYPE_ID AND "+
        		    			 	"B.S_SUB_DOCUMENT_TYPE_ID=C.S_SUB_DOCUMENT_TYPE_ID AND b.n_sub_document_code||'-'||b.s_sub_document_type ='" + cbox.getItemAt(cbox.getSelectedIndex()) + "' AND A.S_DEPARTMENT_ID='"+depid+"'");
        		    }else if(depid.equals("1208000002")){
        		    	 rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE_ID FROM TBL_CV_DOC_TYPE where S_DOCUMENT_TYPE = '" + cbox.getItemAt(cbox.getSelectedIndex()) + "'");
        		    }else if(depid.equals("1200000001")){
        		    	 rset = stmt.executeQuery("SELECT b.S_SUB_DOCUMENT_TYPE_ID as S_DOCUMENT_TYPE_ID FROM TBL_DOCUMENT_TYPE A,TBL_SUB_DOCUMENT_TYPE B WHERE A.S_DOCUMENT_TYPE_ID = B.S_DOCUMENT_TYPE_ID  AND B.S_SUB_DOCUMENT_TYPE = '" + cbox.getItemAt(cbox.getSelectedIndex()) + "'  ORDER BY b.n_sub_document_code");
        				   
        		    }
        		     else
        		     {
        		        rset = stmt.executeQuery("SELECT S_DOCUMENT_TYPE_ID FROM TBL_DOCUMENT_TYPE where S_DOCUMENT_TYPE = '" + cbox.getItemAt(cbox.getSelectedIndex()) + "' and s_department_id = '"+depid+"'");
        	        		 
        		     }
        		     while (rset.next())
        		    {
        		    	filename = rset.getString("S_DOCUMENT_TYPE_ID");
        		    }
        		    rset.close();
        		    stmt.close();
        		    con.close();
        		  } catch (SQLException ex) 
        		  {
        			  System.out.println("Error While Fetching Values =  "  +  ex.toString());
        		  }
            	  save("C:\\DMS\\" + filename,filename);
            	 // p.setEnabled(true);
              }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Image Save Error : "+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE); 
              }
            }
            
          }.start();
        }
      }
    };
  }

  public Action getPrintAction(){
    return new AbstractAction("",new JarImageIcon(getClass(),"32x32/print.png")){
      public void actionPerformed(ActionEvent ev){
        new Thread(){
          public void run(){
            Printer p=new Printer();
            for(int i=0; i<images.getTabCount(); i++){
              JScrollPane sp=(JScrollPane)images.getComponentAt(i);
              ImagePanel ip=(ImagePanel)sp.getViewport().getView();
              p.append(ip);
            }
            p.print();
          }
        }.start();        
      }
    };
  }

  public Action getConvertAction(){
    return new AbstractAction("<html><center><b>Colour</b><br><b>Reduction</b></center></html>"/*,new JarImageIcon(getClass(),"32x32/blackWhite.png")*/){
      public void actionPerformed(ActionEvent ev){
        convertImage();
      }
    };
  }

  public Action getRotateAction(){
    return new AbstractAction("Rotate",new JarImageIcon(getClass(),"32x32/rotate.png")){
      public void actionPerformed(ActionEvent ev){
        JScrollPane sp=(JScrollPane)images.getSelectedComponent();
        if(sp!=null){
          ImagePanel ip=(ImagePanel)sp.getViewport().getView();
          ip.rotate();
        }
      }
    };
  }

  /*public Action getScanner(){
	    return new AbstractAction("Acquire",new JarImageIcon(getClass(),"32x32/scanner.png")){
	      public void actionPerformed(ActionEvent ev){
        	try{
        		scanner.acquire();
        	}catch(ScannerIOException se){
        		se.printStackTrace();
        	}
	      }
	    };
	  }
  */
  /*
  public Action setScanner(){
	    return new AbstractAction(""){
	      public void actionPerformed(ActionEvent ev){
	    	  try{
	    	        scanner.select();
	    	    }catch(ScannerIOException se){
	    	      se.printStackTrace();
	    	    }
      	  }
	    };
	    
	  }
	  */
  protected  void addImage(String fn, BufferedImage img){
    Object md=img.getProperty("iiometadata");
    if((md!=Image.UndefinedProperty)&&(md!=null)&&(md instanceof IIOMetadata)){
//      new MetadataReader().read((IIOMetadata)md);
    }

    System.out.println("Image.Width ="+img.getWidth());
    System.out.println("Image.Height ="+img.getHeight());

    ImagePanel ip=new ImagePanel();
    ip.addPropertyChangeListener(this);

    JScrollPane sp=new JScrollPane(ip);
    sp.getVerticalScrollBar().setUnitIncrement(100);
    sp.getHorizontalScrollBar().setUnitIncrement(100);    
    ip.setImage(img);
//    images.addTab("image",sp);
    images.addTab(fn,new TabCloseIcon(), sp);
    images.setSelectedIndex(images.getTabCount()-1);
  }

  public void open(String filename)throws IOException{
    long time=System.currentTimeMillis();

    String ext=filename.substring(filename.lastIndexOf('.')+1);
    Iterator readers=ImageIO.getImageReadersByFormatName(ext);
    if(!readers.hasNext()){throw new IOException(getClass().getName()+".open:\n\tNo reader for format '"+ext+"' available.");}
    
    ImageReader reader=(ImageReader)readers.next();
    while(!reader.getClass().getName().startsWith("uk.co.mmscomputing")&&readers.hasNext()){// prefer our own reader
      reader=(ImageReader)readers.next();
    }
    File f=new File(filename);
    ImageInputStream iis=ImageIO.createImageInputStream(f);
    try{
      reader.setInput(iis,true);
      try{
        for(int i=0; true; i++){
          IIOMetadata md=reader.getImageMetadata(i);
//          if(md!=null){new MetadataReader().read(md);}
          addImage(f.getName()+" "+i,reader.read(i));
        }
      }catch(IndexOutOfBoundsException ioobe){}
    }catch(Error e){
      System.out.println("9\b"+getClass().getName()+".open:\n\t"+e);
      e.printStackTrace();
      throw e;
    }finally{
      iis.close();
    }
    time=System.currentTimeMillis()-time;
    System.out.println("Opened : "+filename);
    System.out.println("Time used to load images : "+time);
  }

  private IIOImage getIIOImage(ImageWriter writer,ImageWriteParam iwp,BufferedImage image){
    ImageTypeSpecifier   it  = ImageTypeSpecifier.createFromRenderedImage(image);

/*
    try{
      uk.co.mmscomputing.imageio.bmp.BMPMetadata md=(uk.co.mmscomputing.imageio.bmp.BMPMetadata)image.getProperty("iiometadata");
      if(md!=null){
        md.setXPixelsPerMeter(11812);    // force 300 dpi for bmp images
        md.setYPixelsPerMeter(11812);    // works only with mmsc.bmp package
      }
    }catch(Exception e){}
*/

    IIOMetadata md;
    Object      obj=image.getProperty("iiometadata");               // if image is a TwainBufferedImage get metadata
    if((obj!=null)&&(obj instanceof IIOMetadata)){
      md = (IIOMetadata)obj;
    }else{
      md = writer.getDefaultImageMetadata(it,iwp);
    }
    return new IIOImage(image,null,md);
  }

  public synchronized void  save(String filename, String fileType)throws IOException{
	  boolean success = (new File("C:\\DMS")).mkdir();
	  q.setEnabled(false);
      if (! success) {
        //System.out.println("Directory: could not be created");
      }
    if(images.getTabCount()<=0){throw new IOException(getClass().getName()+".save:\n\tNo images available!");}

    //String ext=filename.substring(filename.lastIndexOf('.')+1);
    String ext="jpg";
    Iterator writers=ImageIO.getImageWritersByFormatName(ext);
    if(!writers.hasNext()){throw new IOException(getClass().getName()+".save:\n\tNo writer for format '"+ext+"' available.");}
    ImageWriter writer=(ImageWriter)writers.next();

    while(!writer.getClass().getName().startsWith("uk.co.mmscomputing")&&writers.hasNext()){// prefer our own writer
      writer=(ImageWriter)writers.next();
    }

    ImageWriteParam iwp = writer.getDefaultWriteParam();
    
// this code is used for compressing the image
    	iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    	//iwp.setCompressionType(jpeg);
    	iwp.setCompressionQuality(0.2f);
//    iwp.setCompressionQuality(Float.valueOf("0.05"));
// end compressing
    IIOParamController controller = iwp.getController();
    if(controller!=null){controller.activate(iwp);}
    

    long time=System.currentTimeMillis();

    //File file=new File(filename);
    //if(file.exists()){file.delete();} 

    ImageOutputStream ios=null;
    try{
    	/*ios=ImageIO.createImageOutputStream(file);
    	writer.setOutput(ios);

    	writer.prepareWriteSequence(null);
    	JScrollPane sp=(JScrollPane)images.getComponentAt(images.getSelectedIndex());
    	ImagePanel ip=(ImagePanel)sp.getViewport().getView();
    	writer.writeToSequence(getIIOImage(writer,iwp,ip.getImage()),iwp);
    	writer.endWriteSequence();*/
    	
     //---------------------------- FTP for EOLIFETIME----------------------------//
      if(depid.equals("1200000001"))  
      {
    	long first_start_time = System.currentTimeMillis(); 
    	 
    	System.out.println("=== EO LIFETIME ===");
    	try
    	{
    	FtpWrapper ftp = new FtpWrapper();
    	String hostDomain = ftp.getServerName();
		String Id = ftp.getUsername();
		String Password = ftp.getPassword();
		String ftpDirectoryForDownloadingFile = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyMM");
	    Date date = new Date();
	    String datestring = dateFormat.format(date);
		
	    String seqfilename="";
	    
	    long ftp_st_time = System.currentTimeMillis(); 
	    System.out.println("Time Taken B4 FTP Conn :: "+(ftp_st_time-time)/1000);
	    
	    if (ftp.connectAndLogin(hostDomain, Id, Password)) {
	    	long ftp_end_time = System.currentTimeMillis();  
	    	System.out.println("C::FTP Connected in :: "+(ftp_end_time-first_start_time)/1000+" Secs");
	    	
	    	
	    	long cur_time = System.currentTimeMillis();  
			ftp.setPassiveMode(true);
			ftp.binary();
			
			ftp.setBufferSize(1024000);
			
			ftpDirectoryForDownloadingFile = "/EO_LIFETIME/";
			ftp.changeWorkingDirectory(ftpDirectoryForDownloadingFile);
			long cur_end_time = System.currentTimeMillis();  
			System.out.println("C::FTP Dir Set in :: "+(cur_end_time-first_start_time)/1000+" Secs");
			
			long dbcon_st_time = System.currentTimeMillis();  
        	connectToDB();
        	long dbcon_end_time = System.currentTimeMillis();  
        	System.out.println("C::DB Connected in :: "+(dbcon_end_time-first_start_time)/1000+" Secs");
        
        	int pages = images.getTabCount();
        	String filenamearr[] = new String[pages];
        	
        	PreparedStatement PSfilename = con.prepareStatement("SELECT C_EO_LIFETIME.NEXTVAL as filename FROM SYS.DUAL CONNECT BY LEVEL <= ?");
        	PSfilename.setObject(1, pages);
        	ResultSet RSfilename =  PSfilename.executeQuery();
        	
        	int fnameptr=0;
      	    while(RSfilename.next())
      	    {
      	    	filenamearr[fnameptr] = RSfilename.getObject("filename").toString();
      	    	fnameptr++;
      	    }
      	    RSfilename.close();
      	    PSfilename.close();
			
      	    int displaySeq=1;
      	    String getMaxSQL =  " SELECT MAX(N_DISPLAY_SEQ) as maxseq "+
      	    					" FROM (SELECT N_DISPLAY_SEQ FROM TBL_EO_LIFETIME_DETAIL WHERE S_EQUIPMENT_ID=? and S_SUB_DOCUMENT_TYPE_ID=? "+
      	    					" UNION ALL "+
      	    					" SELECT N_DISPLAY_SEQ FROM TBL_EO_LIFETIME_OFFLINE WHERE S_EQUIPMENT_ID=? and S_SUB_DOCUMENT_TYPE_ID=?) ";
      	    
    	    PreparedStatement getMaxDispSeq = con.prepareStatement(getMaxSQL);
    	    getMaxDispSeq.setObject(1, equipname);
    	    getMaxDispSeq.setObject(2, fileType);
    	    getMaxDispSeq.setObject(3, equipname);
    	    getMaxDispSeq.setObject(4, fileType);
    	    ResultSet RSmaxDispSeq = getMaxDispSeq.executeQuery();
    	    if(RSmaxDispSeq.next())
    	    {
    	    	displaySeq = RSmaxDispSeq.getInt("maxseq");
    	    }
    	    RSmaxDispSeq.close();
    	    getMaxDispSeq.close();
    	    displaySeq++;
    	    
        for(int i=1; i<=images.getTabCount(); i++)
        {
        		
        	long seq_st_time = System.currentTimeMillis();
        	/*PreparedStatement PSfilename = con.prepareStatement("Select C_EO_LIFETIME.NEXTVAL as filename from DUAL");
      	    ResultSet RSfilename =  PSfilename.executeQuery();
      	    if(RSfilename.next())
      	    {
      	    	seqfilename = RSfilename.getObject("filename").toString();
      	    }
      	    RSfilename.close();
      	    PSfilename.close();*/
      	    long seq_end_time = System.currentTimeMillis();
      	    System.out.println("C::Filename fetched in :: "+(seq_end_time-first_start_time)/1000+" Secs");
      	    
      	    
        	long applet_st_time = System.currentTimeMillis();  
 
      	    String newfilename = datestring+filenamearr[i-1];
      	    System.out.println("Filename :: "+newfilename);
  	  	
      	    String filepath = ""; 	  	
  	  	if (filename.lastIndexOf(".") == -1)
  	  	{
  	  		filepath  = filename + "-" + i;
  	  	}
  	  	else
  	  	{
  	  		filepath = filename.substring(0, filename.lastIndexOf(".")) + "-" + i;
  	  	}
  	  	filepath = filepath + ".jpg";//filename.substring(filename.indexOf("."), filename.length());
  	  	File file=new File(filepath);
  	  	if(file.exists()){file.delete();}
	        ios=ImageIO.createImageOutputStream(file);
	        
	        writer.setOutput(ios);
	        writer.prepareWriteSequence(null);
	        JScrollPane sp=(JScrollPane)images.getComponentAt(i - 1);
	        ImagePanel ip=(ImagePanel)sp.getViewport().getView();
	        //ip.setSize(ip.getWidth() / 5, ip.getHeight() / 5);
	        writer.writeToSequence(getIIOImage(writer,iwp,ip.getImage()),iwp);
	        writer.endWriteSequence();
	        ios.close();
	        ios=null;
	       
	        File tempFileRef  = new File(filepath);
	        String name = tempFileRef.getName();
	        String userSeparator="\\";            
	        name = name.substring(name.lastIndexOf(userSeparator)+1,name.length());    
	        if (name.toLowerCase().endsWith("jpg"))
			{
	            InputStream from = null; // Stream to read from source
				from = new FileInputStream(tempFileRef); // Create input stream
				InputStream blogimage = new FileInputStream(tempFileRef);
				String DetailId = "";
				
				long applet_end_time = System.currentTimeMillis();
	      	    System.out.println("C::Processed in  :: "+(applet_end_time-first_start_time)/1000+" Secs");

	      	    
				try{
					
					
					String unixFileName = equipname+"-"+fileType+"-"+newfilename+"-"+System.currentTimeMillis()+".jpg";
					
					long file_st_time = System.currentTimeMillis();				
					boolean filestatus = ftp.uploadFile(filepath, "/EO_LIFETIME/"+unixFileName);
					long file_end_time = System.currentTimeMillis();
					System.out.println("C::File "+newfilename+".jpg uploaded in :: "+(file_end_time-first_start_time)/1000+" Secs");
					
					long permission_st_time = System.currentTimeMillis();
					boolean retVal = ftp.sendSiteCommand("chmod 777 "+unixFileName);
					long permission_end_time = System.currentTimeMillis();
		      	    System.out.println("C::Permission set in :: "+(permission_end_time-first_start_time)/1000+" Secs");
		      	    
					//System.out.println("File Uploaded "+filestatus+" \nFile permission set "+retVal);
		      	  
				    long insert_st_time = System.currentTimeMillis();
					String insertQuery = "INSERT INTO TBL_EO_LIFETIME_OFFLINE (S_DETAIL_ID,s_equipment_id,S_SUB_DOCUMENT_TYPE_ID,S_DOCUMENT_LINK,S_SCANNED_BY,S_YEAR,S_STATUS,N_PENDING,N_DISPLAY_SEQ) " 
				        +" VALUES (?,?,?,?,?,?,'0','1',?)"; 
					PreparedStatement prepStmt = con.prepareStatement(insertQuery);
					prepStmt.setObject(1,newfilename);	
				    prepStmt.setObject(2,equipname);	
				    prepStmt.setObject(3,fileType);	
				    prepStmt.setObject(4,unixFileName);
				    prepStmt.setObject(5,userid);	
				    prepStmt.setObject(7,displaySeq);	
				    displaySeq++;
				    
				    if(depid.equals("1200000001")){
				      	 prepStmt.setObject(6,cbox1.getSelectedItem());
				    }else{
				    	 prepStmt.setObject(6,docid);
				    }
				    boolean insFlag = prepStmt.execute();
				    System.out.println("insFlag :: "+insFlag);
				    
				    prepStmt.close();
				    long insert_end_time = System.currentTimeMillis();
		      	    System.out.println("C::Data Inserted in :: "+(insert_end_time-first_start_time)/1000+" Secs");
				    
				    q.setEnabled(true);
				}catch (Exception sqlExp) {
					mssg = sqlExp.toString();
					
				}
		    
	            from.close();
			    
	            success = tempFileRef.delete();
	            p.enable();
	            //if (!success)
	            	//System.out.println("Cannot delete");
			}
	       
	       
        	}
        con.close();
        if(ftp != null){
			long ftpd_st_time = System.currentTimeMillis();
			ftp.logout();
			ftp.disconnect();
			long ftpd_end_time = System.currentTimeMillis();
      	    System.out.println("C::FTP Disconnected in  :: "+(ftpd_end_time-first_start_time)/1000+" Secs");
		}
	    }
	    else
	    {
	    	System.out.println("Cannot Connect to FTP");
	    	mssg = "Cannot Connect to FTP";
	    }
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
      }
      }     
      //---------------------------- FTP for EOLIFETIME----------------------------//
      else
      {
      for(int i=1; i<=images.getTabCount(); i++){
    	  	String filepath = "";
    	  	
    	  	if (filename.lastIndexOf(".") == -1)
    	  	{
    	  		filepath  = filename + "-" + i;
    	  	}
    	  	else
    	  	{
    	  		filepath = filename.substring(0, filename.lastIndexOf(".")) + "-" + i;
    	  	}
    	  	filepath = filepath + ".jpg";//filename.substring(filename.indexOf("."), filename.length());
    	  	File file=new File(filepath);
    	  	if(file.exists()){file.delete();}
	        ios=ImageIO.createImageOutputStream(file);
	        
	        writer.setOutput(ios);
	        writer.prepareWriteSequence(null);
	        JScrollPane sp=(JScrollPane)images.getComponentAt(i - 1);
	        ImagePanel ip=(ImagePanel)sp.getViewport().getView();
	        //ip.setSize(ip.getWidth() / 5, ip.getHeight() / 5);
	        writer.writeToSequence(getIIOImage(writer,iwp,ip.getImage()),iwp);
	        writer.endWriteSequence();
	        ios.close();
	        ios=null;
	       
	        File tempFileRef  = new File(filepath);
	        String name = tempFileRef.getName();
            String userSeparator="\\";            
            name = name.substring(name.lastIndexOf(userSeparator)+1,name.length());    
            if (name.toLowerCase().endsWith("jpg"))
			{
	            InputStream from = null; // Stream to read from source
	            //FileOutputStream to = null; // Stream to write to destination
				from = new FileInputStream(tempFileRef); // Create input stream
				//to = new FileOutputStream(servlet.getDatabaseProperty("uploadfilepath")+"\\"+Departmenttxt+"\\"+DocNotxt+"\\"+name); // Create output stream
				InputStream blogimage = new FileInputStream(tempFileRef);
				//String fileLink = servlet.getDatabaseProperty("linkpath")+Departmenttxt+"/"+DocNotxt+"/"+name;
		    	//String fileType = name.substring(0,name.indexOf("-"));
				String DetailId = "";
				try{
					//DetailId = NewCodeGenerate("TBL_DOCUMENT_DETAIL");
				}catch (Exception sqlExp) {
					sqlExp.printStackTrace();
					//Exception exp = new Exception("EXECUTE_QUERY_ERROR", sqlExp);
					//throw exp;
				}
				try{
					String sqlQuery ="";
					if(depid.equals("1107000001"))
					{
					    sqlQuery = "INSERT INTO TEMP_DETAIL_HRD(S_DETAIL_ID,S_USER_ID,S_SUB_DOCUMENT_TYPE_ID,S_IMAGE,S_DOCUMENT_ID) VALUES (S_HRD_DETAIL_SEQ.NEXTVAL,?,?,?,?)";
					}
					else if(depid.equals("1107000002"))
					{
						sqlQuery = "INSERT INTO TEMP_DETAIL_EIIPL(S_DETAIL_ID,S_USER_ID,S_DOCUMENT_TYPE_ID,S_IMAGE,S_DOCUMENT_ID) VALUES (S_EIIPL_DETAIL_SEQ.NEXTVAL,?,?,?,?)";	
	
					}
					else if(depid.equals("1208000002"))
					{
						
						sqlQuery = "INSERT INTO TEMP_DETAIL_CV(S_DETAIL_ID,S_USER_ID,S_DOCUMENT_TYPE_ID,S_IMAGE,S_DOCUMENT_ID) VALUES (S_CV_DETAIL_SEQ.NEXTVAL,?,?,?,?)";	
	
					}else if(depid.equals("1200000001")){
					
						sqlQuery = "INSERT INTO TEMP_DETAIL_EO_NEW(S_DETAIL_ID,S_USER_ID,S_SUB_DOCUMENT_TYPE_ID,S_IMAGE,S_YEAR) VALUES (C_EO_LIFETIME.NEXTVAL,?,?,?,?)";	
						
        			 }
					else
					{
						 sqlQuery = "INSERT INTO TEMP_DETAIL(S_DETAIL_ID,S_USER_ID,S_DOCUMENT_TYPE_ID,S_IMAGE,S_DOCUMENT_ID) VALUES (S_FIN_DETAIL_SEQ.NEXTVAL,?,?,?,?)";	
						 //sqlQuery = "{call PROC_NEW_FIN_DETAILS1(?,?,?,?)}";	
					}
					
					connectToDB();
					
					/*PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
					//prepStmt.setObject(1,DetailId);	
				    prepStmt.setObject(1,userid);	
				    prepStmt.setObject(2,fileType);	
				    prepStmt.setBinaryStream(3,blogimage,(int)tempFileRef.length());
				    if(depid.equals("1200000001")){
				      	 prepStmt.setObject(4,cbox1.getSelectedItem());
				    }else{
				    	 prepStmt.setObject(4,docid);
				    }
				    prepStmt.executeQuery();
				    prepStmt.close();*/
				    
					CallableStatement calls = con.prepareCall(sqlQuery);					
			   	    calls.setObject(1,userid);
			   	    calls.setObject(2,fileType);
			   	    calls.setBinaryStream(3,blogimage,(int)tempFileRef.length());
			   	    
			   	    System.out.println("UID "+userid+" &Ftype "+fileType);
			   	 
			   	    if(depid.equals("1200000001")){
			   	    	calls.setObject(4,cbox1.getSelectedItem());
			   	    }else{
			   	    	calls.setObject(4,docid);
			   	    }
			   	    
			   	    calls.execute();
				    blogimage.close();
				    file.delete();
			   	    calls.close();

				    
				    //prepStmt.close();
        		    
        		    
        		   

            		    
        		    
        		    con.close();
        		    q.setEnabled(true);
				}catch (Exception sqlExp) {
					mssg = sqlExp.toString();
					//Exception exp = new Exception("EXECUTE_QUERY_ERROR", sqlExp);
					//throw exp;
				}
		    	//Vector<Comparable> tranVector = new Vector<Comparable>();
				//tranVector.add(servlet.getDatabaseProperty("uploadfilepath")+"\\"+request.getAttribute("Departmenttxt")+"\\"+request.getAttribute("DocNotxt")+"\\"+name);
				//tranVector.add(name.substring(0,name.indexOf("-") - 1));
				//fileList.add(z, tranVector);
				//itemList = itemList + servlet.getDatabaseProperty("linkpath")+Departmenttxt+"/"+DocNotxt+"/"+name + "|";
	            /*byte[] buffer = new byte[4096]; // To hold file contents
	            int bytes_read; // How many bytes in buffer
                while ((bytes_read = from.read(buffer)) != -1)
	            {
	              	to.write(buffer, 0, bytes_read); // write
	            }
	            to.flush();
	            to.close();*/
	            from.close();
			    
	            success = tempFileRef.delete();
	            p.enable();
	            if (!success)
	            	System.out.println("Cannot delete");
			}
	        /*URL url = new URL( "http://172.18.17.141:7001/DMS/Upload/a"+i+".jpg" );
	        HttpURLConnection urlcon = (HttpURLConnection) url.openConnection( );
	        urlcon.setRequestMethod("POST");
//	        file type is image
	        urlcon.setRequestProperty("Content-type", "image/jpg");
	        urlcon.setDoOutput(true);
	        //urlcon.setDoInput(true);
	        JScrollPane sp=(JScrollPane)images.getComponentAt(i - 1);
	        ImagePanel ip=(ImagePanel)sp.getViewport().getView();
	        BufferedImage img = ip.getImage();
//	         urlcon.setDoInput(true);
//	        now just need to write my image to the output stream
	        ImageIO.write((RenderedImage)img, "jpg", urlcon.getOutputStream());  
	        */
	        //String imagePath= uploader.upload(filepath,"image/jpeg");		      
      	}
      }
      
      //-------------SCAN LOG------------------//
        
	    int pages = images.getTabCount();
	    long save_time = (System.currentTimeMillis()-time)/1000;
		PreparedStatement psScanLog;
		try {
			connectToDB();
			psScanLog = con.prepareStatement("Insert into tbl_scan_log(S_USER_ID,S_SCAN_TIME,S_SCAN_DATE,S_PAGES_SCANNED) values(?,?,CURRENT_TIMESTAMP,?)");
			psScanLog.setObject(1,userid);
			psScanLog.setObject(2,save_time);
			psScanLog.setObject(3,pages);
			psScanLog.execute();
			psScanLog.close();
			con.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	    //-------------SCAN LOG------------------//
      
      /*String path = "C:\\DMS";
      List tranList = searchFolders(path);
      //String itemList = "";
      if(tranList.size()>0)
      {
	        for (int z=0; z <tranList.size(); z++)
			{
	        	Vector heading = new Vector();
	        	heading = (Vector)tranList.get(z);
	        	File tempFileRef  = new File(heading.get(0).toString());
	            String name = tempFileRef.getName();
	            String userSeparator="\\";            
	            name = name.substring(name.lastIndexOf(userSeparator)+1,name.length());            
//	            System.out.println("File name:"+ name);
				if (name.toLowerCase().endsWith("jpg"))
				{
		            InputStream from = null; // Stream to read from source
		            //FileOutputStream to = null; // Stream to write to destination
					from = new FileInputStream(tempFileRef); // Create input stream
					//to = new FileOutputStream(servlet.getDatabaseProperty("uploadfilepath")+"\\"+Departmenttxt+"\\"+DocNotxt+"\\"+name); // Create output stream
					InputStream blogimage = new FileInputStream(tempFileRef);
					//String fileLink = servlet.getDatabaseProperty("linkpath")+Departmenttxt+"/"+DocNotxt+"/"+name;
			    	//String fileType = name.substring(0,name.indexOf("-"));
					String DetailId = "";
					try{
						DetailId = NewCodeGenerate("TBL_DOCUMENT_DETAIL");
					}catch (Exception sqlExp) {
						sqlExp.printStackTrace();
						//Exception exp = new Exception("EXECUTE_QUERY_ERROR", sqlExp);
						//throw exp;
					}
					try{
						String sqlQuery = "INSERT INTO TEMP_DETAIL(S_DETAIL_ID,S_USER_ID,S_DOCUMENT_TYPE_ID,S_IMAGE) VALUES (?,?,?,?)";
						connectToDB();
						PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
						prepStmt.setObject(1,DetailId);	
					    prepStmt.setObject(2,userid);	
					    prepStmt.setObject(3,fileType);	
					    //prepStmt.setObject(4,fileLink);
					    prepStmt.setBinaryStream(4,blogimage,(int)tempFileRef.length());
					    prepStmt.executeQuery();
					    prepStmt.close();
					    blogimage.close();
					    
	        		    //rset.close();
	        		    prepStmt.close();
	        		    con.close();
					}catch (SQLException sqlExp) {
						sqlExp.printStackTrace();
						//Exception exp = new Exception("EXECUTE_QUERY_ERROR", sqlExp);
						//throw exp;
					}
			    	//Vector<Comparable> tranVector = new Vector<Comparable>();
					//tranVector.add(servlet.getDatabaseProperty("uploadfilepath")+"\\"+request.getAttribute("Departmenttxt")+"\\"+request.getAttribute("DocNotxt")+"\\"+name);
					//tranVector.add(name.substring(0,name.indexOf("-") - 1));
					//fileList.add(z, tranVector);
					//itemList = itemList + servlet.getDatabaseProperty("linkpath")+Departmenttxt+"/"+DocNotxt+"/"+name + "|";
		            //byte[] buffer = new byte[4096]; // To hold file contents
		            //int bytes_read; // How many bytes in buffer
	                //while ((bytes_read = from.read(buffer)) != -1)
		            //{
		            //  	to.write(buffer, 0, bytes_read); // write
		            //}
		            //to.flush();
		            //to.close();
		            from.close();
				    
		            success = tempFileRef.delete();

		            if (!success)
		            	System.out.println("Cannot delete");
				}
			}
      }*/    
      
      /*if(writer.canWriteSequence()){                               //i.e tiff,sff(fax)
        writer.prepareWriteSequence(null);
        for(int i=0;i<images.getTabCount();i++){
          JScrollPane sp=(JScrollPane)images.getComponentAt(i);
          ImagePanel ip=(ImagePanel)sp.getViewport().getView();
          writer.writeToSequence(getIIOImage(writer,iwp,ip.getImage()),iwp);
        }
        writer.endWriteSequence();
      }else{
        JScrollPane sp=(JScrollPane)images.getComponentAt(0);
        ImagePanel ip=(ImagePanel)sp.getViewport().getView();
        writer.write(null,getIIOImage(writer,iwp,ip.getImage()),iwp);
        for(int i=1; i<images.getTabCount(); i++){
          if(writer.canInsertImage(i)){
            sp=(JScrollPane)images.getComponentAt(i);
            ip=(ImagePanel)sp.getViewport().getView();
            writer.write(null,getIIOImage(writer,iwp,ip.getImage()),iwp);
          }else{
            throw new IOException("Image Writer cannot append image ["+i+"] ("+filename+")");
          }
        }
      }*/
      time=System.currentTimeMillis()-time;
      System.out.println("Saved : "+filename);
      System.out.println("3\bTime used to save images : "+time);
      images.removeAll();
      if(("Cannot Connect to FTP").equals(mssg))
    	  JOptionPane.showMessageDialog(null,mssg, "Information", JOptionPane.INFORMATION_MESSAGE);
      else
    	  JOptionPane.showMessageDialog(null, "File Saved Successfully! Time used to save images : "+(time/1000)+ " Second"+mssg, "Information", JOptionPane.INFORMATION_MESSAGE); 
      

      
    }finally{
      if(ios!=null)
      {
    	  ios.close();
      }
    }
  }

  /*public List searchFolders(String path)
	{
  	int z = 0;
  	File fo = new File(path);
  	List tranList = new ArrayList();
		if(fo.isDirectory())
		{
			System.out.println("Searching in..." + fo.getName());
			String internalNames[] = fo.list();
			for(int i=0; i<internalNames.length; i++)
			{
				if (internalNames[i].endsWith("jpg"))
				{
					Vector<Comparable> tranVector = new Vector<Comparable>();
					tranVector.add(fo.getAbsolutePath() + "\\" + internalNames[i]);
					tranList.add(z, tranVector);
					 z++;
					 //boolean success = fo.delete();
				}
				//searchFolders(new File(fo.getAbsolutePath() + "\\" + internalNames[i]));
			}
		}
		else
	    {
			System.out.println("reached a file..." + fo.getName());
	    }
		return tranList;
  }*/
  
  public synchronized String NewCodeGenerate(String TableName) throws SQLException, 
  Exception
	{
	//Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	ResultSet rs = null;
	String seqNo = "";
	try 
	{
		connectToDB();
		SimpleDateFormat getFormatDate = new SimpleDateFormat("yyMM");
		Date date = new Date();
		String sCode = getFormatDate.format(date);
		int newRec = 0;
		String sql = "SELECT * FROM TBL_CODE_GENERATE WHERE S_TABLE_NAME = ? AND S_CODE = ?";
		ps = con.prepareStatement(sql);
		ps.setObject(1, TableName);
		ps.setObject(2, sCode);
		rs = ps.executeQuery();
		while (rs.next()) 
		{
			newRec = rs.getInt("N_NO") + 1;
			sql = "UPDATE TBL_CODE_GENERATE SET N_NO = ? WHERE S_TABLE_NAME = ? AND S_CODE = ?";
			ps1 = con.prepareStatement(sql);
			ps1.setObject(1, String.valueOf(newRec));
			ps1.setObject(2, TableName);
			ps1.setObject(3, sCode);                    
			int iInserteddRows = ps1.executeUpdate();
			if (iInserteddRows != 1)
			throw new Exception("DB_UPDATE_ERROR", null);
			ps1.close();
		}
		rs.close();
		ps.close();
		if (newRec == 0)
		{
			newRec = 1;
			sql = "INSERT INTO TBL_CODE_GENERATE(S_TABLE_NAME,S_CODE,N_NO) VALUES (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1, TableName);
			ps.setObject(2, sCode);
			ps.setObject(3, String.valueOf(newRec));                    
			int iInserteddRows = ps.executeUpdate();
			if (iInserteddRows != 1)
			throw new Exception("DB_INSERT_ERROR", null);
			ps.close();
		}
		
		String s = Integer.toString(newRec);
		if ( s.length() < 6 )
		{ // pad on left with zeros
			s = "000000".substring( 0, 6 - s.length()) + s;
		} 
		seqNo = sCode + s;
	} 
	catch (SQLException sqlExp) 
	{
		sqlExp.printStackTrace();
		Exception exp = new Exception("EXECUTE_QUERY_ERROR", sqlExp);
		throw exp;
	}
	finally 
	{
		try {
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
				con = null;                
			}
		} catch (Exception e) {
			ps = null;
			ps1 = null;
			rs = null;
			if (con != null) {
				con.close();
				con = null;
			}
		}

	}
	return seqNo;
}
  
  public  void convertImage(){
    new Thread(){
      public void run(){
        try{
          ImageTypeConvertOpPanel itcop=new ImageTypeConvertOpPanel();
          ImageTypeConvertOp itco=itcop.activate();
          if(itco!=null){
            for(int i=0; i<images.getTabCount(); i++){
              JScrollPane sp=(JScrollPane)images.getComponentAt(i);
              ImagePanel ip=(ImagePanel)sp.getViewport().getView();

              BufferedImage image=itco.filter(ip.getImage());
 
              ip.setImage(image);
              ip.revalidate();ip.repaint();

              
              String type="Unknown Type";

              switch(image.getType()){
              case BufferedImage.TYPE_BYTE_BINARY:  type="Byte Binary"; break;
              case BufferedImage.TYPE_BYTE_INDEXED: type="Byte Indexed";break;
              }
              ColorModel cm=image.getColorModel();
              System.out.println("9\bConverted Images to:\n\ntype: "+type+"\nbpp: "+cm.getPixelSize());
            }
          }
        }catch(Exception e){
          System.out.println("9\b"+getClass().getName()+".convertImage:\n\t"+e);
          e.printStackTrace();
        }
      }
    }.start();
  }

/*
  public void convertToBWImage(){
    for(int i=0; i<images.getTabCount(); i++){
      JScrollPane sp=(JScrollPane)images.getComponentAt(i);
      ImagePanel ip=(ImagePanel)sp.getViewport().getView();
      BufferedImage original=ip.getImage();
      BufferedImage image=new BufferedImage(original.getWidth(),original.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
      Graphics2D g=image.createGraphics();
      AffineTransform t=new AffineTransform();
      g.drawRenderedImage(original,t);
      ip.setImage(image);
      ip.revalidate();ip.repaint();
    }
  }
*/
  public  void propertyChange(final PropertyChangeEvent evt){
/*
    String prop=evt.getPropertyName();
    if(prop.equals("open")){
      JTabbedPane tp=(JTabbedPane)getParent();
      tp.setSelectedIndex(1);
      new Thread(){
        public void run(){
          try{
            open((String)evt.getNewValue());
          }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Image Open Error : "+e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE); 
          }
        }
      }.start();
    }else if(prop.equals("save")){
      new Thread(){
        public void run(){
          try{
            open((String)evt.getNewValue());
            int res=savefc.showSaveDialog(null);
            if(res==JFileChooser.APPROVE_OPTION){
              save(savefc.getSelectedFile().getPath());
            }
          }catch(IOException ioe){
            System.out.println("9\b"+ioe.getMessage());
          }
        }
      }.start();
    }
*/
  }

  static private class Printer extends Thread{
    PrinterJob    pj;
    PageFormat    pf;
    Book          bk;
  
    public Printer(){
      pj=PrinterJob.getPrinterJob();

      pf = pj.defaultPage();
      Paper p=pf.getPaper();
      p.setImageableArea(0.0,0.0,p.getWidth(),p.getHeight());
      pf.setPaper(p);
      pf = pj.validatePage(pf);

      bk=new Book();
    }

    public void append(ImagePanel image){
      bk.append(image, pf);
    }
 
    public void print(){
      pj.setPageable(bk);
      if(pj.printDialog()){
        try{
          pj.print(); 
        }catch (Exception e){
          e.printStackTrace();
          System.out.println("9\b"+getClass().getName()+".print:\n\t"+e.getMessage());
        }
      }
    }
  }
  
}

class Uploader{
	
  private String host;
  private int    port;
  private String url,path;

  private OutputStream out;

  public Uploader(URL u,String uploadScript){

    host = u.getHost();

    port = u.getPort();
    if(port==-1){port=u.getDefaultPort();}
   
    path = u.getPath();
    path = path.substring(0,path.lastIndexOf('/')+1);
   
    url  = path+uploadScript;

    System.err.println(host);
    System.err.println(""+port);
    System.err.println(url);
  }

  public void writeln()throws IOException{         out.write('\r');out.write('\n');}
  public void write(String s)throws IOException{   out.write(s.getBytes());}
  public void writeln(String s)throws IOException{ write(s);writeln();}

  public void writeHeader(String host,int port,String url)throws IOException{
    write("POST ");write(url);writeln(" HTTP/1.1");
    write("Host: ");write(host);write(":");writeln(""+port);
  }

  public void writeHeaderContent(String type,String boundary,int len)throws IOException{
    write("Content-Type: ");write(type);write("; boundary=");writeln(boundary);
    write("Content-Length: ");writeln(""+len);
  }

  static class MimeOutputStream extends ByteArrayOutputStream{

    private String boundary;

    public MimeOutputStream(String b)throws IOException{
      boundary=b;
    }

    public void writeln()throws IOException{         write('\r');write('\n');}
    public void write(String s)throws IOException{   write(s.getBytes());}
    public void writeln(String s)throws IOException{ write(s);writeln();}
    public void writeBoundary()throws IOException{   write('-');write('-');writeln(boundary);}
/*
    public void writeMaxFileSize(int size)throws IOException{
      writeBoundary();
      writeln("Content-Disposition: form-data; name=\"MAX_FILE_SIZE\"");
      writeln();
      writeln(""+size);
    }
*/
    public void writeImageFile(String scriptvarname, String fname,String type)throws IOException{
      File f   = new File(fname);
      if(f.length()>5000000){ throw new IOException("FILE SIZE BIGGER THAN 500000 BYTES"); }

      FileInputStream in = new FileInputStream(f);
      try{
        writeBoundary();

        write("Content-Disposition: form-data; name=\"");
        write(scriptvarname);                      // name of variable in server script
        write("\"; filename=\"");
        write(f.getName());
        writeln("\"");

        writeln("Content-Type: "+type);
        writeln();
 
        int count;byte[] buf=new byte[2048];
        while((count=in.read(buf))!=-1){
          write(buf,0,count);
        }
        writeln();
      }finally{
        in.close();
      }
    }

    public void close()throws IOException{
      write('-');write('-');
      write(boundary);
      write('-');write('-');
      writeln();
      super.close();
    }
  }

  public String upload(String fname, String ftype)throws IOException{
    Socket s = null;
    out = null;

    try{
      s   = new Socket(host,port);
      out = s.getOutputStream();

      Random number = new Random();
      String boundary = "---------------------------mmsc"+Long.toString(Math.abs(number.nextLong()),24);

      MimeOutputStream mime = new MimeOutputStream(boundary);
//	      mime.writeMaxFileSize(1000000);
      mime.writeImageFile("picture",fname,ftype);
      mime.close();
      byte[] content = mime.toByteArray();

      writeHeader(host,port,url);
      writeHeaderContent("multipart/form-data",boundary,content.length);
      writeln();

      out.write(content);

      BufferedReader r  = new BufferedReader(new InputStreamReader(s.getInputStream()));
      String         line;

      while(((line=r.readLine())!=null)&&(line.length()!=0)){     // read http header
        System.out.println(line);
      }

      line = r.readLine();                                        // read image path or server error
      if(line.startsWith("ERROR"))
      { 
    	  throw new IOException(line);
      }
      return line;                                                // image path
    }finally{
      if(out!=null){out.close();}
      if(s!=null){s.close();}
    }
  }
}
