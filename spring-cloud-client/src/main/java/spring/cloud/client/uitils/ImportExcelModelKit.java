package spring.cloud.client.uitils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author duai
 * @version V1.0
 * @Title: Excel
 * @Package org.dval.core
 * @Description: 123
 * @date 2017-12-14 15:49
 */
public class ImportExcelModelKit {
    /**
     *
     * @param file 文件
     * @param clazz 转换对象name
     * @param <T>
     * @return
     */
    public static <T extends Model> List<T> getImportData(File file, Class<T> clazz){
        List<T> result = new LinkedList<>();
        try {
            List<Map<String,Object>> datas = ImportExcelKit.getData(new FileInputStream(file),file.getName());
            Table table = TableMapping.me().getTable(clazz);
            for (Map<String,Object> model:datas){
                Model object =  clazz.newInstance();
                for (String key:model.keySet()){
                    Class field = table.getColumnType(key);
                    object.put(key,ImportExcelKit.getData(model.get(key),field));
                }
                result.add((T) object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
