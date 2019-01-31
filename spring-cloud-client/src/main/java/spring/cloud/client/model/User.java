package spring.cloud.client.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

public class User extends Model<User> {

    public static final User dao = new User();



    /*查找用户，用于登录*/
    public List<User> findByNameAndPwd(String name, String password){
        return find("select * from user where name='"+name+"' and password='"+password+"'");
    }



    /*实现保存数据到数据库的功能*/
    public void saveUser(String name,String password) {
        new User().set("name", name).set("password", password).save();
    }

}
