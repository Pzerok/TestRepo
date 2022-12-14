package com.pdf.view;

import java.io.FileInputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.pdf.AbstractITextPdfView;

//import com.lowagie.text.Cell;
//import com.lowagie.text.Document;
//import com.lowagie.text.Font;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Table;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.PdfWriter;
@Component
public class PdfDownView extends AbstractITextPdfView {

	//첫번째 매개변수가 Controller가 넘겨준 데이터 
	//두번째 매개변수는 출력할 문서
	@SuppressWarnings({ "static-access", "deprecation", "unchecked" })
	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 PdfWriter.getInstance(document, response.getOutputStream());
	        String fileName = String.valueOf(model.get("fileName"));
	         
	        // 파일 다운로드 설정
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setContentType("application/pdf");
	         
	        // Document 오픈
	        document.open();
	        
	        // CSS
	        CSSResolver cssResolver = new StyleAttrCSSResolver();
	        CssFile cssFile = XMLWorkerHelper.getInstance().getCSS(new FileInputStream(PdfDownView.class.getClassLoader().getResource("pdf.css").getPath()));
	        cssResolver.addCss(cssFile);
	             
	        // HTML, 폰트 설정
	        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	        fontProvider.register(PdfDownView.class.getClassLoader().getResource("malgun.ttf").getPath(), "MalgunGothic"); // MalgunGothic은 alias,
	        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	         
	        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	         
	        // Pipelines
	        ElementList elements = new ElementList();
	        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
	        HtmlPipeline html = new HtmlPipeline(htmlContext, end);
	        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	        
	        String htmlStr0 = "<html><head><body style='font-family: MalgunGothic;'>"
	                + "<p>PDF 안에sdf 들어갈 내용입니다.</p>"
	                + "<div>PDF 안에sdf 들어갈 내용입니다.</div>"
	                + "<div style='text-align:center; font-size:30px;'; ><h3>한글sdf, English, 漢字.</h3></div>"
	                + "</body></head></html>";
	        
	        XMLWorker worker = new XMLWorker(css, true);
	        XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
	        
	        for (int i =0 ; i <= 1 ; i++) {
	            // 폰트 설정에서 별칭으로 줬던 "MalgunGothic"을 html 안에 폰트로 지정한다.
	            StringReader strReader;
	            
	            strReader = new StringReader(htmlStr0);
	            xmlParser.parse(strReader);
	            PdfPTable table = new PdfPTable(1);
	            PdfPCell cell = new PdfPCell();
	            
	            for (Element element : elements) {
	                cell.addElement(element);
	            }
	            table.addCell(cell);
	            document.add(table);
	            document.newPage();
	        }
	        
	        document.close();
	        writer.close();
	}

}