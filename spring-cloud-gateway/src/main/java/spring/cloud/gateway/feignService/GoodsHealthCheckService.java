package spring.cloud.gateway.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import spring.cloud.gateway.config.GlobalConstants;

/**
 * Created by Harry on 18/12/2017.
 */
@FeignClient(name = GlobalConstants.JFINAL_SERVICE_NAME)
public interface GoodsHealthCheckService {
    @GetMapping("/health")
    String health();
}
