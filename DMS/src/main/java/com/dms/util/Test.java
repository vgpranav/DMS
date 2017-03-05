package com.dms.util;

import java.io.FileOutputStream;
import java.net.URL;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.LoggerFactory;

import com.dms.controller.ViewController;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import uk.co.mmscomputing.application.imageviewer.FtpWrapper;

public class Test {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ViewController.class);

	public static void main(String args[]){
		Document document = new Document(PageSize.A4);
		
		try{
			String abc = "/DMS/8-4-4-1488707397575.jpg";
			System.out.println(abc.substring(0, abc.lastIndexOf("/")+1));
			
			/*
			 PdfWriter.getInstance(document,
		                new FileOutputStream("Image1.pdf"));
		        document.open();
		        
		        FtpWrapper ftp = new FtpWrapper();
		    	String hostDomain = ftp.getServerName();
				String Id = ftp.getUsername();
				String Password = ftp.getPassword();
		        
				if (ftp.connectAndLogin(hostDomain, Id, Password)) {
					
					ftp.changeWorkingDirectory("DMS");
					FTPFile[] files = ftp.listFiles();
					for(FTPFile f1 : files){
						System.out.println(f1.getName());
						FileOutputStream fos = new FileOutputStream("img.jpg");
						ftp.retrieveFile(f1.getName(), fos);
						Image image1 = Image.getInstance("img.jpg");
						
						int indentation = 0;
						float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
					               - document.rightMargin() - indentation) / image1.getWidth()) * 100;
						image1.scalePercent(scaler);
				        document.add(image1);
				        fos.close();
					}
					 	
					Image image1 = Image.getInstance(new URL(ftpUrl));
			        document.add(image1);
				}
				  
		        document.close();
		*/}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
