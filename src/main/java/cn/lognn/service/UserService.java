package cn.lognn.service;

import cn.lognn.controller.Result;
import cn.lognn.domain.User;
import cn.lognn.domain.UserChangePassword;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 用户登录
     *
     * @param user    用户对象
     * @param request HttpServletRequest对象
     * @return 登录结果
     */
    Result login(User user, HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param userChangePassword 封装的用户修改密码对象
     * @return 修改密码结果，成功返回true，否则返回false
     */
    boolean changePassword(UserChangePassword userChangePassword);

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 添加结果，成功返回true，否则返回false
     */
    boolean add(User user);

    /**
     * 检查用户是否已存在
     *
     * @param user 用户对象
     * @return 如果用户已存在返回true，否则返回false
     */
    boolean checkUser(User user);
}
