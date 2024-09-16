package com.doritech.api.Common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.xml.bind.DatatypeConverter;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

@Service
public class General {

	
	public String checkNull(String s) {
		if (s == null)
			return "";
		else
			return s;
	}

	public int checkZero(int s) {
		if (s == 0)
			return 0;
		else
			return s;
	}
	
	 public String checkStatus(String s) {
		 
		 if(s.equals("A")) {
			 return "Activated";
		 }
		 else if(s.equals("D")) {
			 return "Deactivated";
		 }
		 else {
			 return s;
		 }
			 
	 }
	 
	 public Timestamp getCurrentTimeStamp() {
		 return new Timestamp(System.currentTimeMillis());
	 }
	 
	 public Date getCurrentDate() {
		 Long millis = System.currentTimeMillis();
		 return new Date(millis);
	 }
	
	 public String getCellValue(Cell nextCell) {

	      String cellType = nextCell.getCellTypeEnum().toString();
	      System.out.println("cellType:="+cellType);
	      int ci = nextCell.getColumnIndex();
	      String pattern = "####";
	      DecimalFormat decimalFormat = new DecimalFormat(pattern);
	      switch (cellType) {
	      
	      case "NUMERIC":
	         return nextCell.getStringCellValue();
	      case "STRING":
	         return "" + nextCell.getStringCellValue() == "null" ? "" : nextCell.getStringCellValue();
	      case "BLANK":
	         return ""; 
	      default:
	         return "";
	      }
	   }
	 
	 public byte[] base64toByte(String base64String) throws Exception {
			byte[] bytes = null;
			try {
//				bytes = Base64.getDecoder().decode(base64String);
				bytes = DatatypeConverter.parseBase64Binary(base64String.split(",")[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bytes;
		} 
}
