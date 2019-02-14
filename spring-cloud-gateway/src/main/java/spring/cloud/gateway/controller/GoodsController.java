package spring.cloud.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spring.cloud.gateway.feignService.GoodsFeignService;

/**
 * 商品控制层
 *
 * @auther TyCoding
 * @date 2018/10/6
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends com.jfinal.core.Controller {

    @Autowired private GoodsFeignService goodsFeignService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/index")
    public String index() {
        return "pages/index";

    }

    @RequestMapping(value = "/hello")
    public ModelAndView hello(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/findByPage")
    public JSONObject findByPage(@RequestParam(required = false, defaultValue = "50") int pageSize, @RequestParam(required = false, defaultValue = "1") int pageNum, @RequestParam(required = false) String name) {

        String json = goodsFeignService.findByPage(pageSize,pageNum,name);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }


}
