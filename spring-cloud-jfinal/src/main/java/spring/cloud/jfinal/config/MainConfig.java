package spring.cloud.jfinal.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.core.Const;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import spring.cloud.client.model.Goods;
import spring.cloud.client.model.User;
import spring.cloud.jfinal.web.*;

public class MainConfig extends JFinalConfig{

    @Override
    public void configConstant(Constants me) {
        PropKit.use("application.properties");
        me.setDevMode(PropKit.getBoolean("devMode", false));
        //设置多个参数的分隔符号,如不设置,则默认为-
        me.setUrlParaSeparator("&");
        me.setEncoding("utf-8");
        me.setViewType(ViewType.JSP);
        me.setMaxPostSize(100* Const.DEFAULT_MAX_POST_SIZE);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/login", LoginContraler.class);
        me.add("/register", RegisterController.class);
        me.add("/api", ApiController.class);
        me.add("/excl", ExclController.class);
        me.add("/health", GoodsHealthController.class);

    }

    @Override
    public void configEngine(Engine me) {

    }

    /**
     * 配置插件<br>
     * 1，druid数据库连接池<br>
     * 2，配置ActiveRecord插件<br>
     * 3，所有配置在 MappingKit 中搞定<br>
     * 4，缓存插件<br>
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druid = new DruidPlugin(PropKit.get("spring.datasource.url"), PropKit.get("spring.datasource.username"), PropKit.get("spring.datasource.password").trim(),PropKit.get("spring.datasource.driverClassName"));//druid数据库连接池
        druid.addFilter(new StatFilter());//监控  /druid/index.html
        WallFilter wall = new WallFilter();//防sql注入
        wall.setDbType("mysql");
        druid.addFilter(wall);
        druid.setTestOnBorrow(true);
        druid.setTestOnReturn(true);
        me.add(druid);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druid);
        arp.setShowSql(true);
        me.add(arp);
        arp.addMapping("user", User.class);
        arp.addMapping("tb_saopy_goods", Goods.class);
    }
    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

}
