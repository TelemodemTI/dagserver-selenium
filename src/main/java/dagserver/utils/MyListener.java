package dagserver.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class MyListener implements ISuiteListener {

	
	protected String reportpath;
	protected String evidencepath;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	protected SimpleDateFormat sdfst = new SimpleDateFormat("yyyyMMdd");
	protected Workbook workbook;
	protected XWPFDocument document;
	protected Sheet sheet;
	
	@Override
	public void onStart(ISuite suite2) {
		try {
			
			this.reportpath = "C:\\tmp\\test\\report_"+sdf.format(new Date())+".xls";
			this.evidencepath = "C:\\tmp\\test\\evidence_"+sdf.format(new Date())+".doc";
			FileInputStream inputStream = new FileInputStream(new File("./src/main/resources/template_evidence_report.xlsx"));
			workbook = WorkbookFactory.create(inputStream);
			String titleStr = "Testing Cycle " + sdfst.format(new Date());
			
			document = new XWPFDocument();
			XWPFParagraph title = document.createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun titleRun = title.createRun();
			titleRun.setText(titleStr);
			titleRun.setBold(true);
			titleRun.setFontFamily("Courier");
			titleRun.setFontSize(26);
			
			
			workbook.setSheetName(0, "Testing Cycle " + sdfst.format(new Date()));
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onFinish(ISuite suite1) {
		try {
			List<Map<String,String>> list = (List<Map<String, String>>) suite1.getAttribute("dataOutput");
			for (Iterator<Map<String, String>> iterator = list.iterator(); iterator.hasNext();) {
				Map<String, String> map = iterator.next();
				String name = map.get("name"); 
				String classname = map.get("classname"); 
				String status = map.get("status");
				String screens = map.get("screenshoot");
				this.writeEvidenceXls(name, classname, status);
				this.writeEvidenceDoc(name, classname, status, screens);
				new File(map.get("screenshoot")).delete();
			}
			FileOutputStream outputStream = new FileOutputStream(new File(reportpath));
			FileOutputStream evidenceStream = new FileOutputStream(new File(evidencepath));
		    document.write(evidenceStream);
			workbook.write(outputStream);
		    workbook.close();	
		    document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeEvidenceDoc(String name, String classname, String status, String screenfile) throws Exception {
        XWPFTable table = document.createTable();
        XWPFTableRow row1 = table.getRow(0);
        row1.getCell(0).setText("Test Evidence of test:");
        row1.addNewTableCell().setText(name);
        XWPFTableRow row2 = table.createRow();
        row2.getCell(0).setText("Status of test:");
        row2.getCell(1).setText(status);
        XWPFTableRow row3 = table.createRow();
        row3.getCell(0).setText("Suite:");
        row3.getCell(1).setText(classname);

        XWPFParagraph para1 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.BOTH);
		String string1 = "The screenshot of the final screen as test evidence is as follows:";
		XWPFRun para1Run = para1.createRun();
		para1Run.setText(string1);
		
        
		try {
	        XWPFParagraph image = document.createParagraph();
	    	image.setAlignment(ParagraphAlignment.LEFT);
	    	XWPFRun imageRun = image.createRun();
	    	imageRun.setTextPosition(20);
	    	File imagescreen = new File(screenfile);
	    	Path imagePath = imagescreen.toPath();
	    	imageRun.addPicture(Files.newInputStream(imagePath),
	    	XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),Units.toEMU(800), Units.toEMU(600));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
		
			
	}
	public void writeEvidenceXls(String name, String classname, String status) throws IOException {
		Integer rowNum = 0;
        while (sheet.getRow(rowNum) != null) {
            rowNum++;
        }
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(rowNum);
        row.createCell(1).setCellValue(name);
        row.createCell(2).setCellValue(classname);
        row.createCell(3).setCellValue(sdfst.format(new Date()));
        row.createCell(4).setCellValue(status);
        row.createCell(6).setCellValue("Evidence in Doc File");
	}
	
	
}
