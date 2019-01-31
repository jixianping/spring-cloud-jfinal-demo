package spring.cloud.jfinal.web;

import com.jfinal.core.Controller;
import spring.cloud.client.model.User;

public class RegisterController extends Controller{

    public void index() {
        String name = getPara("name");
        String password = getPara("password");
        User.dao.saveUser(name, password);
        render("/theOK.jsp");
    }

    public void index2() {
        String name = getPara("name");
        String password = getPara("password");
        User.dao.saveUser(name, password);
        render("/theOK.jsp");
    }

}
