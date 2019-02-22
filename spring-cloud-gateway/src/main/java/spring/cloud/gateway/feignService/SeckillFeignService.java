package spring.cloud.gateway.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.cloud.client.model.Exposer;
import spring.cloud.client.model.SeckillExecution;
import spring.cloud.client.model.SeckillModel;
import spring.cloud.client.model.SeckillResult;
import spring.cloud.gateway.config.GlobalConstants;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Harry on 15/12/2017.
 */

@FeignClient(name = GlobalConstants.SECKILL_SERVICE_NAME, path = "/seckill")
public interface SeckillFeignService {


    @GetMapping("/list")
    public List<SeckillModel> findSeckillList();

    @GetMapping("/findById")
    public SeckillModel findById(@RequestParam("id") Long id);

    @RequestMapping("/{seckillId}/detail")
    public SeckillModel detail(@PathVariable("seckillId") Long seckillId);

    @GetMapping("/{seckillId}/exposer")
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId);

    @RequestMapping("/{seckillId}/{md5}/execution")
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @RequestParam("money") BigDecimal money,
                                                   @RequestParam(name = "userPhone", required = false) Long userPhone);

}
