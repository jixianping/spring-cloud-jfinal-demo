package spring.cloud.jfinal.web;

import com.jfinal.core.Controller;
import spring.cloud.client.model.User;

import java.util.List;

public class LoginContraler extends Controller{


    public void index(){

        List<User> users = User.dao.findByNameAndPwd(this.getPara("name"), this.getPara("password"));

        if (users.size() > 0) {
            //找到用户
            this.setSessionAttr("userInfo", users.get(0));
            this.render("/pages/index.jsp");
        } else {

            render("/login.jsp");
        }

    }


}
