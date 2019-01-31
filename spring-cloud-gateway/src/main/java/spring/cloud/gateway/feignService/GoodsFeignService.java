package spring.cloud.gateway.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.cloud.gateway.config.GlobalConstants;

/**
 * Created by Harry on 15/12/2017.
 */

@FeignClient(name = GlobalConstants.JFINAL_SERVICE_NAME, path = "/api")
public interface GoodsFeignService {


    /**
     *
     * @param pageSize 分页条数
     * @param pageNum  页数
     * @param name     查询名称
     * @return
     */
    @GetMapping("/findByPage")
    String findByPage(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum, @RequestParam("name") String name);


}
