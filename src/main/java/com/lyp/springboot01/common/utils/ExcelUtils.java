package com.lyp.springboot01.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具类
 */
public class ExcelUtils {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
      "0");// 格式化 number为整

  private static final DecimalFormat DECIMAL_FORMAT_PERCENT = new DecimalFormat(
      "##.00%");//格式化分比格式，后面不足2位的用0补齐

//	private static final DecimalFormat df_per_ = new DecimalFormat("0.00%");//格式化分比格式，后面不足2位的用0补齐,比如0.00,%0.01%

//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); // 格式化日期字符串

  private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat
      .getInstance("yyyy/MM/dd");

  private static final DecimalFormat DECIMAL_FORMAT_NUMBER = new DecimalFormat(
      "0.00E000"); //格式化科学计数器

  private static final Pattern POINTS_PATTERN = Pattern
      .compile("0.0+_*[^/s]+"); //小数匹配

  public static void main(String[] args) {
    String filePath = "C:\\Users\\Administrator\\Desktop\\temp.xlsx";
    getDataFromExcel(filePath);
  }

  /**
   * 读取出filePath中的所有数据信息
   *
   * @param filePath excel文件的绝对路径
   */

  public static void getDataFromExcel(String filePath) {
    //String filePath = "E:\\123.xlsx";

    //判断是否为excel类型文件
    if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
      System.out.println("文件不是excel类型");
    }

    FileInputStream fis = null;
    Workbook wookbook = null;

    try {
      //获取一个绝对地址的流
      fis = new FileInputStream(filePath);
      if (filePath.endsWith(".xls")) {
        //2003版本的excel，用.xls结尾
        wookbook = new HSSFWorkbook(fis);//得到工作簿
      }

      if (filePath.endsWith(".xlsx")) {
        //2007版本的excel，用.xlsx结尾
        wookbook = new XSSFWorkbook(fis);//得到工作簿
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    //得到一个工作表
    Sheet sheet = wookbook.getSheetAt(0);

    //获得表头
    Row rowHead = sheet.getRow(0);

    //判断表头是否正确
    if (rowHead.getPhysicalNumberOfCells() != 3) {
      System.out.println("表头的数量不对!");
    }

    //获得数据的总行数
    List<List<Object>> allRows = new ArrayList<>();
    int totalRowNum = sheet.getLastRowNum();
    for (int i = 1; i <= totalRowNum; i++) {
      Row row = sheet.getRow(i);
      int columns = row.getLastCellNum();
      List<Object> rowList = new ArrayList<>();
      for (int j = 0; j < columns; j++) {
        Cell cell = row.getCell(j);
        Object value = getCellValue(cell);
        rowList.add(value);
      }

      allRows.add(rowList);
    }

    System.out.println(allRows);
  }


  /**
   * 获取excel 单元格数据
   */
  public static String getCellValue(Cell cell) {
    Object value = null;
    switch (cell.getCellTypeEnum()) {
      case _NONE:
        break;
      case STRING:
        value = cell.getStringCellValue();
        break;
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) { //日期
          value = FAST_DATE_FORMAT.format(DateUtil
              .getJavaDate(cell.getNumericCellValue()));//统一转成 yyyy/MM/dd
        } else if ("@".equals(cell.getCellStyle().getDataFormatString())
            || "General".equals(cell.getCellStyle().getDataFormatString())
            || "0_ ".equals(cell.getCellStyle().getDataFormatString())) {
          //文本  or 常规 or 整型数值
          value = DECIMAL_FORMAT.format(cell.getNumericCellValue());
        } else if (POINTS_PATTERN
            .matcher(cell.getCellStyle().getDataFormatString())
            .matches()) { //正则匹配小数类型
          value = cell.getNumericCellValue();  //直接显示
        } else if ("0.00E+00"
            .equals(cell.getCellStyle().getDataFormatString())) {//科学计数
          value = cell.getNumericCellValue();  //待完善
          value = DECIMAL_FORMAT_NUMBER.format(value);
        } else if ("0.00%"
            .equals(cell.getCellStyle().getDataFormatString())) {//百分比
          value = cell.getNumericCellValue(); //待完善
          value = DECIMAL_FORMAT_PERCENT.format(value);
        } else if ("# ?/?"
            .equals(cell.getCellStyle().getDataFormatString())) {//分数
          value = cell.getNumericCellValue(); ////待完善
        } else { //货币
          value = cell.getNumericCellValue();
          value = DecimalFormat.getCurrencyInstance().format(value);
        }
        break;
      case BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case BLANK:
        //value = ",";
        break;
      default:
        value = cell.toString();
    }
    return String.valueOf(value);
  }

}
