package spring.cloud.jfinal.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;
import spring.cloud.client.model.Goods;

import java.util.List;

public class GoodService {

    /**
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    public static Page<Goods> findByPage(int pageNum, int pageSize, String name) {

        String sql = "from tb_saopy_goods ";
        if (StringUtils.isNotBlank(name)) {
            sql = sql + "where goodName like ?";
            return Goods.dao.paginate(pageNum, pageSize, "select *", sql, "%" + name + "%");
        }
        return Goods.dao.paginate(pageNum, pageSize, "select *", sql);
    }

    /**
     * 批量保存
     *
     * @param pmList
     */
    public static void batchSave(List<Goods> pmList) {
        int size = pmList.size();
        if (1000 >= size) {
            Db.batchSave(pmList, size);
        }else {
            int serial = size / 1000;
            for (int i = 0; i < serial; i++) {
                Db.batchSave(pmList.subList(i * 1000, i * 1000 + 1000), 1000);
            }
            int remain = size - serial * 1000;
            Db.batchSave(pmList.subList(serial * 1000, size), remain);
        }
    }

    /*实现保存数据到数据库的功能*/
    public  static void saveGoods(Goods goods) {

       new Goods()._setAttrs(goods).save();
    }

}
