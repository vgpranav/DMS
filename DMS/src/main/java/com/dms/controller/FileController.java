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
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.beans.Society;
import com.dms.dao.DocumentDao;
import com.dms.dao.SocietyDao;

import uk.co.mmscomputing.application.imageviewer.FtpWrapper;


@Controller
public class FileController
{
  private static final int IMG_WIDTH = 100;
  private static final int IMG_HEIGHT = 100;
  private static final String DIR = "SocietyImages";
  
  public FileController() {}
  
  @RequestMapping(value={"/uploadSocietyPhoto"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  @ResponseBody
  public HashMap<String, Object> echoFile(MultipartHttpServletRequest request, HttpServletResponse response)
  {
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
      boolean flag = saveToFileSystem(newFileName, in);
      
      if (flag) {
        DocumentDao ddao = new DocumentDao();
        ddao.savePhotoInfo("society", societyid, newFileName, "SocietyImages/" + newFileName, contentType);
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
  
  private BufferedImage resizeImageWithHint(BufferedImage originalImage, int type)
  {
    BufferedImage resizedImage = new BufferedImage(100, 100, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, 100, 100, null);
    g.dispose();
    g.setComposite(AlphaComposite.Src);
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_RENDERING, 
      RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
      RenderingHints.VALUE_ANTIALIAS_ON);
    return resizedImage;
  }
  
  private boolean saveToFileSystem(String FileName, InputStream in) {
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
        
        boolean dirExists = ftp.changeWorkingDirectory("SocietyImages");
        
        if (!dirExists) {
          ftp.makeDirectory("SocietyImages");
          ftp.changeWorkingDirectory("SocietyImages");
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
  

  @RequestMapping(value={"/getSocietyPhotos"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<HashMap<String, Object>> getSocietyPhotos(@ModelAttribute Society society)
  {
    SocietyDao societyDao = new SocietyDao();
    List<HashMap<String, Object>> photos = new ArrayList();
    try {
      photos = societyDao.getSocietyPhotos(society.getSocietyid(), photos);
    }
    catch (Exception localException) {}
    
    return photos;
  }
}