package com.dms.views;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.dms.beans.Files;
import com.dms.dao.DocumentDao;
import com.dms.util.FtpWrapper;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.mchange.lang.StringUtils;


public class PdfDocumentView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		List<Files> docs = null;
		try{
			Map<String,String> params = (Map<String,String>) model.get("params");
			DocumentDao ddao = new DocumentDao();
			docs = ddao.getDocPathsByDocId(params.get("documentId"),docs);
			document.setPageSize(PageSize.A4);
			
			FtpWrapper ftp = new FtpWrapper();
	    	String hostDomain = ftp.getServerName();
			String Id = ftp.getUsername();
			String Password = ftp.getPassword();
			List<String> generatedFiles = new ArrayList<String>();
			
			if (ftp.connectAndLogin(hostDomain, Id, Password)) {
				for(Files files : docs){
					String path = files.getFilepath();
					String workDir = path.substring(0, path.lastIndexOf("/")+1);
					ftp.changeWorkingDirectory(workDir);
					
					String ranfileName = RandomStringUtils.randomAlphanumeric(10)+".jpg";
					
					
					File out = new File(ranfileName);
					
					FileOutputStream fos = new FileOutputStream(out);
					ftp.retrieveFile(path,fos);
					Image image1 = Image.getInstance(ranfileName);
				
					int indentation = 0;
					float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
				               - document.rightMargin() - indentation) / image1.getWidth()) * 100;
					image1.scalePercent(scaler);
					
			        document.add(image1);
			        fos.close();
			        
					System.out.println("ranfileName ::"+out.getAbsolutePath());

			        generatedFiles.add(out.getAbsolutePath());
				}
			}
			
			/*Table table = new Table(2);
			table.addCell("Month");
			table.addCell(params.get("documentId"));
			document.add(table);*/
			
			/*for(String filename : generatedFiles){
				File f1 = new File(filename);
				if(f1.exists())
					f1.delete();
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
