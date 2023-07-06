package cn.lognn.service;

import cn.lognn.controller.Result;
import cn.lognn.domain.User;
import cn.lognn.domain.UserChangePassword;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    /**
     * 登陆
     * @param user 用户封装对象
     * @param request httpRequest对象
     * @return 登陆结果
     */
    public Result login(User user, HttpServletRequest request);


    /**
     * 修改密码
     * @param userChangePassword 封装的serChangePassword对象
     * @return 修改结果 boolean
     */
    public boolean changePassword(UserChangePassword userChangePassword);


    /**
     * 添加用户
     * @param user user对象
     * @return 添加结果 boolean
     */
    public boolean add(User user);

    /**
     * 检查添加用户是否已经存在
     * @param user user对象
     * @return boolean
     */
    public boolean checkUser(User user);
}
