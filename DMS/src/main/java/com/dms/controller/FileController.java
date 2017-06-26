package com.dms.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.beans.Society;
import com.dms.beans.User;
import com.dms.beans.Vendor;
import com.dms.dao.DocumentDao;
import com.dms.dao.SocietyDao;
import com.dms.util.FtpWrapper;



@Controller
public class FileController
{
  private static final int IMG_WIDTH = 100;
  private static final int IMG_HEIGHT = 100;
  private static final String DIR = "SocietyImages";
  private static final Logger reqreslogger = LoggerFactory.getLogger("reqreslogger");
  
  public FileController() {}
  
  @RequestMapping(value={"/uploadSocietyPhoto"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public HashMap<String, Object> echoFile(MultipartHttpServletRequest request, HttpServletResponse response)
  {
	  //reqreslogger.info("[REQUEST]"+request.getFileNames());
	  
    byte[] bytes = null;
    Long size = null;
    String contentType = null;
    BufferedImage resizedImage = null;
    MultipartFile multipartFile = null;
    BufferedImage originalImage = null;
    String ogFilename = "";
    int type = 0;
    int societyid = 0;
    try {
      multipartFile = request.getFile("file");
      societyid = Integer.parseInt(request.getParameter("societyid"));
      
      size = Long.valueOf(multipartFile.getSize());
      contentType = multipartFile.getContentType();
      InputStream stream = multipartFile.getInputStream();
      ogFilename = multipartFile.getOriginalFilename();
      bytes = IOUtils.toByteArray(stream);
      InputStream in = new ByteArrayInputStream(bytes);
      String newFileName = System.currentTimeMillis() + "-" + ogFilename;
      boolean flag = saveToFileSystem(newFileName, in,"SocietyImages");
      
      //create and save a thumbnail
      InputStream inThumbs = new ByteArrayInputStream(bytes);
	  BufferedImage bImageFromConvert = ImageIO.read(inThumbs);
      byte[] thumb = resizeImageWithHint(bImageFromConvert,1);
      String newFn = newFileName.substring(0, newFileName.lastIndexOf("."))+"_thumbs"+newFileName.substring(newFileName.lastIndexOf("."));
	  saveToFileSystem(newFn,new ByteArrayInputStream(thumb),"SocietyImages");
	  inThumbs.close();
      
      if (flag) {
        DocumentDao ddao = new DocumentDao();
        ddao.savePhotoInfo("society", societyid, newFileName, "SocietyImages/" + newFileName, contentType,true);
      }
      
      originalImage = ImageIO.read(new ByteArrayInputStream(bytes));


    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    HashMap<String, Object> map = new HashMap();
    map.put("fileoriginalsize", size);
    map.put("contenttype", contentType);
    map.put("base64", new String(Base64Utils.encode(convertToBytes(originalImage))));
    
    return map;
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
  

  @RequestMapping(value={"/uploadMemberPhoto"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public HashMap<String, Object> uploadMemberPhoto(MultipartHttpServletRequest request, HttpServletResponse response)
  {
	  //reqreslogger.info("[REQUEST]"+request.getFileNames());
	  
    byte[] bytes = null;
    Long size = null;
    String contentType = null;
    BufferedImage resizedImage = null;
    MultipartFile multipartFile = null;
    BufferedImage originalImage = null;
    String ogFilename = "";
    int type = 0;
    int userid = 0;
    try {
      multipartFile = request.getFile("file");
      userid = Integer.parseInt(request.getParameter("userid"));
      
      size = Long.valueOf(multipartFile.getSize());
      contentType = multipartFile.getContentType();
      InputStream stream = multipartFile.getInputStream();
      ogFilename = multipartFile.getOriginalFilename();
      bytes = IOUtils.toByteArray(stream);
      InputStream in = new ByteArrayInputStream(bytes);
      String newFileName = System.currentTimeMillis() + "-" + ogFilename;
      boolean flag = saveToFileSystem(newFileName, in,"UserImages");
      
      //create and save a thumbnail
      InputStream inThumbs = new ByteArrayInputStream(bytes);
	  BufferedImage bImageFromConvert = ImageIO.read(inThumbs);
      byte[] thumb = resizeImageWithHint(bImageFromConvert,1);
      String newFn = newFileName.substring(0, newFileName.lastIndexOf("."))+"_thumbs"+newFileName.substring(newFileName.lastIndexOf("."));
	  saveToFileSystem(newFn,new ByteArrayInputStream(thumb),"UserImages");
	  inThumbs.close();
	  
      if (flag) {
        DocumentDao ddao = new DocumentDao();
        ddao.savePhotoInfo("user", userid, newFileName, "UserImages/" + newFileName, contentType,false);
      }
      
      originalImage = ImageIO.read(new ByteArrayInputStream(bytes));

    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    HashMap<String, Object> map = new HashMap();
    map.put("fileoriginalsize", size);
    map.put("contenttype", contentType);
    map.put("base64", new String(Base64Utils.encode(convertToBytes(originalImage))));
    
    return map;
  }
  
  @RequestMapping(value={"/getSocietyPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<HashMap<String, Object>> getSocietyPhotos(@ModelAttribute Society society)
  {
	  //reqreslogger.info("[REQUEST]"+society.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<HashMap<String, Object>> photos = new ArrayList();
    try {
      photos = societyDao.getSocietyPhotos(society.getSocietyid(),"society","SocietyImages", photos);
    }
    catch (Exception localException) {}
    
    return photos;
  }
  
  
  @RequestMapping(value={"/getMemberPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<HashMap<String, Object>> getMemberPhotos(@ModelAttribute User user)
  {
	  //reqreslogger.info("[REQUEST]"+user.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<HashMap<String, Object>> photos = new ArrayList();
    try {
      photos = societyDao.getSocietyPhotos(user.getUserid(),"user","UserImages",photos);
    }
    catch (Exception localException) {}
    
    return photos;
  }
  
  @RequestMapping(value={"/getVendorPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<HashMap<String, Object>> getVendorPhotos(@ModelAttribute Vendor vendor)
  {
	  //reqreslogger.info("[REQUEST]"+vendor.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<HashMap<String, Object>> photos = new ArrayList();
    try {
      photos = societyDao.getSocietyPhotos(vendor.getVendorid(),"vendor","VendorImages",photos);
    }
    catch (Exception localException) {}
    
    return photos;
  }
  
  @RequestMapping(value={"/getVendorCards"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<HashMap<String, Object>> getVendorCards(@ModelAttribute Vendor vendor)
  {
	  //reqreslogger.info("[REQUEST]"+vendor.toString());
	  
    SocietyDao societyDao = new SocietyDao();
    List<HashMap<String, Object>> photos = new ArrayList();
    try {
      photos = societyDao.getSocietyPhotos(vendor.getVendorid(),"vendorcards","VendorCards",photos);
    }
    catch (Exception localException) {}
    
    return photos;
  }
  
  
  @RequestMapping(value={"/uploadVendorPhoto"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public HashMap<String, Object> uploadVendorPhoto(MultipartHttpServletRequest request, HttpServletResponse response)
  {
	  //reqreslogger.info("[REQUEST]"+request.getFileNames());
	  
    byte[] bytes = null;
    Long size = null;
    String contentType = null;
    BufferedImage resizedImage = null;
    MultipartFile multipartFile = null;
    BufferedImage originalImage = null;
    String ogFilename = "";
    int type = 0;
    int vendorid = 0;
    try {
      multipartFile = request.getFile("file");
      vendorid = Integer.parseInt(request.getParameter("vendorid"));
      
      size = Long.valueOf(multipartFile.getSize());
      contentType = multipartFile.getContentType();
      InputStream stream = multipartFile.getInputStream();
      ogFilename = multipartFile.getOriginalFilename();
      bytes = IOUtils.toByteArray(stream);
      InputStream in = new ByteArrayInputStream(bytes);
      String newFileName = System.currentTimeMillis() + "-" + ogFilename;
      boolean flag = saveToFileSystem(newFileName, in,"VendorImages");
      
      //create and save a thumbnail
      InputStream inThumbs = new ByteArrayInputStream(bytes);
	  BufferedImage bImageFromConvert = ImageIO.read(inThumbs);
      byte[] thumb = resizeImageWithHint(bImageFromConvert,1);
      String newFn = newFileName.substring(0, newFileName.lastIndexOf("."))+"_thumbs"+newFileName.substring(newFileName.lastIndexOf("."));
	  saveToFileSystem(newFn,new ByteArrayInputStream(thumb),"VendorImages");
	  inThumbs.close();
	  
      if (flag) {
        DocumentDao ddao = new DocumentDao();
        ddao.savePhotoInfo("vendor", vendorid, newFileName, "VendorImages/" + newFileName, contentType,false);
      }
      
      originalImage = ImageIO.read(new ByteArrayInputStream(bytes));


    }
    catch (IOException e)
    {


      e.printStackTrace();
    }
    
    HashMap<String, Object> map = new HashMap();
    map.put("fileoriginalsize", size);
    map.put("contenttype", contentType);
    map.put("base64", new String(Base64Utils.encode(convertToBytes(originalImage))));
    
    return map;
  }
  
  
  @RequestMapping(value={"/uploadVendorCards"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public HashMap<String, Object> uploadVendorCards(MultipartHttpServletRequest request, HttpServletResponse response)
  {
	  //reqreslogger.info("[REQUEST]"+request.getFileNames());
	  
    byte[] bytes = null;
    Long size = null;
    String contentType = null;
    BufferedImage resizedImage = null;
    MultipartFile multipartFile = null;
    BufferedImage originalImage = null;
    String ogFilename = "";
    int type = 0;
    int vendorid = 0;
    try {
      multipartFile = request.getFile("file");
      vendorid = Integer.parseInt(request.getParameter("vendorid"));
      
      size = Long.valueOf(multipartFile.getSize());
      contentType = multipartFile.getContentType();
      InputStream stream = multipartFile.getInputStream();
      ogFilename = multipartFile.getOriginalFilename();
      bytes = IOUtils.toByteArray(stream);
      InputStream in = new ByteArrayInputStream(bytes);
      String newFileName = System.currentTimeMillis() + "-" + ogFilename;
      boolean flag = saveToFileSystem(newFileName, in,"VendorCards");
      
      
      //create and save a thumbnail
      InputStream inThumbs = new ByteArrayInputStream(bytes);
	  BufferedImage bImageFromConvert = ImageIO.read(inThumbs);
      byte[] thumb = resizeImageWithHint(bImageFromConvert,1);
      String newFn = newFileName.substring(0, newFileName.lastIndexOf("."))+"_thumbs"+newFileName.substring(newFileName.lastIndexOf("."));
	  saveToFileSystem(newFn,new ByteArrayInputStream(thumb),"VendorCards");
	  inThumbs.close();
	  
      if (flag) {
        DocumentDao ddao = new DocumentDao();
        ddao.savePhotoInfo("vendorcards", vendorid, newFileName, "VendorCards/" + newFileName, contentType,false);
      }
      
      originalImage = ImageIO.read(new ByteArrayInputStream(bytes));


    }
    catch (IOException e)
    {


      e.printStackTrace();
    }
    
    HashMap<String, Object> map = new HashMap();
    map.put("fileoriginalsize", size);
    map.put("contenttype", contentType);
    map.put("base64", new String(Base64Utils.encode(convertToBytes(originalImage))));
    
    return map;
  }
  
}