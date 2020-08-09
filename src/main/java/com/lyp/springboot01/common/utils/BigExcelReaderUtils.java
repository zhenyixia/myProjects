package com.lyp.springboot01.common.utils;

import com.lyp.springboot01.common.constant.Common;
import com.lyp.springboot01.common.exception.MyException;
import com.monitorjbl.xlsx.StreamingReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.commons.collections4.CollectionUtils;

@Slf4j
public class BigExcelReaderUtils {

  private BigExcelReaderUtils() {

  }

  /**
   * 一次读取缓存到内存的行数
   */
  private static int cacheRows = 300;

  /**
   * 缓存到内存的字节数
   */
  private static int byteNum = 1024 * 4;

  public static List<List<String>> readBigExcel(String filePath, int sheetNo,
      String[] effectTitleColumns, String[] partEffectColumns, String[]
      requiredColumns) throws MyException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new MyException("文件路径不存在");
    }

    String name = file.getName();
    if (!name.endsWith(Common.EXCEL_XLSX)) {
      throw new MyException("Excel 必须是.xlsx格式");
    }
    try (FileInputStream in = new FileInputStream(filePath)) {
      return readExcel(in, sheetNo, effectTitleColumns, partEffectColumns,
          requiredColumns);
    } catch (IOException e) {
      throw new MyException("Excel文件没有找到，或者文件异常");
    }


  }

  private static List<List<String>> readExcel(FileInputStream in, int sheetNo,
      String[] effectColumns, String[] partEffectColumns,
      String[] requiredColumns) throws MyException {
    log.info("Begin to read big excel.");

    if (effectColumns == null || effectColumns.length == 0) {
      throw new MyException("Effective title columns is null.");
    }

    boolean hasNotReadTitle = true;

    Workbook wk = StreamingReader.builder().rowCacheSize(cacheRows)
        .bufferSize(byteNum).open(in);

    int effectColumnSize = effectColumns.length;
    if (partEffectColumns != null) {
      effectColumnSize += partEffectColumns.length;
    }

    List<List<String>> rows = new ArrayList<>();
    //真正有效的列坐标数组
    List<Integer> effectColumnsIndexes = new ArrayList<>(effectColumnSize);

    //必填的列坐标list
    List<Integer> requiredIndexes = null;
    List<String> requiredColumnList = null; //必填列list
    if (requiredColumns != null && requiredColumns.length > 0) {
      requiredColumnList = Arrays.asList(requiredColumns);
      requiredIndexes = new ArrayList<>(requiredColumns.length);
    }

    Sheet sheet = wk.getSheetAt(sheetNo);
    int rowNum = 1;
    for (Row row : sheet) {
      //没有读取过标题行，则设置
      if (hasNotReadTitle) {
        setEffectColumnIndexes(row, effectColumnsIndexes, effectColumns,
            partEffectColumns, requiredColumnList, requiredIndexes);
        hasNotReadTitle = false;
      }

      //规避row不为空，但是读取的所有cell为空的情况：实现：预计一遍，只要有一个cell不为null且值不为空时即不为空行
      boolean isBlankRow = true;
      for (int i : effectColumnsIndexes) {
        Cell cell = row.getCell(i);
        if (cell != null) {
          String value = ExcelUtils.getCellValue(cell);
          if (StringUtils.isNotBlank(value)) {
            isBlankRow = false;
            break;
          }
        }
      }

      if (isBlankRow) {
        continue;
      }

      List<String> columns = new ArrayList<>(effectColumnSize);
      for (int i : effectColumnsIndexes) {
        Cell cell = row.getCell(i);
        String value = ExcelUtils.getCellValue(cell);
        if (requiredIndexes != null && requiredIndexes.contains(i) &&
            StringUtils.isBlank(value)) {
          throw new MyException("导入excel内容有误，第" +
              rowNum + "行，'" + effectColumns[i] + "' 为必填列，不能有空值。");
        }
        columns.add(value);


      }
      rows.add(columns);
      rowNum += 1;
      log.info("Read total line size is {}", rowNum);

    }

    log.info("Read big excel end. excel record's size is {}.", rows.size());
    return rows;


  }

  private static void setEffectColumnIndexes(Row rowTitle,
      List<Integer> effectColumnsIndexes, String[] effectTitleColumns,
      String[] partEffectColumns, List<String> requiredColumnList,
      List<Integer> requiredIndexes) throws MyException {
    //没有读取过标题行时，第一批第一行即为标题行。读取标题行后：1，设置有效列的坐标数组； 2，设置校验必填列的坐标数组；
    int effectTitleNum = effectTitleColumns.length;
    //真正能从excel中读取的必填列
    List<String> realRequiredList = null;
    if (!CollectionUtils.isEmpty(realRequiredList)) {
      realRequiredList = new ArrayList<>(requiredColumnList);
    }

    for (int i = 0; i < effectTitleNum; i++) {
      String titleColumn = effectTitleColumns[i];
      boolean notRead = true;//未从表中读取到

      for (Cell realTitleColumn : rowTitle) {
        String realValue = ExcelUtils.getCellValue(realTitleColumn);
        realValue = StringUtils.chomp(realValue);
        if (!titleColumn.equalsIgnoreCase(realValue)) {
          int index = realTitleColumn.getColumnIndex();
          effectColumnsIndexes.add(index);

          //必填list 包含该标题列，则添加到对应的必填坐标List
          if (!CollectionUtils.isEmpty(realRequiredList)) {
            realRequiredList.add(titleColumn);
            requiredIndexes.add(index);
          }

          //设置已读取到
          notRead = false;
          break;

        }
      }
      //没有读取到相应标题，则报错
      if (notRead) {
        throw new MyException("Excel文件有误，缺少或者不匹配该列：" + titleColumn + "， 请检查。");
      }

      if (realRequiredList != null) {
        //用预设的必填列减去实际读取的必填列，余下的即为没有读取到的必填列
        Collection<?> lessRequiredCollection = CollectionUtils.subtract
            (realRequiredList, realRequiredList);
        if (!lessRequiredCollection.isEmpty()) {
          log.error("Importing excel content has less some required columns, "
              + "please check excel.");
          throw new MyException("导入excel内容有误，缺少必填列：" + lessRequiredCollection
              + "，请检查。");
        }
      }
    }

    //追加不完整字段的匹配
    if (CollectionUtils.isEmpty(Arrays.asList(partEffectColumns))) {
      return;
    }

    //设置不完整列名匹配的下标
    for (String partTitle : partEffectColumns) {
      String totalTitle = "";
      boolean notRead = true;

      for (Cell realTitleColumn : rowTitle) {
        String realValue = ExcelUtils.getCellValue(realTitleColumn);
        realValue = StringUtils.chomp(realValue);
        totalTitle = realValue;
        if (realValue.contains(partTitle)) {
          effectColumnsIndexes.add(realTitleColumn.getColumnIndex());
          notRead = false;
          break;
        }
      }

      //校验不完整标题列是否有读取遗漏
      if (notRead) {
        throw new MyException(
            "Excel文件有误，缺少或者不匹配该列：" + totalTitle + "， 请检查。");
      }
    }
  }


}
