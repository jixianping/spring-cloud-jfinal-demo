package spring.cloud.client.uitils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author duai
 * @version V1.0
 * @Title: Excel
 * @Package org.dval.core
 * @Description: 123
 * @date 2017-12-14 15:10
 */
public class ImportExcelBeanKit {
    public static <T> List<T> getImportData(File file, Class<T> clazz){
        List<T> result = new LinkedList<>();
        try {
            List<Map<String,Object>> datas = ImportExcelKit.getData(new FileInputStream(file),file.getName());
            for (Map<String,Object> bean:datas){
                Object object = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()){
                    field.setAccessible(true);
                    if (bean.containsKey(field.getName())){
                        field.set(object,ImportExcelKit.getData(bean.get(field.getName()),field.getType()));
                    }
                }
                result.add((T) object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
