package com.lms.ctaa.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



/**
 * @Description 对于通过Excel、txt等文本文件进行的添加数据校验
 * @author zhengjiajia
 * @date 2017年4月18日
 * @version 1.0
 */
public class ExcelImportUtil {
	/**
	 * @Description:添加数据有效性检查
	 * @param sheet 要添加此检查的Sheet
	 * @param firstRow 开始行
	 * @param lastRow  结束行
 	 * @param firstCol 开始列
	 * @param lastCol  结束列
	 * @param explicitListValues  有效检查的下拉列表
	 * @throws IllegalArgumentException 如果传入的行或者列小于0(<0)或者结束行/列 比 开始行/列小  
	 */
	public static void setValidationData(Sheet sheet,int firstRow,int lastRow,int firstCol,int lastCol,String[] explicitListValues) throws IllegalArgumentException{
	 	if(firstRow <0 || lastRow <0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol){
	 		throw new IllegalArgumentException("Wrong Row or Column index : "+ firstRow+":"+lastRow+":"+firstCol+":"+lastCol);
	 	}
		
		if(sheet instanceof XSSFSheet){
			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheet);
			XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint)dvHelper.createExplicitListConstraint(explicitListValues);
			CellRangeAddressList addressList = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
			XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(dvConstraint,addressList);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			sheet.addValidationData(validation);
		} else if(sheet instanceof HSSFSheet){
			CellRangeAddressList addressList = new CellRangeAddressList(firstRow,lastRow,firstCol,lastRow);
			DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(explicitListValues);  
			DataValidation validation = new HSSFDataValidation(addressList,dvConstraint);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			sheet.addValidationData(validation);
		}
	}
	
	public static Workbook create(InputStream in) throws IOException,InvalidFormatException{
		if(!in.markSupported()){
			in = new PushbackInputStream(in,8);
		}
		if(POIFSFileSystem.hasPOIFSHeader(in)){
			return new HSSFWorkbook(in);
		}
		if(POIXMLDocument.hasOOXMLHeader(in)){
//			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}
	
	public static Map<String,Object> checkRtnPortFile(CommonsMultipartFile file,InputStream is,Workbook workbook,int sheetNum){
		Map<String,Object> messageMap = new HashMap<String,Object>();
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row row = sheet.getRow(0);//取头信息
		
		Sheet sheet_ = workbook.getSheetAt(1);
		Row row_ = sheet_.getRow(0);//取头信息
		
		//开始读取模板excel文件
		if(row == null){
			messageMap.put("status", "failure");
			messageMap.put("message", "请勿导入空模板!");
			return messageMap;
		}
		
		//开始读取模板excel文件
		if(row_ == null){
			messageMap.put("status", "failure");
			messageMap.put("message", "请勿导入空模板!");
			return messageMap;
		}
		
		boolean flag = false;
		//循环获取第一行的每列值进行验证
		for(int i=0;i<row.getPhysicalNumberOfCells();i++){
			String value = getCellFormatValue(row.getCell(i));
			if(StringUtils.isEmpty(value)){
				messageMap.put("status", "failure");
				messageMap.put("message", "导入模板不正确,请重新上传!");
				return messageMap;
			}
			if(i == 0){
				if(!value.equals("提单号")){
					flag = false;
				}
			}else if(i == 1){
				if(!value.equals("集装箱/散杂货")){
					flag = false;
				}
			}else if(i == 2){
				if(!value.equals("申报单位")){
					flag = false;
				}
			}else if(i == 3){
				if(!value.equals("货主单位")){
					flag = false;
				}
			}else if(i == 4){
				if(!value.equals("报关单号")){
					flag = false;
				}
			}else if(i == 5){
				if(!value.equals("主管地海关代码")){
					flag = false;
				}
			}else if(i == 6){
				if(!value.equals("卸货地代码")){
					flag = false;
				}
			}else if(i == 7){
				if(!value.equals("航班航次编号")){
					flag = false;
				}
			}else if(i == 8){
				if(!value.equals("航名")){
					flag = false;
				}
			}else if(i == 9){
				if(!value.equals("运输方式")){
					flag = false;
				}
			}else if(i == 10){
				if(!value.equals("运输工具")){
					flag = false;
				}
			}else if(i == 11){
				if(!value.equals("数据来源代码")){
					flag = false;
				}
			}else if(i == 12){
				if(!value.equals("进出口")){
					flag = false;
				}
			}else if(i == 13){
				if(!value.equals("总重量")){
					flag = false;
				}
			}else if(i == 14){
				if(!value.equals("件数")){
					flag = false;
				}
			}else if(i == 15){
				if(!value.equals("货物种类")){
					flag = false;
				}
			}
		}
		
		//循环获取第一行的每列值进行验证
		for(int i=0;i<row_.getPhysicalNumberOfCells();i++){
			String value = getCellFormatValue(row_.getCell(i));
			if(StringUtils.isEmpty(value)){
				messageMap.put("status", "failure");
				messageMap.put("message", "导入模板不正确,请重新上传!");
				return messageMap;
			}
			if(i == 0){
				if(!value.equals("提单号")){
					flag = false;
				}
			}else if(i == 1){
				if(!value.equals("运输车辆车牌号")){
					flag = false;
				}
			}else if(i == 2){
				if(!value.equals("前集装箱号")){
					flag = false;
				}
			}else if(i == 3){
				if(!value.equals("前集装箱类型")){
					flag = false;
				}
			}else if(i == 4){
				if(!value.equals("前集装箱重量")){
					flag = false;
				}
			}else if(i == 5){
				if(!value.equals("后集装箱号")){
					flag = false;
				}
			}else if(i == 6){
				if(!value.equals("后集装箱类型")){
					flag = false;
				}
			}else if(i == 7){
				if(!value.equals("后集装箱重量")){
					flag = false;
				}
			}else if(i == 8){
				if(!value.equals("车架号")){
					flag = false;
				}
			}else if(i == 9){
				if(!value.equals("车架类型")){
					flag = false;
				}
			}else if(i == 10){
				if(!value.equals("车架重")){
					flag = false;
				}
			}else if(i == 11){
				if(!value.equals("备注")){
					flag = false;
				}
			} 
		}
		if(flag){
			messageMap.put("status", "failure");
			messageMap.put("message", "导入模板不正确，请重新上传!");
			return messageMap;
		}
		messageMap.put("status", "success");
		messageMap.put("message", "验证通过!");
		return messageMap;
		
	}
	
	private static String getCellFormatValue(Cell cell){
		String cellValue = "";
		if(cell != null){
			//判断当前Cell的类型
			switch (cell.getCellType()){
			//如果当前Cell的Type为NUMERIC数字
			case Cell.CELL_TYPE_NUMERIC:
				//判断当前的cell是否为Date
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellValue = sdf.format(date);
				}else{
					//取得当前Cell的数值
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
				//方程式
			case HSSFCell.CELL_TYPE_FORMULA:{
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			}
			//如果当前Cell的Type为字符串
			case HSSFCell.CELL_TYPE_STRING:
				//取得当前Cell字符串
				cellValue = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				break;
			//默认的Cell值空值
				default:
					cellValue = "";
			}
		}else{
			cellValue = "";
		}
		return cellValue;
	}
	
	
}

 