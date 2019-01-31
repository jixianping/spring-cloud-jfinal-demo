package spring.cloud.jfinal.web;


import com.jfinal.core.Controller;

public class IndexController extends Controller{

    public void index(){

        render("pages/index.jsp");
    }
}
