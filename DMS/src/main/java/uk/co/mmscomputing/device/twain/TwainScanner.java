package uk.co.mmscomputing.device.twain;

import java.util.Enumeration;
import java.util.Vector;
import java.awt.image.*;
import javax.swing.*;

import java.io.*;
import uk.co.mmscomputing.device.scanner.*;

public class TwainScanner extends Scanner implements TwainConstants{

  public TwainScanner(){
    metadata=new TwainIOMetadata();
    jtwain.setScanner(this);
  }

// call native routine; TwainScanner -> jtwain.class -> jtwain.dll

  @Override
public void select()throws ScannerIOException{		      	       // select twain source
    jtwain.select(this);
  }

  public TwainIdentity[] getIdentities(){
    Vector identities=new Vector();
    try{
      jtwain.getIdentities(this,identities);
    }catch(Exception e){
      metadata.setException(e);
      fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
    }
    return (TwainIdentity[])identities.toArray(new TwainIdentity[identities.size()]);
  }
 
  @Override
public String[] getDeviceNames()throws ScannerIOException{
    Vector identities=new Vector();

    jtwain.getIdentities(this,identities);

    String[]    names = new String[identities.size()];
    Enumeration ids   = identities.elements();
    for(int i=0;ids.hasMoreElements();i++){
      TwainIdentity id = (TwainIdentity)ids.nextElement();
      names[i]= id.getProductName();
    }
    return names;
  }
 
  @Override
public void select(String name)throws ScannerIOException{		    // select twain source
    jtwain.select(this,name);
  }
 
  @Override
public String getSelectedDeviceName()throws ScannerIOException{
    return jtwain.getSource().getProductName();
  }

  @Override
public void acquire()throws ScannerIOException{                 // initiate scan
    jtwain.acquire(this);
  }

  @Override
public void setCancel(boolean c)throws ScannerIOException{
    jtwain.setCancel(this,c);
  }


// callback function jtwain.dll -> jtwain.class -> TwainScanner.class

  void setImage(BufferedImage image){    // received an image
    try{
      metadata.setImage(image);
      fireListenerUpdate(ScannerIOMetadata.ACQUIRED);
    }catch(Exception e){
      metadata.setException(e);
      fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
    }
  }

  void setImage(File file){              // received an image using file transfer
    try{
      metadata.setFile(file);
      fireListenerUpdate(ScannerIOMetadata.FILE);
    }catch(Exception e){
      metadata.setException(e);
      fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
    }
  }

  void setImageBuffer(TwainTransfer.MemoryTransfer.Info info){
//    System.out.println(info.toString());

    try{
      ((TwainIOMetadata)metadata).setMemory(info);
      fireListenerUpdate(ScannerIOMetadata.MEMORY);
    }catch(Exception e){
      metadata.setException(e);
      fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
    }
  }


  protected void negotiateCapabilities(TwainSource source){     
    // Called in jtwain when source is in state 4: negotiate capabilities
    ((TwainIOMetadata)metadata).setSource(source);
    fireListenerUpdate(ScannerIOMetadata.NEGOTIATE);
    if(metadata.getCancel()){                     // application wants us to abort scan
      try{
        source.close();
      }catch(Exception e){
        metadata.setException(e);
        fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
      }
    }
  }

  void setState(TwainSource source){
    metadata.setState(source.getState());
    ((TwainIOMetadata)metadata).setSource(source);
    fireListenerUpdate(ScannerIOMetadata.STATECHANGE);
  }

  void signalInfo(String msg,int val){
    metadata.setInfo(msg+" [0x"+Integer.toHexString(val)+"]");
    fireListenerUpdate(ScannerIOMetadata.INFO);
  }

  void signalException(String msg){
    Exception e=new TwainIOException(getClass().getName()+".setException:\n    "+msg);
    metadata.setException(e);
    fireListenerUpdate(ScannerIOMetadata.EXCEPTION);
  }

  @Override
public boolean isAPIInstalled(){return jtwain.isInstalled();}

  @Override
public JComponent getScanGUI()throws ScannerIOException{
    return getScanGUI(4);
  }

  @Override
public JComponent getScanGUI(int mode)throws ScannerIOException{
    return new TwainPanel(this,mode);
  }


//  protected void finalize()throws Throwable{
//    System.err.println(getClass().getName()+".finalize:");
//  }

  static public Scanner getDevice(){
    return new TwainScanner();
  }
}
