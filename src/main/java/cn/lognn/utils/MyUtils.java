package cn.lognn.utils;

import cn.lognn.domain.Log;
import cn.lognn.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;
import static java.time.ZoneId.of;

public class MyUtils {
    private static final String SESSION_USER_KEY = "user";
    private static final String TIMEZONE_ID = "GMT+8";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private MyUtils() {
        // 私有构造函数为空
    }

    /**
     * 检查用户是否登录，并且判断用户权限是否为1（管理员权限）。
     *
     * @param request HttpServletRequest对象
     * @return 如果用户已登录且权限为1，则返回true；否则返回false
     */
    public static boolean checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = getUserFromSession(session);
        return user != null && user.getGrade() == 1;
    }

    /**
     * 获取当前登录用户的名称。
     *
     * @param request HttpServletRequest对象
     * @return 当前登录用户的名称，如果用户未登录则返回null
     */
    public static String getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = getUserFromSession(session);
        return user != null ? user.getName() : null;
    }

    /**
     * 从Session中获取User对象。
     *
     * @param session HttpSession对象
     * @return Session中的User对象，如果不存在则返回null
     */
    private static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(SESSION_USER_KEY);
    }

    /**
     * 创建日志对象并设置相关属性。
     *
     * @param request    HttpServletRequest对象
     * @param name       日志名称
     * @param type       日志类型
     * @param changeType 变更类型
     * @param sum        数量
     * @return 设置好属性的日志对象
     */
    public static Log setLog(HttpServletRequest request, String name, String type, String changeType, Integer sum) {
        String userName = getUser(request);
        Log log = new Log();
        ZoneId zoneId = of(TIMEZONE_ID);
        LocalDateTime date = now(zoneId);
        String dateTime = date.format(DATE_TIME_FORMATTER);
        log.setUser(userName);
        log.setDatetime(dateTime);
        log.setName(name);
        log.setType(type);
        log.setChangetype(changeType);
        log.setNumber(sum);
        return log;
    }

    /**
     * 注销用户的登录状态。
     *
     * @param request HttpServletRequest对象
     */
    public static void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
