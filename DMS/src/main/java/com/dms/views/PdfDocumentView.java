package com.dms.views;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.dms.beans.Files;
import com.dms.dao.DocumentDao;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import uk.co.mmscomputing.application.imageviewer.FtpWrapper;

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
			
			if (ftp.connectAndLogin(hostDomain, Id, Password)) {
				for(Files files : docs){
					String path = files.getFilepath();
					String workDir = path.substring(0, path.lastIndexOf("/")+1);
					ftp.changeWorkingDirectory(workDir);
					
					FileOutputStream fos = new FileOutputStream("img.jpg");
					ftp.retrieveFile(path,fos);
					Image image1 = Image.getInstance("img.jpg");
				
					int indentation = 0;
					float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
				               - document.rightMargin() - indentation) / image1.getWidth()) * 100;
					image1.scalePercent(scaler);
					
			        document.add(image1);
			        fos.close();
				}
			}
			
			/*Table table = new Table(2);
			table.addCell("Month");
			table.addCell(params.get("documentId"));
			document.add(table);*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
