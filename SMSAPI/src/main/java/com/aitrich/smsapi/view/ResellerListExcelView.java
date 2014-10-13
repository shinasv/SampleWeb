package com.aitrich.smsapi.view;



import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.aitrich.smsapi.model.Reseller;

public class ResellerListExcelView extends AbstractExcelView {

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container

		// create a new Excel sheet
		HSSFSheet excelSheet = workbook.createSheet("Reseller List");
		setExcelHeader(excelSheet);

		@SuppressWarnings("unchecked")
		List<Reseller> resellerList = (List<Reseller>) model
				.get("resellerList");
		setExcelRows(excelSheet, resellerList);
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("ID");
		excelHeader.createCell(1).setCellValue("Name");
		excelHeader.createCell(2).setCellValue("Address");
		excelHeader.createCell(3).setCellValue("Email");
		excelHeader.createCell(4).setCellValue("Phone");
		excelHeader.createCell(5).setCellValue("Secondary Phone");
		excelHeader.createCell(6).setCellValue("website");
	}

	public void setExcelRows(HSSFSheet excelSheet, List<Reseller> resellerList) {
		int record = 1;
		for (Reseller reseller : resellerList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(reseller.getId());
			excelRow.createCell(1).setCellValue(reseller.getName());
			excelRow.createCell(2).setCellValue(reseller.getAddress());
			excelRow.createCell(3).setCellValue(reseller.getEmail());
			excelRow.createCell(4).setCellValue(reseller.getPhone());
			excelRow.createCell(5).setCellValue(reseller.getSecondaryPhone());
			excelRow.createCell(6).setCellValue(reseller.getWebsite());

		}
	}

}
