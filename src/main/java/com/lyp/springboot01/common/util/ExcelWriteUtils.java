package com.lyp.springboot01.common.util;

import com.lyp.springboot01.common.exception.MyException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class ExcelWriteUtils {


  public static <T> void createWorkbookByBeans(OutputStream os,
      String excelType,
      List<String> heads, List<String> fields, List<T> beans, List<String>
      mustBeStrings) throws MyException {

    Workbook workbook = createWorkbookByBeans(excelType, heads, fields, beans,
        mustBeStrings);
    try {
      workbook.write(os);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        os.close();
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }


  private static <T> Workbook createWorkbookByBeans(String excelType,
      List<String> heads, List<String> fields, List<T> beans, List<String>
      mustBeStrings) throws MyException {

    Workbook workbook = initWorkBook(excelType);
    Sheet sheet = workbook.createSheet();
    setSheetHead(sheet, heads);

    try {
      //添加内容
      int rowNum = 1;
      for (T bean : beans) {
        //在索引1的位置创建行，即第二行开始
        Row row = sheet.createRow(rowNum++);
        int col = 0;
        for (String field : fields) {
          Cell cell = row.createCell(col++);
          cell.setCellType(CellType.STRING);

          Object val = PropertyUtils.getProperty(bean, field);
          if (val == null) {
            continue;
          }

          if (val instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cell.setCellValue(sdf.format(val));
          }

          //判断是否要转成字符串
          String valStr = String.valueOf(val).trim();
          if (isConvertToString(valStr, field, mustBeStrings)) {
            cell.setCellValue(valStr);
            continue;
          }

          if (NumberUtils.isParsable(valStr)) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(NumberUtils.toDouble(valStr));
          }
        }
      }
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
      log.error(
          "Get value from object failed by PropertyUtils.getProperty : {}.",
          e.getMessage());
    }

    return workbook;
  }

  private static boolean isConvertToString(String valStr, String field,
      List<String> mustBeStrings) {
    if (mustBeStrings != null && mustBeStrings.contains(field)) {
      return true;
    }

    if (valStr.startsWith("0") && valStr.length() > 1) {
      return true;
    }

    return !NumberUtils.isParsable(valStr);
  }

  /**
   * 设置excel表头
   */
  private static void setSheetHead(Sheet sheet, List<String> heads) {
    Row firstRow = sheet.createRow(0);
    int col = 0;
    for (String title : heads) {
      Cell cell = firstRow.createCell(col++);
      cell.setCellValue(title);
    }
  }


  private static Workbook initWorkBook(String excelType) throws MyException {
    Workbook workbook;

    if ("xls".equals(excelType)) {
      workbook = new HSSFWorkbook();
    } else if ("xlsx".equals(excelType)) {
      workbook = new XSSFWorkbook();
    } else {
      throw new MyException("Excel type is illegal: " + excelType);
    }

    return workbook;

  }

}
