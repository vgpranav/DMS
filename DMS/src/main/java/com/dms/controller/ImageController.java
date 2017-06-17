package com.dms.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dms.dao.DocumentDao;
import com.dms.util.FtpWrapper;

@Controller
public class ImageController {

	public ImageController() {}
	  
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@RequestMapping(value="/getUserImage/{type}/{folder}/{id}")
	   public void getUserImage(HttpServletResponse response , 
			   @PathVariable("type") String type,
			   @PathVariable("folder") String folder,
			   @PathVariable("id") long docId
			   ) throws IOException{
		 DocumentDao documentDao = new DocumentDao();
		 byte[] buffer=null;
		 try{
			 buffer = documentDao.getPhotoFromFTP(docId,type,folder,false);
		 }catch(Exception e){
			logger.error(e.getMessage());
		 }
	     response.setContentType("image/jpeg");
	     InputStream in1 = new ByteArrayInputStream(buffer);
	     IOUtils.copy(in1, response.getOutputStream());
	   }
	
	@RequestMapping(value="/getUserImageThumb/{type}/{folder}/{id}")
	   public void getUserImageThumb(HttpServletResponse response , 
			   @PathVariable("type") String type,
			   @PathVariable("folder") String folder,
			   @PathVariable("id") long docId
			   ) throws IOException{
		 DocumentDao documentDao = new DocumentDao();
		 byte[] buffer=null;
		 try{
			 buffer = documentDao.getPhotoFromFTP(docId,type,folder,true);
		 }catch(Exception e){
			logger.error(e.getMessage());
		 }
	     response.setContentType("image/jpeg");
	     InputStream in1 = new ByteArrayInputStream(buffer);
	     IOUtils.copy(in1, response.getOutputStream());
	   }
	
	@RequestMapping(value="/getFileToDisplay/{filename}")
	   public void getFileToDisplay(HttpServletResponse response , 
			   @PathVariable("filename") String filename
			   ) throws IOException{
		 DocumentDao documentDao = new DocumentDao();
		 byte[] buffer=null;
		 try{
			 buffer = documentDao.getFileToDisplay(filename,false);
		 }catch(Exception e){
			logger.error(e.getMessage());
		 }
	     response.setContentType("image/jpeg");
	     InputStream in1 = new ByteArrayInputStream(buffer);
	     IOUtils.copy(in1, response.getOutputStream());
	   }
	
	@RequestMapping(value="/getFileToDisplayThumb/{filename}")
	   public void getFileToDisplayThumb(HttpServletResponse response , 
			   @PathVariable("filename") String filename
			   ) throws IOException{
		 DocumentDao documentDao = new DocumentDao();
		 byte[] buffer=null;
		 try{
			 buffer = documentDao.getFileToDisplay(filename,true);
		 }catch(Exception e){
			logger.error(e.getMessage());
		 }
	     response.setContentType("image/jpeg");
	     InputStream in1 = new ByteArrayInputStream(buffer);
	     IOUtils.copy(in1, response.getOutputStream());
	   }
	
	
	 private byte[] resizeImageWithHint(BufferedImage originalImage, int type)
	  {
	    BufferedImage resizedImage = new BufferedImage(50, 50, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, 50, 50, null);
	    g.dispose();
	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
	      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, 
	      RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	      RenderingHints.VALUE_ANTIALIAS_ON);
	    return convertToBytes(resizedImage);
	  }
	 
	 private byte[] convertToBytes(BufferedImage resizedImage) {
		    byte[] imageInByte = null;
		    try {
		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
		      ImageIO.write(resizedImage, "jpg", baos);
		      baos.flush();
		      imageInByte = baos.toByteArray();
		      baos.close();
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		    return imageInByte;
		  }
	 
	 private boolean saveToFileSystem(String FileName, InputStream in,String Dir) {
		    boolean filestatus = false;
		    FtpWrapper ftp = new FtpWrapper();
		    String hostDomain = ftp.getServerName();
		    String Id = ftp.getUsername();
		    String Password = ftp.getPassword();
		    try
		    {
		      String ftpDirectoryForDownloadingFile = "DMS";
		      
		      if (ftp.connectAndLogin(hostDomain, Id, Password)) {
		        ftp.setPassiveMode(true);
		        ftp.binary();
		        ftp.setBufferSize(1024000);
		        ftp.changeWorkingDirectory(ftpDirectoryForDownloadingFile);
		        
		        boolean dirExists = ftp.changeWorkingDirectory(Dir);
		        
		        if (!dirExists) {
		          ftp.makeDirectory(Dir);
		          ftp.changeWorkingDirectory(Dir);
		        }
		        
		        filestatus = ftp.storeFile(FileName, in);
		        boolean bool1 = ftp.sendSiteCommand("chmod 777 " + FileName);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		      try
		      {
		        ftp.logout();
		        ftp.disconnect();
		      } catch (IOException ex) {
		        ex.printStackTrace();
		      }
		    }
		    finally
		    {
		      try
		      {
		        ftp.logout();
		        ftp.disconnect();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
		    return filestatus;
		  }
}
