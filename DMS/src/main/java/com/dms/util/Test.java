package com.dms.util;

import java.io.FileOutputStream;
import java.net.URL;

import org.slf4j.LoggerFactory;

import com.dms.controller.ViewController;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class Test {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ViewController.class);

	public static void main(String args[]){
		Document document = new Document();
		
		try{
			 PdfWriter.getInstance(document,
		                new FileOutputStream("Image.pdf"));
		        document.open();

		        Image image1 = Image.getInstance("17031488512275085-1.jpg");
		        document.add(image1);

		        
		            String imageUrl = "ftp://127.0.0.1/DMS/1-1-1-1488607148314.jpg";

		            Image image2 = Image.getInstance(new URL(imageUrl));
		        document.add(image2);

		        document.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
