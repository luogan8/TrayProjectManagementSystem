package cn.lognn.utils;


import cn.lognn.domain.Log;
import cn.lognn.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public static String getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user.getName();
    }

    public static Log setLog(HttpServletRequest request, String name, String type, String changeType, Integer sum){
        String userName = getUser(request);
        Log log = new Log();
        //获取东八区时区ID
        ZoneId zoneId = ZoneId.of("GMT+8");
        //获取当前时间
        LocalDateTime date = LocalDateTime.now(zoneId);
        //创建格式化器
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化时间
        String dateTime = date.format(dateTimeFormatter);
        log.setUser(userName);
        log.setDatetime(dateTime);
        log.setName(name);
        log.setType(type);
        log.setChangetype(changeType);
        log.setNumber(sum);
        return log;
    }

    public static void logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
