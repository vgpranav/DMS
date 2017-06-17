package com.dms.views;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.dms.beans.Files;
import com.dms.beans.GenericBean;
import com.dms.dao.DocumentDao;
import com.dms.util.FtpWrapper;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PdfDocumentView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		List<Files> docs = null;
		List<GenericBean> data = null;
		try{
			Map<String,String> params = (Map<String,String>) model.get("params");
			DocumentDao ddao = new DocumentDao();
			docs = ddao.getDocPathsByDocId(params.get("documentId"),docs);
			document.setPageSize(PageSize.A4);
			
			//Uncomment to add info to pdf
			/*data = ddao.getdisplayDataByDocId(params.get("documentId"), data);
			
			
			for(GenericBean gbean : data){
				PdfPTable  table = new PdfPTable(2);
				PdfPCell c1 = new PdfPCell(new Phrase(gbean.getFieldname()));
				c1.setPadding(10);
				c1.setBackgroundColor(Color.lightGray);
				table.addCell(c1);
				
				
				PdfPCell c2 = new PdfPCell(new Phrase(gbean.getFieldvalue()));
				c1.setPadding(10);
				table.addCell(c2);
				 
				document.add(table);
			}*/
			
			
			FtpWrapper ftp = new FtpWrapper();
	    	String hostDomain = ftp.getServerName();
			String Id = ftp.getUsername();
			String Password = ftp.getPassword();
			List<String> generatedFiles = new ArrayList<String>();
			boolean wdFlag=false;
			if (ftp.connectAndLogin(hostDomain, Id, Password)) {
				for(Files files : docs){
					String path = files.getFilepath();
					String workDir = path.substring(0, path.lastIndexOf("/")+1);
					
					if(!wdFlag){
						ftp.changeWorkingDirectory(workDir);
						wdFlag=true;
					}
					//String wd = ftp.printWorkingDirectory();
					
					InputStream stream = ftp.retrieveFileStream(files.getFilename());
					
					if(stream!=null){
						byte[] bytes = IOUtils.toByteArray(stream);
						Image image1 = Image.getInstance(bytes);
						int indentation = 0;
						float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
					               - document.rightMargin() - indentation) / image1.getWidth()) * 100;
						image1.scalePercent(scaler);
				        document.add(image1);
					}
					ftp.completePendingCommand();
			        
			        
			        /*fos.close();*/
			        
					/*System.out.println("ranfileName ::"+out.getAbsolutePath());*/

			        /*generatedFiles.add(out.getAbsolutePath());*/
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
		}finally{
			document.close();
		}
	}
}
