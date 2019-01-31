package spring.cloud.client.uitils;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duai
 * @version V1.0
 * @Title: Excel
 * @Package org.dval.core
 * @Description: 说明
 * @date 2017-12-13 10:45
 */
public class ExportExcelTemplateKit {
    private static String encoding = "UTF-8";
    private static final String CONTENT_TYPE = "application/msexcel;charset=" + getEncoding();
    public static String getEncoding() {
        return encoding;
    }

    /**
     * 模版导出 重构
     * @param response 输出流
     * @param path 模版地址
     * @param resultName 结果名
     * @param map 传入数据
     * @throws IOException
     */
    public static void renderExcelTempl(HttpServletResponse response, String path, String resultName, Map<String,Object> map) throws IOException {
        if (!UrlEncoderKit.hasUrlEncoded(resultName)){
            resultName = URLEncoder.encode(resultName, "UTF-8");
        }
        response.setHeader("content-disposition", "attachment;filename="+resultName);
        response.setContentType(CONTENT_TYPE);
        OutputStream outputStream = response.getOutputStream();
        Workbook workbook = getExportTemplate(path,map);
        //将内容写入输出流并把缓存的内容全部发出去
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 模版导出 重构
     * @param response 输出流
     * @param datas 导出数据
     * @param varName 变量名
     * @param path 模版地址
     * @param resultName 输出文件名
     * @param mapOther 需要传入的其他对象 如：时间的格式化等工具类 使用el表达式进行调用
     * @throws IOException
     */
    public static void renderExcelTempl(HttpServletResponse response, List<?> datas, String varName, String path, String resultName, Map<String,Object> mapOther) throws IOException {
        if (!UrlEncoderKit.hasUrlEncoded(resultName)){
            resultName = URLEncoder.encode(resultName, "UTF-8");
        }
        Map<String,Object> map = new HashMap<>(2);
        map.put(varName,datas);
        if (mapOther!=null&&!mapOther.isEmpty()){
            map.putAll(mapOther);
        }
        renderExcelTempl(response,path,resultName,map);
    }

    /**
     *  模版导出 重构
     * @param response 输出流
     * @param datas 导出数据
     * @param varName 变量名
     * @param path 模版地址
     * @param resultName 输出文件名
     * @throws IOException
     */
    public static void renderExcelTempl(HttpServletResponse response, List<?> datas, String varName, String path, String resultName) throws IOException {
        resultName = URLEncoder.encode(resultName, "UTF-8");
        renderExcelTempl(response,datas,varName,path,resultName,null);
    }

    /**
     *
     * @param tempName 模版名
     * @return
     */
    private   static Workbook getExportTemplate(String tempName,Map<String,Object> map) {
        InputStream in=null;
        Workbook workbook= null;
        XLSTransformer transformer = new XLSTransformer();
        try {
            in = new BufferedInputStream(ExportExcelTemplateKit.class.getResourceAsStream("/".concat(tempName)));
            workbook=transformer.transformXLS(in, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in!=null){try {in.close();} catch (IOException e) {}}
        }
        return workbook;
    }
}
