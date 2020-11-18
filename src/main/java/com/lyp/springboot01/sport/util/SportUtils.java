package com.lyp.springboot01.sport.util;

import com.lyp.springboot01.common.exception.MyException;
import com.lyp.springboot01.sport.model.RunDetail;
import com.lyp.springboot01.sport.model.RunDetailVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.math.NumberUtils;

public class SportUtils {
  private static final String LAST_TIME_REG = "^(\\d+)(\\.)(\\d+)$";

  private static final Pattern lastTimePattern = Pattern.compile(LAST_TIME_REG);

  public static List<RunDetail> convertRunDetail(List<RunDetailVO> detailVOS) throws MyException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    List<RunDetail> details = new ArrayList<>();
    RunDetail runDetail;
    String runDate = null;
    try {
      int curYear = LocalDate.now().getYear();
      for (RunDetailVO detailVO : detailVOS) {
        runDetail = new RunDetail();
        runDetail.setAddress(detailVO.getAddress());

        BigDecimal length = new BigDecimal(detailVO.getKilometer());
        runDetail.setKilometer(length);
        runDate = String.format("%d.%s", curYear, detailVO.getRunDate());
        runDetail.setRunDate(sdf.parse(runDate));

        String lastTimeStr = detailVO.getLastTime();
        int lastSecond = convert2Second(lastTimeStr);
        runDetail.setRunSecond(lastSecond);
        runDetail.setTimeByKm(new BigDecimal(lastSecond).divide(length, 0, RoundingMode.HALF_UP));
        runDetail.setKmByHour(length.divide(new BigDecimal(lastSecond / 60.0 / 60), 1, RoundingMode.HALF_UP));
        details.add(runDetail);
      }
    } catch (ParseException e) {
      throw new MyException("跑步日期格式异常，应该为: MM.dd，实际为：{0}.", runDate);
    }

    return details;
  }

  /**
   * 转化为秒数
   *
   * @param lastTimeStr 持续时间，格式为： 10分25秒 --> 625s
   */
  private static int convert2Second(String lastTimeStr) throws MyException {

    Matcher matcher = lastTimePattern.matcher(lastTimeStr);
    if (!matcher.matches()) {
      throw new MyException("跑步时长格式有误，应该如:10.25，当前为：" + lastTimeStr);
    }

    String minStr = matcher.group(1);
    String secStr = matcher.group(3);
    int min = NumberUtils.toInt(minStr, 0);
    int sec = NumberUtils.toInt(secStr, 0);
    return min * 60 + sec;
  }

  public static void main(String[] args) throws MyException {
    String lastTime = "10分25秒";
    System.out.println(convert2Second(lastTime));
  }
}
