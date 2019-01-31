package spring.cloud.seckill.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.cloud.client.exception.RepeatKillException;
import spring.cloud.client.exception.SeckillCloseException;
import spring.cloud.client.exception.SeckillException;
import spring.cloud.client.model.Exposer;
import spring.cloud.client.model.SeckillExecution;
import spring.cloud.client.model.SeckillModel;
import spring.cloud.client.model.SeckillResult;
import spring.cloud.client.model.enumModel.SeckillStatEnum;
import spring.cloud.seckill.service.SeckillService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 秒杀商品的控制层
 *
 * @auther TyCoding
 * @date 2018/10/6
 */
@RequestMapping("/seckill")
@RestController
@Api(description = "秒杀信息相关接口")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public List<SeckillModel>  findSeckillList(Model model) {
        List<SeckillModel> list = seckillService.findAll();
        return  list;
    }

    @ResponseBody
    @GetMapping("/findById")
    public SeckillModel findById(@RequestParam("id") Long id) {
        return seckillService.findById(id);
    }

    @RequestMapping("/{seckillId}/detail")
    public SeckillModel detail(@PathVariable("seckillId") Long seckillId, Model model) {
        return  seckillService.findById(seckillId);
    }

    @ResponseBody
    @GetMapping("/{seckillId}/exposer")
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/{seckillId}/{md5}/execution")
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @RequestParam("money") BigDecimal money,
                                                   @RequestParam(name = "userPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, money, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
    }

    @ResponseBody
    @GetMapping(value = "/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
