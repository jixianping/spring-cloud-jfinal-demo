package spring.cloud.client.uitils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author duai
 * @version V1.0
 * @Title: logistics
 * @Package net.xgs.commons.utils
 * @Description: 说明
 * @date 2017-11-03 14:44
 */
public class ImportExcelKit {
    /**
     *2003版excel数据读取
     * @param inputStream
     * @return
     */
    public static List<Map<String,Object>> getHssfData(FileInputStream inputStream){
        XSSFWorkbook workbook;
        List<Map<String,Object>> rows = new LinkedList<>();
        try {
            workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet=null;
            //循环sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet=workbook.getSheetAt(i);
                int colsnum = 0;
                //循环每一行
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    Map<String,Object> map = new LinkedHashMap<>(5);
                    XSSFRow row=sheet.getRow(j);
                    if(null != row){
                        //列数以excel第二行为准，第二行为标题，第一行为excel导入提示信息
                        colsnum = sheet.getRow(1).getPhysicalNumberOfCells();
                        getHeader(colsnum,sheet.getRow(0),map);
                        getData(row,map);
                        rows.add(map);
                    }
                }
                //返回所有数据，第一个list表示sheet，第二个list表示sheet内所有行数据，第三个string[]表示单元格数据
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  rows;
    }
    private static void getHeader(int colsnum,Row row,Map<String,Object> rows){
        for (int k = 0; k < colsnum; k++) {
            //判断单元格是否为null，若为null，则置空
            if(null != row.getCell(k)) {
                rows.put(String.valueOf(getCellValue(row,k)),"");
            }
        }
    }
    private static void getData( Row row, Map<String,Object> rowObj){
        Object cols = new Object();
        //循环每一个单元格，以一行为单位，组成一个数组
        int i = 0;
        for (String key:rowObj.keySet()){
            rowObj.put(key,getCellValue(row,i));
            i++;
        }
    }
    public static Object getCellValue(Row row, int column){
        Object val = "";
        try{
            Cell cell = row.getCell(column);
            if (cell != null){

               if (cell.getCellTypeEnum() == CellType.NUMERIC){
                    val = cell.getNumericCellValue();
                }else if (cell.getCellTypeEnum() == CellType._NONE){
                    val = cell.getStringCellValue();
                }else if (cell.getCellTypeEnum() == CellType.BLANK){
                    val = cell.getBooleanCellValue();
                }else if (cell.getCellTypeEnum() == CellType.BOOLEAN){
                    val = cell.getBooleanCellValue();
                }else if (cell.getCellTypeEnum() == CellType.ERROR){
                    val = cell.getErrorCellValue();
                }else if (cell.getCellTypeEnum() == CellType.FORMULA){
                    val = cell.getCellFormula();
                }else if (cell.getCellTypeEnum() == CellType.STRING){
                    val = cell.getStringCellValue();
                }else if(HSSFDateUtil.isCellDateFormatted(cell)){
                    val = cell.getDateCellValue();
               }
            }
        }catch (Exception e) {
            return val;
        }
        return val;
    }
    /**
     *2007版excel数据读取
     * @param inputStream
     * @return
     */
    public static  List<Map<String,Object>> getXssfData(FileInputStream inputStream){
        HSSFWorkbook workbook;
        List<Map<String,Object>> data = new LinkedList<>();
        try {
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet=null;
            //循环sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet=workbook.getSheetAt(i);
                int colsnum = 0;
                //循环每一行
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    Map<String,Object> map = new LinkedHashMap<>(5);
                    HSSFRow row=sheet.getRow(j);
                    if(null != row){
                        //列数以excel第二行为准，第二行为标题，第一行为excel导入提示信息
                        colsnum = sheet.getRow(1).getPhysicalNumberOfCells();
                        getHeader(colsnum,sheet.getRow(0),map);
                        getData(row,map);
                        data.add(map);
                    }
                }
                //返回所有数据，第一个list表示sheet，第二个list表示sheet内所有行数据，第三个string[]表示单元格数据
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  data;
    }
    public static List<Map<String,Object>> getData(FileInputStream inputStream,String fileName){
        final String xlsx = "xlsx";
        final String xls = "xls";
        if (fileName.contains(xlsx)) {
            return ImportExcelKit.getHssfData(inputStream);
        }else if (fileName.contains(xls)) {
            return   ImportExcelKit.getXssfData(inputStream);
        }
        return null;
    }

    public static Object getData(Object val,Class<?> valType){
        if (valType == String.class){
            String s = String.valueOf(val.toString());
            if(StringUtils.endsWith(s, ".0")){
                val = StringUtils.substringBefore(s, ".0");
            }else{
                val = String.valueOf(val.toString());
            }
        }else if (valType == Integer.class){
            val = Double.valueOf(val.toString()).intValue();
        }else if (valType == Long.class){
            val = Double.valueOf(val.toString()).longValue();
        }else if (valType == Double.class){
            val = Double.valueOf(val.toString());
        }else if (valType == Float.class){
            val = Float.valueOf(val.toString());
        }else if (valType == Date.class){
            val = DateUtil.getJavaDate((Double)val);
        }
        return val;
    }

}
