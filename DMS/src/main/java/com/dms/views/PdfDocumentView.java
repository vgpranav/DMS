package com.dms.views;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.dms.dao.DocumentDao;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfDocumentView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		Map<String,String> params = (Map<String,String>) model.get("params");

		DocumentDao ddao = new DocumentDao();
		//Set<String>
		
		
		Table table = new Table(2);
		table.addCell("Month");
		table.addCell(params.get("documentId"));

		document.add(table);
	}
}
