package spring.cloud.jfinal.web;


import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import spring.cloud.client.model.Goods;
import spring.cloud.jfinal.service.GoodService;

public class ApiController extends Controller{
    private static final GoodService service = Duang.duang(GoodService.class, Tx.class);
    public void findByPage(){
        int limit = this.getParaToInt("pageSize");
        int page = this.getParaToInt("pageNum");
        String name = this.getPara("name");

        Page<Goods> goodsList = service.findByPage(page,limit,name);
        renderJson(goodsList);
    }

}
