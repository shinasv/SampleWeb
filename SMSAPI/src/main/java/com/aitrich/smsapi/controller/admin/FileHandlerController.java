package com.aitrich.smsapi.controller.admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.Role;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.RoleService;
import com.aitrich.smsapi.services.UserAccountService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
@RequestMapping("/admin")
public class FileHandlerController {

	@Autowired
	private ResellerService resellerService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserAccountService userAccountService;

	private static final Logger logger = LoggerFactory
			.getLogger(FileHandlerController.class);

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {

		List<Reseller> resellerList = resellerService.findAll();

		return new ModelAndView("ResellerListExcel", "resellerList",
				resellerList);
	}

	/**
	 * Upload single file using Spring Controller
	 */

	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public @ResponseBody
	List<String> save(@RequestParam("files") MultipartFile files, Model map) {
		String returnMsg = "Saved Succesfully";
		List<String> stringList = new ArrayList<String>();
		String fileName = files.getOriginalFilename();
		map.addAttribute("files", fileName);

		String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		String excel = "xlsx";
		if (fileName.isEmpty()) {
			returnMsg = "Enter an excel file(.xlsx)";
			stringList.add(returnMsg);
			return stringList;
		}
		if (!extension.equals(excel)) {

			returnMsg = "Enter an excel file(.xlsx)";
			stringList.add(returnMsg);
			return stringList;
		}

		else {
			try {

				InputStream input = files.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(input);
				XSSFSheet sheet = workbook.getSheetAt(0);

				Iterator<Row> ite = sheet.rowIterator();

				while (ite.hasNext()) {
					Reseller reseller = new Reseller();

					Row row = ite.next();
					
					{
						reseller.setName(row.getCell(0).getStringCellValue());
						reseller.setAddress(row.getCell(1).getStringCellValue());
						reseller.setEmail(row.getCell(2).getStringCellValue());
						long number = (long) row.getCell(3)
								.getNumericCellValue();
						reseller.setPhone(String.valueOf(number));
						number = (long) row.getCell(4).getNumericCellValue();
						reseller.setSecondaryPhone(String.valueOf(number));
						reseller.setWebsite(row.getCell(5).getStringCellValue());
						UserAccount userAccount = new UserAccount();
						userAccount.setUserName(row.getCell(6)
								.getStringCellValue());
						userAccount.setPassword(row.getCell(7)
								.getStringCellValue());

						int returnStatus = validateExcelFileCell(reseller,
								userAccount);
						if (returnStatus == 0) {
							Role role = roleService
									.findRoleByRoleName("ROLE_RESELLER");
							List<Role> roles = new ArrayList<Role>();
							roles.add(role);

							userAccount.setRoles(roles);

							resellerService.resellerRegistration(reseller,
									userAccount);
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " => successfully added ";
							stringList.add(returnMsg);

						}

						if (returnStatus == 1) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " => Email already exist ";
							stringList.add(returnMsg);
						}

						if (returnStatus == 2) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " => Email & Phone no. already exist";
							stringList.add(returnMsg);
						}

						if (returnStatus == 3) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :"
									+ rawnumber
									+ " => Email,Phone no. & User Name already exist";
							stringList.add(returnMsg);
						}
						if (returnStatus == 4) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " =>  Phone no  already exist ";
							stringList.add(returnMsg);
						}
						if (returnStatus == 5) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " =>Phone no & User Name already exist ";
							stringList.add(returnMsg);
						}
						if (returnStatus == 6) {
							int rawnumber = row.getRowNum();
							rawnumber = rawnumber + 1;
							returnMsg = "Row No :" + rawnumber
									+ " => User Name already exist ";
							stringList.add(returnMsg);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return stringList;

	}

	private int validateExcelFileCell(Reseller reseller, UserAccount userAccount) {
		// TODO Auto-generated method stub

		Reseller reseller1 = resellerService.findResellerByEmail(reseller
				.getEmail());
		if (reseller1 != null
				&& (reseller.getEmail() != null || !reseller.getEmail().equals(
						reseller1.getEmail()))) {
			reseller1 = resellerService
					.findResellerByPhone(reseller.getPhone());
			if (reseller1 != null
					&& (reseller.getPhone() != null || !reseller.getPhone()
							.equals(reseller1.getPhone()))) {
				UserAccount userAccount1 = userAccountService
						.findUserByUserName(userAccount.getUserName());
				if (userAccount1 != null
						&& (userAccount.getUserName() != null || !userAccount
								.getUserName().equals(
										userAccount1.getUserName()))) {
					return 3;

				}
				return 2;
			}

			return 1;
		}

		reseller1 = resellerService.findResellerByPhone(reseller.getPhone());
		if (reseller1 != null
				&& (reseller.getPhone() != null || !reseller.getPhone().equals(
						reseller1.getPhone()))) {
			UserAccount userAccount1 = userAccountService
					.findUserByUserName(userAccount.getUserName());
			if (userAccount1 != null
					&& (userAccount.getUserName() != null || !userAccount
							.getUserName().equals(userAccount1.getUserName()))) {
				return 5;

			}
			return 4;
		}

		UserAccount userAccount1 = userAccountService
				.findUserByUserName(userAccount.getUserName());
		if (userAccount1 != null
				&& (userAccount.getUserName() != null || !userAccount
						.getUserName().equals(userAccount1.getUserName()))) {
			return 6;

		}

		return 0;

	}
}
