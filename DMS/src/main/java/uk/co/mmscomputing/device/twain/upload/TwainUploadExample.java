package uk.co.mmscomputing.device.twain.upload;

import java.io.*;
import java.net.*;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JApplet;

import uk.co.mmscomputing.device.scanner.*;

public class TwainUploadExample extends JApplet implements ActionListener, ScannerListener{

  String  filename;

  Scanner scanner;
  Button  acquireButton,selectButton;

  Uploader uploader;

  @Override
public void init(){
    setLayout(new GridLayout(0,2));
    selectButton = new Button("select");
    add(selectButton);
    selectButton.addActionListener(this);

    acquireButton = new Button("acquire");
    add(acquireButton);
    acquireButton.addActionListener(this);

    filename=System.getProperty("user.home")+"/test.jpg";
    showStatus(filename);

    scanner=Scanner.getDevice();
    scanner.addListener(this);

//    String scannerName = getParameter("scanner");
//    System.err.println(scannerName);
//    scanner.select(scannerName);

    uploader=new Uploader(getCodeBase(),"");
  }

  public void actionPerformed(ActionEvent evt){
	  try{
	    if(evt.getSource()==acquireButton){
	      showStatus("acquiring ... "+filename);
	      scanner.acquire();
	    }else if(evt.getSource()==selectButton){
	      showStatus("selecting source");
	      scanner.select();
	    }
	  }catch(ScannerIOException se){
	      se.printStackTrace();
	    }
  }

  public void update(ScannerIOMetadata.Type type, ScannerIOMetadata metadata){

    if(type.equals(ScannerIOMetadata.ACQUIRED)){
      BufferedImage image=metadata.getImage();
      System.out.println("Have an image now!");
      try{
        ImageIO.write(image, "jpg", new File(filename));
        showStatus("uploading ... "+filename);
        upload();
      }catch(Exception e){
        e.printStackTrace();
        showStatus("ERROR, look in Java Console for details!");
      }
/*
    }else if(type.equals(ScannerIOMetadata.NEGOTIATE)){
      ScannerDevice device=metadata.getDevice();
      try{
        device.setShowUserInterface(true);
        device.setShowProgressBar(true);
        device.setRegionOfInterest(0,0,210.0,300.0);
        device.setResolution(100);
      }catch(Exception e){
        e.printStackTrace();
        showStatus("ERROR, look in Java Console for details!");
      }
*/
    }else if(type.equals(ScannerIOMetadata.STATECHANGE)){
      System.err.println(metadata.getStateStr());
    }else if(type.equals(ScannerIOMetadata.EXCEPTION)){
      metadata.getException().printStackTrace();
      showStatus("ERROR, look in Java Console for details!");
    }
  }

  private void upload()throws IOException{
    String imagePath=uploader.upload(filename,"image/jpeg");

    // Browser settings to: check for new version every time you visit (or similar)

    URL url = new URL(getCodeBase().toString()+imagePath);    //    System.err.println(url.toString());
    this.getAppletContext().showDocument(url,"_self");
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
      if(f.length()>500000){ throw new IOException("FILE SIZE BIGGER THAN 500000 BYTES"); }

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

    @Override
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
//      mime.writeMaxFileSize(1000000);
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
      if(line.startsWith("ERROR")){ throw new IOException(line);}
      return line;                                                // image path
    }finally{
      if(out!=null){out.close();}
      if(s!=null){s.close();}
    }
  }
} 