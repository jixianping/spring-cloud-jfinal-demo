package spring.cloud.jfinal.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

import spring.cloud.demo.cache.CacheService;
import spring.cloud.jfinal.service.GoodService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Harry on 2017/5/31.
 */
public class GoodsHealthController extends Controller {

    @Autowired private CacheService cacheService;
    private static final GoodService service = Duang.duang(GoodService.class, Tx.class);
    public static final Logger LOGGER = LoggerFactory.getLogger(GoodsHealthController.class);

    private static final String HEALTH_KEY = "spring.cloud.goods#healthCheck#key";

    private static final int CACHE_TIME = 500;  //ms
    private static final int db_TIME = 2000;  //ms

    private static final String FAIL = "fail";
    private static final String SUCCESS = "success";

    public String health(HttpServletResponse response) {

        /* check the cache time */
        long start = System.currentTimeMillis();
        try {
            this.cacheService.putString(HEALTH_KEY, HEALTH_KEY);
            String strInCache = this.cacheService.getString(HEALTH_KEY);
            if ( !HEALTH_KEY.equals(strInCache) ) {
                LOGGER.error("healthCheckController error， get from cache:{}, put into cache:{}, not the same", strInCache, HEALTH_KEY);
                response.setStatus(500);
                return FAIL;
            }
            long end = System.currentTimeMillis();
            if (end - start > 500) {
                LOGGER.error("healthCheckController error, check cache time expire {}ms, actually:{}ms",CACHE_TIME, (end-start));
                response.setStatus(500);
                return FAIL;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }

        /* check db */
        start = System.currentTimeMillis();
        service.findByPage(1,1,null);
        long end = System.currentTimeMillis();
        if (end - start > db_TIME) {
            LOGGER.error("healthCheckController error, listAllUsers expire {}ms, actually:{}ms", db_TIME, (end-start));
            response.setStatus(500);
            return FAIL;
        }

        return SUCCESS;
    }

    private String addString(String pre, String after) {
        if ( null == pre && null == after ) return null;
        if ( null == pre ) return after;
        if ( null == after ) return pre;
        return pre +"\n" + after;
    }
}
