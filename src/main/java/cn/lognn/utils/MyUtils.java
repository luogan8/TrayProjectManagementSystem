package cn.lognn.utils;


import cn.lognn.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class MyUtils {
    private MyUtils(){
        //私有构造函数为空
    }

    public static boolean checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();

        //这里的User是登录时查询的数据，如果修改了User数据，需重新登录。
        User user = (User) session.getAttribute("user");

        if (user == null){
            return false;
        }

        return user.getGrade() == 1;

    }
}
